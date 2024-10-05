package main.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import static main.db.DBConnector.connector;

public class DBMethods {

    Connection connector;


    public DBMethods() {

        connector = connector();
    }

    public void printDataFrom(String targetColumn) {
        try {
            Statement statement = connector.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + targetColumn);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("studentName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public void deleteStudent(String studentName)

    //public void updateStudentName(String studentName)

    //public void updateStudentData(String studentName, String newDataType, String newData)

    public void addStudent(String[] studentInfo) {

        String studentName = studentInfo[0];
        String studentAge = studentInfo[1];
        int studentGrade = Integer.parseInt(studentInfo[2]);
        String studentTeacher = studentInfo[3];
        int studentReadingLevel = Integer.parseInt(studentInfo[4]);
        int studentMathLevel = Integer.parseInt(studentInfo[5]);

        try{
            Statement statement = connector.createStatement();

            String query = "INSER INTO students (" +
                    studentName +
                    studentAge +
                    studentGrade +
                    studentTeacher +
                    studentReadingLevel +
                    studentMathLevel;
        } catch (SQLException e){
            e.printStackTrace();
        }

    }



    public void addTable(String tableTitle) { //to reset the db
        try {
            Statement statement = connector.createStatement();

            String query = "CREATE TABLE students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100)," +
                    "age VARCHAR(100)," +
                    "grade INT," +
                    "teacher VARCHAR(100)," +
                    "reading_level INT," +
                    "math_level INT";

            ResultSet resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String tableTitle){ //deletes db

        try{
            Statement statement = connector.createStatement();

            String query = "DROP TABLE "+ tableTitle;

            ResultSet resultSet = statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }




}