package com.example.officialdictionary;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.scene.web.WebView;

import org.controlsfx.control.textfield.TextFields;

public class HelloController {
    Database database = new Database();
    @FXML
    WebView webView = new WebView();
    @FXML
    private TextField targetSearch;

    public void Search(ActionEvent event) throws SQLException {
        String word = targetSearch.getText();
        TextFields.bindAutoCompletion(targetSearch, database.listWord.keySet());
        webView.getEngine().loadContent(database.searchWord(word));
    }
    public void Speak() {
        String word = targetSearch.getText();
        FreeTTS.Speak(word);
    }

     public void Add(ActionEvent event) throws IOException {
        SwitchScreen.Switch("Add-view.fxml", event);
     }
     @FXML
     TextField targetAdd;
     @FXML
     TextField explainAdd;
     public void addNewWord() throws SQLException {
         database.addWord(targetAdd.getText(),explainAdd.getText());
     }

     public void Edit(ActionEvent event) throws IOException {
        SwitchScreen.Switch("Edit-view.fxml", event);
     }
     @FXML
     TextField targetEdit;
     @FXML
     TextField explainEdit;
     public void editAWord() throws SQLException {
         database.editWord(targetEdit.getText(), explainEdit.getText());
     }

     public void Delete(ActionEvent event) throws IOException {
        SwitchScreen.Switch("Delete-view.fxml", event);
     }
    @FXML
    TextField targetDelete;
     public void deleteAWord() throws SQLException {
         database.deleteWord(targetDelete.getText());
     }

     public void Help(ActionEvent event) throws IOException {
        SwitchScreen.Switch("Help-view.fxml", event );
     }
     public void Back(ActionEvent event) throws IOException {
        SwitchScreen.Switch("hello-view.fxml", event);
     }


     public void TranslateOnline(ActionEvent event) throws IOException {
         SwitchScreen.Switch("TranslateOnline-view.fxml",event);
     }
     @FXML
     TextArea textTranslate;
     @FXML
     TextArea textExplain;
    public void Translate() throws Exception {
        String fromLang = "en";
        String toLang = "vi";
        String text = textTranslate.getText();
        textExplain.setText(Translator.translate(fromLang, toLang, text));
    }

    public void Exit() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Click yes to exit or no to cancel",ButtonType.YES, ButtonType.NO);
        alert.setTitle("Warning!");
        alert.setHeaderText("Do you want to exit?");
        Optional<ButtonType> option = alert.showAndWait();

         if (option.get() == ButtonType.YES) {
            Platform.exit();
        } else {
             return;
         }

    }

     /*public void saveData() throws SQLException {
         database.HashmapToDatabase();
     }*/
}
/*
public void Translate() throws Exception {
        String fromLang = "en";
        String toLang = "vi";
        String text = textTranslate.getText();
        textExplain.setText(Translator.translate(fromLang, toLang, text));
    }
 */