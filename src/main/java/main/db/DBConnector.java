package main.db;

import java.sql.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class DBConnector {


    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.3:3306/student_data",
                    "root",
                    "siYYx?N3egeYEq@8"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");


            while (resultSet.next()) {
                System.out.println(resultSet.getString("studentName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//    public static void main(String[] args) {
//        Button button;
//        System.out.println("Hello world");
//    }

    }
}

