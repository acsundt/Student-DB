package main.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableColumn;


import java.io.IOException;

public class MainApp extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        TableView<Student> studentTableView

        Button button;
        button = new Button("Go to Reading Scores");
        Button button2;
        button2 = new Button("Go to Math Scores");
        Button button3;
        button3 = new Button("Go to all Students");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        layout.getChildren().add(button2);
        layout.getChildren().add(button3);

        Scene scene1 = new Scene(layout,300,250);


        primaryStage.setScene(scene1);
        primaryStage.show();


        Controller handler = new Controller();
        button.setOnAction(handler);








        primaryStage.setTitle("Student Sort");





        VBox vbox = new VBox(table);


        Scene scene2 = new Scene(vbox);
        primaryStage.setScene(scene2);
        primaryStage.setTitle("TableView Example");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.show();


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

        String[] studentInfoArr1 = {"Aidan", "19", "14","Olsen"};
        String[] studentInfoArr2 = {"Owen", "20", "14","Finnegan"};
        String[] studentInfoArr3 = {"Oscar", "19", "15","Locke"};

        return FXCollections.observableArrayList(
                new Student(studentInfoArr1),
                new Student(studentInfoArr2),
                new Student(studentInfoArr3)
        );
    }


    public static void main(String[] args) {
        launch();
    }
}