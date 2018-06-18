/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ProductoBL;
import Modelo.Natural;
import Modelo.Producto;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class JBuscarProducto extends javax.swing.JDialog {

    private ProductoBL logicaNegocio;
    private ArrayList<Producto> listaProductos;
    private Producto productoElegido;
    private ArrayList<Producto> listaFiltro;

    /**
     * @return the productoElegido
     */
    public Producto getProductoElegido() {
        return productoElegido;
    }

    /**
     * @param productoElegido the productoElegido to set
     */
    public void setProductoElegido(Producto productoElegido) {
        this.productoElegido = productoElegido;
    }

    /**
     * Creates new form JBuscarProducto
     */
    public JBuscarProducto(Dialog f, boolean b) {
        super(f, b);

        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //JFramePedidos.value=0;
            }
        });
        logicaNegocio = new ProductoBL();
        listaProductos = new ArrayList<Producto>(logicaNegocio.listarProducto());
        DefaultTableModel modelo = (DefaultTableModel) tableProducto.getModel();
        Object[] fila = new Object[3];
        for (int i = 0; i < listaProductos.size(); i++) {
            fila[0] = listaProductos.get(i).getidProducto();
            fila[1] = listaProductos.get(i).getnombProducto();
            fila[2] = listaProductos.get(i).getprecio();
            modelo.addRow(fila);
        }
        setTitle("Busqueda de productos");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProducto = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Tabla de Productos");

        tableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableProducto);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre:");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnFiltrar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSeleccionar)
                            .addGap(18, 18, 18)
                            .addComponent(btnSalir))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnSalir)
                    .addComponent(btnSeleccionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        //JFramePedidos.value=0;
        super.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        try {
            if (txtFiltro.getText().isEmpty()) {
                setProductoElegido(new Producto());
                setProductoElegido(listaProductos.get(tableProducto.getSelectedRow()));
                //JFramePedidos.value=2;
                super.dispose();
            } else {
                setProductoElegido(new Producto());
                setProductoElegido(listaFiltro.get(tableProducto.getSelectedRow()));
                super.dispose();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        String filtro = txtFiltro.getText().toLowerCase();
        listaFiltro = new ArrayList<Producto>();
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getnombProducto().toLowerCase().contains(filtro)) {
                listaFiltro.add(listaProductos.get(i));
            }
        }
        DefaultTableModel modelo = (DefaultTableModel) tableProducto.getModel();
        modelo.setRowCount(0);
        Object[] fila = new Object[3];
        for (int i = 0; i < listaFiltro.size(); i++) {
            //fila[0] = listaEmpleados.get(i).getID();
            fila[0] = listaFiltro.get(i).getidProducto();
            fila[1] = listaFiltro.get(i).getnombProducto();
            fila[2] = listaFiltro.get(i).getprecio();
            modelo.addRow(fila);
        }

    }//GEN-LAST:event_btnFiltrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JBuscarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JBuscarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JBuscarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JBuscarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JBuscarProducto(null, false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProducto;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
