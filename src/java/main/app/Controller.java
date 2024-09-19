package main.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


public class Controller implements EventHandler<ActionEvent> {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    @Override
    public void handle(ActionEvent event) {

        Object source = event.getSource();

        if (source instanceof Button){

            Button clickedButton = (Button) source;

            if (clickedButton.getText().equals("Sup")) {
                System.out.println("Button!");
            }
        }

    }
}