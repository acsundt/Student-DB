package main.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableColumn;
import main.db.DBMethods;


import java.io.IOException;

public class MainApp extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        DBMethods DBConnector = new DBMethods();
        //DBConnector.createMathTable();
        //DBConnector.deleteTable("reading_scores");
        //DBConnector.createReadingTable();
        //DBConnector.deleteTable("students");
       // DBConnector.createStudentTable();

        //String[] myInfo = {"owen","20","15","Eric"};
        //DBConnector.addStudent(myInfo);
        DBConnector.addReadingScore(DBConnector.findStudentIdByName("owen"),50,100,20);
        DBConnector.addMathScore(DBConnector.findStudentIdByName("owen"),100,50);


        // Create the reading table view (for demonstration)
        TableView<Student> studentTableView = createStudentTableView(); // Assume this method creates the student table view
        Button switchToStudentTableButton1 = new Button("Go to the Student Table");

        TableView<ReadingScores> readingTableView = createReadingTableView(); // Assume this method creates the reading table view

        //Create the math scores table view
        TableView<MathScores> mathTableView = createMathTableView();
        Button switchToMathTableButton1 = new Button("Go to Math Scores");

        // HBox for scene1 (contains the reading table and the button)
        HBox hbox = new HBox(10); // Set spacing between buttons
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().addAll(switchToStudentTableButton1, switchToMathTableButton1);


        // Create a layout and add the reading table and hbox to it
        BorderPane layout1 = new BorderPane();
        layout1.setCenter(readingTableView);
        layout1.setBottom(hbox); // Place the hbox at the bottom

        Scene scene1 = new Scene(layout1, 500, 400);


        Button switchToReadingButton1 = new Button("Go to Reading Scores");

        //Create the math scores table view
        Button switchToMathTableButton2 = new Button("Go to Math Scores");

        // HBox for scene2 (contains the student table and the button)
        HBox hbox2 = new HBox(10); // Set spacing between buttons
        hbox2.setAlignment(Pos.BOTTOM_CENTER);
        hbox2.setPadding(new Insets(10));
        hbox2.getChildren().addAll(switchToReadingButton1, switchToMathTableButton2);

        // Create a layout and add the student table and hbox2 to it
        BorderPane layout2 = new BorderPane();
        layout2.setCenter(studentTableView);
        layout2.setBottom(hbox2); // Place the hbox2 at the bottom

        Scene scene2 = new Scene(layout2, 500, 400);

        Button switchToReadingButton2 = new Button("Go to Reading Scores");
        Button switchToStudentTableButton2 = new Button("Go to the Student Table");

        HBox hbox3 = new HBox(10);
        hbox3.setAlignment(Pos.BOTTOM_CENTER);
        hbox3.setPadding(new Insets(10));
        hbox3.getChildren().addAll(switchToReadingButton2,switchToStudentTableButton2);


        BorderPane layout3 = new BorderPane();
        layout3.setCenter(mathTableView);
        layout3.setBottom(hbox3);

        Scene scene3 = new Scene(layout3, 500, 400);



        // Set the action for switching to the student table scene
        switchToReadingButton1.setOnAction(e -> {
            primaryStage.setScene(scene1);
        });
        switchToReadingButton2.setOnAction(e -> {
            primaryStage.setScene(scene1);
        });

        // Set the action for switching to the reading scores scene
        switchToStudentTableButton1.setOnAction(e -> {
            primaryStage.setScene(scene2);
        });
        switchToStudentTableButton2.setOnAction(e -> {
            primaryStage.setScene(scene2);
        });

        switchToMathTableButton1.setOnAction(e -> {
            primaryStage.setScene(scene3);
        });
        switchToMathTableButton2.setOnAction(e -> {
            primaryStage.setScene(scene3);
        });


        primaryStage.setScene(scene1);
        primaryStage.show();


        Controller handler = new Controller();
        //button.setOnAction(handler);








        primaryStage.setTitle("Student Sort");





      //  VBox vbox = new VBox(table);


        //Scene scene2 = new Scene(vbox);
       // primaryStage.setScene(scene2);
     //   primaryStage.setTitle("TableView Example");
       // primaryStage.setWidth(600);
      //  primaryStage.setHeight(500);
      //  primaryStage.show();


