module com.example.comp228_db_javafx_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;


    opens com.example.comp228_db_javafx_demo to javafx.fxml;
    exports com.example.comp228_db_javafx_demo;
}