package paneles.buscar.tipos;

import com.placeholder.PlaceHolder;
import conexion.ConexionSQL;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tipoCliente extends javax.swing.JPanel {
    PlaceHolder holder;
    ConexionSQL c = new ConexionSQL();
    Connection con = c.conexion();
    
    /** Creates new form tipoCliente */
    public tipoCliente() {
        initComponents();
        Placeholder();
        rbCedula.setSelected(true);
        mostrarDatos("select * from tmaeclialq");
    }
    
    public void Placeholder() {
        new PlaceHolder(txtBuscarCliente, "Buscar");
        txtBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
    }
    
    public void mostrarDatos(String SQL){
        
        String[] titulos = {"RUC/Cedula", "Personeria", "Nombre", "Convencional", "Celular", "Dirección", "Correo", "Tipo", "Fecha"};
        String[] registros = new String[9];
        
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("cedruc_cliente");
                registros[1] = rs.getString("per_cliente");
                registros[2] = rs.getString("nom_cliente");
                registros[3] = rs.getString("telcel_cliente");
                registros[4] = rs.getString("telcon_cliente");
                registros[5] = rs.getString("dir_cliente");
                registros[6] = rs.getString("cor_cliente");
                registros[7] = rs.getString("tipo_cliente");
                registros[8] = rs.getString("fec_cliente");
                modelo.addRow(registros);
                tbCliente.setModel(modelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(pnlContenedor, "Error al mostrar los datos " + e.getMessage());
        }
    }
    
    public void filtrarDatos(String valor){
        String personeria = String.valueOf(cbBuscarPersoneria.getSelectedItem()); 
        String tipo = String.valueOf(cbBuscarTipo.getSelectedItem()); 
        String SQL = "";
        if (rbNombre.isSelected()) {
            if (tipo == "Premium" || tipo == "Ocasional") {
                SQL = "select * from tmaeclialq where nom_cliente like '"+valor+"%' AND tipo_cliente like '"+tipo+"'";
                if (personeria == "Natural" || personeria == "Jurídica") {
                    SQL = "select * from tmaeclialq where nom_cliente like '"+valor+"%' AND tipo_cliente like '"+tipo+"' AND per_cliente like '"+personeria+"'";
                }
            }else if (personeria == "Natural" || personeria == "Jurídica") {
                SQL = "select * from tmaeclialq where nom_cliente like '"+valor+"%' AND per_cliente like '"+personeria+"'";
                if (tipo == "Premium" || tipo == "Ocasional") {
                    SQL = "select * from tmaeclialq where nom_cliente like '"+valor+"%' AND tipo_cliente like '"+tipo+"' AND per_cliente like '"+personeria+"'";
                }
            }else{
                SQL = "select * from tmaeclialq where nom_cliente like '"+valor+"%'";   
            }     
            //JOptionPane.showMessageDialog(pnlContenedor, cbBuscarPersoneria.getSelectedItem());
        }else if(rbCedula.isSelected()){
            SQL = "select * from tmaeclialq where cedruc_cliente like '"+valor+"%'";
            //JOptionPane.showMessageDialog(pnlContenedor, "seleccionado nombre!");
        }
        this.mostrarDatos(SQL);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        jpFiltro = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbBuscarTipo = new javax.swing.JComboBox<>();
        cbBuscarPersoneria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCliente = new javax.swing.JTable();
        jpClienteEntrada = new javax.swing.JPanel();
        txtBuscarCliente = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        rbNombre = new javax.swing.JRadioButton();
        rbCedula = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));

        jpFiltro.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Tipo:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Personeria:");

        cbBuscarTipo.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        cbBuscarTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Premium", "Ocasional" }));
        cbBuscarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbBuscarTipoMouseClicked(evt);
            }
        });
        cbBuscarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscarTipoActionPerformed(evt);
            }
        });

        cbBuscarPersoneria.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        cbBuscarPersoneria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Natural", "Jurídica" }));
        cbBuscarPersoneria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbBuscarPersoneriaMouseClicked(evt);
            }
        });
        cbBuscarPersoneria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscarPersoneriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFiltroLayout = new javax.swing.GroupLayout(jpFiltro);
        jpFiltro.setLayout(jpFiltroLayout);
        jpFiltroLayout.setHorizontalGroup(
            jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroLayout.createSequentialGroup()
                .addGroup(jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbBuscarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFiltroLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpFiltroLayout.createSequentialGroup()
                        .addComponent(cbBuscarPersoneria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpFiltroLayout.setVerticalGroup(
            jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBuscarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBuscarPersoneria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        tbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RUC/Cédula", "Personería", "Nombre", "Convencional", "Celular", "Dirección", "Correo", "Tipo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCliente.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tbCliente);

        jpClienteEntrada.setBackground(new java.awt.Color(255, 255, 255));

        txtBuscarCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtBuscarCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarCliente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtBuscarCliente.setPhColor(new java.awt.Color(255, 255, 255));
        txtBuscarCliente.setPlaceholder("SEARCH");
        txtBuscarCliente.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarClienteActionPerformed(evt);
            }
        });
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_small.png"))); // NOI18N

        javax.swing.GroupLayout jpClienteEntradaLayout = new javax.swing.GroupLayout(jpClienteEntrada);
        jpClienteEntrada.setLayout(jpClienteEntradaLayout);
        jpClienteEntradaLayout.setHorizontalGroup(
            jpClienteEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClienteEntradaLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpClienteEntradaLayout.setVerticalGroup(
            jpClienteEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClienteEntradaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpClienteEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        rbNombre.setBackground(new java.awt.Color(255, 255, 255));
        rbNombre.setText("Nombre");
        rbNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNombreActionPerformed(evt);
            }
        });

        rbCedula.setBackground(new java.awt.Color(255, 255, 255));
        rbCedula.setText("RUC/Cèdula");
        rbCedula.setActionCommand("jRadioButton");
        rbCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCedulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(jpFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbCedula)
                            .addComponent(rbNombre))
                        .addGap(28, 28, 28)
                        .addComponent(jpClienteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpClienteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(rbCedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbNombre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rbNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNombreActionPerformed
        rbCedula.setSelected(false);
    }//GEN-LAST:event_rbNombreActionPerformed

    private void rbCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCedulaActionPerformed
        rbNombre.setSelected(false);
    }//GEN-LAST:event_rbCedulaActionPerformed

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        filtrarDatos(txtBuscarCliente.getText());
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void cbBuscarTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBuscarTipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuscarTipoMouseClicked

    private void cbBuscarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscarTipoActionPerformed
        String personeria = String.valueOf(cbBuscarPersoneria.getSelectedItem());
        String tipo = String.valueOf(cbBuscarTipo.getSelectedItem());
        String valor = String.valueOf(txtBuscarCliente.getText());
        String SQL = "";
        if ("Buscar".equals(valor)) {
            if (tipo == "Premium" || tipo == "Ocasional") {
                SQL= "select * from tmaeclialq where tipo_cliente like '"+tipo+"'";
                if (personeria == "Natural" || personeria == "Jurídica") {
                    SQL = "select * from tmaeclialq where tipo_cliente like '"+tipo+"' AND per_cliente like '"+personeria+"'";
                }
            }else if (personeria == "Natural" || personeria == "Jurídica") {
                    SQL = "select * from tmaeclialq where per_cliente like '"+personeria+"'";
            }else{
                SQL = "select * from tmaeclialq";
            }
            mostrarDatos(SQL);
        }else{
            filtrarDatos(valor);
        }
        
    }//GEN-LAST:event_cbBuscarTipoActionPerformed

    private void cbBuscarPersoneriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBuscarPersoneriaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuscarPersoneriaMouseClicked

    private void cbBuscarPersoneriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscarPersoneriaActionPerformed
        String personeria = String.valueOf(cbBuscarPersoneria.getSelectedItem());
        String tipo = String.valueOf(cbBuscarTipo.getSelectedItem());
        String valor = String.valueOf(txtBuscarCliente.getText());
        String SQL = "";
        if ("Buscar".equals(valor)) {
            if (personeria == "Natural" || personeria == "Jurídica") {
                SQL = "select * from tmaeclialq where per_cliente like '"+personeria+"'";
                if (tipo == "Premium" || tipo == "Ocasional") {
                    SQL = "select * from tmaeclialq where tipo_cliente like '"+tipo+"' AND per_cliente like '"+personeria+"'";
                }
            }else if (tipo == "Premium" || tipo == "Ocasional") {
                SQL= "select * from tmaeclialq where tipo_cliente like '"+tipo+"'";
            }else{
                SQL = "select * from tmaeclialq";
            }
            mostrarDatos(SQL);
        }else{
            filtrarDatos(valor);
        }
        
    }//GEN-LAST:event_cbBuscarPersoneriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbBuscarPersoneria;
    private javax.swing.JComboBox<String> cbBuscarTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpClienteEntrada;
    private javax.swing.JPanel jpFiltro;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JRadioButton rbCedula;
    private javax.swing.JRadioButton rbNombre;
    private javax.swing.JTable tbCliente;
    private app.bolivia.swing.JCTextField txtBuscarCliente;
    // End of variables declaration//GEN-END:variables

}
