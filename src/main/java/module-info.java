module main.app.studentsortdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens main.app.studentsortdb to javafx.fxml;
    exports main.app.studentsortdb;
}