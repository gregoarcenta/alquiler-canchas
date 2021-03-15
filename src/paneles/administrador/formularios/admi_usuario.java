package paneles.administrador.formularios;


import alertas.AlertError;
import alertas.AlertInformation;
import alertas.AlertSuccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.placeholder.PlaceHolder;
import conexion.ConexionSQL;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;
import principal.Principal;
import validaciones.Validacion;

/**
 *
 * @author modulo usuario
 */
public class admi_usuario extends javax.swing.JPanel {
    Validacion v = new Validacion();
    ConexionSQL enlace = new ConexionSQL();
    Connection conect = enlace.conexion();
    PlaceHolder holder;
 
    /**
     * Creates new form admi_usuario
     */
    public admi_usuario() {
        initComponents();
        mostrarDatos();
        
        txt_ced.setPlaceholder("Cédula");
        txt_ced.setPhColor(new java.awt.Color(102, 102, 102));
        txt_ced.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txt_nom.setPlaceholder("Nombre Usuario");
        txt_nom.setPhColor(new java.awt.Color(102, 102, 102));
        txt_nom.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txt_usu.setPlaceholder("Usuario");
        txt_usu.setPhColor(new java.awt.Color(102, 102, 102));
        txt_usu.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txt_cla.setPlaceholder("Clave");
        txt_cla.setPhColor(new java.awt.Color(102, 102, 102));
        txt_cla.setFont(new Font("Tahoma", Font.BOLD, 18));
 
    }
    
    public boolean existenDatos(String cedula){
        try {
            String SQL = "select * from tmaeusualq where ced_usuario = '"+cedula+"'";
            Statement st=conect.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            while (rs.next()) {                
                if (rs.getString("ced_usuario")!="") {
                    return true;
                }
                else{
                    return false;
                }
            }
        }catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null, "Hubo un error.\n"
                                     + "Por favor, ingrese una cedula válida.", 
                                        "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
 
    public void mostrarDatos(){
              
        DefaultTableModel tusuario = new DefaultTableModel();
  
        tusuario.addColumn("Cedula Usuario");
        tusuario.addColumn("Nombre Usuario");
        tusuario.addColumn("Usuario");
        tusuario.addColumn("Clave Asignada");
        tusuario.addColumn("Rol");
        tusuario.addColumn("Fecha registro");
        
        tabla_usuario.setModel(tusuario);
        String []datos = new String[7];
        
        try {
            Statement leer = conect.createStatement();
            ResultSet resultado = leer.executeQuery("SELECT * FROM tmaeusualq");
            
            while (resultado.next()){
                datos[0] = resultado.getString(2);
                datos[1] = resultado.getString(3);
                datos[2] = resultado.getString(4);
                datos[3] = resultado.getString(5);
                datos[4] = resultado.getString(6);
                datos[5] = resultado.getString(7);
               
                tusuario.addRow(datos);
            }
            tabla_usuario.setModel(tusuario);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e+"Error al consultar");
        }
    }
    
