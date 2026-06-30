package com.example.demo.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BossController {
    public Button workersCardButton;
    public Button backButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToView4(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/myProfile.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(800);
        stage.getScene().getWindow().setWidth(1200);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }


    @FXML
    public void switchToView5(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/workersProfile.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(800);
        stage.getScene().getWindow().setWidth(1200);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }

    @FXML
    public void switchToView6(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/landing.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(800);
        stage.getScene().getWindow().setWidth(1200);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }

    @FXML
    public void switchToView7(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/managersProfile.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(800);
        stage.getScene().getWindow().setWidth(1200);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }

    @FXML
    public void switchToView8(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(300);
        stage.getScene().getWindow().setWidth(400);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }

    public Button myComplexButton;

    public void handleRegistration(ActionEvent actionEvent)

    {

    }

    public void goToManagersTable(ActionEvent actionEvent) throws IOException {
        switchToView7(actionEvent);
        System.out.println("Success managers table");
    }

    public void goToMyProfileTable(ActionEvent actionEvent) throws IOException {
        switchToView4(actionEvent);
        System.out.println("my profile");
    }

    public void goToWorkersTable(ActionEvent actionEvent) throws IOException {
        switchToView5(actionEvent);
        System.out.println("Workers table");
    }

    public void goBackToLandingPage(ActionEvent actionEvent) throws IOException {
        switchToView6(actionEvent);
        System.out.println("Success managers table");
    }

    public void logout(ActionEvent actionEvent) throws IOException {
         switchToView8(actionEvent);
    }
}
