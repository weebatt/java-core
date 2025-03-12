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

public class LogInStage {

    @FXML
    private PasswordField passwordHidden;

    @FXML
    private TextField passwordVisible;

    @FXML
    private CheckBox showPasswordButton;

    @FXML
    private TextField usernameText;

    @FXML
    private Text textOfError;

    @FXML
    void switchBack(MouseEvent event) throws IOException {
        Client.setRoot("main");
    }

    @FXML
    void checkingPassword(MouseEvent mouseEven) throws ServerUnavailableException, IOException, ClassNotFoundException {
        String username = usernameText.getText();
        String password = passwordHidden.getText();
        Client.clientRequester.sendRequest("log_in", username, password, null);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            String response = null;
            try {
                response = (String) Client.clientHandler.receiveResponse();
            } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                System.err.println("LogIn " + e.getMessage());
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
                textOfError.setText("This user doesn't exist. Please, recheck entered username and password!");
            }
        });
        pause.play();
    }

    @FXML
    void showPassword(MouseEvent event) {
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
