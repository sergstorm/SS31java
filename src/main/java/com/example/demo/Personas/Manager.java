package com.example.demo.Personas;

import com.example.demo.Repo.Validaciones;

public class Manager extends Usuario implements Validaciones
{
    public Manager(int id, String name, String email, String tipo, String password) {
        super(id, name, email, tipo, password);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
