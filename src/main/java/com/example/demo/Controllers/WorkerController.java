package com.example.demo.Controllers;

import com.example.demo.BaseData.Conexion;
import com.example.demo.BaseData.Inscripcion;
import com.example.demo.BaseData.Task;
import com.example.demo.Personas.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.postgresql.util.PSQLException;

// Importa tu clase de conexión si está en otro paquete, por ejemplo:
// import com.example.demo.Util.Conexion;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class WorkerController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Componentes FXML de la Tabla Workers
    @FXML private TableView<Worker> tableWorkers;
    @FXML private TableColumn<Worker, Integer> colWorkerId;
    @FXML private TableColumn<Worker, String> colWorkerName;
    @FXML private TableColumn<Worker, String> colWorkerEmail;

    // Componentes FXML de la Tabla Tasks
    @FXML private TableView<Task> tableTasks;
    @FXML private TableColumn<Task, Integer> colTaskId;
    @FXML private TableColumn<Task, String> colTaskName;
    @FXML private TableColumn<Task, String> colTaskPriority;
    @FXML private TableColumn<Task, String> colTaskStatus;

    @FXML private TableView<Inscripcion> tableInscripciones;
    @FXML private TableColumn<Inscripcion, Integer> colInscripcionId;
    @FXML private TableColumn<Inscripcion, String> colInscripcionWorker;
    @FXML private TableColumn<Inscripcion, String> colInscripcionTask;
    @FXML private TableColumn<Inscripcion, LocalDate> colInscripcionDate;

    private final ObservableList<Inscripcion> inscripcionList = FXCollections.observableArrayList();

    // Listas observables que contienen los datos reales
    private final ObservableList<Worker> workerList = FXCollections.observableArrayList();
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    // Reutilizamos tu conexión estática existente
    private static Connection con = Conexion.getInstance().getConection();

    @FXML
    public void initialize() {
        // 1. Configurar las columnas de Workers
        colWorkerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colWorkerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colWorkerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // 2. Configurar las columnas de Tasks
        colTaskId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTaskName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTaskPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        colTaskStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        // 3. table
        colInscripcionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colInscripcionWorker.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        colInscripcionTask.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        colInscripcionDate.setCellValueFactory(new PropertyValueFactory<>("fechaInscripcion"));

        // Vincular la lista observable a la tabl

        // 3. Vincular las listas a las tablas correspondientes
        tableWorkers.setItems(workerList);
        tableTasks.setItems(taskList);
        tableInscripciones.setItems(inscripcionList);

        // 4. Cargar datos desde la Base de Datos usando la conexión existente
        cargarDatosDesdeBD();

        // Cargar los datos desde la BD (este método se actualiza en el paso 3)
       // loadDataFromDatabase();
    }


    /**
     * Consulta la Base de Datos utilizando la conexión global 'con'
     */
    public void cargarDatosDesdeBD() {
        if (con == null) {
            System.err.println("Error: La conexión a la base de datos no está activa.");
            return;
        }

        workerList.clear();
        taskList.clear();

        inscripcionList.clear(); // Limpiar la lista de inscripciones

       // String queryWorkers = "SELECT usuario_id, name, email FROM usuario WHERE tipo_usuario = 'worker'";
       // String queryTasks = "SELECT task_id, task_name, priority, deadline, status FROM task";

        // Consu

        String queryWorkers = "SELECT usuario_id, name, email, tipo_usuario FROM usuario WHERE tipo_usuario = 'worker';";

        // 1. Volvemos a añadir 'description' a la consulta SQL
        //String queryTasks = "SELECT task_id, task_name, priority, status, description, deadline FROM tasks;";
        // Cambia tu consulta actual para que incluya la columna:
        String queryTasks = "SELECT task_id, task_name, priority, status, description, deadline FROM tasks;";

        String queryInscripciones = "SELECT i.inscripcion_id, u.name AS worker_name, t.task_name, i.fecha_inscripcion " +
                "FROM inscripcion i " +
                "INNER JOIN usuario u ON i.usuario_id = u.usuario_id " +
                "INNER JOIN tasks t ON i.task_id = t.task_id";




        try {
            // --- CARGAR WORKERS ---
            try (PreparedStatement stmt = con.prepareStatement(queryWorkers);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    workerList.add(new Worker(
                            rs.getInt("usuario_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("tipo_usuario"),
                            ""
                    ));
                }
            }

            // --- CARGAR TASKS ---
            try (PreparedStatement stmt = con.prepareStatement(queryTasks);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    java.sql.Date dbDate = rs.getDate("deadline");
                    LocalDate deadline = (dbDate != null) ? dbDate.toLocalDate() : null;

                    // 2. Leemos el valor real de la columna 'description' desde el ResultSet
                    String desc = rs.getString("description");
                    if (desc == null) desc = ""; // Evitamos problemas si la descripción está vacía en la BD

                    // CORRECCIÓN: Pasamos 'deadline' directamente como objeto LocalDate (sin .toString())
                    taskList.add(new Task(
                            rs.getInt("task_id"),
                            rs.getString("task_name"),
                            rs.getString("priority"),
                            rs.getString("status"),
                            desc,
                            deadline
                    ));
                }
            }

            //cargar inscripciones
            Statement stmt = con.createStatement();
            try (ResultSet rs = stmt.executeQuery(queryInscripciones)) {
                while (rs.next()) {
                    LocalDate date = null;
                    if (rs.getDate("fecha_inscripcion") != null) {
                        date = rs.getDate("fecha_inscripcion").toLocalDate();
                    }

                    inscripcionList.add(new Inscripcion(
                            rs.getInt("inscripcion_id"),
                            rs.getString("worker_name"),
                            rs.getString("task_name"),
                            date
                    ));
                }
            }


            System.out.println("¡Tablas sincronizadas con la Base de Datos con éxito!");

        } catch (SQLException e) {
            System.err.println("Error en consultas de WorkerController: " + e.getMessage());
            e.printStackTrace();
        }
    }




