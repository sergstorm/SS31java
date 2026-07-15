package com.example.demo.Controllers;

import com.example.demo.BaseData.Conexion;
import com.example.demo.BaseData.Task;
import com.example.demo.Personas.Boss;
import com.example.demo.Personas.UserSession;
import com.example.demo.Personas.Usuario;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class BossController {
    public Button workersCardButton;
    public Button backButton;
    public Label bossEmail;

    public Button backToLanding2;
    public Button editTableButton;
    public TextField bossNameField;
    public TextField bossEmailField;
    public TextField bossPassField;
    public Label bossMessageProfile;
    public Button bossChanfeProfileButton;
    public Button btnCancel;
    public Button btnSave;
    public TextField txtNewName;
    public TextField txtNewRole;
    public TextField txtNewStatus;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Usuario usuario2 = UserSession.getInstance().getUsuario();



    private static Connection con = Conexion.getInstance().getConection();

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
        stage.getScene().getWindow().setHeight(500);
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
        stage.getScene().getWindow().setWidth(1300);
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

    public void handleRegistration(ActionEvent actionEvent) {

    }

    public void goToManagersTable(ActionEvent actionEvent) throws IOException {
        switchToView9(actionEvent);
        System.out.println("Success managers table");
    }

    public void goToMyProfileTable(ActionEvent actionEvent) throws IOException {
        switchToView12(actionEvent);
        System.out.println("my profile");
    }

    public void goToWorkersTable(ActionEvent actionEvent) throws IOException {
        switchToView5(actionEvent);
        System.out.println("Workers table");
    }

    public void goBackToLandingPage(ActionEvent actionEvent) throws IOException {
        if (usuario2.getTipo().equals("boss"))
        {
            System.out.println("boss");
            switchToView6(actionEvent);
            System.out.println("Success boss table");
        }
        else
        {
            System.out.println("Not boss");

        }

    }

    public void logout(ActionEvent actionEvent) throws IOException {
        String a = "1";
        String b = "2";
        String c = "3";
        if (a.equals("1"))
            switchToView8(actionEvent);
        else if (b.equals("2")) {
            switchToView6(actionEvent);
        } else if (c.equals("3")) {
            switchToView6(actionEvent);
        }
    }



    public void logoutFromBossProfile(ActionEvent actionEvent) throws IOException {
        switchToView8(actionEvent);
    }

    public void goBackToMain(ActionEvent actionEvent) throws IOException {
        switchToView6(actionEvent);
    }

    public void boosHandleSave(ActionEvent actionEvent) {

       // Usuario usuarioActual = UserSession.getInstance().getUsuario();
        Usuario usuarioActual = usuario2;

// 1. Obtener los nuevos valores desde los campos de tu vista (JavaFX)
        String nuevoName = bossNameField.getText();
        String nuevoEmail = bossEmailField.getText();
        String nuevoPassword = bossPassField.getText();

        System.out.println(usuarioActual + " Usuario actual ");

        int idUsuario = usuarioActual.getId();
        System.out.println(idUsuario + " this usuario id    +    simpleb---");

        System.out.println("Actualizando usuario ID " + idUsuario + " con nombre: " + nuevoName);

// 2. Sentencia SQL con 4 parámetros (?)
        String sql = "UPDATE usuario SET name = ?, email = ?, password = ? WHERE usuario_id = ?;";

// Uso de try-with-resources para asegurar el cierre del statement
        try (PreparedStatement statement = con.prepareStatement(sql)) {

            // 3. Inyectar los parámetros en orden estricto (1, 2, 3)
            statement.setString(1, nuevoName);
            statement.setString(2, nuevoEmail);
            statement.setString(3, nuevoPassword);

            // CORRECCIÓN AQUÍ: Asignar el parámetro 4 para el WHERE usuario_id = ?
            statement.setInt(4, idUsuario);

            // 4. Ejecutar la consulta en la base de datos
            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("UPDATE " + filasActualizadas + " - ¡Usuario actualizado con éxito!");

                // Sincronizar el objeto en memoria para reflejar los cambios inmediatamente
                usuarioActual.setName(nuevoName);
                usuarioActual.setEmail(nuevoEmail);
                usuarioActual.setPassword(nuevoPassword);

                bossMessageProfile.setText("Exitoso");

                bossChanfeProfileButton.setDisable(true);
                // 1. Create a 3-second delay
                PauseTransition delay = new PauseTransition(Duration.seconds(3));

                // 2. Move the scene switch inside the finished event
                delay.setOnFinished(event -> {
                    try {
                        switchToView6(actionEvent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                // 3. Start the timer (code below this will still run immediately, but the switch waits)
                delay.play();

            } else {
                System.out.println("No se encontró ningún usuario con ID: " + idUsuario);
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar el UPDATE en la tabla usuario");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @FXML
    private ImageView profileImage;


    @FXML
    public void handleSelectImage(ActionEvent actionEvent) {
        try {
            // 1. Obtener el usuario actual desde tu sesión global (Singleton)
            Usuario usuarioActual = UserSession.getInstance().getUsuario();
            if (usuarioActual == null) {
                System.err.println("Error: No hay sesión de usuario activa para renombrar el archivo.");
                return;
            }
            int idUsuario = usuarioActual.getId(); // Ej: 15

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Foto de Perfil");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
            );

            // Abrir por defecto en Descargas
            String userHome = System.getProperty("user.home");
            File downloadsFolder = new File(userHome + File.separator + "Downloads");
            if (downloadsFolder.exists()) {
                fileChooser.setInitialDirectory(downloadsFolder);
            }

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                // 2. Extraer la extensión del archivo original (ej: .png o .jpg)
                String nombreOriginal = selectedFile.getName();
                String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));

                // 3. Definir el NUEVO NOMBRE del archivo (Ejemplo: "usuario_15.png")
                String nuevoNombreArchivo = "usuario_" + idUsuario + extension;

                // 4. Construir la ruta de destino utilizando el nuevo nombre
                Path destinoRuta = Paths.get("src", "main", "resources", "img", nuevoNombreArchivo);

                // Crear los directorios si no existen
                Files.createDirectories(destinoRuta.getParent());

                // 5. Copiar y renombrar el archivo en el proceso
                Files.copy(selectedFile.toPath(), destinoRuta, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo renombrado y guardado en: " + destinoRuta.toAbsolutePath());

                // 6. Mostrar la imagen en la vista inmediatamente
                String fileUrl = destinoRuta.toUri().toURL().toString();
                Image nuevaImagen = new Image(fileUrl);
                profileImage.setImage(nuevaImagen);

            } else {
                System.out.println("Selección de imagen cancelada.");
            }

        } catch (Exception e) {
            System.err.println("Error al copiar y renombrar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
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


    public void goBaQckToMain(ActionEvent actionEvent) throws IOException {
        if (usuario2.getTipo().equals("boss"))
        {
            System.out.println("boss");
            switchToView6(actionEvent);
            System.out.println("Success boss table");
        }
        else
        {
            System.out.println("Not boss");

        }
    }

    @FXML
    public void cancelarRegistro(ActionEvent actionEvent) throws IOException {
        // Obtiene la ventana actual (Stage) a partir del botón que disparó el evento y la cierra
        switchToView7(actionEvent);
    }

    @FXML
    public void guardarMiembro(ActionEvent actionEvent) {
        String name = txtNewName.getText().trim();
        String role = txtNewRole.getText().trim();
        String status = txtNewStatus.getText().trim();

        // 1. Validación básica de campos vacíos
        if (name.isEmpty() || role.isEmpty() || status.isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios.");
            // Opcional: Aquí puedes cambiar un Label de error en tu interfaz si lo agregas
            return;
        }

        // 2. Sentencia SQL de inserción (Ajusta los nombres de las columnas a tu tabla 'managers')
        // Asumiendo que las columnas se llaman: department (como rol), alias (como nombre) y aim (como estado)
        // O las columnas exactas que use tu tabla de base de datos.
        String sql = "INSERT INTO managers (department, alias, aim) VALUES (?, ?, ?);";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, role);
            pstmt.setString(2, name);
            pstmt.setString(3, status);

            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Nuevo miembro guardado exitosamente en la BD!");

                // 3. CVolver
                switchToView7(actionEvent);
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar insertar el nuevo miembro: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
