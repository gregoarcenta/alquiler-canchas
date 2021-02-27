package paneles.cliente;

import conexion.ConexionSQL;
import com.placeholder.PlaceHolder;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import validaciones.Validacion;


public final class pnlCliente extends javax.swing.JPanel {
    PlaceHolder holder;
    ConexionSQL cnn = new ConexionSQL();
    Connection con = cnn.conexion();
    Validacion v = new Validacion();

    public pnlCliente() {
       initComponents();
       Placeholder();
       txtTipoCliente.setSelectedItem("Ocasional");
       txtTipoCliente.setEnabled(false);  
       jlCedula.setVisible(false);
       jlemail.setVisible(false);
       jlName.setVisible(false);
       jlCelular.setVisible(false);
       jlConvencional.setVisible(false);
       jlDireccion.setVisible(false);
    }
    
    public void Placeholder() {
        holder = new PlaceHolder(txtCedulaRuc, "RUC/Cèdula");
        txtCedulaRuc.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(txtCedulaRuc);
        holder = new PlaceHolder(txtNombre, "Nombre del representante");
        txtNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
        holder = new PlaceHolder(txtConvencional, "Nùmero Tèlefono Convencional");
        txtConvencional.setFont(new Font("Tahoma", Font.BOLD, 18));
        holder = new PlaceHolder(txtCelular, "Nùmero Tèlefono Celular");
        txtCelular.setFont(new Font("Tahoma", Font.BOLD, 18));
        holder = new PlaceHolder(txtDireccion, "Direcciòn");
        txtDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
        holder = new PlaceHolder(txtCorreo, "Correo");
        txtCorreo.setFont(new Font("Tahoma", Font.BOLD, 18));
    }
    
    public void limpiarCajas(){
        
        txtCedulaRuc.setText("");
        txtNombre.setText("");
        txtCelular.setText("");
        txtConvencional.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtPersoneria.setSelectedItem("Personería");
        txtTipoCliente.setSelectedItem(null);
    }
    
