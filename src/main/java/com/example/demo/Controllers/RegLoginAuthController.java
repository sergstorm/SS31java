package com.example.demo.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegLoginAuthController
{

    public TextField usernameField;
    public TextField emailField;
    public PasswordField passwordField;
    public TextField loginUsernameField;
    public PasswordField loginPasswordField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToView1(ActionEvent event) throws IOException {
        // Load the FXML file for view 1
        root = FXMLLoader.load(getClass().getResource("/reg.fxml"));
        //root = FXMLLoader.load(getClass().getResource("com/example/demo1/reg.fxml"));
       // root = FXMLLoader.load(getClass().getResource("/com/example/demo/reg.fxml"));

        // Option A: If view2.fxml is in src/main/resources/com/example/demo/

        // Use a forward slash relative to your resources folder, NOT a C:\ drive path
        URL fxmlLocation = getClass().getResource("/reg.fxml");

        System.out.println(fxmlLocation + "  lockjdsb  ");

        if (fxmlLocation == null) {
            throw new RuntimeException("Error: Could not find reg.fxml in the target folder!");
        }

        Parent root = FXMLLoader.load(fxmlLocation);

// Option B: If view2.fxml is in the exact same folder structure as the controller
       // URL fxmlLocation = getClass().getResource("view2.fxml");
       // Parent root = FXMLLoader.load(fxmlLocation);


        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().setRoot(root);
    }

    @FXML
    public void switchToView2(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
       // root = FXMLLoader.load(getClass().getResource("login.fxml"));
        // Get the current Stage from the button click event

        URL fxmlLocation = getClass().getResource("/login.fxml");
        System.out.println(fxmlLocation+"  lockjdsb  ");
        Parent root = FXMLLoader.load(fxmlLocation);


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().setRoot(root);
    }

    @FXML
    public void switchToView3(ActionEvent event) throws IOException {
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


    public void handleLogin(ActionEvent actionEvent) throws IOException {
        System.out.println("HandleLogin");
        if (true)
        {
            switchToView3(actionEvent);
            System.out.println("Success 2 !!!");
        }
        else
            System.out.println("Exeption");
    }

    public void handleRegistration(ActionEvent actionEvent) throws IOException {
        System.out.println("Hi ");
        if (true)
        {
            switchToView2(actionEvent);
            System.out.println("Success");
        }
        else
            System.out.println("Exeption");
    }
}
