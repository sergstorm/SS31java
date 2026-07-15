package com.example.demo.BaseData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class Task {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty priority;
    private final SimpleObjectProperty<LocalDate> deadline;
    private final SimpleStringProperty status;
    private final String description;

    // CONSTRUCTOR PRINCIPAL: Ideal para usar desde la Base de Datos
    public Task(int id, String name, String priority, String status, String description, LocalDate deadline) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.priority = new SimpleStringProperty(priority);
        this.status = new SimpleStringProperty(status);
        this.description = description != null ? description : "";
        this.deadline = new SimpleObjectProperty<>(deadline); // Guarda la fecha real
    }

    // GETTERS Y PROPIEDADES (Para JavaFX TableView)
    public int getId() { return id.get(); }
    public SimpleIntegerProperty idProperty() { return id; }

    // Alias para evitar el warning anterior de la tabla
    public int getTaskId() { return id.get(); }

    public String getName() { return name.get(); }
    public SimpleStringProperty nameProperty() { return name; }

    // Alias para evitar el warning anterior de la tabla
    public String getTaskName() { return name.get(); }

    public String getPriority() { return priority.get(); }
    public SimpleStringProperty priorityProperty() { return priority; }

    public LocalDate getDeadline() { return deadline.get(); }
    public SimpleObjectProperty<LocalDate> deadlineProperty() { return deadline; }

    public String getStatus() { return status.get(); }
    public SimpleStringProperty statusProperty() { return status; }

    public String getDescription() { return description; }
}
