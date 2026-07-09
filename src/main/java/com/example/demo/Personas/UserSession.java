package com.example.demo.Personas;



public class UserSession {
    // Instancia única en toda la aplicación
    private static UserSession instance;

    // Aquí se almacena el usuario actual en memoria
    private Usuario usuarioLogueado;

    // Constructor privado para evitar que se cree con 'new UserSession()'
    private UserSession() {}

    // Método global para obtener la sesión desde cualquier parte
    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Métodos para guardar y obtener el usuario
    public void setUsuario(Usuario usuario) {
        this.usuarioLogueado = usuario;
    }

    public Usuario getUsuario() {
        return this.usuarioLogueado;
    }

    // Método para cerrar sesión y limpiar la memoria
    public void cleanUserSession() {
        this.usuarioLogueado = null;
    }
}
