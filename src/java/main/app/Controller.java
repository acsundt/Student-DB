package main.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import main.db.DBConnector;
import main.db.DBMethods;

import java.sql.Connection;

import static main.db.DBConnector.connector;


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

        DBMethods methodConnect = new DBMethods();
       // Connection connector = connector();

        Object source = event.getSource();

        if (source instanceof Button){

            Button clickedButton = (Button) source;

            if (clickedButton.getText().equals("Go to Reading Scores")) {
                //methodConnect.displayReadingTable();

                methodConnect.printDataFrom("students");
            }
        }

    }

}