    public void insertarDatos(){
        try {
            PreparedStatement guardar = conect.prepareStatement("INSERT INTO  tmaeusualq(ced_usuario,nom_usuario,usu_usuario,cla_usuario,rol_usuario,fec_usuario) VALUES(?,?,?,?,?,CURDATE())");
    
            guardar.setString(1, txt_ced.getText());
            guardar.setString(2, txt_nom.getText());
            guardar.setString(3, txt_usu.getText());
            guardar.setString(4, txt_cla.getText());
            guardar.setString(5, txt_rol.getSelectedItem().toString());
         
            guardar.executeUpdate();
            new AlertSuccess(new Principal(), true).setVisible(true); 
            mostrarDatos();
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e+ "no se logro el registro");
        }
    }
    
    
    public void ModificarDatos(){
         int fila = tabla_usuario.getSelectedRow();
         String ced_usuario = tabla_usuario.getValueAt(fila,0).toString();
         String nom_usuario = tabla_usuario.getValueAt(fila,1).toString();
         String usu_usuario = tabla_usuario.getValueAt(fila,2).toString();
         String cla_usuario= tabla_usuario.getValueAt(fila,3).toString();
         String rol_usuario = tabla_usuario.getValueAt(fila,4).toString();       
        try {
            PreparedStatement actu = conect.prepareStatement(" UPDATE tmaeusualq SET nom_usuario='"+nom_usuario+"',usu_usuario='"+usu_usuario+"',cla_usuario='"+cla_usuario+"',rol_usuario='"+rol_usuario+"' WHERE ced_usuario = '"+ced_usuario+"' ");
            actu.executeUpdate();
            
            AlertSuccess a = new AlertSuccess(new Principal(), true);
            a.titulo.setText("Registro actualizado exitosamente!");
            a.setVisible(true);
            
            mostrarDatos();
                  
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e +"no se logro actualizar");
         }
         
         
     }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_usuario = new javax.swing.JTable();
        btnagregar = new javax.swing.JButton();
        txt_nom = new app.bolivia.swing.JCTextField();
        txt_usu = new app.bolivia.swing.JCTextField();
        txt_cla = new app.bolivia.swing.JCTextField();
        txt_ced = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_rol = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N

        tabla_usuario.setAutoCreateRowSorter(true);
        tabla_usuario.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        tabla_usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_usuario.setColumnSelectionAllowed(true);
        tabla_usuario.setDropMode(javax.swing.DropMode.INSERT);
        tabla_usuario.setGridColor(new java.awt.Color(255, 255, 255));
        tabla_usuario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_usuario.setShowGrid(false);
        jScrollPane1.setViewportView(tabla_usuario);
        tabla_usuario.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnagregar.setBackground(new java.awt.Color(255, 255, 255));
        btnagregar.setForeground(new java.awt.Color(255, 255, 255));
        btnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mas_registrar.png"))); // NOI18N
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        txt_nom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txt_nom.setForeground(new java.awt.Color(51, 51, 51));
        txt_nom.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_nom.setPhColor(new java.awt.Color(255, 255, 255));
        txt_nom.setPlaceholder("SEARCH");
        txt_nom.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txt_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomActionPerformed(evt);
            }
        });

        txt_usu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txt_usu.setForeground(new java.awt.Color(51, 51, 51));
        txt_usu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_usu.setPhColor(new java.awt.Color(255, 255, 255));
        txt_usu.setPlaceholder("SEARCH");
        txt_usu.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txt_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuActionPerformed(evt);
            }
        });

        txt_cla.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txt_cla.setForeground(new java.awt.Color(51, 51, 51));
        txt_cla.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_cla.setPhColor(new java.awt.Color(255, 255, 255));
        txt_cla.setPlaceholder("SEARCH");
        txt_cla.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txt_cla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_claActionPerformed(evt);
            }
        });

        txt_ced.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txt_ced.setForeground(new java.awt.Color(51, 51, 51));
        txt_ced.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_ced.setPhColor(new java.awt.Color(255, 255, 255));
        txt_ced.setPlaceholder("SEARCH");
        txt_ced.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txt_ced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cedActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setText("Ingresar");

        txt_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Administrador", "Usuario" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descarga.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txt_ced, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_usu, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txt_cla, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnagregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_usu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(353, 353, 353))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        if(v.validateCedula(txt_ced.getText()) && v.validateName(txt_nom.getText()) && v.validateCedula(txt_usu.getText()) && !txt_cla.getText().equals("") && txt_rol.getSelectedItem() != "Seleccionar"){
            if (!existenDatos(txt_ced.getText())) {
                insertarDatos();
            } else {
                AlertInformation a = new AlertInformation(new Principal(), true);
                a.titulo.setText("Cedula ingresada ya existe");
                a.titulo2.setText("Inténtelo nuevamente!");
                a.titulo2.setForeground(Color.BLACK);
                a.setVisible(true);
            } 
        }else{
            AlertError a = new AlertError(new Principal(), true);
            a.titulo.setText("Por favor, rellena el formulario correctamente");
            a.setVisible(true);
        }        
    }//GEN-LAST:event_btnagregarActionPerformed

    private void txt_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomActionPerformed

    private void txt_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuActionPerformed

    private void txt_claActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_claActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_claActionPerformed

    private void txt_cedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cedActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ModificarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_usuario;
    private app.bolivia.swing.JCTextField txt_ced;
    private app.bolivia.swing.JCTextField txt_cla;
    private app.bolivia.swing.JCTextField txt_nom;
    private javax.swing.JComboBox<String> txt_rol;
    private app.bolivia.swing.JCTextField txt_usu;
    // End of variables declaration//GEN-END:variables
}
