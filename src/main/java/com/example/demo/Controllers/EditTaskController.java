package com.example.demo.Controllers;

import com.example.demo.BaseData.Conexion;
import com.example.demo.BaseData.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditTaskController
{
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
        //edti
        String a = taskNameField.getText();
        String b = priorityCombo.getValue();
        String time = deadlinePicker.getValue().toString();
        String c = statusCombo.getValue();

        System.out.println("Edit worker table"+a+b+time+c);
        Task task = new Task(1,a,b,"deadline",c);

        try {
            PreparedStatement statement = con.prepareStatement(" INSERT INTO 'task' (a, b, time, c) VALUES ?,?,?,?)");
            //PreparedStatement statement = con.prepareStatement("INSERT INTO usuario (nombre, email, tipo, password) VALUES (?, ?, ?, ?)");

            statement.setString(1, task.getTaskName());
            statement.setString(2,task.getPriority());
            statement.setString(3, task.getDeadline());
            statement.setString(4, task.getStatus());

            int filas = statement.executeUpdate();
            if (filas > 0){
                System.out.println("Agreagado");
            }
            } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);

             }

    }

    
}
