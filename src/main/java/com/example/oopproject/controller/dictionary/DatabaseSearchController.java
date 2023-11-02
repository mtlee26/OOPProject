package com.example.oopproject.controller.dictionary;

import com.example.oopproject.controller.Controller;
import com.example.oopproject.database.Database;
import com.example.oopproject.dictionary.TranslatorApi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.oopproject.dictionary.TextToSpeech.playSoundGoogleTranslateEnToVi;

public class DatabaseSearchController extends Controller implements Initializable {
//    private Database database = new Database();
    private String wordInput;
    ObservableList<String> listResults = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> suggestList;
    @FXML
    private TextArea meaningBox;
    @FXML
    private Button searchButton;
    @FXML
    private Button saveChangeButton;
    @FXML
    private Button cancelChangeButton;
    @FXML
    private Label alertNotFound;
    @FXML
    private TextArea sourceArea;
    @FXML
    private TextArea meaningArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.textProperty().addListener((observable, oldValue, newValue)-> {
            listResults.clear();
            if (!newValue.isBlank()) {
                listResults = database.dictionarySearcher(newValue);
                suggestList.setItems(listResults);
            }
        });
        searchButton.disableProperty().bind(searchField.textProperty().isEmpty());
    }

    @FXML
    public void onEnter() {
        onSearchButtonClick();
    }

    @FXML
    public void onSearchButtonClick() {
        wordInput = searchField.getText().trim().toLowerCase();
        String wordExplain = database.databaseLookup(wordInput);
        if (wordExplain == null) {
            alertNotFound.setVisible(true);
            meaningBox.clear();
        } else {
            meaningBox.setText(wordExplain);
            alertNotFound.setVisible(false);
        }
    }

    @FXML
    public void onMouseClickedSuggestList() {
        String word = suggestList.getSelectionModel().getSelectedItem();
        if (word != null) {
            String wordExplain = database.databaseLookup(word);
            meaningBox.setText(wordExplain);
        }
    }

    @FXML
    public void onChangeButtonClick() {
        meaningBox.setEditable(true);
        saveChangeButton.setVisible(true);
        cancelChangeButton.setVisible(true);
    }

    @FXML
    public void onSaveChangeButtonClick() {
        String newMeaning = meaningBox.getText();
        database.changeWord(wordInput, newMeaning);
        //thong bao change success
        meaningBox.setEditable(false);
        saveChangeButton.setVisible(false);
        cancelChangeButton.setVisible(false);
    }

    @FXML
    public void onCancelButtonClick() {
        //thong bao (do you want to exit change)
        meaningBox.setEditable(false);
        saveChangeButton.setVisible(false);
        cancelChangeButton.setVisible(false);
    }

    @FXML
    public void onDeleteButtonClick() {
        if (wordInput != null) {
            database.deleteWord(wordInput);
            refresh();
            //thong bao delete successfully
        }

    }

    public void refresh() {
        searchField.clear();
        meaningBox.clear();
    }

    @FXML
    public void onAddButtonClick(ActionEvent actionEvent) {
        // TODO
//        setScene(actionEvent, "/Views/AddWordView.fxml");
    }

    @FXML
    public void onSoundButton() {
        wordInput = searchField.getText().trim().toLowerCase();
        playSoundGoogleTranslateEnToVi(wordInput);
    }

    @FXML
    public void onClickTranslate() {
        String source = sourceArea.getText();
        String translate = TranslatorApi.translateEnToVi(source);
        meaningArea.setText(translate);
    }
}
