package com.example.oopproject.controller.dictionary;

import com.example.oopproject.controller.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import static com.example.oopproject.DictionaryApplication.scene;
import static com.example.oopproject.DictionaryApplication.stage;

public class AddWordController {
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
            MenuController.database.addWord(wordTarget, wordExplain);
            //save successfully
            newWordTargetField.clear();
            newWordExplainField.clear();
        } else {

        }

    }

    @FXML
    public void onCancelAddWordButtonClick() {
        //TODO alert
        stage.setScene(scene);
        stage.show();
//        Stage stage = (Stage) saveAddWordButton.getScene().getWindow();
//        stage.close();
//        stage.setScene(scene);
//        stage.show();
    }
}
