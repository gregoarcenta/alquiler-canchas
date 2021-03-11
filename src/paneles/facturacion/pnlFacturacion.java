package paneles.facturacion;

import alertas.AlertError;
import alertas.AlertInformation;
import alertas.AlertSuccess;
import conexion.ConexionSQL;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import principal.Principal;
import validaciones.Validacion;

public final class pnlFacturacion extends javax.swing.JPanel {
    ConexionSQL cnn = new ConexionSQL();
    Connection con = cnn.conexion();
    Validacion v = new Validacion();

    private static Connection conexion;
    private static PreparedStatement pst;
    private static ResultSet rs;

    //variables para el boton guardar de facturacion
    int id_cliente;
    int id_cancha;  
    int des_alquiler;
    String fec_alquiler;
    String hor_alquiler;
    String tie_alquiler;
    double pre_alquiler;
    

    public pnlFacturacion() {
        initComponents();
        Placeholder();
    }

    public void Placeholder() {
        txtCedulaRuc.setPlaceholder("RUC/Cédula");
        txtCedulaRuc.setPhColor(new java.awt.Color(102, 102, 102));
        txtCedulaRuc.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtCodigoCancha.setPlaceholder("Código de la cancha");
        txtCodigoCancha.setPhColor(new java.awt.Color(102, 102, 102));
        txtCodigoCancha.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtNombreRepresentante.setPlaceholder("Nombre del representante");
        txtNombreRepresentante.setPhColor(new java.awt.Color(102, 102, 102));
        txtNombreRepresentante.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtNumConvencional.setPlaceholder("Número Télefono Convencional");
        txtNumConvencional.setPhColor(new java.awt.Color(102, 102, 102));
        txtNumConvencional.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtNumCelular.setPlaceholder("Número Télefono Celular");
        txtNumCelular.setPhColor(new java.awt.Color(102, 102, 102));
        txtNumCelular.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtDireccion.setPlaceholder("Dirección");
        txtDireccion.setPhColor(new java.awt.Color(102, 102, 102));
        txtDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtCorreo.setPlaceholder("Correo");
        txtCorreo.setPhColor(new java.awt.Color(102, 102, 102));
        txtCorreo.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtPrecioCanchaConsulta.setPlaceholder("Precio de la Cancha");
        txtPrecioCanchaConsulta.setPhColor(new java.awt.Color(102, 102, 102));
        txtPrecioCanchaConsulta.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtDescuento.setPlaceholder("0%");
        txtDescuento.setPhColor(new java.awt.Color(102, 102, 102));
        txtDescuento.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtHora1.setPlaceholder("hora");
        txtHora1.setPhColor(new java.awt.Color(102, 102, 102));
        txtHora1.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        txtTiempo.setPlaceholder("Tiempo");
        txtTiempo.setPhColor(new java.awt.Color(102, 102, 102));
        txtTiempo.setFont(new Font("Tahoma", Font.BOLD, 18));
    }
    
