package com.example.demo.Controllers;

import com.example.demo.BaseData.Conexion;
import com.example.demo.BaseData.Task;
import com.example.demo.Personas.UserSession;
import com.example.demo.Personas.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditTaskController
{
    private Usuario usuario2 = UserSession.getInstance().getUsuario();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField taskIdField;

    @FXML
    private TextArea taskNameField;

    @FXML
    private ComboBox<String> priorityCombo;

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private ComboBox<String> statusCombo;

    private static Connection con = Conexion.getInstance().getConection();

    @FXML
    public void initialize() {
        priorityCombo.getItems().addAll(
                "Low",
                "Medium",
                "High",
                "Critical"
        );

        statusCombo.getItems().addAll(
                "Pending",
                "In Progress",
                "Completed",
                "Cancelled"
        );
    }

    public void handleUpdate(ActionEvent actionEvent)
    {
        if (usuario2.getTipo().equals("manager")) {
            System.out.println("manager");


            //edti
            String a = taskNameField.getText();
            String b = priorityCombo.getValue();
            String time = deadlinePicker.getValue().toString();
            String c = statusCombo.getValue();

            System.out.println("Edit worker table" + a + b + time + c);
           // Task task = new Task(1, a, b, deadlinePicker.getValue(), c);

            try {
                // 1. Preparar la sentencia SQL con los marcadores de posición (?)
                String sql = "INSERT INTO tasks (task_name, priority, deadline, status) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = con.prepareStatement(sql);

                // 2. Asignar los valores a cada parámetro (el índice empieza en 1)
                statement.setString(1, a);
                statement.setString(2, b);
                statement.setString(3, time);
                statement.setString(4, c);

                // 3. Ejecutar la inserción en la base de datos
                // int filasInsertadas = statement.executeUpdate();


                int filas = statement.executeUpdate();
                if (filas > 0) {
                    System.out.println("Agreagado");
                }
            } catch (SQLException e) {
                System.out.println("error");
                throw new RuntimeException(e);

            }
        }
        else
        {
            System.out.println("Not Manager");

        }

    }

    @FXML
    public void switchToView5(ActionEvent event) throws IOException {
        // Load the FXML file for view 2

        //FXMLLoader loader = new FXMLLoader(
        //        getClass().getResource("workersProfile.fxml"));
        // Parent root = loader.load();
        //fet datos from database//


        root = FXMLLoader.load(getClass().getResource("/workersProfile.fxml"));
        // Get the current Stage from the button click event
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Update the existing scene with the new root layout
        stage.getScene().getWindow().setHeight(850);
        stage.getScene().getWindow().setWidth(1200);
        stage.centerOnScreen();
        stage.getScene().setRoot(root);
    }


    public void backToWorkProfile(ActionEvent actionEvent) throws IOException {
        switchToView5(actionEvent);
        System.out.println("Go back workProfile");
    }
}
