package ConexionSQL;
import java.sql.*;
import javax.swing.JOptionPane;
public class ConexionSQL {
   Connection cnx = null;
   public Connection conexion(){
      
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/alquiler_canchas", "root", "");
            
         } catch (ClassNotFoundException | SQLException e) {
             JOptionPane.showMessageDialog(null, "Error en la conexion de la Base de Datos" + e.getMessage());
                 } 
      return cnx;
   }
}