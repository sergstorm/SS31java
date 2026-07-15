package com.example.demo.Personas;

public abstract class Usuario
{
   protected int id;
   protected String name;
   protected String email;
   protected String tipo;
   protected String password;
  // protected static ControllerUsario controllerUsario = new ControllerUsario();


    public Usuario(int id, String name, String email, String tipo, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tipo = tipo;
        this.password = password;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                //", password='" + password + '\'' +
                '}';
    }

    public int getUsuarioId() {
        int a = getUsuarioId();
        return a;
    }
}