    public boolean existenDatos(String cedula){
        try {
            String SQL = "select * from tmaeclialq where cedruc_cliente = '"+cedula+"'";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            while (rs.next()) {                
                if (rs.getString("cedruc_cliente")!="") {
                    return true;
                }
                else{
                    return false;
                }
            }
        }catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null, "Hubo un error.\n"
                                     + "Por favor, ingrese un ruc o cedula válida.", 
                                        "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
     public void mostrarDatos(String cedula){
        String SQL = "select * from tmaeclialq where cedruc_cliente = '"+cedula+"' ";
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SQL);     
            while (rs.next()) {                
            txtNombre.setText(rs.getString("nom_cliente"));
            txtCelular.setText(rs.getString("telcel_cliente"));
            txtConvencional.setText(rs.getString("telcon_cliente"));
            txtDireccion.setText(rs.getString("dir_cliente"));
            txtCorreo.setText(rs.getString("cor_cliente"));             
            txtTipoCliente.setSelectedItem(rs.getString("tipo_cliente"));
            txtPersoneria.setSelectedItem(rs.getString("per_cliente"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error" + e.getMessage());           
        }     
    }

    public void insertarDatos(){
        try{
            String SQL="insert into tmaeclialq (cedruc_cliente, per_cliente, nom_cliente, telcel_cliente, telcon_cliente, dir_cliente, cor_cliente, tipo_cliente, fec_cliente) values (?,?,?,?,?,?,?,?,CURDATE())";
            
            PreparedStatement pst = con.prepareStatement(SQL);
            
            pst.setString(1, txtCedulaRuc.getText());
            pst.setString(2, String.valueOf(txtPersoneria.getSelectedItem()));
            pst.setString(3, txtNombre.getText());
            pst.setString(4, txtCelular.getText());
            pst.setString(5, txtConvencional.getText());
            pst.setString(6, txtDireccion.getText());
            pst.setString(7, txtCorreo.getText());
            //pst.setString(8, String.valueOf(txtTipoCliente.getSelectedItem()));
            pst.setString(8, "Ocasional");
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Rgistro guardado exitosamente", 
                                              "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);         
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en el registro " + e.getMessage());
        }
    }
    
    public void actualizarDatos(){
        try{        
            String SQL="update tmaeclialq set per_cliente=?, nom_cliente=?, telcel_cliente=?, telcon_cliente=?, dir_cliente=?, cor_cliente=?, tipo_cliente=? where cedruc_cliente = '"+txtCedulaRuc.getText()+"'";
            
            PreparedStatement pst = con.prepareStatement(SQL);
            
            //pst.setString(1, txtCedulaRuc.getText());
            pst.setString(1, String.valueOf(txtPersoneria.getSelectedItem()));
            pst.setString(2, txtNombre.getText());
            pst.setString(3, txtCelular.getText());
            pst.setString(4, txtConvencional.getText());
            pst.setString(5, txtDireccion.getText());
            pst.setString(6, txtCorreo.getText());
            pst.setString(7, String.valueOf(txtTipoCliente.getSelectedItem()));
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "El registro ha sido actualizado exitosamente", 
                                              "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);         
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Actualización " + e.getMessage());
        }
    }
    
    public void eliminarDatos(String cedula){
        int confirmar = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el registro?");
        try {
            if(confirmar == JOptionPane.YES_OPTION){
                String SQL = "delete from tmaeclialq where cedruc_cliente = ?";
                PreparedStatement pst = con.prepareStatement(SQL);
                pst.setString(1, cedula);
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
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        JDFecha = new com.toedter.calendar.JDateChooser();
        JDFecha1 = new com.toedter.calendar.JDateChooser();
        JDFecha2 = new com.toedter.calendar.JDateChooser();
        PnlConsultaFactura = new javax.swing.JPanel();
        txtCedulaRuc = new app.bolivia.swing.JCTextField();
        txtNombre = new app.bolivia.swing.JCTextField();
        txtConvencional = new app.bolivia.swing.JCTextField();
        txtCelular = new app.bolivia.swing.JCTextField();
        txtDireccion = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new app.bolivia.swing.JCTextField();
        jlemail = new javax.swing.JLabel();
        jlCelular = new javax.swing.JLabel();
        jlConvencional = new javax.swing.JLabel();
        jlName = new javax.swing.JLabel();
        jlCedula = new javax.swing.JLabel();
        jlDireccion = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtPersoneria = new javax.swing.JComboBox<>();
        txtTipoCliente = new javax.swing.JComboBox<>();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu1.setText("jMenu1");

        setToolTipText("");

        PnlConsultaFactura.setBackground(new java.awt.Color(255, 255, 255));

        txtCedulaRuc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtCedulaRuc.setForeground(new java.awt.Color(51, 51, 51));
        txtCedulaRuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCedulaRuc.setPhColor(new java.awt.Color(255, 255, 255));
        txtCedulaRuc.setPlaceholder("SEARCH");
        txtCedulaRuc.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtCedulaRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaRucActionPerformed(evt);
            }
        });
        txtCedulaRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaRucKeyReleased(evt);
            }
        });

        txtNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNombre.setPhColor(new java.awt.Color(255, 255, 255));
        txtNombre.setPlaceholder("SEARCH");
        txtNombre.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtConvencional.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtConvencional.setForeground(new java.awt.Color(51, 51, 51));
        txtConvencional.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtConvencional.setPhColor(new java.awt.Color(255, 255, 255));
        txtConvencional.setPlaceholder("SEARCH");
        txtConvencional.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtConvencional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConvencionalActionPerformed(evt);
            }
        });
        txtConvencional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConvencionalKeyReleased(evt);
            }
        });

        txtCelular.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtCelular.setForeground(new java.awt.Color(51, 51, 51));
        txtCelular.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCelular.setPhColor(new java.awt.Color(255, 255, 255));
        txtCelular.setPlaceholder("SEARCH");
        txtCelular.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
        });

        txtDireccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtDireccion.setForeground(new java.awt.Color(51, 51, 51));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtDireccion.setPhColor(new java.awt.Color(255, 255, 255));
        txtDireccion.setPlaceholder("SEARCH");
        txtDireccion.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 67, 166));
        jLabel2.setText("Registro de Cliente");

        txtCorreo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtCorreo.setForeground(new java.awt.Color(51, 51, 51));
        txtCorreo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCorreo.setPhColor(new java.awt.Color(255, 255, 255));
        txtCorreo.setPlaceholder("SEARCH");
        txtCorreo.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });

        jlemail.setBackground(new java.awt.Color(255, 255, 255));
        jlemail.setForeground(new java.awt.Color(255, 0, 0));
        jlemail.setText("Tienes que ingresar un Email valido");

        jlCelular.setBackground(new java.awt.Color(255, 255, 255));
        jlCelular.setForeground(new java.awt.Color(255, 0, 0));
        jlCelular.setText("El Celular tiene que ser de 10 Dígitos");

        jlConvencional.setBackground(new java.awt.Color(255, 255, 255));
        jlConvencional.setForeground(new java.awt.Color(255, 0, 0));
        jlConvencional.setText("El teléfono convencional solo permiten entre 7 a  9 dígitos");

        jlName.setBackground(new java.awt.Color(255, 255, 255));
        jlName.setForeground(new java.awt.Color(255, 0, 0));
        jlName.setText("El nombre solo permite letras y maximo 60 caracteres");

        jlCedula.setBackground(new java.awt.Color(255, 255, 255));
        jlCedula.setForeground(new java.awt.Color(255, 0, 0));
        jlCedula.setText("El Ruc o Cédula solo debe contener numeros y maximo 13 dígitos");

        jlDireccion.setBackground(new java.awt.Color(255, 255, 255));
        jlDireccion.setForeground(new java.awt.Color(255, 0, 0));
        jlDireccion.setText("La direccion solo puede contener letras y numeros");

        javax.swing.GroupLayout PnlConsultaFacturaLayout = new javax.swing.GroupLayout(PnlConsultaFactura);
        PnlConsultaFactura.setLayout(PnlConsultaFacturaLayout);
        PnlConsultaFacturaLayout.setHorizontalGroup(
            PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                .addGap(0, 24, Short.MAX_VALUE)
                .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlName, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlConvencional, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlemail, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtConvencional, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCedulaRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        PnlConsultaFacturaLayout.setVerticalGroup(
            PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(txtCedulaRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jlCedula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jlName)
                .addGap(8, 8, 8)
                .addComponent(txtConvencional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlConvencional)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jlCelular)
                .addGap(5, 5, 5)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jlDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlemail)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        btnGuardar.setBackground(new java.awt.Color(99, 242, 135));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar_factura.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(99, 242, 135));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar_opt.png"))); // NOI18N
        btnActualizar.setText("Editar");
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(85, 70, 140));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar_factura.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtPersoneria.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        txtPersoneria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personería", "Natural", "Jurídica" }));
        txtPersoneria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersoneriaActionPerformed(evt);
            }
        });

        txtTipoCliente.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        txtTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de cliente", "Ocasional", "Premium" }));
        txtTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPersoneria, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtPersoneria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(PnlConsultaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PnlConsultaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaRucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaRucActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtConvencionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConvencionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConvencionalActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtPersoneriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersoneriaActionPerformed
   
    }//GEN-LAST:event_txtPersoneriaActionPerformed

    private void txtTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoClienteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(v.validateCedula(txtCedulaRuc.getText()) && v.validateName(txtNombre.getText()) && v.validateCelular(txtCelular.getText()) && v.validateConvencional(txtConvencional.getText()) && v.validateDireccion(txtDireccion.getText()) && v.validateEmail(txtCorreo.getText()) && txtPersoneria.getSelectedItem() != "Personería"){
            if (!existenDatos(txtCedulaRuc.getText())) {
                insertarDatos();
                limpiarCajas();
            } else {
                actualizarDatos();
                limpiarCajas();
            } 
        }else{
             JOptionPane.showMessageDialog(null, "Los datos ingresados no son los correctos.\n"
                                     + "Por favor, intente nuevamente.", 
                                       "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       String cedula = txtCedulaRuc.getText();
        if("RUC/Cèdula".equals(cedula)){
            JOptionPane.showMessageDialog(null, "No hay datos para actualizar.\n"
                                     + "Por favor, ingrese un ruc/cedula en el formulario.",                                      "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }else if (existenDatos(cedula)) {
                mostrarDatos(cedula);   
        } else{
            JOptionPane.showMessageDialog(null, "El Ruc o cedula ingresado no existe.\n"
                                     + "Por favor, ingrese un número válido.", 
                                       "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String cedula = txtCedulaRuc.getText();
        if("RUC/Cèdula".equals(cedula)){
            JOptionPane.showMessageDialog(null, "No hay datos para eliminar.\n"
                                     + "Por favor, ingrese un numero de ruc/cédula en el formulario.",                                      "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }else{
            eliminarDatos(cedula);
            limpiarCajas();
        } 
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        if(v.validateEmail(txtCorreo.getText())){
            jlemail.setVisible(false);
        }else{
            jlemail.setVisible(true);
        }
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if(v.validateName(txtNombre.getText())){
            jlName.setVisible(false);
        }else{
            jlName.setVisible(true);
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtCedulaRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaRucKeyReleased
        if(v.validateCedula(txtCedulaRuc.getText())){
            jlCedula.setVisible(false);
        }else{
            jlCedula.setVisible(true);
        }
    }//GEN-LAST:event_txtCedulaRucKeyReleased

    private void txtConvencionalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConvencionalKeyReleased
        if(v.validateConvencional(txtConvencional.getText())){
            jlConvencional.setVisible(false);
        }else{
            jlConvencional.setVisible(true);
        }
    }//GEN-LAST:event_txtConvencionalKeyReleased

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        if(v.validateCelular(txtCelular.getText())){
            jlCelular.setVisible(false);
        }else{
            jlCelular.setVisible(true);
        }
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
       if(v.validateDireccion(txtDireccion.getText())){
            jlDireccion.setVisible(false);
        }else{
            jlDireccion.setVisible(true);
        }
    }//GEN-LAST:event_txtDireccionKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDFecha;
    private com.toedter.calendar.JDateChooser JDFecha1;
    private com.toedter.calendar.JDateChooser JDFecha2;
    private javax.swing.JPanel PnlConsultaFactura;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlCedula;
    private javax.swing.JLabel jlCelular;
    private javax.swing.JLabel jlConvencional;
    private javax.swing.JLabel jlDireccion;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlemail;
    private app.bolivia.swing.JCTextField txtCedulaRuc;
    private app.bolivia.swing.JCTextField txtCelular;
    private app.bolivia.swing.JCTextField txtConvencional;
    private app.bolivia.swing.JCTextField txtCorreo;
    private app.bolivia.swing.JCTextField txtDireccion;
    private app.bolivia.swing.JCTextField txtNombre;
    private javax.swing.JComboBox<String> txtPersoneria;
    private javax.swing.JComboBox<String> txtTipoCliente;
    // End of variables declaration//GEN-END:variables

}