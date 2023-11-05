package com.example.oopproject.controller.dictionary;

import com.example.oopproject.controller.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static com.example.oopproject.controller.MenuController.addRoot;

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
        //stage.setScene(scene);
        //stage.show();
        addRoot.setVisible(false);
    }
}
