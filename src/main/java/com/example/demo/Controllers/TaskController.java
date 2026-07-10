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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TaskController {



    // 1. Declarar los Labels del FXML
    @FXML private Label lblId;
    @FXML private Label lblName;
    @FXML private Label lblPriority;
    @FXML private Label lblDeadline;
    @FXML private Label lblStatus;
    @FXML private VBox panelDetalles;


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

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtPriority;
    @FXML private TextField txtDeadline;
    @FXML private ComboBox<String> cmbStatus; // Cambiado a ComboBox
    //@FXML private VBox panelDetalles;


    // private Connection con; // Tu objeto de conexión a la BD (Debe estar inicializado)
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    /**
     * El método initialize() se ejecuta automáticamente al cargar la vista FXML.
     */
    @FXML
    public void initialize() {

        cmbStatus.setItems(FXCollections.observableArrayList("In Progress", "Completed"));

        // 2. Vincular las columnas de la vista con los atributos de la clase Task
        // Los strings del PropertyValueFactory deben coincidir con los nombres de variables en tu clase Task
        TaskId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        speciality.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        Experience.setCellValueFactory(new PropertyValueFactory<>("priority"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        achivment.setCellValueFactory(new PropertyValueFactory<>("status"));

        // === AQUÍ SE CONECTA Y SE USA EL MÉTODO ===
        tasksTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mostrarDetallesTarea(newValue);
        });

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

    @FXML
    private void limpiarSeleccion() {
        if (usuario2.getTipo().equals("manager"))
        {
            System.out.println("manager");
            // 1. Quita la selección activa en la interfaz de la tabla
            tasksTable.getSelectionModel().clearSelection();
            // 2. Llama a tu método existente para que limpie los Labels de la izquierda
            mostrarDetallesTarea(null);
        }
        else
        {
            System.out.println("Not Manager");
        }

    }

    @FXML
    private void eliminarTarea() {

        if (usuario2.getTipo().equals("manager"))
        {
            System.out.println("manager");

            // Obtener el objeto seleccionado actualmente
            Task tareaSeleccionada = tasksTable.getSelectionModel().getSelectedItem();

            if (tareaSeleccionada == null) {
                System.out.println("Por favor, selecciona una tarea de la tabla para eliminar.");
                return;
            }

            // Consulta SQL para eliminar usando el ID
            String sql = "DELETE FROM tasks WHERE task_id = ?;";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, tareaSeleccionada.getTaskId());

                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Tarea eliminada con éxito de la base de datos.");

                    // Refrescar los datos de la interfaz volviendo a leer la BD
                    cargarDatosDesdeBD();

                    // Limpiar los campos de la izquierda
                    limpiarSeleccion();
                }
            } catch (SQLException e) {
                System.err.println("Error al intentar eliminar la tarea: " + e.getMessage());
                e.printStackTrace();
            }
          } else
        {
            System.out.println("Not Manager");

        }
    }

    private void mostrarDetallesTarea(Task task) {
        if (task != null) {
            txtId.setText(String.valueOf(task.getTaskId()));
            txtName.setText(task.getTaskName());
            txtPriority.setText(task.getPriority());
            txtDeadline.setText(task.getDeadline());
            cmbStatus.setValue(task.getStatus()); // Asigna el valor en el ComboBox

            // Cambio dinámico de color según prioridad (Tu lógica existente)
            String prioridad = task.getPriority() != null ? task.getPriority().toLowerCase() : "";
            if (prioridad.contains("low")) {
                panelDetalles.setStyle("-fx-background-color: #00FF00; -fx-background-radius: 8; -fx-padding: 15;");
            } else if (prioridad.contains("medium")) {
                panelDetalles.setStyle("-fx-background-color: #FFFFE0; -fx-background-radius: 8; -fx-padding: 15;");
            } else if (prioridad.contains("high")) {
                panelDetalles.setStyle("-fx-background-color: #FF7F7F; -fx-background-radius: 8; -fx-padding: 15;");
            } else {
                panelDetalles.setStyle("-fx-background-color: #880808; -fx-background-radius: 8; -fx-padding: 15;");
            }
        } else {
            // Limpiar los campos si no hay selección
            txtId.setText("");
            txtName.setText("");
            txtPriority.setText("");
            txtDeadline.setText("");
            cmbStatus.setValue(null);
            panelDetalles.setStyle("-fx-background-color: #F8FAFC; -fx-background-radius: 8; -fx-padding: 15;");
        }
    }
    @FXML
    private void actualizarTarea() {

        if (usuario2.getTipo().equals("manager")) {
            System.out.println("manager");
            // 1. Quita la selección activa en la interfaz de la tabla
            tasksTable.getSelectionModel().clearSelection();
            // 2. Llama a tu método existente para que limpie los Labels de la izquierda
            mostrarDetallesTarea(null);
            // Verificar que haya una tarea seleccionada editándose (validando el campo ID)
            if (txtId.getText().isEmpty()) {
                System.out.println("Selecciona una tarea de la tabla antes de intentar actualizar.");
                return;
            }

            // Consulta SQL para modificar los registros
            String sql = "UPDATE tasks SET task_name = ?, priority = ?, deadline = ?, status = ? WHERE task_id = ?;";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                // Capturar los nuevos textos modificados por el usuario en la izquierda
                pstmt.setString(1, txtName.getText());
                pstmt.setString(2, txtPriority.getText());
                pstmt.setString(3, txtDeadline.getText());
                pstmt.setString(4, cmbStatus.getValue()); // Toma el valor del ComboBox
                pstmt.setInt(5, Integer.parseInt(txtId.getText()));

                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("¡Tarea actualizada con éxito en la Base de Datos!");

                    // Recargar la tabla para mostrar los nuevos cambios y actualizar contadores
                    cargarDatosDesdeBD();

                    // Limpiar los campos
                    limpiarSeleccion();
                }
            } catch (SQLException e) {
                System.err.println("Error al intentar actualizar la tarea: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Not Manager");
        }
    }
}
