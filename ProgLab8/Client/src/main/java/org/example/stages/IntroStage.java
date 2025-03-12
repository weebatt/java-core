package org.example.stages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import org.example.Client;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroStage implements Initializable {
    private Media media;
    private MediaPlayer player;

    @FXML
    private MediaView mediaView;

    @FXML
    private Text text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL mediaUrl = getClass().getResource("/org/example/videos/botik.mp4");
        if (mediaUrl == null) {
            throw new IllegalArgumentException("Media file not found");
        }
        try {
            media = new Media(mediaUrl.toURI().toString());
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
        }
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.play();
    }

    @FXML
    void switchToEntryStage(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            player.seek(player.getTotalDuration());
            text.setText("");
        } else if (event.getCode() == KeyCode.SPACE){
            player.stop();
            text.setText("");
            Client.setRoot("main");
        }
    }
}
