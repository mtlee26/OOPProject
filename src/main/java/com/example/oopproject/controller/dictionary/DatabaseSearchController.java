package com.example.oopproject.controller.dictionary;

import com.example.oopproject.controller.Controller;
import com.example.oopproject.database.Database;
import com.example.oopproject.dictionary.TranslatorApi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.oopproject.dictionary.TextToSpeech.playSoundGoogleTranslateEnToVi;

public class DatabaseSearchController extends Controller implements Initializable {
//    private Database database = new Database();
    private String wordInput;
    private String wordFromWordList;
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
//    protected Database database = new Database();

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
        wordFromWordList = suggestList.getSelectionModel().getSelectedItem();
        wordInput = wordFromWordList;
        if (wordFromWordList != null) {
            String wordExplain = database.databaseLookup(wordFromWordList);
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
            System.out.println(wordInput);
            if (searchField.getText().trim().toLowerCase().equals(wordInput)) {
                System.out.println(searchField.getText());
                searchField.clear();
            }
            database.deleteWord(wordInput);
            listResults.remove(wordInput);
            meaningBox.clear();
        }

//        if(wordFromWordList != null) {
//            System.out.println(wordFromWordList);
//            if (searchField.getText().trim().toLowerCase().equals(wordFromWordList)) {
//                System.out.println(searchField.getText());
//                searchField.clear();
//            }
//            database.deleteWord(wordFromWordList);
//            listResults.remove(wordFromWordList);
//            meaningBox.clear();
//        }
    }


    @FXML
    public void onAddButtonClick(ActionEvent actionEvent) {
        // TODO
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/AddWordView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSoundButton() {
        //wordInput = searchField.getText().trim().toLowerCase();
        playSoundGoogleTranslateEnToVi(wordInput);
    }

    @FXML
    public void onClickTranslate() {
        String source = sourceArea.getText();
        String translate = TranslatorApi.translateEnToVi(source);
        meaningArea.setText(translate);
    }
}
