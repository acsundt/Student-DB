package main.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainApp extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Student Sort");
        Button button;
        button = new Button("Sup");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout,300,250);


        primaryStage.setScene(scene);
        primaryStage.show();


        Controller handler = new Controller();
        button.setOnAction(handler);

    }

    public static void main(String[] args) {
        launch();
    }
}