package com.example.demo.BaseData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
    static String URL ="jdbc:mysql://bv0o2fzngveyhydtipmi-mysql.services.clever-cloud.com:3306/bv0o2fzngveyhydtipmi";
    private static String USER ="ueasacbdkyejnoud";
    private static String PASSWORD ="VGnaOaoVZ2cjfQjNwoc6";


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
     public Connection getConnection()
     {
         return connect;
     }
}
