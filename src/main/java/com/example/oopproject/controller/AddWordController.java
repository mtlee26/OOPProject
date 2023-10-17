package com.example.oopproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AddWordController extends Controller {
    @FXML
    private TextArea newWordTargetField;
    @FXML
    private TextArea newWordExplainField;
    @FXML
    private Button saveAddWordButton;

    @FXML
    public void onSaveAddWordButtonClick() {
        String wordTarget = newWordTargetField.getText();
        String wordExplain = newWordExplainField.getText();
        if (wordTarget != null && wordExplain != null) {
            database.addWord(wordTarget, wordExplain);
            //save successfully
            newWordTargetField.clear();
            newWordExplainField.clear();
        }

    }

    @FXML
    public void onCancelAddWordButtonClick() {
        //alert
    }

    @FXML
    public void onExitAddWordButtonClick(ActionEvent actionEvent) {
        setScene(actionEvent, "/Views/DictionaryView.fxml");
    }
}
