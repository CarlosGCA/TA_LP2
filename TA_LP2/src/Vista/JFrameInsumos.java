package Vista;

//a @author Sebastian

import Modelo.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Controlador.InsumoBL;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class JFrameInsumos extends javax.swing.JDialog {

    /**
     * Creates new form JFrameInsumos
     */
    private ArrayList<String> unidadesMedida;
    private ArrayList<Insumo> listaInsumo;
    private InsumoBL logicaNegocio;
    
    public JFrameInsumos(Dialog f, Boolean b) {
        super(f,b);
        initComponents();
        
        logicaNegocio = new InsumoBL();
        unidadesMedida = new ArrayList<String>();
        
        //muestra id correlativo
        textID.setText(String.valueOf(logicaNegocio.obtenerID()));
        
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        unidadesMedida = logicaNegocio.listarMedidas();
        for(String v:unidadesMedida){
            String aux=v;
            modelo.addElement(v);
            jComboBox2.setModel(modelo);
        }
        
        listaInsumo = logicaNegocio.listarInsumo();
        DefaultTableModel aux= (DefaultTableModel) jTable1.getModel();
        Object [] fila = new Object [3];
        unidadMed um;
         for(int i=0;i<listaInsumo.size();i++){
            fila[0] = listaInsumo.get(i).getidInsumo();
            fila[1] = listaInsumo.get(i).getdescripcion();
            um = listaInsumo.get(i).getunidMed();
            if(um == unidadMed.kg) fila[2] = "KILOGRAMOS";
            else if(um == unidadMed.cajas) fila[2] = "CAJAS";
            else if(um == unidadMed.lt) fila[2] = "LITROS";
            else if(um == unidadMed.unid) fila[2] = "UNIDADES";            
            aux.addRow(fila);
        }
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });
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
        labelID = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        labelMedida = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEliminar = new javax.swing.JToggleButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        textID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Datos de Insumos ");

        labelID.setText("ID");

        labelNombre.setText("Nombre");

        labelMedida.setText("Unidad de Medida");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Unidad de Medida"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        textID.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(192, 192, 192)
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelID)
                            .addComponent(labelNombre)
                            .addComponent(labelMedida))
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textID)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnEliminar)
                    .addComponent(btnRegistrar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelID)
                    .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMedida)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int indx = jTable1.getSelectedRow();
        int auxID = Integer.parseInt(jTable1.getModel().getValueAt(indx, 0).toString());
        String auxNombreInsumo = jTable1.getModel().getValueAt(indx, 1).toString();
        String auxUnidadMedida = jTable1.getModel().getValueAt(indx, 2).toString();
        logicaNegocio.eliminarInsumo(auxID);
        ((DefaultTableModel)jTable1.getModel()).removeRow(indx);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        int idInsumo = Integer.parseInt(textID.getText());
        String nombre = textNombre.getText();
        String auxMedida = (String)jComboBox2.getSelectedItem();
        int medida=1;
        for(int i=0;i<unidadesMedida.size();i++){
            String aux = unidadesMedida.get(i);
            if(auxMedida.equals(aux)){
                medida +=i;
                break;
            };
        }        
        int resul=logicaNegocio.registrarInsumo(idInsumo, nombre,medida );
        if(resul >0){
            listaInsumo = logicaNegocio.listarInsumo();
            DefaultTableModel aux= (DefaultTableModel) jTable1.getModel();
            Object [] fila = new Object [3];
            unidadMed um;
            for(int i=0;i<listaInsumo.size();i++){
                fila[0] = listaInsumo.get(i).getidInsumo();
                fila[1] = listaInsumo.get(i).getdescripcion();
                um = listaInsumo.get(i).getunidMed();
                if(um == unidadMed.kg) fila[2] = "KILOGRAMOS";
                else if(um == unidadMed.cajas) fila[2] = "CAJAS";
                else if(um == unidadMed.lt) fila[2] = "LITROS";
                else if(um == unidadMed.unid) fila[2] = "UNIDADES";            
                aux.addRow(fila);            
            }
            JOptionPane.showMessageDialog(null, "Se ha agregado con exito", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        } 
            
    }//GEN-LAST:event_btnRegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameInsumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameInsumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameInsumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameInsumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameInsumos(null,false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelMedida;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTextField textID;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
