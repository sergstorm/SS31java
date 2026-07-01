package com.example.demo.Personas;

import com.example.demo.Repo.Validaciones;

public class Boss extends Usuario implements Validaciones
{
    public Boss(int id, String name, String email, String tipo, String password) {
        super(id, name, email, tipo, password);
    }



    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
