package paneles.cancha;

import com.placeholder.PlaceHolder;
import conexion.ConexionSQL;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class pnlCancha extends javax.swing.JPanel {
    PlaceHolder holder;
    ConexionSQL c = new ConexionSQL();
    Connection con = c.conexion();

    public pnlCancha() {
        initComponents();
        Placeholder();
        cbEstCancha.setSelectedItem("Libre");
        cbEstCancha.setEnabled(false);
    }
    
    public void Placeholder() {
        new PlaceHolder(txtCodCancha, "Código");
        txtCodCancha.setFont(new Font("Tahoma", Font.BOLD, 14));
        new PlaceHolder(txtPrecioCancha, "Precio por hora");
        txtPrecioCancha.setFont(new Font("Tahoma", Font.BOLD, 14));
        new PlaceHolder(txtObsCancha, "Observaciones...");
        txtObsCancha.setFont(new Font("Tahoma", Font.BOLD, 14));
    }
    
    public void limpiarCajas(){    
        txtCodCancha.setText("");
        txtObsCancha.setText("");
        txtPrecioCancha.setText("");
        cbEstCancha.setSelectedItem("Libre");
        cbTipoCancha.setSelectedItem("Seleccionar");
        cbDesCancha.setSelectedItem(null);
    }
    
    public boolean existenDatos(String codigo){
        try {
            String SQL = "select * from tmaecanalq where cod_cancha = '"+codigo+"'";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            while (rs.next()) {                
                if (rs.getString("cod_cancha")!="") {
                    return true;
                }
                else{
                    return false;
                }
            }
        }catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null, "El codigo no existe.\n"
                                     + "Por favor, ingrese un código válido.", 
                                        "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public void mostrarDatos(String codigo){
        try {
            String SQL = "select * from tmaecanalq where cod_cancha = '"+codigo+"'";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SQL);  
            while (rs.next()) {                
            txtObsCancha.setText(rs.getString("obs_cancha"));
            txtPrecioCancha.setText(rs.getString("cos_cancha"));            
            cbTipoCancha.setSelectedItem(rs.getString("tipo_cancha"));
            cbDesCancha.setSelectedItem(rs.getString("des_cancha"));
            cbEstCancha.setSelectedItem(rs.getString("est_cancha"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage());           
        }     
    }
     
    public void insertarDatos(){
        try{
            String SQL="insert into tmaecanalq (cod_cancha, tipo_cancha, des_cancha, cos_cancha, est_cancha, obs_cancha) values (?,?,?,?,?,?)";
            
            PreparedStatement pst = con.prepareStatement(SQL);
            
            pst.setString(1, txtCodCancha.getText());
            pst.setString(2, String.valueOf(cbTipoCancha.getSelectedItem()));
            pst.setString(3, String.valueOf(cbDesCancha.getSelectedItem()));
            pst.setString(4, txtPrecioCancha.getText());
            pst.setString(5, "Libre");
            pst.setString(6, txtObsCancha.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Registro guardado exitosamente", 
                                              "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de registro " + e.getMessage());
        }
    }
    
    public void actualizarDatos(){
        try{
            String SQL="update tmaecanalq set tipo_cancha=?, des_cancha=?, cos_cancha=?, est_cancha=?, obs_cancha=? where cod_cancha = '"+txtCodCancha.getText()+"'";
            
            PreparedStatement pst = con.prepareStatement(SQL);
            
            //pst.setString(1, txtCedulaRuc.getText());
            pst.setString(1, String.valueOf(cbTipoCancha.getSelectedItem()));
            pst.setString(2, String.valueOf(cbDesCancha.getSelectedItem()));
            pst.setString(3, txtPrecioCancha.getText());
            pst.setString(4, String.valueOf(cbEstCancha.getSelectedItem()));
            pst.setString(5, txtObsCancha.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "El registro ha sido actualizado exitosamente", 
                                              "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);         
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Actualización " + e.getMessage());
        }
    }
    
    public void eliminarDatos(String codigo){
        int confirmar = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el registro?");
        try {
            if(confirmar == JOptionPane.YES_OPTION){
                String SQL = "delete from tmaecanalq where cod_cancha = ?";
                PreparedStatement pst = con.prepareStatement(SQL);
                pst.setString(1, codigo);
                if(pst.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "El registro ha sido eliminado exitosamente", 
                                              "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha podido eliminar el registro\n"
                    + "Inténtelo nuevamente.", "Error en la operación", 
                    JOptionPane.ERROR_MESSAGE);
                }
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar el registro\n Inténtelo nuevamente.\n"
                                    + "Error: "+e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
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

        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardarCancha = new javax.swing.JButton();
        btnEliminarCancha = new javax.swing.JButton();
        btnEditarCancha = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObsCancha = new javax.swing.JTextArea();
        txtCodCancha = new app.bolivia.swing.JCTextField();
        txtPrecioCancha = new app.bolivia.swing.JCTextField();
        cbEstCancha = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        cbDesCancha = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cbTipoCancha = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(50, 67, 166));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Registro de Canchas");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardarCancha.setBackground(new java.awt.Color(99, 242, 135));
        btnGuardarCancha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardarCancha.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCancha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar_factura.png"))); // NOI18N
        btnGuardarCancha.setText("Guardar");
        btnGuardarCancha.setBorder(null);
        btnGuardarCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCanchaActionPerformed(evt);
            }
        });

        btnEliminarCancha.setBackground(new java.awt.Color(85, 70, 140));
        btnEliminarCancha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarCancha.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCancha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar_factura.png"))); // NOI18N
        btnEliminarCancha.setText("Eliminar");
        btnEliminarCancha.setBorder(null);
        btnEliminarCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCanchaActionPerformed(evt);
            }
        });

        btnEditarCancha.setBackground(new java.awt.Color(50, 67, 166));
        btnEditarCancha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarCancha.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCancha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar_opt.png"))); // NOI18N
        btnEditarCancha.setText("Editar");
        btnEditarCancha.setBorder(null);
        btnEditarCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCanchaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtObsCancha.setColumns(20);
        txtObsCancha.setRows(5);
        txtObsCancha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txtObsCancha);

        txtCodCancha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtCodCancha.setForeground(new java.awt.Color(51, 51, 51));
        txtCodCancha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCodCancha.setPhColor(new java.awt.Color(255, 255, 255));
        txtCodCancha.setPlaceholder("SEARCH");
        txtCodCancha.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtCodCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodCanchaActionPerformed(evt);
            }
        });

        txtPrecioCancha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtPrecioCancha.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioCancha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPrecioCancha.setPhColor(new java.awt.Color(255, 255, 255));
        txtPrecioCancha.setPlaceholder("SEARCH");
        txtPrecioCancha.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtPrecioCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCanchaActionPerformed(evt);
            }
        });

        cbEstCancha.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        cbEstCancha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libre", "Ocupada" }));
        cbEstCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstCanchaActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cbDesCancha.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        cbDesCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDesCanchaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Descripción");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDesCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDesCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        cbTipoCancha.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        cbTipoCancha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Fútbol", "Indor", "Tenis", "Básquet" }));
        cbTipoCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoCanchaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Tipo:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTipoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecioCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(cbEstCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(cbEstCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(txtCodCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(txtPrecioCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCanchaActionPerformed
        if (!existenDatos(txtCodCancha.getText())) {
            insertarDatos();
            limpiarCajas();
        } else {
            actualizarDatos();
            limpiarCajas();
        } 
    }//GEN-LAST:event_btnGuardarCanchaActionPerformed

    private void txtCodCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodCanchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodCanchaActionPerformed

    private void txtPrecioCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCanchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCanchaActionPerformed

    private void cbDesCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDesCanchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDesCanchaActionPerformed

    private void cbEstCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstCanchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstCanchaActionPerformed

    private void cbTipoCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoCanchaActionPerformed
        if(cbTipoCancha.getSelectedItem()== "Fútbol"){
            cbDesCancha.removeAllItems();
            cbDesCancha.addItem("Césped");
            cbDesCancha.addItem("Sintética");
        }else if(cbTipoCancha.getSelectedItem()== "Indor"){
            cbDesCancha.removeAllItems();
            cbDesCancha.addItem("Cemento");
        }else if(cbTipoCancha.getSelectedItem()== "Tenis"){
            cbDesCancha.removeAllItems();
            cbDesCancha.addItem("Cemento");
            cbDesCancha.addItem("Césped");
        }else if(cbTipoCancha.getSelectedItem()== "Básquet"){
            cbDesCancha.removeAllItems();
            cbDesCancha.addItem("Cemento");
            cbDesCancha.addItem("Madera");
        }
    }//GEN-LAST:event_cbTipoCanchaActionPerformed

    private void btnEditarCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCanchaActionPerformed
        String codigo = txtCodCancha.getText();
        if("Código".equals(codigo)){
            JOptionPane.showMessageDialog(null, "No hay datos para actualizar.\n"
                                     + "Por favor, ingrese un código de cancha en el formulario.",                                      "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }else if (existenDatos(codigo)) {
                mostrarDatos(codigo);   
        } else{
            JOptionPane.showMessageDialog(null, "El código no existe.\n"
                                     + "Por favor, ingrese un código válido.", 
                                       "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarCanchaActionPerformed

    private void btnEliminarCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCanchaActionPerformed
        String codigo = txtCodCancha.getText();
        if("Código".equals(codigo)){
            JOptionPane.showMessageDialog(null, "No hay datos para eliminar.\n"
                                     + "Por favor, ingrese un código de cancha en el formulario.",                                      "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }else{
            eliminarDatos(codigo);
            limpiarCajas();
        } 
    }//GEN-LAST:event_btnEliminarCanchaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarCancha;
    private javax.swing.JButton btnEliminarCancha;
    private javax.swing.JButton btnGuardarCancha;
    private javax.swing.JComboBox<String> cbDesCancha;
    private javax.swing.JComboBox<String> cbEstCancha;
    private javax.swing.JComboBox<String> cbTipoCancha;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private app.bolivia.swing.JCTextField txtCodCancha;
    private javax.swing.JTextArea txtObsCancha;
    private app.bolivia.swing.JCTextField txtPrecioCancha;
    // End of variables declaration//GEN-END:variables
}