//
//
//        Button button;
//        button = new Button("Sup");
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//
//        Scene scene = new Scene(layout,300,250);
//
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//
//        Controller handler = new Controller();
//        button.setOnAction(handler);

    }

    private TableView<Student> createStudentTableView(){

        TableView<Student> table = new TableView<>();
        // Define the columns
        TableColumn<Student, String> studentNameColumn = new TableColumn<>("Student Name");
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        TableColumn<Student, String> studentAgeColumn = new TableColumn<>("Age");
        studentAgeColumn.setCellValueFactory(new PropertyValueFactory<>("studentAge"));

        TableColumn<Student, Integer> studentGradeColumn = new TableColumn<>("Grade");
        studentGradeColumn.setCellValueFactory(new PropertyValueFactory<>("studentGrade"));

        TableColumn<Student, String> studentTeacherColumn = new TableColumn<>("Teacher");
        studentTeacherColumn.setCellValueFactory(new PropertyValueFactory<>("studentTeacher"));

        table.getColumns().add(studentNameColumn);
        table.getColumns().add(studentAgeColumn);
        table.getColumns().add(studentGradeColumn);
        table.getColumns().add(studentTeacherColumn);

        table.setItems(getStudentData());

        return table;

    }






    private ObservableList<Student> getStudentData() {
        DBMethods dbMethods = new DBMethods(); // Create an instance of DBMethods
        String[][] studentInfo = dbMethods.getAllStudentsInfo(); // Get student info from the database

        ObservableList<Student> studentList = FXCollections.observableArrayList(); // Initialize an observable list

        // Iterate through each student info and add it to the observable list
        for (String[] info : studentInfo) {
            String name = info[0]; // Name
            String ageStr = info[1]; // Age
            int grade = Integer.parseInt(info[2]); // Grade
            String teacher = info[3]; // Teacher

            // Check for null or empty values before parsing
            int age = (ageStr != null && !ageStr.isEmpty()) ? Integer.parseInt(ageStr) : 0; // Default to 0 if null or empty

            // Assuming the Student class has a constructor that takes name, age, grade, and teacher
            Student student = new Student(info);
            studentList.add(student); // Add the student object to the list
        }

        return studentList; // Return the populated observable list
    }

    private TableView<ReadingScores> createReadingTableView(){

        TableView<ReadingScores> table = new TableView<>();
        // Define the columns
        TableColumn<ReadingScores, String> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("student_id"));

        TableColumn<ReadingScores, Integer> MCAPColumn = new TableColumn<>("MCAP");
        MCAPColumn.setCellValueFactory(new PropertyValueFactory<>("MCAP"));

        TableColumn<ReadingScores, Integer> i_readyColumn = new TableColumn<>("i-ready");
        i_readyColumn.setCellValueFactory(new PropertyValueFactory<>("i_ready"));

        TableColumn<ReadingScores, String> dibelsColumn = new TableColumn<>("DIBELS");
        dibelsColumn.setCellValueFactory(new PropertyValueFactory<>("dibels"));

        table.getColumns().add(studentIdColumn);
        table.getColumns().add(MCAPColumn);
        table.getColumns().add(i_readyColumn);
        table.getColumns().add(dibelsColumn);

        table.setItems(getReadingData());

        return table;

    }

    private ObservableList<ReadingScores> getReadingData() {
        DBMethods dbMethods = new DBMethods(); // Create an instance of DBMethods
        int[][] readingData = dbMethods.getAllReadingData(); // Get student info from the database

        ObservableList<ReadingScores> readingList = FXCollections.observableArrayList(); // Initialize an observable list

        // Iterate through each student info and add it to the observable list
        for (int[] info : readingData) {


            // Assuming the Student class has a constructor that takes name, age, grade, and teacher
            ReadingScores readingScores = new ReadingScores(info);
            readingList.add(readingScores); // Add the student object to the list
        }

        return readingList; // Return the populated observable list
    }


    private TableView<MathScores> createMathTableView(){

        TableView<MathScores> table = new TableView<>();
        // Define the columns
        TableColumn<MathScores, String> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("student_id"));

        TableColumn<MathScores, Integer> MCAPColumn = new TableColumn<>("MCAP");
        MCAPColumn.setCellValueFactory(new PropertyValueFactory<>("MCAP"));

        TableColumn<MathScores, Integer> i_readyColumn = new TableColumn<>("i-ready");
        i_readyColumn.setCellValueFactory(new PropertyValueFactory<>("i_ready"));

        table.getColumns().add(studentIdColumn);
        table.getColumns().add(MCAPColumn);
        table.getColumns().add(i_readyColumn);

        table.setItems(getMathData());

        return table;

    }

    private ObservableList<MathScores> getMathData() {
        DBMethods dbMethods = new DBMethods(); // Create an instance of DBMethods
        int[][] mathData = dbMethods.getAllReadingData(); // Get student info from the database

        ObservableList<MathScores> mathList = FXCollections.observableArrayList(); // Initialize an observable list

        // Iterate through each student info and add it to the observable list
        for (int[] info : mathData) {


            // Assuming the Student class has a constructor that takes name, age, grade, and teacher
            MathScores mathScores = new MathScores(info);
            mathList.add(mathScores); // Add the student object to the list
        }

        return mathList; // Return the populated observable list
    }


    public static void main(String[] args) {
        launch();
    }
}