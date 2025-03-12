package org.example.stages;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.Client;
import org.example.exceptions.ServerUnavailableException;

import java.io.IOException;
import java.util.Objects;

public class SignUpStage {

    @FXML
    private PasswordField passwordHidden1;

    @FXML
    private PasswordField passwordHidden2;

    @FXML
    private TextField passwordVisible1;

    @FXML
    private TextField passwordVisible2;

    @FXML
    private CheckBox showPasswordButton;

    @FXML
    private Text textOfError;

    @FXML
    private TextField usernameText;

    @FXML
    void switchBack(MouseEvent event) throws IOException {
        Client.setRoot("main");
    }

    @FXML
    void checkingPassword(MouseEvent mouseEvent) throws ServerUnavailableException, ClassNotFoundException, IOException {
        if (passwordHidden1.getText().equals(passwordHidden2.getText()) || passwordVisible1.getText().equals(passwordVisible2.getText())){
            String username = usernameText.getText();
            String password = passwordHidden1.getText();
            Client.clientRequester.sendRequest("register", username, password, null);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                String response = null;
                try {
                    response = (String) Client.clientHandler.receiveResponse();
                } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                    System.err.println("SignUp " + e.getMessage());
                    Client.connecting();
                }
                if (Objects.equals(response, "1")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("You are successfully registered or logged in. Please, wait a few seconds and you will be automatically transferred to entry page!");
                    alert.show();

                    PauseTransition transferPause = new PauseTransition(Duration.seconds(1.5));
                    transferPause.setOnFinished(transferEvent -> {
                        Client.setRoot("main");
                    });
                    transferPause.play();
                } else {
                    textOfError.setText("This username already exists!");
                }
            });
            pause.play();
        }
        else {
            textOfError.setText("You made a mistake in password! Recheck yourself!");
        }
    }

    @FXML
    void showPassword(MouseEvent event) {
        executorForShowPassword(passwordHidden1, passwordVisible1);
        executorForShowPassword(passwordHidden2, passwordVisible2);
    }

    public void executorForShowPassword(PasswordField passwordHidden, TextField passwordVisible){
        if (showPasswordButton.isSelected()) {
            passwordVisible.setText(passwordHidden.getText());
            passwordVisible.setVisible(true);
            passwordHidden.setVisible(false);
            return;
        }
        passwordHidden.setText(passwordVisible.getText());
        passwordHidden.setVisible(true);
        passwordVisible.setVisible(false);
    }
}
