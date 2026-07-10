package com.example.demo.Controllers;

import com.example.demo.BaseData.Conexion;
import com.example.demo.BaseData.Task;
import com.example.demo.Personas.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RegLoginAuthController extends Usuario
{


    public PasswordField passwordField;
    public TextField loginUsernameField;
    public PasswordField loginPasswordField;
    public TextField usernameField123;
    public TextField regUsername;
    public TextField regEmail;
    public PasswordField regPassword;
    public Label regLable;
    public Button regButton;
    public Label logLable;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Usuario usuario;
    private static Connection con = Conexion.getInstance().getConection();

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
        stage.getScene().getWindow().setHeight(850);
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

    // Nota: Importa la clase BossController desde su paquete original



    public  <T> Usuario login(ActionEvent actionEvent)
    {
       //usuario = null;
       String name = loginUsernameField.getText();
       String password2 = loginPasswordField.getText();
         try {
             PreparedStatement preparedStatement =  con.prepareStatement("SELECT * FROM usuario WHERE name=?");
                preparedStatement.setString(1,name);
               ResultSet rs = preparedStatement.executeQuery();
               if (rs.next())
               {
                  int id = rs.getInt("usuario_id");
                 String name2 = rs.getString("name");
                 String password = rs.getString("password");
                 String tipo = rs.getString("tipo_usuario");
                   if (Objects.equals(password2, password) && Objects.equals(name2, name))
                  {
                     switch (tipo.toLowerCase())
                      {
                          case "boss":
                              usuario = (Usuario) new Boss(id,name2,"",tipo,password);
                              switchToView3(actionEvent);
                              System.out.println("Success 2 !!! Boss ---- " + usuario.getName());
                             break;
                          case "manager":
                              usuario = (Usuario) new Manager2Usario(id,name,"",tipo,password);
                              switchToView11(actionEvent);
                              System.out.println("Success 3 !!! Manager   ---   "+usuario.getName());
                              break;
                          case "worker":
                              usuario = (Usuario) new Worker(id,name,"",tipo,password);
                              switchToView10(actionEvent);
                              System.out.println("Success 4 !!! Worker"+usuario.getName());
                              break;
                          default:
                              System.out.println("Tipo desconocido");break;
                      }
                  }
                   else
                   {
                       logLable.setText("Wrong input");
                       System.out.println("Wrong input datos");
                   }
              }

               else
               {
                   logLable.setText("Wrong input2");
                   System.out.println("Wrong input datos2");
               }

          }
          catch (SQLException | IOException e) {
              throw new RuntimeException(e);
          }
        //System.out.println(" usuario id "+usuario.getId());
        // 1. Tu código actual donde creas al usuario (ejemplo)
       // Usuario nuevoUsuario = new Usuario(15, "q", "q", "q", "boss");

        // 2. GUARDAR EN LA SESIÓN GLOBAL
        UserSession.getInstance().setUsuario(usuario);

        // 3. Abrir la siguiente ventana normalmente (ya no necesitas usar loader.getController)
        System.out.println("Usuario guardado en la sesión global correctament");
        return usuario;
   }




    public void handleReg(ActionEvent actionEvent)
    {
        //edti
        String a = regUsername.getText();
        String b = regEmail.getText();
        String c = regPassword.getText();
        String d = "worker";



        System.out.println("Reg user table"+a+b+c);
        Task task = new Task(1,a,b,"deadline",c);

        try {
            // 1. Añadida la columna 'tipo_usuario' y los 4 signos de interrogación necesarios
            String sql = "INSERT INTO usuario (name, email, password, tipo_usuario) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);

// 2. Asignar los valores a cada parámetro (los índices coinciden con los 4 '?')
            statement.setString(1, a);             // Se mapea a 'name'
            statement.setString(2, b);             // Se mapea a 'email'
            statement.setString(3, c);             // Se mapea a 'password'
            statement.setString(4, d);      // Se mapea a 'tipo_usuario'

// 3. Ejecutar la inserción
          //  int filasInsertadas = statement.executeUpdate();


            // 3. Ejecutar la inserción en la base de datos
            // int filasInsertadas = statement.executeUpdate();


            int filas = statement.executeUpdate();
            if (filas > 0){
                System.out.println("Agregado");
                regLable.setText("REGISTRADO");
                regButton.setDisable(true);
               // 1. Create a 3-second delay
                PauseTransition delay = new PauseTransition(Duration.seconds(3));

               // 2. Move the scene switch inside the finished event
                delay.setOnFinished(event -> {
                    try {
                        switchToView2(actionEvent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                // 3. Start the timer (code below this will still run immediately, but the switch waits)
                delay.play();

            }
        } catch (SQLException e) {
            System.out.println("error");
            regLable.setText("ERRORO IN REGISTRATION");
            throw new RuntimeException(e);

        }

    }
}
