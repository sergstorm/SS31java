package com.example.demo.BaseData;

public class Task {

    private final int taskId;
    private final String taskName;
    private final String priority;
    private final String deadline;
    private final String status;

    public Task(int taskId, String taskName, String priority, String deadline, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getPriority() {
        return priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }
}
