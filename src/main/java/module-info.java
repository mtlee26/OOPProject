module com.example.oopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oopproject to javafx.fxml;
    exports com.example.oopproject;
    opens com.example.oopproject.controller to javafx.fxml;
    exports com.example.oopproject.controller;
}