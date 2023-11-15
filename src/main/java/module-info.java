module com.example.oopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;
    requires jlayer;
    requires java.desktop;
    requires javafx.media;

    opens com.example.oopproject to javafx.fxml;
    exports com.example.oopproject;
    opens com.example.oopproject.controller to javafx.fxml;
    exports com.example.oopproject.controller;
    exports com.example.oopproject.controller.game;
    opens com.example.oopproject.controller.game to javafx.fxml;
    exports com.example.oopproject.controller.dictionary;
    opens com.example.oopproject.controller.dictionary to javafx.fxml;
    //exports com.example.oopproject.database;
    exports com.example.oopproject.game;
    opens com.example.oopproject.game to javafx.fxml;
    exports com.example.oopproject.game.entities;
    exports com.example.oopproject.game.graphics;
    //opens com.example.oopproject.game to javafx.fxml;
    exports com.example.oopproject.game.bear;
    opens com.example.oopproject.game.bear to javafx.fxml;
    exports com.example.oopproject.game.soccer;
    opens com.example.oopproject.game.soccer to javafx.fxml;
    exports com.example.oopproject.dictionary;
}

