package com.example.oopproject.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/EVdictionary";
    private String user = "root";
    private String password = "letam_2624";
    private Connection connection;
    private PreparedStatement ps;

    Dictionary dictionary = new Dictionary();
    public Database() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            String SQLQuery = "SELECT * FROM dictionary";
            ps = connection.prepareStatement(SQLQuery);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String word = resultSet.getString("target");
                dictionary.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> dictionarySearcher(String wordInput) {
        ObservableList<String> suggestList = FXCollections.observableArrayList();
        for (String x : dictionary.getWordList()) {
            if(x.startsWith(wordInput)) {
                suggestList.add(x);
            }
        }
        return suggestList;
    }

    public String databaseLookup(String wordInput) {
        String wordExplain = null;
        String SQLQuery = "SELECT * FROM dictionary WHERE target = ?";
        try {
            ps = connection.prepareStatement(SQLQuery);
            ps.setString(1, wordInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                wordExplain = rs.getString("definition");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dictionary.htmlToText(wordExplain);
    }

    public void addWord(String wordTarget, String wordExplain) {
        String SQLQuery = "INSERT INTO dictionary (target, definition) VALUES (?,?)";
        try {
            ps = connection.prepareStatement(SQLQuery);
            ps.setString(1, wordTarget);
            ps.setString(2, wordExplain);
            try {
                ps.executeUpdate();

            } catch (SQLIntegrityConstraintViolationException e) {
                //word existed in dictionary
                //alert

            }
            dictionary.add(wordTarget);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeWord(String wordTarget, String wordExplain) {
        String SQLQuery = "UPDATE dictionary SET definition = ? WHERE target = ?";
        try {
            ps = connection.prepareStatement(SQLQuery);
            ps.setString(1, wordExplain);
            ps.setString(2, wordTarget);
            ps.executeUpdate();
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

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Database db = new Database();
//        db.addWord("tam", "/tam/\n-sinh vien");
//        System.out.println(db.databaseLookup("tam"));
//    }
}
