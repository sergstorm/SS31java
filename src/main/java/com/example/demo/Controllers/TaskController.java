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
import java.time.LocalDate;
import java.util.Objects;


public class TaskController {


    public Button subscribeButton;
    public Button btnSubscribe;
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

    @FXML private TableView<Usuario> tableWorkers; // Tu clase modelo Usuario
    @FXML private TableView<Task> tableTasks;       // Tu clase modelo Task

    @FXML
    void handleSubscribeWorker(ActionEvent event) {
        Usuario selectedWorker = tableWorkers.getSelectionModel().getSelectedItem();
        Task selectedTask = tableTasks.getSelectionModel().getSelectedItem();

        if (selectedWorker != null && selectedTask != null) {
            int idUsuario = selectedWorker.getUsuarioId();
            int idTarea = selectedTask.getId();

            // Aquí ejecutas tu INSERT INTO inscripcion (usuario_id, task_id) VALUES (?, ?)
            System.out.println("Inscribiendo usuario " + idUsuario + " en tarea " + idTarea);
        } else {
            // Mostrar alerta: Selecciona un trabajador y una tarea
        }
    }


    /**
     * El método initialize() se ejecuta automáticamente al cargar la vista FXML.
     */
    @FXML
    public void initialize() {
        // 1. Configurar los estados disponibles en el ComboBox
        cmbStatus.setItems(FXCollections.observableArrayList("In Progress", "Completed"));

        // 2. Vincular las columnas de la vista con los atributos de la clase Task
        TaskId.setCellValueFactory(new PropertyValueFactory<>("id"));
        speciality.setCellValueFactory(new PropertyValueFactory<>("name"));
        Experience.setCellValueFactory(new PropertyValueFactory<>("priority"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        achivment.setCellValueFactory(new PropertyValueFactory<>("status"));

        // === SOLUCIÓN: Vincular la lista observable a la tabla ===
        // Sin esta línea, los datos se cargan en segundo plano pero nunca se muestran en la pantalla
        tasksTable.setItems(taskList);

        // 3. Listener para detectar clics en la tabla y mostrar los detalles
        tasksTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mostrarDetallesTarea(newValue);
        });

        // 4. Cargar los datos desde la base de datos
        cargarDatosDesdeBD();
    }



