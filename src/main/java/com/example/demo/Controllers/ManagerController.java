package com.example.demo.Controllers;


import com.example.demo.BaseData.Conexion;
import com.example.demo.Personas.Manager; // Importa tu nuevo modelo
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerController {

    // Vincular con los fx:id de las columnas de tu fxml
    @FXML private TableView<Manager> managersTable; // Asegúrate de asignar este fx:id en tu TableView del FXML
    @FXML private TableColumn<Manager, Integer> managrId;
    @FXML private TableColumn<Manager, String> department;
    @FXML private TableColumn<Manager, String> alias;
    @FXML private TableColumn<Manager, String> achivment; // Mapea al fx:id="achivment"
    @FXML private TableColumn<Manager, String> aim;

    private static final Connection con = Conexion.getInstance().getConection();
    private final ObservableList<Manager> managerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Enlazar columnas con los atributos declarados en Manager.java
        managrId.setCellValueFactory(new PropertyValueFactory<>("managerId"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        alias.setCellValueFactory(new PropertyValueFactory<>("alias"));
        achivment.setCellValueFactory(new PropertyValueFactory<>("achievement")); // Usa el getter getAchievement()
        aim.setCellValueFactory(new PropertyValueFactory<>("aim"));

        cargarManagersDesdeBD();
    }

    private void cargarManagersDesdeBD() {
        managerList.clear();
        String sql = "SELECT manager_id, department, alias, achievement, aim FROM managers ORDER BY manager_id ASC;";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Manager manager = new Manager(
                        rs.getInt("manager_id"),
                        rs.getString("department"),
                        rs.getString("alias"),
                        rs.getString("achievement"),
                        rs.getString("aim")
                );
                managerList.add(manager);
            }

            // Renderizar la lista en la interfaz
            managersTable.setItems(managerList);
            System.out.println("Se cargaron con éxito " + managerList.size() + " mánagers en la tabla.");

        } catch (SQLException e) {
            System.err.println("Error al intentar leer la tabla 'managers': " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent)
    {

    }

    public void goBackToLandingPageManagers(ActionEvent actionEvent)
    {

    }
}

