package com.example.demo.Controllers;

import com.example.demo.BaseData.Task;
import com.example.demo.Personas.Boss;
import com.example.demo.Personas.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BossController {
    public Button workersCardButton;
    public Button backButton;
    public Label bossEmail;
    public TextField nameField;
    public TextField emailField;
    public TextField phoneField;
    public DatePicker dobPicker;
    public TextArea bioArea;
    public Button backToLanding2;
    public Button editTableButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Usuario usuario;

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

        //FXMLLoader loader = new FXMLLoader(
        //        getClass().getResource("workersProfile.fxml"));
       // Parent root = loader.load();

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
        System.out.println("Succes 7 managersTable");
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

    @FXML
    public void switchToView12(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/bossProfile.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(600);
        stage.getScene().getWindow().setWidth(800);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }

    @FXML
    public void switchToView9(ActionEvent event) throws IOException {
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
    public void switchToView11(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/landingManager.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(800);
        stage.getScene().getWindow().setWidth(1200);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }

    @FXML
    public void switchToViewEditWorkersTable(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        root = FXMLLoader.load(getClass().getResource("/editWorkerTable.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(800);
        stage.getScene().getWindow().setWidth(1200);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }


    public Button myComplexButton;

    public void handleRegistration(ActionEvent actionEvent)

    {

    }

    public void goToManagersTable(ActionEvent actionEvent) throws IOException {
        switchToView9(actionEvent);
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
        String a = "1";
        String b = "2";
        String c = "3";
        if (a.equals("1"))
         switchToView8(actionEvent);
        else if (b.equals("2")) {
            switchToView6(actionEvent);
        }
        else if (c.equals("3")) {
            switchToView6(actionEvent);
        }
    }
    public void logout2(ActionEvent actionEvent) throws IOException {
        String a = "1";
        String b = "2";
        String c = "3";
        if (a.equals("1"))
            switchToView8(actionEvent);
        else if (b.equals("2")) {
            switchToView6(actionEvent);
        }
        else if (c.equals("3")) {
            switchToView6(actionEvent);
        }
    }
    public void logout3(ActionEvent actionEvent) throws IOException {
        String a = "1";
        String b = "2";
        String c = "3";
        if (a.equals("1"))
            switchToView8(actionEvent);
        else if (b.equals("2")) {
            switchToView6(actionEvent);
        }
        else if (c.equals("3")) {
            switchToView6(actionEvent);
        }
    }

    public void toMain(ActionEvent actionEvent) throws IOException {
        switchToView6(actionEvent);
        System.out.println("Go to main");
    }

    public void logoutFromBossProfile(ActionEvent actionEvent) throws IOException {
        switchToView8(actionEvent);
    }

    public void goBackToMain(ActionEvent actionEvent) throws IOException {
        switchToView6(actionEvent);
    }

    public void editBossProfile(ActionEvent actionEvent) throws IOException {

        switchToView12(actionEvent);
        System.out.println("Go to change boss profile");
    }

    public void handleChangePhoto(ActionEvent actionEvent)
    {

    }

    public void handleSave(ActionEvent actionEvent) throws IOException {
        String BossNEwNAme = nameField.getText();
        String BossNewEmail = emailField.getText();
        String BossNewPhone = phoneField.getText();
        String NewBossDate = dobPicker.getAccessibleText();
        String BossBioArea = bioArea.getText();
        System.out.println(BossNEwNAme+BossNewEmail+BossNewPhone+NewBossDate+BossBioArea+" ----Boss Data");
        Boss boss = new Boss(2,BossNEwNAme,BossNewEmail,"Boss","newPAssword");
        //Save new Boss in data Base///
        switchToView6(actionEvent);

    }

    public void goBackToLandingPageManagers(ActionEvent actionEvent) throws IOException {
        System.out.println("goBackToLandingPageManagers");

        //String tipo = usuario.getTipo();
       // if (Objects.equals(tipo, "boss"))
        //     switchToView6(actionEvent);
        //else if (Objects.equals(tipo, "manager")) {
            switchToView11(actionEvent);
       // }
    }

    public void editWorkesTable(ActionEvent actionEvent) throws IOException {
        switchToViewEditWorkersTable(actionEvent);
    }


}
