package com.mycompany.clientesdb.persistencia;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    Connection conectar = null;

    String usuario = "root";
    String pass = "";
    String bd = "clientes";
    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario,pass);
            JOptionPane.showMessageDialog(null, "Conexión con la base de datos correcta");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a l abase de datos, error: " + e.toString());
        }
        return conectar;
    }

}
