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



}
