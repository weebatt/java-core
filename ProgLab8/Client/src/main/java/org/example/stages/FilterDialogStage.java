package org.example.stages;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FilterDialogStage {
    @FXML
    private TextField usernameFilter;
    @FXML
    private TextField studentsCountMinFilter;
    @FXML
    private TextField studentsCountMaxFilter;
    @FXML
    private TextField groupNameFilter;

    private TableStage tableStage;

    public void setTableStage(TableStage tableStage) {
        this.tableStage = tableStage;
    }

    @FXML
    public void applyFilters() {
        tableStage.applyFilters(
                usernameFilter.getText(),
                studentsCountMinFilter.getText(),
                studentsCountMaxFilter.getText(),
                groupNameFilter.getText()
        );
        Stage stage = (Stage) usernameFilter.getScene().getWindow();
        stage.close();
    }
}
