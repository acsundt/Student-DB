package main.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.sql.PreparedStatement;



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

    public String deleteStudent(String studentName){

        int studentId = findStudentIdByName(studentName);

        deleteAssociatedInfo(studentId);

        String query = "DELETE FROM students WHERE id = ?;";

        try (PreparedStatement preparedStatement = connector.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "Student deleted successfully.";
            } else {
                return "Student not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error Deleting Student";

    }

    public void deleteAssociatedInfo(int studentId){
        String query1 = "DELETE FROM reading_scores WHERE student_id = ?;";

        try (PreparedStatement preparedStatement = connector.prepareStatement(query1)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query2 = "DELETE FROM math_scores WHERE student_id = ?;";

        try (PreparedStatement preparedStatement = connector.prepareStatement(query2)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public void updateStudentName(String studentName)

    //public void updateStudentData(String studentName, String newDataType, String newData)

    public void addStudent(String[] studentInfo) {

        String studentName = studentInfo[0];
        String studentAge = studentInfo[1];
        int studentGrade = Integer.parseInt(studentInfo[2]);
        String studentTeacher = studentInfo[3];
        int reading_id = Integer.parseInt(studentInfo[4]);
        int math_id = Integer.parseInt(studentInfo[5]);

        try{
            Statement statement = connector.createStatement();

            String query = "INSERT INTO students (name, age, grade, teacher, reading_ID, math_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";

            // Create a PreparedStatement to avoid SQL injection
            PreparedStatement preparedStatement = connector.prepareStatement(query);
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, studentAge);
            preparedStatement.setInt(3, studentGrade);
            preparedStatement.setString(4, studentTeacher);
            preparedStatement.setInt(5, reading_id);
            preparedStatement.setInt(6, math_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void createReadingTable(){
        //holds  mcap score, i- ready, dibels
        try{
            Statement statement = connector.createStatement();
            String query = "CREATE TABLE reading_scores (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "student_id INT," +        // Student ID to link with students table
                    "MCAP INT," +
                    "i_ready INT," +
                    "dibels INT," +
                    "FOREIGN KEY (student_id) REFERENCES students(id)"+
                    ");";
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addReadingScore(int studentId, int mcapScore, int iReadyScore, int dibelsScore){
        String query = "INSERT INTO reading_scores (student_id, MCAP, i_ready, dibels) VALUES (?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connector.prepareStatement(query)) {
            // Set the parameters for the prepared statement
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, mcapScore);
            preparedStatement.setInt(3, iReadyScore);
            preparedStatement.setInt(4, dibelsScore);

            // Execute the update
            preparedStatement.executeUpdate();
//            System.out.println("Reading score added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addMathTable(){
        //holds mcap score, i-5 ready
        //i-5 ready is only grades 3-5
        //holds  mcap score, i- ready, dibels
        try{
            Statement statement = connector.createStatement();
            String query = "CREATE TABLE math_scores (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "student_id INT," +        // Student ID to link with students table
                    "MCAP INT," +
                    "i_ready INT," +
                    "FOREIGN KEY (student_id) REFERENCES students(id)"+
                    ");";
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addMathScore(int studentId, int mcapScore, int iReadyScore){
        String query = "INSERT INTO reading_scores (student_id, MCAP, i_ready, dibels) VALUES (?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connector.prepareStatement(query)) {
            // Set the parameters for the prepared statement
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, mcapScore);
            preparedStatement.setInt(3, iReadyScore);

            // Execute the update
            preparedStatement.executeUpdate();
//            System.out.println("Reading score added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int findStudentIdByName(String studentName){

        String query = "SELECT id FROM students WHERE name = ?;"; // SQL query to find the student ID
        Integer studentId = null; // Initialize studentId as null

        try (PreparedStatement preparedStatement = connector.prepareStatement(query)) {
            // Set the name parameter
            preparedStatement.setString(1, studentName);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a result is returned
            if (resultSet.next()) {
                studentId = resultSet.getInt("id"); // Get the ID of the found student
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentId; // Return the found student ID or null if not found

    }


    public void addStudentTable(String tableTitle) { //to reset the db
        try {
            Statement statement = connector.createStatement();

            String query = "CREATE TABLE students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100)," +
                    "age VARCHAR(100)," +
                    "grade INT," +
                    "teacher VARCHAR(100)," +
                    "reading_ID INT," +
                    "math_ID INT" +
                    ");";

            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String tableTitle){

        try{
            Statement statement = connector.createStatement();

            String query = "DROP TABLE "+ tableTitle;

            ResultSet resultSet = statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }




}