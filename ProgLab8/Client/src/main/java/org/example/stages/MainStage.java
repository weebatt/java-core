package org.example.stages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.Client;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainStage implements Initializable {

    @FXML
    private Text eighthText;

    @FXML
    private Text eleventhText;

    @FXML
    private Text firstText;

    @FXML
    private Text fourthText;

    @FXML
    private Text greeting;

    @FXML
    private Button intro;

    @FXML
    private ChoiceBox<String> languagesVariants;

    @FXML
    private Button logIn;

    @FXML
    private Button logOut;

    @FXML
    private Button next;

    @FXML
    private Text ninthText;

    @FXML
    private Text secondText;

    @FXML
    private Text seventhText;

    @FXML
    private Button signUp;

    @FXML
    private Text sixthText;

    @FXML
    private Text tenthText;

    @FXML
    private Text thirdText;

    @FXML
    private Text thithText;

    @FXML
    private Text helloText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languagesVariants.getItems().setAll("Russian", "Slovenian", "Ukrainian", "Spanish (Puerto Rico)");
        languagesVariants.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Russian":
                    setLanguage(new Locale("ru", "RU"));
                    break;
                case "Slovenian":
                    setLanguage(new Locale("sl", "SL"));
                    break;
                case "Ukrainian":
                    setLanguage(new Locale("uk", "UK"));
                    break;
                case "Spanish (Puerto Rico)":
                    setLanguage(new Locale("es", "PR"));
                    break;
            }
        });
        greeting.setText(" " + Client.clientRequester.getUserName());
    }

    private void setLanguage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("gui", locale);

        greeting.setText(bundle.getString("greeting"));
        intro.setText(bundle.getString("intro"));
        logIn.setText(bundle.getString("logIn"));
        logOut.setText(bundle.getString("logOut"));
        signUp.setText(bundle.getString("signUp"));
        next.setText(bundle.getString("next"));
        firstText.setText(bundle.getString("firstText"));
        secondText.setText(bundle.getString("secondText"));
        thirdText.setText(bundle.getString("thirdText"));
        fourthText.setText(bundle.getString("fourthText"));
        thithText.setText(bundle.getString("thithText"));
        sixthText.setText(bundle.getString("sixthText"));
        seventhText.setText(bundle.getString("seventhText"));
        eighthText.setText(bundle.getString("eighthText"));
        ninthText.setText(bundle.getString("ninthText"));
        tenthText.setText(bundle.getString("tenthText"));
        eleventhText.setText(bundle.getString("eleventhText"));
    }

    @FXML
    void logOut(MouseEvent event) {
        Client.clientRequester.setUserName("new User", "1234");
        greeting.setText("Hello, new User");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("You are successfully log out!");
        alert.show();
    }

    @FXML
    void switchToIntro(MouseEvent event) throws IOException {
        Client.setRoot("intro");
    }

    @FXML
    void switchToLogIn(MouseEvent event) throws IOException {
        Client.setRoot("logIn");
    }

    @FXML
    void switchToCommands(MouseEvent event) throws IOException {
        Client.setRoot("commands");
    }

    @FXML
    void switchToSignUp(MouseEvent event) throws IOException {
        Client.setRoot("signUp");
    }
}
