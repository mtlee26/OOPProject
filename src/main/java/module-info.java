module com.example.oopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;
    requires jlayer;
    requires java.desktop;

    opens com.example.oopproject to javafx.fxml;
    exports com.example.oopproject;
    opens com.example.oopproject.controller to javafx.fxml;
    exports com.example.oopproject.controller;
    exports com.example.oopproject.controller.game;
    opens com.example.oopproject.controller.game to javafx.fxml;
    exports com.example.oopproject.controller.dictionary;
    opens com.example.oopproject.controller.dictionary to javafx.fxml;
    exports com.example.oopproject.database;
}

