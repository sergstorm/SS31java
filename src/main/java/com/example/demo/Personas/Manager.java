package com.example.demo.Personas;


public class Manager {
    private int managerId;
    private String department;
    private String alias;
    private String achievement;
    private String aim;

    // Constructor completo
    public Manager(int managerId, String department, String alias, String achievement, String aim) {
        this.managerId = managerId;
        this.department = department;
        this.alias = alias;
        this.achievement = achievement;
        this.aim = aim;
    }

    // GETTERS Y SETTERS (Esenciales para PropertyValueFactory)
    public int getManagerId() { return managerId; }
    public void setManagerId(int managerId) { this.managerId = managerId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getAchievement() { return achievement; }
    public void setAchievement(String achievement) { this.achievement = achievement; }

    public String getAim() { return aim; }
    public void setAim(String aim) { this.aim = aim; }
}

