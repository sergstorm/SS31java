package com.example.demo.Controllers;


import com.example.demo.BaseData.Conexion;
import com.example.demo.BaseData.Task; // Importa tu modelo
import com.example.demo.Personas.UserSession;
import com.example.demo.Personas.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TaskController {

    public Label tat;
    public Label ipt;
    public Label ct;
    public Button backButton2;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Connection con = Conexion.getInstance().getConection();
    private Usuario usuario2 = UserSession.getInstance().getUsuario();

    // 1. Declarar los componentes del FXML (Deben coincidir exactamente con los fx:id)
    @FXML private TableView<Task> tasksTable;
    @FXML private TableColumn<Task, Integer> TaskId;
    @FXML private TableColumn<Task, String> speciality; // Muestra taskName
    @FXML private TableColumn<Task, String> Experience; // Muestra priority
    @FXML private TableColumn<Task, String> categoria;  // Muestra deadline
    @FXML private TableColumn<Task, String> achivment;  // Muestra status

   // private Connection con; // Tu objeto de conexión a la BD (Debe estar inicializado)
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    /**
     * El método initialize() se ejecuta automáticamente al cargar la vista FXML.
     */
    @FXML
    public void initialize() {
        // 2. Vincular las columnas de la vista con los atributos de la clase Task
        // Los strings del PropertyValueFactory deben coincidir con los nombres de variables en tu clase Task
        TaskId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        speciality.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        Experience.setCellValueFactory(new PropertyValueFactory<>("priority"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        achivment.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 3. Cargar los datos desde la base de datos
        cargarDatosDesdeBD();
    }

    private void cargarDatosDesdeBD() {
        taskList.clear(); // Limpiar la lista antes de volver a llenarla

        // Asegurar que la columna de ID se incluya en el SELECT
        String sql = "SELECT task_id, task_name, priority, deadline, status FROM tasks;";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Recorrer las filas que arrojó la base de datos
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("priority"),
                        rs.getString("deadline"),
                        rs.getString("status")
                );
                // Agregar el objeto mapeado a la lista observable
                taskList.add(task);
            }

            // 4. Asignar la lista completa al TableView
            tasksTable.setItems(taskList);
            int a = taskList.size();
            tat.setText(String.valueOf(a));
            // Suponiendo que 'taskList' es tu ObservableList<Task> con las tareas de la base de datos
            long tareasCompletadas = taskList.stream()
                    .filter(task -> "Completed".equalsIgnoreCase(task.getStatus()))
                    .count();

            System.out.println("Número de tareas completadas: " + tareasCompletadas);

            ct.setText(String.valueOf(tareasCompletadas));
            System.out.println("Se cargaron exitosamente " + taskList.size() + " tareas en la tabla.");

            long tareasEnProgreso = taskList.stream()
                    .filter(task -> "In Progress".equalsIgnoreCase(task.getStatus()))
                    .count();

            System.out.println("Número de tareas en progreso: " + tareasEnProgreso);
            ipt.setText(String.valueOf(tareasEnProgreso));

        } catch (SQLException e) {
            System.err.println("Error al intentar leer la tabla 'tasks': " + e.getMessage());
            e.printStackTrace();
        }
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

    public void editWorkesTable(ActionEvent actionEvent) throws IOException {
        if (usuario2.getTipo().equals("manager"))
        {
            System.out.println("manager");
            switchToViewEditWorkersTable(actionEvent);
            System.out.println("To editWorkers table");
        }
        else
        {
            System.out.println("Not Manager");

        }

    }

    public void logout(ActionEvent actionEvent) throws IOException {
        switchToView8(actionEvent);
        System.out.println("logout");
    }

    public void goBackToLandingPage(ActionEvent actionEvent) throws IOException {
        if (usuario2.getTipo().equals("manager"))
        {
            System.out.println("manager");
            switchToView11(actionEvent);
            System.out.println("Go back to landingManager");
        }
        else
        {
            System.out.println("Not Manager");

        }

    }
}
