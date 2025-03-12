package org.example.stages;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.Client;
import org.example.exceptions.ServerUnavailableException;
import org.example.models.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableStage implements Initializable {
    public Map<Long, Object> groupResponse = new LinkedHashMap<>();
    private FilteredList<Object> filteredData;

    @FXML
    private TableColumn<StudyGroup, String> adminName;
    @FXML
    private TableColumn<StudyGroup, Float> coordinates;
    @FXML
    private TableColumn<StudyGroup, LocalDate> creationDate;
    @FXML
    private TableColumn<StudyGroup, Long> expelledStudents;
    @FXML
    private TableColumn<StudyGroup, EyesColor> eyeColor;
    @FXML
    private TableColumn<StudyGroup, FormOfEducation> formOfEducation;
    @FXML
    private TableColumn<StudyGroup, Long> groupId;
    @FXML
    private TableColumn<StudyGroup, String> groupName;
    @FXML
    private TableColumn<StudyGroup, HairColor> hairColor;
    @FXML
    private TableColumn<StudyGroup, Long> height;
    @FXML
    private TableColumn<StudyGroup, Float> locationXY;
    @FXML
    private TableColumn<StudyGroup, String> locationName;
    @FXML
    private TableColumn<StudyGroup, Country> nationality;
    @FXML
    private TableColumn<StudyGroup, Integer> studentsCount;
    @FXML
    private TableColumn<StudyGroup, Integer> transferredStudents;
    @FXML
    private TableColumn<StudyGroup, String> userName;
    @FXML
    private TableView<Object> table;
    @FXML
    private Button removeButton;
    @FXML
    private TextField numberOfKey;
    @FXML
    private TextField numberOfGreaterKey;

    @FXML
    void backToCommands(MouseEvent event) throws IOException {
        Client.setRoot("commands");
    }

    @FXML
    void callDialogPane(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/filter.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(table.getScene().getWindow());

            FilterDialogStage controller = loader.getController();
            controller.setTableStage(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void callRemoveGreater(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fillCollectionObject.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(table.getScene().getWindow());

            FillCollectionObjectDialogStage controller = loader.getController();
            controller.setTableStage(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void callRemoveLower(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fillCollectionObject.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(table.getScene().getWindow());

            FillCollectionObjectDialogStage controller = loader.getController();
            controller.setTableStage(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeGreaterKey(MouseEvent mouseEvent) throws ServerUnavailableException, IOException, ClassNotFoundException {
        Client.clientRequester.sendRequest("remove_greater_key " + numberOfGreaterKey.getText(), null, null, null);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            String response = null;
            try {
                response = (String) Client.clientHandler.receiveResponse();
            } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (response.equals("1")){
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText(String.format("The command remove_greater_key %s was successfully completed", numberOfGreaterKey.getText()));
                alert.show();
            } else {
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText(String.format("The command remove_greater_key %s wasn't successfully completed =((((", numberOfGreaterKey.getText()));
                alert.show();
            }
        });
        pause.play();
    }

    @FXML
    void removeKey(MouseEvent mouseEvent) throws ServerUnavailableException, IOException, ClassNotFoundException {
        Client.clientRequester.sendRequest("remove_key " + numberOfKey.getText(), null, null, null);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            String response = null;
            try {
                response = (String) Client.clientHandler.receiveResponse();
            } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (response.equals("1")){
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText(String.format("The command remove_key %s was successfully completed", numberOfGreaterKey.getText()));
                alert.show();
            } else {
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText(String.format("The command remove_key %s wasn't successfully completed =((((", numberOfGreaterKey.getText()));
                alert.show();
            }
        });
        pause.play();
    }

    @FXML
    void clear(MouseEvent mouseEvent) throws ServerUnavailableException, IOException, ClassNotFoundException {
        Client.clientRequester.sendRequest("clear", null, null, null);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            String response = null;
            try {
                response = (String) Client.clientHandler.receiveResponse();
            } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                System.err.println("Clear " + e.getMessage());
                Client.connecting();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (Objects.equals(response, "1")){
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("The collection was successfully cleared!");
                alert.show();
            } else {
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("You need to sign up or log in!");
                alert.show();
            }
        });
        pause.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Client.clientRequester.sendRequest("show", null, null, null);
        } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
            System.err.println("Error processing sending command show in TableStage" + e.getMessage());
        }
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                groupResponse = (Map<Long, Object>) Client.clientHandler.receiveResponse();
                initializeTableColumns();

                ObservableList<Object> studyGroupsList = FXCollections.observableArrayList(groupResponse.values());
                filteredData = new FilteredList<>(studyGroupsList, p -> true);

                SortedList<Object> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());

                table.setItems(sortedData);
                removeButton.setOnAction(removeButtonEvent -> deleteSelectedRow(studyGroupsList));
            } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                System.err.println("Receiving response from show command in TableStage " + e.getMessage());
            }
        });
        pause.play();
    }

    private void initializeTableColumns() {
        groupId.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        groupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        coordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        studentsCount.setCellValueFactory(new PropertyValueFactory<>("studentsCount"));
        expelledStudents.setCellValueFactory(new PropertyValueFactory<>("expelledStudents"));
        transferredStudents.setCellValueFactory(new PropertyValueFactory<>("transferredStudents"));
        formOfEducation.setCellValueFactory(new PropertyValueFactory<>("formOfEducation"));
        adminName.setCellValueFactory(new PropertyValueFactory<>("adminName"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        eyeColor.setCellValueFactory(new PropertyValueFactory<>("eyeColor"));
        hairColor.setCellValueFactory(new PropertyValueFactory<>("hairColor"));
        nationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        locationName.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        locationXY.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    public void applyFilters(String username, String studentsCountMin, String studentsCountMax, String groupName) {
        filteredData.setPredicate(studyGroup -> filter(studyGroup, username, studentsCountMin, studentsCountMax, groupName));
    }

    private boolean filter(Object studyGroup, String username, String studentsCountMin, String studentsCountMax, String groupName) {
        StudyGroup group = (StudyGroup) studyGroup;

        boolean usernameMatches = username == null || username.isEmpty() || group.getUserName().toLowerCase().contains(username.toLowerCase());
        boolean groupNameMatches = groupName == null || groupName.isEmpty() || group.getGroupName().toLowerCase().contains(groupName.toLowerCase());
        boolean studentsCountMatches = true;

        try {
            int minCount = studentsCountMin.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(studentsCountMin);
            int maxCount = studentsCountMax.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(studentsCountMax);
            studentsCountMatches = group.getStudentsCount() >= minCount && group.getStudentsCount() <= maxCount;
        } catch (NumberFormatException e) {
            studentsCountMatches = false;
        }

        return usernameMatches && groupNameMatches && studentsCountMatches;
    }

    private void deleteSelectedRow(ObservableList studyGroupsList) {
        StudyGroup selectedItem = (StudyGroup) table.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            if (!selectedItem.getUserName().equals(Client.clientRequester.getUserName())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("You can't delete objects of other users!");
                alert.show();
            } else {
                studyGroupsList.remove(selectedItem);
                try {
                    Client.clientRequester.sendRequest("remove_key " + selectedItem.getGroupId(), null, null, null);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> {
                        try {
                            Client.clientHandler.receiveResponse();
                        } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle(null);
                            alert.setHeaderText(null);
                            alert.setContentText("Receive error " + e.getMessage());
                            alert.show();
                        }
                    });
                    pause.play();
                } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                    System.out.println("Send error " + e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("You don't choose any object!");
            alert.show();
        }
    }
}
