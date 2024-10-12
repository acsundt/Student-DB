package main.app;

//class that holds the data of each student to be displayed on the application
public class Student {

    String studentName;
    String studentAge;
    int studentGrade;
    String studentTeacher;

    public Student(String[] studentInfo){

        this.studentName = studentInfo[0];
        this.studentAge = studentInfo[1];
        this.studentGrade = Integer.parseInt(studentInfo[2]);
        this.studentTeacher = studentInfo[3];

    }

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }


    public String getStudentTeacher() {
        return studentTeacher;
    }

    public void setStudentTeacher(String studentTeacher) {
        this.studentTeacher = studentTeacher;
    }












}
