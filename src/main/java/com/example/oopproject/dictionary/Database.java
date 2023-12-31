package com.example.oopproject.dictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Collections;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/evdictionary";
    private String user = "root";
    private String password = "letam_2624";
    private Connection connection;
    private PreparedStatement ps;

    private com.example.oopproject.dictionary.Dictionary dictionary = new com.example.oopproject.dictionary.Dictionary();
    public Database() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            String SQLQuery = "SELECT * FROM dictionary";
            ps = connection.prepareStatement(SQLQuery);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String target = resultSet.getString("target");
                String explain = resultSet.getString("definition");
                Word word = new Word(target, explain);
                dictionary.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String lookup(String wordTarget) {
        return dictionary.getMeaning(wordTarget);
    }

    public ObservableList<String> dictionarySearcher(String wordInput) {
        wordInput = wordInput.trim().toLowerCase();
        ObservableList<String> suggestList = FXCollections.observableArrayList();
        for (String x : dictionary.getWordList().keySet()) {
            if(x.startsWith(wordInput)) {
                suggestList.add(x);
            }
        }
        FXCollections.sort(suggestList);
        return suggestList;
    }

    public void addWord(String wordTarget, String wordExplain) {
        String SQLQuery = "INSERT INTO dictionary (target, definition) VALUES (?,?)";
        try {
            ps = connection.prepareStatement(SQLQuery);
            ps.setString(1, wordTarget);
            ps.setString(2, wordExplain);
            try {
                ps.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Save word successfully.");
                alert.showAndWait();
                dictionary.getWordList().put(wordTarget, wordExplain);
            } catch (SQLIntegrityConstraintViolationException e) {
                //word existed in dictionary
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("This word has already existed.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeWord(String wordChange, String wordExplain) {
        String SQLQuery = "UPDATE dictionary SET definition = ? WHERE target = ?";
        try {
            ps = connection.prepareStatement(SQLQuery);
            ps.setString(1, wordExplain);
            ps.setString(2, wordChange);
            ps.executeUpdate();
            dictionary.getWordList().replace(wordChange, wordExplain);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWord(String word) {
        String SQLQuery = "DELETE FROM dictionary WHERE target = ?";
        try {
            ps = connection.prepareStatement(SQLQuery);
            ps.setString(1, word);
            ps.executeUpdate();
            dictionary.remove(word);
            System.out.println("delete successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public String lookup(String wordInput) {
//        String wordExplain = null;
//        String SQLQuery = "SELECT * FROM dictionary WHERE target = ?";
//        try {
//            ps = connection.prepareStatement(SQLQuery);
//            ps.setString(1, wordInput);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String html = rs.getString("definition");
//                wordExplain = dictionary.htmlToText(html);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return wordExplain;
//    }
}
