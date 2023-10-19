module com.example.oopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;
    requires jlayer;

    opens com.example.oopproject to javafx.fxml;
    exports com.example.oopproject;
    opens com.example.oopproject.controller to javafx.fxml;
    exports com.example.oopproject.controller;
}

