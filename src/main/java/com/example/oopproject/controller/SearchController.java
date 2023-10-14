package com.example.oopproject.controller;

import com.example.oopproject.dictionary.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private String word_input;
    ObservableList<String> listResults = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> suggestList;
    @FXML
    private TextArea meaningBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile();
        searchField.setOnKeyTyped(keyEvent -> {
            if (searchField.getText().isEmpty()) {

            } else {
                handleOnKeyTyped();
            }
        });
    }

    @FXML
    private void handleOnKeyTyped() {
        word_input = searchField.getText().trim().toLowerCase();
        listResults = dictionaryManagement.dictionarySearcher(word_input);
        suggestList.setItems(listResults);
    }

    @FXML
    private void onSearchButton() {
        word_input = searchField.getText().trim().toLowerCase();
        String word_explain = dictionaryManagement.dictionaryLookup(word_input);
        meaningBox.setText(word_explain);
    }

    @FXML
    private void onMouseClickedSuggestList() {
        word_input = suggestList.getSelectionModel().getSelectedItem();
        searchField.setText(word_input);
        String word_explain = dictionaryManagement.dictionaryLookup(word_input);
        meaningBox.setText(word_explain);
    }

}