// ... (El resto de tus atributos de clase se mantienen igual)

    //import org.postgresql.util.PSQLException; // Asegúrate de importar esto

    @FXML
    public void handleSubscribeWorker(ActionEvent actionEvent) {
        System.out.println("Handle Subscribe Activado");

        Worker selectedWorker = tableWorkers.getSelectionModel().getSelectedItem();
        Task selectedTask = tableTasks.getSelectionModel().getSelectedItem();

        if (selectedWorker == null || selectedTask == null) {
            showErrorAlert("Selección incompleta", "Debes seleccionar un trabajador y una tarea de las tablas.");
            return;
        }

        String sql = "INSERT INTO inscripcion (usuario_id, task_id, fecha_inscripcion) VALUES (?, ?, ?)";

        try (
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, selectedWorker.getId());
            pstmt.setInt(2, selectedTask.getId());
            pstmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                showInfoAlert("Inscripción Exitosa",
                        "El trabajador " + selectedWorker.getName() + " ha sido asignado a la tarea: " + selectedTask.getName());

                tableWorkers.getSelectionModel().clearSelection();
                tableTasks.getSelectionModel().clearSelection();
                cargarDatosDesdeBD();
            }

        } catch (PSQLException e) {
            // "23505" es el código estándar de PostgreSQL para errores de clave duplicada (Unique Violation)
            if ("23505".equals(e.getSQLState())) {
                showWarningAlert("Registro Duplicado",
                        "El trabajador '" + selectedWorker.getName() + "' ya se encuentra inscrito en la tarea '" + selectedTask.getName() + "'.");
            } else {
                // Cualquier otro error propio de PostgreSQL
                showErrorAlert("Error en la Base de Datos", "Error de PostgreSQL: " + e.getMessage());
            }
        } catch (Exception e) {
            // Cualquier otro error general (conexión, drivers, etc.)
            e.printStackTrace();
            showErrorAlert("Error General", "Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    // Añade este método auxiliar para las advertencias visuales limpias
    private void showWarningAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
        System.out.println("Warning alert");
    }


    // Métodos auxiliares para mostrar mensajes en pantalla al usuario
    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    //Unsubscribe worker
    @FXML
    public void handleUnsubscribeWorker(ActionEvent actionEvent) {
        System.out.println("Handle Unsubscribe Activado");

        // 1. Obtener la inscripción seleccionada de la tabla de abajo
        Inscripcion selectedInscripcion = tableInscripciones.getSelectionModel().getSelectedItem();

        // 2. Validar que se haya seleccionado un registro
        if (selectedInscripcion == null) {
            showErrorAlert("Selección vacía", "Debes seleccionar una asignación de la tabla de inscripciones para darla de baja.");
            return;
        }

        // 3. Ventana de confirmación para evitar borrados accidentales
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmar Baja");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Estás seguro de que deseas eliminar la asignación de '"
                + selectedInscripcion.getWorkerName() + "' en la tarea '" + selectedInscripcion.getTaskName() + "'?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            // 4. Ejecutar DELETE en la base de datos
            String sql = "DELETE FROM inscripcion WHERE inscripcion_id = ?";

            try (
                 PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setInt(1, selectedInscripcion.getId());
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    showInfoAlert("Baja Exitosa", "La asignación ha sido eliminada correctamente.");

                    // 5. Refrescar la base de datos para actualizar la UI automáticamente
                    //loadDataFromDatabase();
                    cargarDatosDesdeBD();
                }

            } catch (Exception e) {
                e.printStackTrace();
                showErrorAlert("Error de eliminación", "No se pudo eliminar la inscripción: " + e.getMessage());
            }
        }
    }

    @FXML
    public void switchToWorksTable(ActionEvent event) throws IOException {
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

    public void handleGoBack(ActionEvent actionEvent) throws IOException {
        switchToWorksTable(actionEvent);
    }

    public void loadDataFromDatabase() {
        workerList.clear();
        taskList.clear();
        inscripcionList.clear(); // Limpiar la lista de inscripciones

        String queryWorkers = "SELECT usuario_id, name, email FROM usuario WHERE tipo_usuario = 'worker'";
        String queryTasks = "SELECT task_id, task_name, priority, deadline, status FROM task";

        // Consulta SQL con INNER JOIN para resolver los nombres de las IDs
        String queryInscripciones = "SELECT i.inscripcion_id, u.name AS worker_name, t.task_name, i.fecha_inscripcion " +
                "FROM inscripcion i " +
                "INNER JOIN usuario u ON i.usuario_id = u.usuario_id " +
                "INNER JOIN tasks t ON i.task_id = t.task_id";

        try (
             Statement stmt = con.createStatement()) {

            // ... (Mantén aquí tus bloques de lectura ResultSet de Workers y Tasks) ...

            // Ejecutar y leer Inscripciones
            try (ResultSet rs = stmt.executeQuery(queryInscripciones)) {
                while (rs.next()) {
                    LocalDate date = null;
                    if (rs.getDate("fecha_inscripcion") != null) {
                        date = rs.getDate("fecha_inscripcion").toLocalDate();
                    }

                    inscripcionList.add(new Inscripcion(
                            rs.getInt("inscripcion_id"),
                            rs.getString("worker_name"),
                            rs.getString("task_name"),
                            date
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Error de carga", "No se pudieron refrescar las tablas: " + e.getMessage());
        }
    }
}
