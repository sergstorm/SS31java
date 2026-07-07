package com.example.demo.Controllers;

import com.example.demo.BaseData.Conexion;
import com.example.demo.Personas.Boss;
import com.example.demo.Personas.Manager;
import com.example.demo.Personas.Usuario;
import com.example.demo.Personas.Worker;
import com.example.demo.Repo.Hashing;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static javafx.scene.input.KeyCode.T;

public class RegLoginAuthController
{


    public PasswordField passwordField;
    public TextField loginUsernameField;
    public PasswordField loginPasswordField;
    public TextField usernameField123;
    public TextField regUsername;
    public TextField regEmail;
    public PasswordField regPassword;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Usuario usuario;
   // private static Connection con = Conexion.getInstance().getConnection();

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



    @FXML
    public void switchToView10(ActionEvent event) throws IOException {
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

//    public <T> Usuario login()
//    {
//        usuario = null;
//        String name = loginUsernameField.getText();
//        String password2 = loginPasswordField.getText();
//          try {
//                PreparedStatement preparedStatement =  con.prepareStatement("SELECT * FROM usuario WHERE name=?");
//                preparedStatement.setString(1,name);
//                ResultSet rs = preparedStatement.executeQuery();
//                if (rs.next())
//                {
//                  int id = rs.getInt("id");
//                  String nombre = rs.getString("nombre");
//                  String password = rs.getString("password");
//                  String tipo = rs.getString("tipo");
//                    if (Hashing.verificar(password2,password))
//                   {
//                      switch (tipo.toLowerCase())
//                      {
//                          case "BOSS": usuario = (Usuario) new Boss(id,nombre,"",tipo,password);break;
//                          case "MANAGER": usuario = (Usuario) new Manager(id,nombre,"",tipo,password);break;
//                          case "TRABAJADOR": usuario = (Usuario) new Worker(id,nombre,"",tipo,password);break;
//                          default:
//                              System.out.println("Tipo desconocido");break;
 //                     }
 //                 }
 //             }
 //         }
 //         catch (SQLException e) {
 //             throw new RuntimeException(e);
 //         }
 //       return usuario;
 //   }

    public void handleLogin(ActionEvent actionEvent) throws IOException {
        //System.out.println("HandleLogin Tipo "+login().getTipo());

           String login = loginUsernameField.getText();
           String password  = loginPasswordField.getText();



        if (Objects.equals(login, "1") && Objects.equals(password, "1"))
        {
            Usuario boos = new Boss(1,"BBB","","boss","");
            switchToView3(actionEvent);
            System.out.println("Success 2 !!! Boss ---- " + boos.getName());
        }
        else if (Objects.equals(login, "2") && Objects.equals(password, "2"))
        {
            Usuario manager = new Manager(2,"MMM","","manager","");
            switchToView11(actionEvent);
            System.out.println("Success 3 !!! Manager   ---   "+manager.getName());
        }
        else if (Objects.equals(login, "3") && Objects.equals(password, "3"))
        {
            Usuario worker = new Worker(3,"WWW","","worker","");
            switchToView10(actionEvent);
            System.out.println("Success 4 !!! Worker"+worker.getName());
        }
        else
            System.out.println("Exeption");
    }

    public void handleRegistration(ActionEvent actionEvent) throws IOException {

        String userName = regUsername.getText();
        String email = regEmail.getText();
        String passwd = regPassword.getText();



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
