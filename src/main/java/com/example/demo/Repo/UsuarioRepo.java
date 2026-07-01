package com.example.demo.Repo;

import com.example.demo.Personas.Usuario;

import java.util.LinkedList;
import java.util.List;

public interface UsuarioRepo {
    void agregarUsuario(Usuario usuario);
    List<Usuario> mostrarUsuarios();
    <T> T login(String nombre, String password);
    LinkedList<Usuario> mostrarAlumnos();
    void EliminarUsuario(Usuario usuario);
}