    public boolean existeCedula(String cedula){
        try {
            String SQL = "select * from tmaeclialq where cedruc_cliente = '"+cedula+"'";
            Statement st=con.createStatement();
            rs = st.executeQuery(SQL);
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
    
    public boolean existeCodigo(String codigo){
        try {
            String SQL = "select * from tmaecanalq where cod_cancha = '"+codigo+"' and est_cancha = 'Libre'";
            Statement st=con.createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {                
                if (rs.getString("cod_cancha")!="") {
                    return true;
                }
                else{
                    return false;
                }
            }
        }catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null, "Hubo un error.\n"
                                     + "Por favor, ingrese un codigo válido.", 
                                        "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public int getDescuento(String personeria, String tipo){
        if (tipo.equals("Premium")) {
            if (personeria.equals("Jurídica")) {
                return 15;
            }else if(personeria.equals("Natural")){
                return 10;
            }
        }
        return 0;
    }

    public void buscarCedulaCliente(String cedruc_cliente) {
        String descuento;

        try {
            conexion = cnn.conexion();
            String SLQ = "SELECT * FROM tmaeclialq WHERE cedruc_cliente = '"+cedruc_cliente+"'";
            pst = conexion.prepareStatement(SLQ);
            rs = pst.executeQuery();

            if (rs.next()) {
                descuento = getDescuento(rs.getString("per_cliente"), rs.getString("tipo_cliente")) + "%";
                this.id_cliente = rs.getInt("id_cliente");
                txtNombreRepresentante.setText(rs.getString("nom_cliente"));
                txtNumConvencional.setText(rs.getString("telcon_cliente"));
                txtNumCelular.setText(rs.getString("telcel_cliente"));
                txtDireccion.setText(rs.getString("dir_cliente"));
                txtCorreo.setText(rs.getString("cor_cliente"));
                CbxPersoneria.setSelectedItem(rs.getString("per_cliente"));
                txtDescuento.setText(descuento);
                jLDescuento.setText(descuento);
                bloquearCajasTextos();
                
            } else {
                JOptionPane.showMessageDialog(null, "cedula no encontrada");
                Limpiar();
            }

            conexion.close();

        } catch (HeadlessException | SQLException e) {
            System.out.println("Error al buscar el RUC/Cédula " + e);
        }
    }

    //Busqueda de facturacion por codigo
    public void buscarCodigoCancha(String cod_cancha) {
        try {
            conexion = cnn.conexion();
            String consulta = "SELECT * FROM tmaecanalq WHERE cod_cancha = '"+cod_cancha+"'";
            pst = conexion.prepareStatement(consulta);
            rs = pst.executeQuery();

            if (rs.next()) {
                 this.id_cancha = rs.getInt("id_cancha");
                txtPrecioCanchaConsulta.setText(rs.getString("cos_cancha"));
                bloquearCajasTextos();

            } else {
                JOptionPane.showMessageDialog(null, "EL código no existe");
                Limpiar();
            }
            conexion.close();

        } catch (HeadlessException | SQLException e) {
            System.out.println("Error al buscar el código " + e);
        }
    }
    
    public void guardar_nuevaConsulta_factura(int id_cliente,int id_cancha, String fec_alquiler,
            String hor_alquiler, double pre_alquiler, String tie_alquiler, int des_alquiler) {

        try {
            conexion = cnn.conexion();

            //String consulta = "INSERT INTO ttraalqalq INNER JOIN tmaeclialq ON ttraalqalq.id_cliente=tmaeclialq.id_cliente"
            //+ "INNER JOIN tmaecanalq ON ttraalqalq.id_cancha=tmaecanalq.id_cancha"
            //+ "(id_alquiler,id_cliente,id_cancha,fec_alquiler,hor_alquiler,pre_alquiler,tie_alquiler,des_alquiler) VALUES (?,?,?,?,?,?,?,?)";
            String consulta = "INSERT INTO ttraalqalq (id_cliente,id_cancha,fec_alquiler,hor_alquiler,pre_alquiler,tie_alquiler,des_alquiler) VALUES (?,?,?,?,?,?,?) ";
            pst = conexion.prepareStatement(consulta);
            //resultado = sentencia_preparada.executeQuery();
            //sentencia_preparada.setInt(1, id_alquiler);
            pst.setInt(1, id_cliente);
            pst.setInt(2, id_cancha);
            pst.setString(3, fec_alquiler);
            pst.setString(4, hor_alquiler);
            pst.setDouble(5, pre_alquiler);
            pst.setString(6, tie_alquiler);
            pst.setDouble(7, des_alquiler);
            int i = pst.executeUpdate();
            actualizarEstado(txtCodigoCancha.getText());
            if (i > 0) {
                new AlertSuccess(new Principal(), true).setVisible(true); 
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
    
    public void actualizarEstado(String codigo){
        try{
            String SQL="update tmaecanalq set est_cancha=? where cod_cancha = '"+ codigo +"'";
            
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, "Ocupada");          
            pst.execute();    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la Actualización " + e.getMessage());
        }
    }

    public void bloquearCajasTextos() {
        txtCedulaRuc.setEnabled(false);
        txtCodigoCancha.setEnabled(false);
        txtNombreRepresentante.setEnabled(false);
        txtNumConvencional.setEnabled(false);
        txtNumCelular.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtPrecioCanchaConsulta.setEnabled(false);
        CbxPersoneria.setEnabled(false);
        txtDescuento.setEnabled(false);
    }

    public void desbloquear() {
        txtCedulaRuc.setEnabled(true);
        txtCodigoCancha.setEnabled(true);
        //txtNombreRepresentante.setEnabled(true);
        //txtNumConvencional.setEnabled(true);
        //txtNumCelular.setEnabled(true);
        //txtDireccion.setEnabled(true);
        //txtCorreo.setEnabled(true);
        //txtPrecioCanchaConsulta.setEnabled(true);
        //CbxPersoneria.setEnabled(true);
        //txtDescuento.setEnabled(true);

    }

    public void Limpiar() {
        txtCedulaRuc.setText("");
        txtCodigoCancha.setText("");
        txtNombreRepresentante.setText("");
        txtNumConvencional.setText("");
        txtNumCelular.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtPrecioCanchaConsulta.setText("");
        CbxPersoneria.setSelectedIndex(0);
        txtDescuento.setText("");
    }

    public void calcularPrecio() {
        double subTotal;
        double costoTotal;
        double descuento = 0;
        String pattern = ".##";
        Locale locale  = new Locale("en", "UK");
        DecimalFormat format = (DecimalFormat)NumberFormat.getNumberInstance(locale);
        format.applyPattern(pattern);
        double costoHora = Double.parseDouble(txtPrecioCanchaConsulta.getText());
        double tiempoAlquiler = Double.parseDouble(txtTiempo.getText());
        String desString = String.valueOf(txtDescuento.getText());
        if (desString.length() == 3) {
            descuento = Double.parseDouble(desString.substring(0,2));
        }
        
        try {
            subTotal = costoHora * tiempoAlquiler;         
            txtPrecio.setText(format.format(subTotal));
            jLSubtotal.setText(format.format(subTotal));
            if (descuento>0) {
                double totalConDescuento = calcularDescuento(subTotal, descuento);
                costoTotal = totalConDescuento + (totalConDescuento * 0.12);
            }else{
                costoTotal = subTotal + ( subTotal * 0.12 );
            }
            jLIvaFactura.setText("0.12");
            jLTotalFactura.setText(format.format(costoTotal));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No deben quedar campos vacios");
        }
    }

    public double calcularDescuento(double subtotal, double descuento) {    
        double totalConDescuento = subtotal - ((subtotal * descuento)/100);
        return totalConDescuento;
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
        jLabel7 = new javax.swing.JLabel();
        PnlConsultaFactura = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCedulaRuc = new app.bolivia.swing.JCTextField();
        txtCodigoCancha = new app.bolivia.swing.JCTextField();
        txtNombreRepresentante = new app.bolivia.swing.JCTextField();
        txtNumConvencional = new app.bolivia.swing.JCTextField();
        txtNumCelular = new app.bolivia.swing.JCTextField();
        txtDescuento = new app.bolivia.swing.JCTextField();
        txtDireccion = new app.bolivia.swing.JCTextField();
        CbxPersoneria = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new app.bolivia.swing.JCTextField();
        btnBuscarCedula = new javax.swing.JButton();
        btnBuscarCodigoCancha = new javax.swing.JButton();
        txtPrecioCanchaConsulta = new app.bolivia.swing.JCTextField();
        PnlMuestraFactura = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCalcularPrecioFactura = new javax.swing.JButton();
        txtTiempo = new app.bolivia.swing.JCTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnEliminarFecha = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        tableFacturacion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtPrecio = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jLSubtotal = new javax.swing.JLabel();
        jLTotalFactura = new javax.swing.JLabel();
        jLIvaFactura = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHora1 = new app.bolivia.swing.JCTextField();
        jLDescuento = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        btnImprimirFactura = new javax.swing.JButton();
        btnEliminarFactura = new javax.swing.JButton();
        btnGuardarFactura = new javax.swing.JButton();

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(50, 67, 166));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Facturación");

        PnlConsultaFactura.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 67, 166));
        jLabel1.setText("Descuento:");

        txtCedulaRuc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtCedulaRuc.setForeground(new java.awt.Color(51, 51, 51));
        txtCedulaRuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCedulaRuc.setPhColor(new java.awt.Color(255, 255, 255));
        txtCedulaRuc.setPlaceholder("SEARCH");
        txtCedulaRuc.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtCedulaRuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCedulaRucMouseEntered(evt);
            }
        });

        txtCodigoCancha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtCodigoCancha.setForeground(new java.awt.Color(51, 51, 51));
        txtCodigoCancha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCodigoCancha.setPhColor(new java.awt.Color(255, 255, 255));
        txtCodigoCancha.setPlaceholder("SEARCH");
        txtCodigoCancha.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtCodigoCancha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCodigoCanchaMouseEntered(evt);
            }
        });

        txtNombreRepresentante.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtNombreRepresentante.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreRepresentante.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNombreRepresentante.setPhColor(new java.awt.Color(255, 255, 255));
        txtNombreRepresentante.setPlaceholder("SEARCH");
        txtNombreRepresentante.setSelectedTextColor(new java.awt.Color(51, 51, 51));

        txtNumConvencional.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtNumConvencional.setForeground(new java.awt.Color(51, 51, 51));
        txtNumConvencional.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNumConvencional.setPhColor(new java.awt.Color(255, 255, 255));
        txtNumConvencional.setPlaceholder("SEARCH");
        txtNumConvencional.setSelectedTextColor(new java.awt.Color(51, 51, 51));

        txtNumCelular.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtNumCelular.setForeground(new java.awt.Color(51, 51, 51));
        txtNumCelular.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNumCelular.setPhColor(new java.awt.Color(255, 255, 255));
        txtNumCelular.setPlaceholder("SEARCH");
        txtNumCelular.setSelectedTextColor(new java.awt.Color(51, 51, 51));

        txtDescuento.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtDescuento.setForeground(new java.awt.Color(51, 51, 51));
        txtDescuento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtDescuento.setPhColor(new java.awt.Color(255, 255, 255));
        txtDescuento.setPlaceholder("SEARCH");
        txtDescuento.setSelectedTextColor(new java.awt.Color(51, 51, 51));

        txtDireccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtDireccion.setForeground(new java.awt.Color(51, 51, 51));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtDireccion.setPhColor(new java.awt.Color(255, 255, 255));
        txtDireccion.setPlaceholder("SEARCH");
        txtDireccion.setSelectedTextColor(new java.awt.Color(51, 51, 51));

        CbxPersoneria.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        CbxPersoneria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personería-Seleccione", "Natural", "Jurídica" }));
        CbxPersoneria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxPersoneriaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 67, 166));
        jLabel2.setText("Nueva Consulta");

        txtCorreo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtCorreo.setForeground(new java.awt.Color(51, 51, 51));
        txtCorreo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCorreo.setPhColor(new java.awt.Color(255, 255, 255));
        txtCorreo.setPlaceholder("SEARCH");
        txtCorreo.setSelectedTextColor(new java.awt.Color(51, 51, 51));

        btnBuscarCedula.setBackground(new java.awt.Color(50, 67, 166));
        btnBuscarCedula.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscarCedula.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCedula.setText("Buscar");
        btnBuscarCedula.setBorder(null);
        btnBuscarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCedulaActionPerformed(evt);
            }
        });

        btnBuscarCodigoCancha.setBackground(new java.awt.Color(50, 67, 166));
        btnBuscarCodigoCancha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscarCodigoCancha.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCodigoCancha.setText("Buscar");
        btnBuscarCodigoCancha.setBorder(null);
        btnBuscarCodigoCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCodigoCanchaActionPerformed(evt);
            }
        });

        txtPrecioCanchaConsulta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtPrecioCanchaConsulta.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioCanchaConsulta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPrecioCanchaConsulta.setPhColor(new java.awt.Color(255, 255, 255));
        txtPrecioCanchaConsulta.setPlaceholder("SEARCH");
        txtPrecioCanchaConsulta.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtPrecioCanchaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCanchaConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlConsultaFacturaLayout = new javax.swing.GroupLayout(PnlConsultaFactura);
        PnlConsultaFactura.setLayout(PnlConsultaFacturaLayout);
        PnlConsultaFacturaLayout.setHorizontalGroup(
            PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlConsultaFacturaLayout.createSequentialGroup()
                            .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(CbxPersoneria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombreRepresentante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNumConvencional, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNumCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                            .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCedulaRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarCodigoCancha, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                .addComponent(btnBuscarCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(txtPrecioCanchaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(337, Short.MAX_VALUE)))
        );
        PnlConsultaFacturaLayout.setVerticalGroup(
            PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlConsultaFacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCedulaRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarCodigoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                        .addComponent(txtNombreRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtNumConvencional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtNumCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtPrecioCanchaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(CbxPersoneria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
            .addGroup(PnlConsultaFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PnlConsultaFacturaLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel2)
                    .addContainerGap(411, Short.MAX_VALUE)))
        );

        PnlMuestraFactura.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        btnCalcularPrecioFactura.setBackground(new java.awt.Color(255, 255, 255));
        btnCalcularPrecioFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcularPrecioFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mas_registrar.png"))); // NOI18N
        btnCalcularPrecioFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularPrecioFacturaActionPerformed(evt);
            }
        });

        txtTiempo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtTiempo.setForeground(new java.awt.Color(51, 51, 51));
        txtTiempo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTiempo.setPhColor(new java.awt.Color(255, 255, 255));
        txtTiempo.setPlaceholder("SEARCH");
        txtTiempo.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        btnEliminarFecha.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarFecha.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar_hora.png"))); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jTable1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Hora", "Tiempo", "Precio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout tableFacturacionLayout = new javax.swing.GroupLayout(tableFacturacion);
        tableFacturacion.setLayout(tableFacturacionLayout);
        tableFacturacionLayout.setHorizontalGroup(
            tableFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableFacturacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tableFacturacionLayout.setVerticalGroup(
            tableFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableFacturacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(153, 153, 153));
        txtPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPrecio.setText("Precio");
        txtPrecio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Descuento:");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("IVA 12%:");

        Total.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        Total.setForeground(new java.awt.Color(153, 153, 153));
        Total.setText("Total:");

        jLSubtotal.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLSubtotal.setForeground(new java.awt.Color(153, 153, 153));
        jLSubtotal.setText("0.00");

        jLTotalFactura.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLTotalFactura.setForeground(new java.awt.Color(153, 153, 153));
        jLTotalFactura.setText("0.00");

        jLIvaFactura.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLIvaFactura.setForeground(new java.awt.Color(153, 153, 153));
        jLIvaFactura.setText("0.00");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Subtotal:");

        txtHora1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtHora1.setForeground(new java.awt.Color(51, 51, 51));
        txtHora1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtHora1.setPhColor(new java.awt.Color(255, 255, 255));
        txtHora1.setPlaceholder("SEARCH");
        txtHora1.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtHora1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHora1ActionPerformed(evt);
            }
        });

        jLDescuento.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLDescuento.setForeground(new java.awt.Color(153, 153, 153));
        jLDescuento.setText("0.00");

        javax.swing.GroupLayout PnlMuestraFacturaLayout = new javax.swing.GroupLayout(PnlMuestraFactura);
        PnlMuestraFactura.setLayout(PnlMuestraFacturaLayout);
        PnlMuestraFacturaLayout.setHorizontalGroup(
            PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlMuestraFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlMuestraFacturaLayout.createSequentialGroup()
                        .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlMuestraFacturaLayout.createSequentialGroup()
                                .addComponent(btnCalcularPrecioFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PnlMuestraFacturaLayout.createSequentialGroup()
                                .addComponent(btnEliminarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tableFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlMuestraFacturaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(Total)
                            .addComponent(jLabel6))
                        .addGap(82, 82, 82)
                        .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLSubtotal)
                            .addComponent(jLIvaFactura)
                            .addComponent(jLTotalFactura)
                            .addComponent(jLDescuento))
                        .addGap(21, 21, 21))))
        );
        PnlMuestraFacturaLayout.setVerticalGroup(
            PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlMuestraFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCalcularPrecioFactura)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlMuestraFacturaLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnEliminarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlMuestraFacturaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLSubtotal)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLDescuento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLIvaFactura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlMuestraFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Total)
                    .addComponent(jLTotalFactura))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btnImprimirFactura.setBackground(new java.awt.Color(50, 67, 166));
        btnImprimirFactura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnImprimirFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inprimir_factura.png"))); // NOI18N
        btnImprimirFactura.setText("Imprimir");
        btnImprimirFactura.setBorder(null);

        btnEliminarFactura.setBackground(new java.awt.Color(85, 70, 140));
        btnEliminarFactura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar_factura.png"))); // NOI18N
        btnEliminarFactura.setText("Eliminar");
        btnEliminarFactura.setBorder(null);
        btnEliminarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFacturaActionPerformed(evt);
            }
        });

        btnGuardarFactura.setBackground(new java.awt.Color(99, 242, 135));
        btnGuardarFactura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardarFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar_factura.png"))); // NOI18N
        btnGuardarFactura.setText("Guardar");
        btnGuardarFactura.setBorder(null);
        btnGuardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PnlConsultaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnlMuestraFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnEliminarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimirFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlConsultaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlMuestraFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimirFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CbxPersoneriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxPersoneriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxPersoneriaActionPerformed

    private void btnGuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFacturaActionPerformed
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (txtFecha.getDate()!= null) {
            this.fec_alquiler = formatter.format(txtFecha.getDate());
        }else{
            AlertError a = new AlertError(new Principal(), true);
            a.titulo.setText("La fecha ingresada no es correcta");
            a.setVisible(true);
            return;
        }
 
        this.hor_alquiler = txtHora1.getText();
        this.tie_alquiler = txtTiempo.getText();        
        this.pre_alquiler = Double.parseDouble(jLTotalFactura.getText());
        String desString = String.valueOf(txtDescuento.getText());
        this.des_alquiler = Integer.parseInt(desString.substring(0,1));
        if (desString.length() == 3) {
            this.des_alquiler = Integer.parseInt(desString.substring(0,2));
        }
        
        
        if(v.validateId(this.id_cliente) && v.validateId(this.id_cancha) && v.validateFecha(this.fec_alquiler) && v.validateHora(this.hor_alquiler) && v.validateId(Integer.parseInt(this.tie_alquiler)) && v.validatePrecio(String.valueOf(this.pre_alquiler)) && v.validateDescuento(this.des_alquiler) ){
            if (existeCedula(txtCedulaRuc.getText())) {
                guardar_nuevaConsulta_factura(this.id_cliente, this.id_cancha, this.fec_alquiler, this.hor_alquiler, this.pre_alquiler, this.tie_alquiler, this.des_alquiler);             
            } else {
                AlertError a = new AlertError(new Principal(), true);
                a.titulo.setText("Por favor, rellena el formulario correctamente");
                a.setVisible(true);
            } 
        }else{
            AlertError a = new AlertError(new Principal(), true);
            a.titulo.setText("Por favor, rellena el formulario correctamente:<");
            a.setVisible(true);
        }
        } catch (NumberFormatException e) {
            AlertError a = new AlertError(new Principal(), true);
            a.titulo.setText("Uno de los campos no es el correcto");
            a.titulo2.setText("Por favor, intenta nuevamente!");
            a.titulo2.setForeground(Color.BLACK);
            a.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardarFacturaActionPerformed

    private void txtTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoActionPerformed

    private void txtPrecioCanchaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCanchaConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCanchaConsultaActionPerformed

    private void txtHora1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHora1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHora1ActionPerformed

    private void btnBuscarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCedulaActionPerformed
        String cedula = txtCedulaRuc.getText();
        if("RUC/Cédula".equals(cedula) || "".equals(cedula)){ 
            new AlertError(new Principal(), true).setVisible(true);
        }else if (existeCedula(cedula)) {
                buscarCedulaCliente(cedula); 
        } else {
            AlertInformation a = new AlertInformation(new Principal(), true);
            a.titulo.setText("Ruc o cedula ingresado no valido");
            a.titulo2.setText("Inténtelo nuevamente!");
            a.titulo2.setForeground(Color.BLACK);
            a.setVisible(true);
        }  
    }//GEN-LAST:event_btnBuscarCedulaActionPerformed

    private void btnBuscarCodigoCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCodigoCanchaActionPerformed
         String codigo = txtCodigoCancha.getText();
        if("Código de la cancha".equals(codigo) || "".equals(codigo)){ 
           AlertError a = new AlertError(new Principal(), true);
           a.titulo.setText("Por favor, ingresa un código valido");
           a.setVisible(true);
        }else if (existeCodigo(codigo)) {
                buscarCodigoCancha(codigo);
        } else {
            AlertInformation a = new AlertInformation(new Principal(), true);
            a.titulo.setText("El código ingresado no es valido");
            a.titulo2.setText("o la cancha ya esta ocupada!");
            a.titulo2.setForeground(Color.BLACK);
            a.setVisible(true);
        }  
        
    }//GEN-LAST:event_btnBuscarCodigoCanchaActionPerformed

    private void txtCedulaRucMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCedulaRucMouseEntered
        desbloquear();
    }//GEN-LAST:event_txtCedulaRucMouseEntered

    private void txtCodigoCanchaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoCanchaMouseEntered
        desbloquear();
    }//GEN-LAST:event_txtCodigoCanchaMouseEntered

    private void btnCalcularPrecioFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularPrecioFacturaActionPerformed
        calcularPrecio();
        
    }//GEN-LAST:event_btnCalcularPrecioFacturaActionPerformed

    private void btnEliminarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFacturaActionPerformed
        
    }//GEN-LAST:event_btnEliminarFacturaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbxPersoneria;
    private javax.swing.JPanel PnlConsultaFactura;
    private javax.swing.JPanel PnlMuestraFactura;
    private javax.swing.JLabel Total;
    private javax.swing.JButton btnBuscarCedula;
    private javax.swing.JButton btnBuscarCodigoCancha;
    private javax.swing.JButton btnCalcularPrecioFactura;
    private javax.swing.JButton btnEliminarFactura;
    private javax.swing.JButton btnEliminarFecha;
    private javax.swing.JButton btnGuardarFactura;
    private javax.swing.JButton btnImprimirFactura;
    private javax.swing.JLabel jLDescuento;
    private javax.swing.JLabel jLIvaFactura;
    private javax.swing.JLabel jLSubtotal;
    private javax.swing.JLabel jLTotalFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel tableFacturacion;
    private app.bolivia.swing.JCTextField txtCedulaRuc;
    private app.bolivia.swing.JCTextField txtCodigoCancha;
    private app.bolivia.swing.JCTextField txtCorreo;
    private app.bolivia.swing.JCTextField txtDescuento;
    private app.bolivia.swing.JCTextField txtDireccion;
    private com.toedter.calendar.JDateChooser txtFecha;
    private app.bolivia.swing.JCTextField txtHora1;
    private app.bolivia.swing.JCTextField txtNombreRepresentante;
    private app.bolivia.swing.JCTextField txtNumCelular;
    private app.bolivia.swing.JCTextField txtNumConvencional;
    private javax.swing.JLabel txtPrecio;
    private app.bolivia.swing.JCTextField txtPrecioCanchaConsulta;
    private app.bolivia.swing.JCTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
