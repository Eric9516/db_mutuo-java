package com.mycompany.clientesdb.logica;

import com.mycompany.clientesdb.persistencia.Conexion;
import java.awt.HeadlessException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_cliente;
    private String numero_cliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String localidad;
    private String telefono;
    private String observaciones;

    public Cliente() {
    }

    public Cliente(int id_cliente, String numero_cliente, String nombre, String apellido, String direccion, String localidad, String telefono, String observaciones) {
        this.id_cliente = id_cliente;
        this.numero_cliente = numero_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
        this.observaciones = observaciones;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNumero_cliente() {
        return numero_cliente;
    }

    public void setNumero_cliente(String numero_cliente) {
        this.numero_cliente = numero_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void InsertarCliente(JTextField paramNombre, JTextField paramApellido, JTextField paramDomicilio, JTextField paramTelefono, JTextField paramLocalidad, JTextArea paramObservaciones, JTextField paramNumCliente) throws SQLException {
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setDireccion(paramDomicilio.getText());
        setLocalidad(paramLocalidad.getText());
        setTelefono(paramTelefono.getText());
        setObservaciones(paramObservaciones.getText());
        setNumero_cliente(paramNumCliente.getText());

        Conexion objetoConexion = new Conexion();

        String consulta = "insert into clientes (nombre,apellido,domicilio,telefono,localidad,observaciones,numeroCLiente) values (?,?,?,?,?,?,?);";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getDireccion());
            cs.setString(4, getTelefono());
            cs.setString(5, getLocalidad());
            cs.setString(6, getObservaciones());
            cs.setString(7, getNumero_cliente());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión, error: " + e.toString());

        }
    }

    public void MostrarClientes(JTable paramTablaTotalClientes) {
        Conexion objetoConexion = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalClientes.setRowSorter(ordenarTabla);

        String sql = "";

        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Domicilio");
        modelo.addColumn("Telefono");
        modelo.addColumn("Localidad");
        modelo.addColumn("Observaciones");
        modelo.addColumn("Numero de cliente");

        paramTablaTotalClientes.setModel(modelo);

        sql = "select * from clientes";

        String[] datos = new String[8];
        Statement st;

        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
         
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalClientes.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros: " + e.toString());
        }

    }

}