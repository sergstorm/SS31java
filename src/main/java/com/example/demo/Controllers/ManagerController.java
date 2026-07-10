package com.example.demo.Controllers;


import com.example.demo.BaseData.Conexion;
import com.example.demo.Personas.Manager; // Importa tu nuevo modelo
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ManagerController {
    public Button EBB;
    public Button GBB;
    public Button LbB;
    private Usuario usuario2 = UserSession.getInstance().getUsuario();
    private Stage stage;
    private Scene scene;
    private Parent root;

    public Button backToLanding;
    public Button backToLanding2;
    // Vincular con los fx:id de las columnas de tu fxml
    @FXML private TableView<Manager> managersTable; // Asegúrate de asignar este fx:id en tu TableView del FXML
    @FXML private TableColumn<Manager, Integer> managrId;
    @FXML private TableColumn<Manager, String> department;
    @FXML private TableColumn<Manager, String> alias;
    @FXML private TableColumn<Manager, String> achivment; // Mapea al fx:id="achivment"
    @FXML private TableColumn<Manager, String> aim;

    @FXML private VBox panelDetallesManager;
    @FXML private TextField txtManagerId;
    @FXML private TextField txtDepartment;
    @FXML private TextField txtAlias;
    @FXML private TextField txtAchievement;
    @FXML private TextField txtAim;

    private ObservableList<Manager> managerList = FXCollections.observableArrayList();


    private static final Connection con = Conexion.getInstance().getConection();
    //private final ObservableList<Manager> managerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // ... (Mantén aquí tus códigos previos de la tabla de Tareas)

        // 1. Vincular columnas de la tabla de Mánagers (Asegura que coincidan con los atributos de tu clase Manager)
        managrId.setCellValueFactory(new PropertyValueFactory<>("managerId"));
        department.setCellValueFactory(new PropertyValueFactory<>("departmentName")); // Cambiar según tu variable en el modelo
        alias.setCellValueFactory(new PropertyValueFactory<>("alias"));
        achivment.setCellValueFactory(new PropertyValueFactory<>("currentAchievement")); // Cambiar según tu variable en el modelo
        aim.setCellValueFactory(new PropertyValueFactory<>("aim"));

        // 2. NUEVO: Escuchar la selección de filas de la tabla de mánagers
        managersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mostrarDetallesManager(newValue);
        });

        // Cargar inicialmente los datos de ambas tablas

        cargarManagersDesdeBD(); // Método para leer la tabla de mánagers
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

    public void logout(ActionEvent actionEvent) throws IOException {
        System.out.println(" go to login");
        usuario2=null;
        switchToView8(actionEvent);
    }

    public void goBackToLandingPageManagers(ActionEvent actionEvent) throws IOException {
        System.out.println("go back to landing manageers page");
        switchToView11(actionEvent);
    }

    // Muestra la información del mánager en los TextFields izquierdos al hacer clic
    private void mostrarDetallesManager(Manager manager) {
        if (manager != null) {
            txtManagerId.setText(String.valueOf(manager.getManagerId()));
            txtDepartment.setText(manager.getDepartmentName());
            txtAlias.setText(manager.getAlias());
            txtAchievement.setText(manager.getCurrentAchievement());
            txtAim.setText(manager.getAim());

            // Opcional: Cambiar de color el panel si el mánager alcanzó la meta (Aim)
            // Suponiendo que son valores comparables, o simplemente un estilo estable
            panelDetallesManager.setStyle("-fx-background-color: #F8FAFC; -fx-background-radius: 8; -fx-padding: 15;");
        } else {
            txtManagerId.setText("");
            txtDepartment.setText("");
            txtAlias.setText("");
            txtAchievement.setText("");
            txtAim.setText("");
            panelDetallesManager.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 8; -fx-padding: 15;");
        }
    }

    // Acción del botón Limpiar
    @FXML
    private void limpiarSeleccionManager() {
        if (Objects.equals(usuario2.getTipo(), "boss"))
        {
            System.out.println("Boss usuario --- ---");
        managersTable.getSelectionModel().clearSelection();
        mostrarDetallesManager(null);
        }
        else
        {
            LbB.setDisable(true);
            System.out.println("Not a Boss");
        }
    }

    // Acción del botón Guardar (Actualiza en BD)
    @FXML
    private void actualizarManager() {
        if (txtManagerId.getText().isEmpty()) {
            System.out.println("Selecciona un mánager de la tabla para editar.");
            return;
        }

        // Corregido con los nombres de columna exactos de tu PostgreSQL
        String sql = "UPDATE managers SET department = ?, alias = ?, achievement = ?, aim = ? WHERE manager_id = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, txtDepartment.getText());
            pstmt.setString(2, txtAlias.getText());
            pstmt.setString(3, txtAchievement.getText());
            pstmt.setString(4, txtAim.getText());
            pstmt.setInt(5, Integer.parseInt(txtManagerId.getText()));

            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("¡Mánager actualizado de forma exitosa!");
                cargarManagersDesdeBD();
                limpiarSeleccionManager();
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar actualizar el mánager: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Acción del botón Eliminar
    @FXML
    private void eliminarManager() {
        Manager seleccionado = managersTable.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            System.out.println("Selecciona un mánager de la tabla para eliminar.");
            return;
        }

        String sql = "DELETE FROM managers WHERE manager_id = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, seleccionado.getManagerId());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Mánager eliminado exitosamente.");
                cargarManagersDesdeBD();
                limpiarSeleccionManager();
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar mánager: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Asegúrate de tener implementado el método de lectura inicial
    private void cargarManagersDesdeBD() {
        managerList.clear();

        // Nombres de columnas reales de tu consulta: department y achievement
        String sql = "SELECT manager_id, department, alias, achievement, aim FROM managers;";

        try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Manager m = new Manager(
                        rs.getInt("manager_id"),
                        rs.getString("department"),   // Corregido
                        rs.getString("alias"),
                        rs.getString("achievement"),  // Corregido
                        rs.getString("aim")
                );
                managerList.add(m);
            }
            managersTable.setItems(managerList);
        } catch (SQLException e) {
            System.err.println("Error en la consulta de mánagers: " + e.getMessage());
            e.printStackTrace();
        }
    }


}

