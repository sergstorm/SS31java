package com.example.demo.BaseData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
    static String URL ="jdbc:postgresql://localhost:5432/f3";
    private static String USER ="postgres";
    private static String PASSWORD ="qwe";


    private static Connection connect;
    private static Conexion instance;


     private Conexion()
     {
         try
         {
             connect = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
             System.out.println("Conecto");

         } catch (SQLException e) {
             System.out.println("No conecto");
             throw new RuntimeException(e);

         }
     }
     public static Conexion getInstance()
     {
         if (instance==null)
         {
             instance = new Conexion();
         }
         return instance;
     }
     public Connection getConection()
     {
         return connect;
     }
}
