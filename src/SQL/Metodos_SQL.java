/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import alertas.AlertError;
import alertas.AlertSuccess;
import conexion.ConexionSQL;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import principal.Principal;

/**
 *
 * @author Usuario
 */
public class Metodos_SQL {

    ConexionSQL cnn = new ConexionSQL();
    Connection con = cnn.conexion();

    private static Connection conexion;
    private static PreparedStatement sentencia_preparada;
    private static ResultSet resultado;

    public void guardar_nuevaConsulta_factura(int id_cliente,int id_cancha, String fec_alquiler,
            String hor_alquiler, double pre_alquiler, String tie_alquiler, double des_alquiler) {

        try {
            conexion = cnn.conexion();

            //String consulta = "INSERT INTO ttraalqalq INNER JOIN tmaeclialq ON ttraalqalq.id_cliente=tmaeclialq.id_cliente"
            //+ "INNER JOIN tmaecanalq ON ttraalqalq.id_cancha=tmaecanalq.id_cancha"
            //+ "(id_alquiler,id_cliente,id_cancha,fec_alquiler,hor_alquiler,pre_alquiler,tie_alquiler,des_alquiler) VALUES (?,?,?,?,?,?,?,?)";
            String consulta = "INSERT INTO ttraalqalq (id_cliente,id_cancha,fec_alquiler,hor_alquiler,pre_alquiler,tie_alquiler,des_alquiler) VALUES (?,?,?,?,?,?,?) ";
            sentencia_preparada = conexion.prepareStatement(consulta);
            //resultado = sentencia_preparada.executeQuery();
            //sentencia_preparada.setInt(1, id_alquiler);
            sentencia_preparada.setInt(1, id_cliente);
            sentencia_preparada.setInt(2, id_cancha);
            sentencia_preparada.setString(3, fec_alquiler);
            sentencia_preparada.setString(4, hor_alquiler);
            sentencia_preparada.setDouble(5, pre_alquiler);
            sentencia_preparada.setString(6, tie_alquiler);
            sentencia_preparada.setDouble(7, des_alquiler);
            int i = sentencia_preparada.executeUpdate();

            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Datos Guardados");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
            conexion.close();
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error" + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }

    }
    
    
        public void eliminacionDatosAlquiler(String fec_alquiler) {
        try {
            conexion = cnn.conexion();
            String consulta_eliminacion = "DELETE FROM ttraalqalq WHERE fec_alquiler = ?";
            sentencia_preparada = conexion.prepareStatement(consulta_eliminacion);
            sentencia_preparada.setString(1, fec_alquiler);
            int resultado_fila_afectada = sentencia_preparada.executeUpdate();
            
            if(resultado_fila_afectada > 0){
                AlertSuccess a = new AlertSuccess(new Principal(), true);
                a.titulo.setText("Los datos fueron eliminados!");
                a.setVisible(true);
            }else{
                AlertError a = new AlertError(new Principal(), true);
                a.titulo.setText("No se pudieron eliminar los datos");
                a.setVisible(true);  
            }
            conexion.close();
            
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error" + e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
    }

}
