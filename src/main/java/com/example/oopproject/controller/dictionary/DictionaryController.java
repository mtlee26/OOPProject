package com.example.oopproject.controller.dictionary;

import com.example.oopproject.controller.MenuController;
import com.example.oopproject.dictionary.Dictionary;
import com.example.oopproject.dictionary.TranslatorApi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.oopproject.DictionaryApplication.stage;
import static com.example.oopproject.dictionary.TextToSpeech.playSoundGoogleTranslateEnToVi;

public class DictionaryController extends MenuController implements Initializable {
    private String wordInput;
    private String wordFromWordList;
    private String explain;
    ObservableList<String> listResults = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> suggestList;
    @FXML
    private ScrollPane meaningBox;
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
    @FXML
    private TextArea editMeaning;
    @FXML
    private Button translateButton;
//    protected Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.textProperty().addListener((observable, oldValue, newValue)-> {
            listResults.clear();
            if (!newValue.isBlank()) {
                listResults = database.dictionarySearcher(newValue);
                suggestList.setItems(listResults);
            } else {
                meaningBox.setContent(null);
            }
        });
//        searchField.textProperty().addListener((observable, oldValue, newValue)-> {
//            if (newValue.isEmpty()) {
//                meaningArea.clear();
//                System.out.println("clear");
//            }
//        });
//        meaningArea.e;
        searchButton.disableProperty().bind(searchField.textProperty().isEmpty());
        translateButton.disableProperty().bind(sourceArea.textProperty().isEmpty());
    }

    @FXML
    public void onEnter() {
        onSearchButtonClick();
    }

    @FXML
    public void onSearchButtonClick() {
        wordInput = searchField.getText().trim().toLowerCase();
        String wordExplain = database.lookup(wordInput);
        if (wordExplain == null) {
            alertNotFound.setVisible(true);
            meaningBox.setContent(null);
        } else {
            show(wordExplain);
        }
    }

    @FXML
    public void onMouseClickedSuggestList() {
        wordFromWordList = suggestList.getSelectionModel().getSelectedItem();
        wordInput = wordFromWordList;
        if (wordFromWordList != null) {
            String wordExplain = database.lookup(wordFromWordList);
            show(wordExplain);
        }
    }

    @FXML
    public void onChangeButtonClick() {
        meaningBox.setVisible(false);
        editMeaning.setVisible(true);
        editMeaning.setText(explain);
        saveChangeButton.setVisible(true);
        cancelChangeButton.setVisible(true);
        searchField.setDisable(true);
    }

    @FXML
    public void onSaveChange() {
        Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
        saveAlert.setHeaderText(null);
        saveAlert.setContentText("Do you want to save change to this word meaning?");
        Optional<ButtonType> option = saveAlert.showAndWait();
        if (option.get() == ButtonType.OK) {
            explain = editMeaning.getText();
            database.changeWord(wordInput, explain);
            editMeaning.setVisible(false);
            saveChangeButton.setVisible(false);
            cancelChangeButton.setVisible(false);
            meaningBox.setVisible(true);
            searchField.setDisable(false);
            show(explain);
        }
    }

    @FXML
    public void onCancelChange() {
        editMeaning.setVisible(false);
        saveChangeButton.setVisible(false);
        cancelChangeButton.setVisible(false);
        meaningBox.setVisible(true);
        searchField.setDisable(false);
        //show(explain);
    }

    @FXML
    public void onDeleteButtonClick() {
        if (wordInput != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setHeaderText(null);
            deleteAlert.setContentText("Do you want to delete this word?");
            Optional<ButtonType> option = deleteAlert.showAndWait();
            if (option.get() == ButtonType.OK) {
                if (searchField.getText().trim().toLowerCase().equals(wordInput)) {
                    searchField.clear();
                }
                database.deleteWord(wordInput);
                listResults.remove(wordInput);
                meaningBox.setContent(null);
                wordInput = null;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("No word found");
            alert.showAndWait();
        }
    }

    @FXML
    public void onAddButtonClick() {
        // TODO
        addRoot.setVisible(true);
    }

    @FXML
    public void onSoundButton() {
        System.out.println(wordInput);
        playSoundGoogleTranslateEnToVi(wordInput);
    }

    @FXML
    public void onExitButtonClick() {
        stage.close();
    }

    @FXML
    public void onClickTranslate() {
        String source = sourceArea.getText();
        String translate = TranslatorApi.translateEnToVi(source);
        meaningArea.setText(translate);
    }

    public void show(String wordExplain) {
        VBox vbox = new VBox(5);
        String[] s = wordExplain.split("\n", -1);
        explain = "";
        Font font1 = Font.font("Arial", FontWeight.MEDIUM, 16);
        Font font2 = Font.font("Arial", FontWeight.BOLD,16);
        Font font3 = Font.font("Arial", 14);
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].trim();
            if (s[i].startsWith("@")) {
                explain = explain + s[i] + "\n";
                String tmp = s[i];
                Text text = new Text(tmp);
                text.setFont(font1);
                vbox.getChildren().add(text);
            } else if (s[i].startsWith("*")) {
                explain = explain + "  " + s[i] + "\n";
                String tmp = "  " + s[i] ;
                Text text = new Text(tmp);
                text.setFont(font2);
                vbox.getChildren().add(text);
            } else if (s[i].startsWith("-")) {
                explain = explain + "     " + s[i] + "\n";
                String tmp = "     " + s[i];
                Text text = new Text(tmp);
                text.setFont(font3);
                vbox.getChildren().add(text);
            } else {
                explain = explain + " " + s[i] + "\n";
                String tmp = " " + s[i];
                Text text = new Text(tmp);
                text.setFont(font3);
                vbox.getChildren().add(text);
            }
        }
        meaningBox.setContent(vbox);
        alertNotFound.setVisible(false);
    }

    @FXML
    public void onListen() {
        String input = sourceArea.getText();
        playSoundGoogleTranslateEnToVi(input);
    }
}
