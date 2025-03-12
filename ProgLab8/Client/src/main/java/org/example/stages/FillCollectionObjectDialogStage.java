package org.example.stages;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.example.Client;
import org.example.exceptions.ServerUnavailableException;
import org.example.models.*;
import org.example.utility.ClientCommandManager;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FillCollectionObjectDialogStage implements Initializable {
    @FXML
    private TextField adminName;

    @FXML
    private TextField coordinates;

    @FXML
    private TextField expelledStudents;

    @FXML
    private ChoiceBox<String> eyeColor;

    @FXML
    private ChoiceBox<String> formOfEducation;

    @FXML
    private TextField groupName;

    @FXML
    private ChoiceBox<String> hairColor;

    @FXML
    private TextField height;

    @FXML
    private TextField locationXY;

    @FXML
    private TextField locationName;

    @FXML
    private ChoiceBox<String> nationality;

    @FXML
    private TextField studentCount;

    @FXML
    private TextField transferredStudents;

    private AnimationStage animationStage;

    private long groupId;

    private String username;

    private TableStage tableStage;

    public void setAnimationStage(AnimationStage animationStage, long groupId, String username){
        this.animationStage = animationStage;
        this.groupId = groupId;
        this.username = username;
    }

    public void setTableStage(TableStage tableStage){
        this.tableStage = tableStage;
    }

    private String groupNameText;
    private String coordinatesText;
    private String studentCountText;
    private String expelledStudentsText;
    private String transferredStudentsText;
    private String adminNameText;
    private String heightText;
    private String locationText;
    private String locationNameText;
    private String formOfEducationText;
    private String eyeColorText;
    private String hairColorText;
    private String nationalityText;

    @FXML
    void insert(MouseEvent event) throws ServerUnavailableException, IOException, ClassNotFoundException {
        if (validateInput()) {
            groupNameText = groupName.getText();
            coordinatesText = coordinates.getText();
            studentCountText = studentCount.getText();
            expelledStudentsText = expelledStudents.getText();
            transferredStudentsText = transferredStudents.getText();
            adminNameText = adminName.getText();
            heightText = height.getText();
            locationText = locationXY.getText();
            locationNameText = locationName.getText();
            formOfEducationText = String.valueOf(formOfEducation.getSelectionModel().getSelectedItem());
            eyeColorText = String.valueOf(eyeColor.getSelectionModel().getSelectedItem());
            hairColorText = String.valueOf(hairColor.getSelectionModel().getSelectedItem());
            nationalityText = String.valueOf(nationality.getSelectionModel().getSelectedItem());
            StudyGroup studyGroup = (StudyGroup) ClientCommandManager.clientCommands.get("insertObject").executionForInsertObject(groupNameText, Float.parseFloat(coordinatesText.split(";")[0]), Integer.parseInt(coordinatesText.split(";")[1]),
                    Integer.parseInt(studentCountText), Long.parseLong(expelledStudentsText), Integer.parseInt(transferredStudentsText), adminNameText, Long.parseLong(heightText),
                    Integer.parseInt(locationText.split(";")[0]), Float.parseFloat(locationText.split(";")[1]), locationNameText, FormOfEducation.valueOf(formOfEducationText),
                    EyesColor.valueOf(eyeColorText), HairColor.valueOf(hairColorText), Country.valueOf(nationalityText), null);
            Client.clientRequester.sendRequest("insert", null, null, studyGroup);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(insertEvent -> {
                String response = null;
                try {
                    response = (String) Client.clientHandler.receiveResponse();
                } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                    System.err.println("Collection FillCollectionObjectDialogStage error " + e.getMessage());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (Objects.equals(response, "1")){
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText(String.format("New study group object %s was successfully added by %s!", groupNameText, Client.clientRequester.getUserName()));
                    alert.show();
                } else {
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Something wrong with adding new study group object!");
                    alert.show();
                }
            });
            pause.play();
        }
    }

    @FXML
    void update(MouseEvent event) throws ServerUnavailableException, IOException, ClassNotFoundException {
        if (validateInput() && Client.clientRequester.getUserName().equals(username)) {
            groupNameText = groupName.getText();
            coordinatesText = coordinates.getText();
            studentCountText = studentCount.getText();
            expelledStudentsText = expelledStudents.getText();
            transferredStudentsText = transferredStudents.getText();
            adminNameText = adminName.getText();
            heightText = height.getText();
            locationText = locationXY.getText();
            locationNameText = locationName.getText();
            formOfEducationText = String.valueOf(formOfEducation.getSelectionModel().getSelectedItem());
            eyeColorText = String.valueOf(eyeColor.getSelectionModel().getSelectedItem());
            hairColorText = String.valueOf(hairColor.getSelectionModel().getSelectedItem());
            nationalityText = String.valueOf(nationality.getSelectionModel().getSelectedItem());
            StudyGroup studyGroup = (StudyGroup) ClientCommandManager.clientCommands.get("insertObject").executionForInsertObject(groupNameText, Float.parseFloat(coordinatesText.split(";")[0]), Integer.parseInt(coordinatesText.split(";")[1]),
                    Integer.parseInt(studentCountText), Long.parseLong(expelledStudentsText), Integer.parseInt(transferredStudentsText), adminNameText, Long.parseLong(heightText),
                    Integer.parseInt(locationText.split(";")[0]), Float.parseFloat(locationText.split(";")[1]), locationNameText, FormOfEducation.valueOf(formOfEducationText),
                    EyesColor.valueOf(eyeColorText), HairColor.valueOf(hairColorText), Country.valueOf(nationalityText), null);
            Client.clientRequester.sendRequest("update " + groupId, null, null, studyGroup);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(insertEvent -> {
                String response = null;
                try {
                    response = (String) Client.clientHandler.receiveResponse();
                } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                    System.err.println("Collection FillCollectionObjectDialogStage error " + e.getMessage());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (Objects.equals(response, "1")){
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText(String.format("Study group object %s with %s was successfully updated by %s!", groupId, groupNameText, Client.clientRequester.getUserName()));
                    alert.show();
                } else {
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText(String.format("Something wrong with updating study group object %s with %s!", groupId, groupNameText));
                    alert.show();
                }
            });
            pause.play();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("You can't update other user's collection object!");
            alert.show();
        }
    }

    @FXML
    void removeGreater(MouseEvent event) throws ServerUnavailableException, IOException, ClassNotFoundException {
        if (validateInput()) {
            groupNameText = groupName.getText();
            coordinatesText = coordinates.getText();
            studentCountText = studentCount.getText();
            expelledStudentsText = expelledStudents.getText();
            transferredStudentsText = transferredStudents.getText();
            adminNameText = adminName.getText();
            heightText = height.getText();
            locationText = locationXY.getText();
            locationNameText = locationName.getText();
            formOfEducationText = String.valueOf(formOfEducation.getSelectionModel().getSelectedItem());
            eyeColorText = String.valueOf(eyeColor.getSelectionModel().getSelectedItem());
            hairColorText = String.valueOf(hairColor.getSelectionModel().getSelectedItem());
            nationalityText = String.valueOf(nationality.getSelectionModel().getSelectedItem());
            StudyGroup studyGroup = (StudyGroup) ClientCommandManager.clientCommands.get("insertObject").executionForInsertObject(groupNameText, Float.parseFloat(coordinatesText.split(";")[0]), Integer.parseInt(coordinatesText.split(";")[1]),
                    Integer.parseInt(studentCountText), Long.parseLong(expelledStudentsText), Integer.parseInt(transferredStudentsText), adminNameText, Long.parseLong(heightText),
                    Integer.parseInt(locationText.split(";")[0]), Float.parseFloat(locationText.split(";")[1]), locationNameText, FormOfEducation.valueOf(formOfEducationText),
                    EyesColor.valueOf(eyeColorText), HairColor.valueOf(hairColorText), Country.valueOf(nationalityText), null);
            Client.clientRequester.sendRequest("remove_greater", null, null, studyGroup);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(insertEvent -> {
                String response = null;
                try {
                    response = (String) Client.clientHandler.receiveResponse();
                } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                    System.err.println("Collection FillCollectionObjectDialogStage error " + e.getMessage());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (Objects.equals(response, "1")){
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("The command remove_greater was successfully completed!");
                    alert.show();
                } else {
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("The command remove_greater wasn't successfully completed! =((((((");
                    alert.show();
                }
            });
            pause.play();
        }
    }

    @FXML
    void removeLower(MouseEvent event) throws ServerUnavailableException, IOException, ClassNotFoundException {
        if (validateInput()) {
            groupNameText = groupName.getText();
            coordinatesText = coordinates.getText();
            studentCountText = studentCount.getText();
            expelledStudentsText = expelledStudents.getText();
            transferredStudentsText = transferredStudents.getText();
            adminNameText = adminName.getText();
            heightText = height.getText();
            locationText = locationXY.getText();
            locationNameText = locationName.getText();
            formOfEducationText = String.valueOf(formOfEducation.getSelectionModel().getSelectedItem());
            eyeColorText = String.valueOf(eyeColor.getSelectionModel().getSelectedItem());
            hairColorText = String.valueOf(hairColor.getSelectionModel().getSelectedItem());
            nationalityText = String.valueOf(nationality.getSelectionModel().getSelectedItem());
            StudyGroup studyGroup = (StudyGroup) ClientCommandManager.clientCommands.get("insertObject").executionForInsertObject(groupNameText, Float.parseFloat(coordinatesText.split(";")[0]), Integer.parseInt(coordinatesText.split(";")[1]),
                    Integer.parseInt(studentCountText), Long.parseLong(expelledStudentsText), Integer.parseInt(transferredStudentsText), adminNameText, Long.parseLong(heightText),
                    Integer.parseInt(locationText.split(";")[0]), Float.parseFloat(locationText.split(";")[1]), locationNameText, FormOfEducation.valueOf(formOfEducationText),
                    EyesColor.valueOf(eyeColorText), HairColor.valueOf(hairColorText), Country.valueOf(nationalityText), null);
            Client.clientRequester.sendRequest("remove_lower", null, null, studyGroup);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(insertEvent -> {
                String response = null;
                try {
                    response = (String) Client.clientHandler.receiveResponse();
                } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                    System.err.println("Collection FillCollectionObjectDialogStage error " + e.getMessage());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (Objects.equals(response, "1")){
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("The command remove_lower was successfully completed!");
                    alert.show();
                } else {
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("The command remove_lower wasn't successfully completed! =((((((");
                    alert.show();
                }
            });
            pause.play();
        }
    }

    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();

        // Validate adminName
        if (adminName.getText().isEmpty()) {
            errors.append("Admin Name cannot be null or empty.\n");
        }

        // Validate coordinates
        // Assuming coordinates are in format "x;y"
        try {
            String[] coords = coordinates.getText().split(";");
            double x = Double.parseDouble(coords[0]);
            int y = Integer.parseInt(coords[1]);
            if (x <= -339) {
                errors.append("Coordinate x must be greater than -339.\n");
            }
        } catch (Exception e) {
            errors.append("Coordinates must be in format 'x;y'.\n");
        }

        // Validate location
        // Assuming location are in format "x;y"
        try {
            String[] locate = locationXY.getText().split(";");
            int x = Integer.parseInt(locate[0]);
            float y = Float.parseFloat(locate[1]);
        } catch (Exception e) {
            errors.append("Location must be in format 'x;y'.\n");
        }

        // Validate locationName
        if (locationName.getText().isEmpty() || locationName.getText().length() > 979) {
            errors.append("Location Name cannot be null, empty, or longer than 979 characters.\n");
        }

        // Validate groupName
        if (groupName.getText() == null || groupName.getText().isEmpty()) {
            errors.append("Group Name cannot be null or empty.\n");
        }

        // Validate height
        try {
            long heightValue = Long.parseLong(height.getText());
            if (heightValue <= 0) {
                errors.append("Height must be greater than 0.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("Height must be a valid number.\n");
        }

        // Validate studentCount
        try {
            int studentCountValue = Integer.parseInt(studentCount.getText());
            if (studentCountValue <= 0) {
                errors.append("Student Count must be greater than 0.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("Student Count must be a valid number.\n");
        }

        // Validate expelledStudents
        try {
            long expelled = Long.parseLong(expelledStudents.getText());
            if (expelled <= 0) {
                errors.append("Expelled Students must be greater than 0.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("Expelled Students must be a valid number.\n");
        }

        // Validate transferredStudents
        try {
            int transferred = Integer.parseInt(transferredStudents.getText());
            if (transferred <= 0) {
                errors.append("Transferred Students must be greater than 0.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("Transferred Students must be a valid number.\n");
        }

        if (!errors.isEmpty()) {
            showAlert("Validation Errors", errors.toString());
            return false;
        }

        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        formOfEducation.getItems().addAll("DISTANCE_EDUCATION", "FULL_TIME_EDUCATION", "EVENING_CLASSES");
        eyeColor.getItems().addAll("RED", "BLACK", "BLUE", "WHITE");
        hairColor.getItems().addAll("RED", "BLACK", "BLUE", "BROWN");
        nationality.getItems().addAll("GERMANY", "SPAIN", "INDIA", "THAILAND");
    }
}
