package com.example.demo.BaseData;
 // Ajusta el paquete según tu proyecto

public class Task {
    private int taskId;
    private String taskName;
    private String priority;
    private String deadline;
    private String status;

    // Constructor completo
    public Task(int taskId, String taskName, String priority, String deadline, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    // GETTERS Y SETTERS (Esenciales para JavaFX)
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

