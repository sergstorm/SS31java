package com.example.demo.Personas;

 // Cambia esto por el nombre real de tu paquete

public class Manager {

    // Atributos privados (deben coincidir con el PropertyValueFactory)
    private int managerId;
    private String departmentName;
    private String alias;
    private String currentAchievement;
    private String aim;

    // Constructor completo para instanciar desde la base de datos
    public Manager(int managerId, String departmentName, String alias, String currentAchievement, String aim) {
        this.managerId = managerId;
        this.departmentName = departmentName;
        this.alias = alias;
        this.currentAchievement = currentAchievement;
        this.aim = aim;
    }

    // Constructor vacío (opcional, útil para frameworks o inicializaciones vacías)
    public Manager() {
    }

    // ==========================================
    // GETTERS Y SETTERS (Indispensables para JavaFX)
    // ==========================================

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCurrentAchievement() {
        return currentAchievement;
    }

    public void setCurrentAchievement(String currentAchievement) {
        this.currentAchievement = currentAchievement;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    // Método toString() opcional para depuración en consola
    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", departmentName='" + departmentName + '\'' +
                ", alias='" + alias + '\'' +
                ", currentAchievement='" + currentAchievement + '\'' +
                ", aim='" + aim + '\'' +
                '}';
    }
}


