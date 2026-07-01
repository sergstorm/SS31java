package com.example.demo.Personas;

import com.example.demo.Repo.Validaciones;

public class Worker extends Usuario implements Validaciones
{
    public Worker(int id, String name, String email, String tipo, String password) {
        super(id, name, email, tipo, password);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
