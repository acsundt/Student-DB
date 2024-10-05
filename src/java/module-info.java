module main.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens main.app to javafx.fxml;
    exports main.app;
//    exports main.app;
    //opens main.app to javafx.fxml;
    //exports main.app.studentsortdb;
    //opens main.app.studentsortdb to javafx.fxml;
}