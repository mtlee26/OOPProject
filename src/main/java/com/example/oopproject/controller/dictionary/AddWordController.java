package com.example.oopproject.controller.dictionary;

import com.example.oopproject.controller.MenuController;
import com.example.oopproject.dictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

import static com.example.oopproject.controller.MenuController.addRoot;

public class AddWordController implements Initializable {
    @FXML
    private TextArea newWordTargetField;
    @FXML
    private TextArea newWordExplainField;
    @FXML
    private Button saveAddWordButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveAddWordButton.disableProperty().bind(newWordTargetField.textProperty().isEmpty());
        saveAddWordButton.disableProperty().bind(newWordExplainField.textProperty().isEmpty());
    }

    @FXML
    public void onSaveAddWordButtonClick() {
        String wordTarget = newWordTargetField.getText();
        String wordExplain = newWordExplainField.getText();
        if (wordTarget != null && wordExplain != null) {
            MenuController.database.addWord(wordTarget, wordExplain);
            //save successfully
            newWordTargetField.clear();
            newWordExplainField.clear();
        }
    }

    @FXML
    public void onCancelAddWordButtonClick() {
        addRoot.setVisible(false);
    }
}
