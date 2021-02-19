/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles.buscar.tipos;

import com.placeholder.PlaceHolder;
import java.awt.Font;

/**
 *
 * @author Arcentales
 */
public class tipoCancha extends javax.swing.JPanel {
    PlaceHolder holder;
    /**
     * Creates new form tipoCancha
     */
    public tipoCancha() {
        initComponents();
        Placeholder();
    }

    public void Placeholder() {
        holder = new PlaceHolder(txtBuscarCancha, "Buscar");
        txtBuscarCancha.setFont(new Font("Tahoma", Font.BOLD, 16));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor2 = new javax.swing.JPanel();
        jpTipo2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        rbCanchaTipo = new javax.swing.JRadioButton();
        rbCanchaEstado = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCancha = new javax.swing.JTable();
        jpClienteEntrada = new javax.swing.JPanel();
        txtBuscarCancha = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlContenedor2.setBackground(new java.awt.Color(255, 255, 255));

        jpTipo2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("Tipo de Búsqueda:");

        rbCanchaTipo.setBackground(new java.awt.Color(255, 255, 255));
        rbCanchaTipo.setText("Estado");
        rbCanchaTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCanchaTipoActionPerformed(evt);
            }
        });

        rbCanchaEstado.setBackground(new java.awt.Color(255, 255, 255));
        rbCanchaEstado.setText("Tipo");
        rbCanchaEstado.setActionCommand("jRadioButton");
        rbCanchaEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCanchaEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpTipo2Layout = new javax.swing.GroupLayout(jpTipo2);
        jpTipo2.setLayout(jpTipo2Layout);
        jpTipo2Layout.setHorizontalGroup(
            jpTipo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTipo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jpTipo2Layout.createSequentialGroup()
                        .addComponent(rbCanchaEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbCanchaTipo)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jpTipo2Layout.setVerticalGroup(
            jpTipo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipo2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpTipo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCanchaTipo)
                    .addComponent(rbCanchaEstado)))
        );

        tbCancha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Tipo", "Descripción", "Costo por hora", "Estado", "Observaciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbCancha);

        jpClienteEntrada.setBackground(new java.awt.Color(255, 255, 255));

        txtBuscarCancha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(51, 51, 51)));
        txtBuscarCancha.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarCancha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtBuscarCancha.setPhColor(new java.awt.Color(255, 255, 255));
        txtBuscarCancha.setPlaceholder("SEARCH");
        txtBuscarCancha.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        txtBuscarCancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCanchaActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N

        javax.swing.GroupLayout jpClienteEntradaLayout = new javax.swing.GroupLayout(jpClienteEntrada);
        jpClienteEntrada.setLayout(jpClienteEntradaLayout);
        jpClienteEntradaLayout.setHorizontalGroup(
            jpClienteEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClienteEntradaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpClienteEntradaLayout.setVerticalGroup(
            jpClienteEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClienteEntradaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpClienteEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout pnlContenedor2Layout = new javax.swing.GroupLayout(pnlContenedor2);
        pnlContenedor2.setLayout(pnlContenedor2Layout);
        pnlContenedor2Layout.setHorizontalGroup(
            pnlContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedor2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedor2Layout.createSequentialGroup()
                        .addComponent(jpTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jpClienteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContenedor2Layout.setVerticalGroup(
            pnlContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedor2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpClienteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContenedor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContenedor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rbCanchaTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCanchaTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbCanchaTipoActionPerformed

    private void rbCanchaEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCanchaEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbCanchaEstadoActionPerformed

    private void txtBuscarCanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCanchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCanchaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpClienteEntrada;
    private javax.swing.JPanel jpTipo;
    private javax.swing.JPanel jpTipo1;
    private javax.swing.JPanel jpTipo2;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlContenedor1;
    private javax.swing.JPanel pnlContenedor2;
    private javax.swing.JRadioButton rbCanchaEstado;
    private javax.swing.JRadioButton rbCanchaTipo;
    private javax.swing.JRadioButton rbClienteEstado;
    private javax.swing.JRadioButton rbClienteEstado1;
    private javax.swing.JRadioButton rbClienteTipo;
    private javax.swing.JRadioButton rbClienteTipo1;
    private javax.swing.JTable tbCancha;
    private app.bolivia.swing.JCTextField txtBuscarCancha;
    // End of variables declaration//GEN-END:variables
}