    public void cargarDatosDesdeBD() {
        if (con == null) {
            System.err.println("Error: La conexión a la base de datos no está activa.");
            return;
        }

        taskList.clear(); // Limpia la lista previa

        // CORRECCIÓN: Agregamos explícitamente ', description' aquí
        String queryTasks = "SELECT task_id, task_name, priority, status, description, deadline FROM tasks;";

        try (PreparedStatement stmt = con.prepareStatement(queryTasks);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                java.sql.Date dbDate = rs.getDate("deadline");
                LocalDate deadline = (dbDate != null) ? dbDate.toLocalDate() : null;

                // Ahora sí encuentra la columna porque la pedimos en el SELECT de arriba
                String desc = rs.getString("description");
                if (desc == null) desc = "";

                // Pasamos los 6 parámetros en el orden exacto de tu clase Task
                taskList.add(new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("priority"),
                        rs.getString("status"),
                        desc,
                        deadline
                ));
            }
            System.out.println("¡Tabla de tareas cargada con éxito en TaskController!");

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
    @FXML
    public void switchSbsribeTable(ActionEvent event) throws IOException {
        // Load the FXML file for view 2
        System.out.println("OK+OK");
        root = FXMLLoader.load(getClass().getResource("/managerDashboard.fxml"));
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
                pstmt.setInt(1, tareaSeleccionada.getId());

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
            txtId.setText(String.valueOf(task.getId()));
            txtName.setText(task.getName());
            txtPriority.setText(task.getPriority());
            cmbStatus.setValue(task.getStatus()); // Asigna el valor en el ComboBox

            // --- MANEJO SEGURO DE LA FECHA LÍMITE (DEADLINE) ---
            // Opción 1: Si sigues usando un TextField (txtDeadline)
            if (task.getDeadline() != null) {
                txtDeadline.setText(task.getDeadline().toString());
            } else {
                txtDeadline.setText(""); // Deja el campo vacío si la fecha es nula en la BD
            }

        /*
        // Opción 2: Si cambias tu interfaz a un DatePicker (dpDeadline), usa esto en su lugar:
        dpDeadline.setValue(task.getDeadline()); // JavaFX DatePicker acepta LocalDate directamente (incluso si es null)
        */
            // ----------------------------------------------------

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
            // Limpiar los campos si no hay selección o si se pasa un objeto null
            txtId.setText("");
            txtName.setText("");
            txtPriority.setText("");

            txtDeadline.setText(""); // Si usas DatePicker cambia esto por: dpDeadline.setValue(null);

            cmbStatus.setValue(null);
            panelDetalles.setStyle("-fx-background-color: #F8FAFC; -fx-background-radius: 8; -fx-padding: 15;");
        }
    }

    @FXML
    private void actualizarTarea() {
        // 1. Validar rol de usuario primero
        if (!usuario2.getTipo().equals("manager")) {
            System.out.println("No eres Manager. No tienes permisos para actualizar tareas.");
            return;
        }

        System.out.println("Rol verificado: manager");

        // 2. Verificar que haya un ID válido seleccionado antes de limpiar nada
        if (txtId.getText() == null || txtId.getText().trim().isEmpty()) {
            System.out.println("Selecciona una tarea de la tabla antes de intentar actualizar.");
            return;
        }

        // Consulta SQL para modificar los registros
        String sql = "UPDATE tasks SET task_name = ?, priority = ?, deadline = ?, status = ? WHERE task_id = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, txtName.getText());
            pstmt.setString(2, txtPriority.getText());

            // 3. Manejo seguro de la fecha límite (Deadline)
            // Opción Recomendada: Si usas DatePicker (cambiar el tipo de dpDeadline en tu controlador)
            if (txtDeadline != null) {
               // pstmt.setDate(3, java.sql.Date.valueOf(String.valueOf(txtDeadline)));
                // Convierte LocalDate a java.sql.Date
                String textoFecha = txtDeadline.getText(); // Extrae el contenido real del campo de texto

                if (textoFecha != null && !textoFecha.trim().isEmpty()) {
                    try {
                        // Convierte el texto "AAAA-MM-DD" a un objeto LocalDate nativo
                        java.time.LocalDate localDate = java.time.LocalDate.parse(textoFecha.trim());
                        pstmt.setDate(3, java.sql.Date.valueOf(localDate));
                    } catch (java.time.format.DateTimeParseException e) {
                        System.err.println("Error: El formato de fecha ingresado no es válido. Debe ser AAAA-MM-DD.");
                        return; // Detiene la actualización para evitar que la aplicación falle
                    }
                } else {
                    // Si el usuario borró la fecha, guarda NULL en la base de datos de manera limpia
                    pstmt.setNull(3, java.sql.Types.DATE);
                }

            } else {
                pstmt.setNull(3, java.sql.Types.DATE); // Permite guardar la fecha como NULL si está vacía
            }

            // Reemplaza el bloque de código de la fecha por este control seguro:
            String textoFecha = txtDeadline.getText();
            if (textoFecha != null && !textoFecha.trim().isEmpty()) {
                try {
                    // Valida y parsea el texto a un formato LocalDate correcto
                    java.time.LocalDate localDate = java.time.LocalDate.parse(textoFecha.trim());
                    pstmt.setDate(3, java.sql.Date.valueOf(localDate));
                } catch (java.time.format.DateTimeParseException e) {
                    System.err.println("Error: El formato de fecha debe ser AAAA-MM-DD (Ej: 2026-07-15)");
                    return; // Detiene la ejecución para que no lance la excepción pesada
                }
            } else {
                // Si el campo está vacío, guarda un valor NULL en la base de datos de manera segura
                pstmt.setNull(3, java.sql.Types.DATE);
            }

            pstmt.setString(4, cmbStatus.getValue()); // Toma el valor del ComboBox
            pstmt.setInt(5, Integer.parseInt(txtId.getText().trim()));

            int filasActualizadas = pstmt.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("¡Tarea actualizada con éxito en la Base de Datos!");

                // 4. Limpieza y refresco visual tras el éxito
                tasksTable.getSelectionModel().clearSelection();
                mostrarDetallesTarea(null);

                // Recargar la tabla para mostrar los nuevos cambios y actualizar contadores
                cargarDatosDesdeBD();

                // Limpiar los campos del formulario
                limpiarSeleccion();
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar actualizar la tarea: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void subsc(ActionEvent actionEvent) throws IOException {
        System.out.println(usuario2.getTipo()+"usuarioa 2");
       // if (usuario2.getTipo().equals("manager"))
        switchSbsribeTable(actionEvent);
       // else subscribeButton.setDisable(true);
    }


}
