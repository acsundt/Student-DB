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

        if (studentId == -1) {  // Assuming -1 indicates the student was not found
            return "Student not found.";
        }

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

    public void updateStudentName(String newStudentName, int studentId){

        String query = "UPDATE students SET name = ? WHERE id = ?";

        try(PreparedStatement preparedStatement = connector.prepareStatement(query)){
            preparedStatement.setString(1,newStudentName);
            preparedStatement.setInt(2,studentId);

            int rowsAffected = preparedStatement.executeUpdate();

//            if (rowsAffected > 0) {
//                System.out.println("Student information updated successfully.");
//            } else {
//                System.out.println("No student found with the provided ID.");
//            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }


    public <T> void updateStudentData(String studentName, String newDataType, T newData){

        String query = "UPDATE students SET "+newDataType+" = ? WHERE name = ?";

        try(PreparedStatement preparedStatement = connector.prepareStatement(query)){
            // Set the parameter for newData
            if (newData instanceof String) {
                preparedStatement.setString(1, (String) newData);
            } else if (newData instanceof Integer) {
                preparedStatement.setInt(1, (Integer) newData);
            } else {
                // Handle other types or throw an exception
                throw new IllegalArgumentException("Unsupported data type: " + newData.getClass());
            }
            preparedStatement.setString(2,studentName);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }


    }



    public void addStudent(String[] studentInfo) {

        String studentName = studentInfo[0];
        String studentAge = studentInfo[1];
        int studentGrade = Integer.parseInt(studentInfo[2]);
        String studentTeacher = studentInfo[3];
        int reading_id = Integer.parseInt(studentInfo[4]);
        int math_id = Integer.parseInt(studentInfo[5]);

        try{
            //Statement statement = connector.createStatement();

            String query = "INSERT INTO students (name, age, grade, teacher, reading_ID, math_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";

            // Create a PreparedStatement to avoid SQL injection
            PreparedStatement preparedStatement = connector.prepareStatement(query);
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, studentAge);
            preparedStatement.setInt(3, studentGrade);
            preparedStatement.setString(4, studentTeacher);
           // preparedStatement.setInt(5, reading_id);
            //preparedStatement.setInt(6, math_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void createReadingTable(){
        //holds  mcap score, i- ready, dibels

        String query = "CREATE TABLE IF NOT EXISTS reading_scores (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "student_id INT," +        // Student ID to link with students table
                "MCAP INT," +
                "i_ready INT," +
                "dibels INT," +
                "FOREIGN KEY (student_id) REFERENCES students(id)"+
                ");";
        executeUpdate(query);

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

        String query = "CREATE TABLE IF NOT EXISTS math_scores (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "student_id INT," +        // Student ID to link with students table
                "MCAP INT," +
                "i_ready INT," +
                "FOREIGN KEY (student_id) REFERENCES students(id)"+
                ");";
        executeUpdate(query);

    }

    public void addMathScore(int studentId, int mcapScore, int iReadyScore){
        String query = "INSERT INTO math_scores (student_id, MCAP, i_ready, dibels) VALUES (?, ?, ?, ?);";

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
                return resultSet.getInt("id");            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if student is not found

    }


    public void createStudentTable(String tableTitle) { //to reset the db

        String query = "CREATE TABLE IF NOT EXISTS students (" +
                "student_id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(100)," +
                "age VARCHAR(100)," +
                "grade INT," +
                "teacher VARCHAR(100)" +
//                "reading_ID INT," +
//                "math_ID INT" +
//                "FOREIGN KEY (reading_ID) REFERENCES reading_scores(id)," +
//                "FOREIGN KEY (math_ID) REFERENCES math_scores(id),"+
                ");";

        executeUpdate(query);

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

    private void executeUpdate(String query) {
        try (Statement statement = connector.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



//    public int displayStudentTable(){
//        return 3;
//    }
//
//    public int displayReadingTable(){
//        return 1;
//    }
//
//    public int displayMathTable(){
//        return 2;
//    }




}