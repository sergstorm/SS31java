package com.example.demo.BaseData;


import java.time.LocalDate;
import java.util.LinkedList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class Inscripcion {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty workerName; // Almacena el nombre del trabajador obtenido con JOIN
    private final SimpleStringProperty taskName;   // Almacena el nombre de la tarea obtenido con JOIN
    private final SimpleObjectProperty<LocalDate> fechaInscripcion;

    public Inscripcion(int id, String workerName, String taskName, LocalDate fechaInscripcion) {
        this.id = new SimpleIntegerProperty(id);
        this.workerName = new SimpleStringProperty(workerName);
        this.taskName = new SimpleStringProperty(taskName);
        this.fechaInscripcion = new SimpleObjectProperty<>(fechaInscripcion);
    }

    public int getId() { return id.get(); }
    public SimpleIntegerProperty idProperty() { return id; }

    public String getWorkerName() { return workerName.get(); }
    public SimpleStringProperty workerNameProperty() { return workerName; }

    public String getTaskName() { return taskName.get(); }
    public SimpleStringProperty taskNameProperty() { return taskName; }

    public LocalDate getFechaInscripcion() { return fechaInscripcion.get(); }
    public SimpleObjectProperty<LocalDate> fechaInscripcionProperty() { return fechaInscripcion; }
}

