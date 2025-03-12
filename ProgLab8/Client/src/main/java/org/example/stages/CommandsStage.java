package org.example.stages;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.Client;

import java.io.IOException;

public class CommandsStage {
    @FXML
    private Text textOfError;

    @FXML
    void animation(MouseEvent event) throws IOException {
        if (!Client.clientRequester.getUserName().equals("new User")){
            Client.setRoot("animation");
        } else {
            textOfError.setText("You need to log in!");
        }
    }

    @FXML
    void backToMain(MouseEvent event) throws IOException {
        Client.setRoot("main");
    }

    @FXML
    void showTable(MouseEvent event) throws IOException {
        if (!Client.clientRequester.getUserName().equals("new User")){
            Client.setRoot("table");
        } else {
            textOfError.setText("You need to log in!");
        }
    }

    @FXML
    void switchToCollectionCommands(MouseEvent event) throws IOException {
        if (!Client.clientRequester.getUserName().equals("new User")){
            Client.setRoot("collectionCommands");
        } else {
            textOfError.setText("You need to log in!");
        }
    }
}
