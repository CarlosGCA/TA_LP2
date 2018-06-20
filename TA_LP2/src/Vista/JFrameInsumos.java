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
    public static JModificarInsumo objJModificarInsumo;
    public static JEliminarInsumo objEliminarInsumo;
    int idMax;
    
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
        
        //obtengo el id con el que compara a ver si es un insumo agregado
        idMax=Integer.parseInt(textID.getText());
        
//        listaInsumo = logicaNegocio.listarInsumo();
//        DefaultTableModel aux= (DefaultTableModel) jTable1.getModel();
//        Object [] fila = new Object [3];
//        unidadMed um;
//         for(int i=0;i<listaInsumo.size();i++){
//            fila[0] = listaInsumo.get(i).getidInsumo();
//            fila[1] = listaInsumo.get(i).getdescripcion();
//            um = listaInsumo.get(i).getunidMed();
//            if(um == unidadMed.kg) fila[2] = "KILOGRAMOS";
//            else if(um == unidadMed.cajas) fila[2] = "CAJAS";
//            else if(um == unidadMed.lt) fila[2] = "LITROS";
//            else if(um == unidadMed.unid) fila[2] = "UNIDADES";            
//            aux.addRow(fila);
//        }
        
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
        btnEliminar = new javax.swing.JToggleButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        textID = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        labelDescripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textDescripcion = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();
        labelCantidad = new javax.swing.JLabel();
        textCantidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Datos de Insumos ");

        labelID.setText("ID");

        labelNombre.setText("Nombre");

        labelMedida.setText("Unidad de Medida");

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

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        labelDescripcion.setText("Descripcion");

        textDescripcion.setColumns(20);
        textDescripcion.setRows(5);
        jScrollPane2.setViewportView(textDescripcion);

        btnLimpiar.setText("Limpiar Datos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        labelCantidad.setText("Cantidad Minima");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(192, 192, 192)
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelID)
                            .addComponent(labelNombre)
                            .addComponent(labelMedida)
                            .addComponent(labelDescripcion)
                            .addComponent(labelCantidad))
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textNombre)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textID)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(textCantidad))
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnEliminar)
                    .addComponent(btnRegistrar)
                    .addComponent(btnModificar))
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
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCantidad)
                    .addComponent(textCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescripcion)
                    .addComponent(btnLimpiar))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        objEliminarInsumo = new JEliminarInsumo(this, true);
        objEliminarInsumo.setVisible(true);
        if(objEliminarInsumo.getResultado()>0)JOptionPane.showMessageDialog(null, "Se ha eliminado con exito", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
       try{
            int idInsumo = Integer.parseInt(textID.getText());
            String nombre = textNombre.getText();
            String auxMedida = (String)jComboBox2.getSelectedItem();
            String descripcion = textDescripcion.getText();
            String auxCant = textCantidad.getText();
            Integer cant= Integer.parseInt(textCantidad.getText());
            
            if(nombre.equals(""))
                throw new Exception("Ingrese un nombre valido");
            
            if(!nombre.matches("^[A-Za-z ]*$"))
                throw new Exception("Nombre ingresado invalido, solo letras y numeros");
            if(!!nombre.matches("^[0-9]*$"))
                throw new Exception("Nombre ingresado invalido, solo letras y numeros");
            if(nombre.length()>60) 
                throw new Exception("Ingrese un nombre mas corto");
            if(descripcion.equals(""))
                throw new Exception ("Ingreso una descripcion valida");
            if(!auxCant.matches("^[0-9]*$"))
                throw new Exception ("La cantidad debe ser un numero entero");
            if(auxCant.equals(""))
                throw new Exception ("Ingrese una cantidad valida");
            
            
            int medida=1;
            for(int i=0;i<unidadesMedida.size();i++){
                String aux = unidadesMedida.get(i);
                if(auxMedida.equals(aux)){
                    medida +=i;
                    break;
                };
            }
            if(idInsumo<idMax){
                int resul=logicaNegocio.modificarInsumo(idInsumo, nombre,medida,descripcion,cant );
                if(resul>0) JOptionPane.showMessageDialog(null, "Se ha actualizado con exito", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                int resul=logicaNegocio.registrarInsumo(idInsumo, nombre,medida,descripcion,cant );
                if(resul>0) JOptionPane.showMessageDialog(null, "Se ha agregado con exito", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
        
            
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        objJModificarInsumo = new JModificarInsumo(this, true);
        objJModificarInsumo.setVisible(true);
        
        if(objJModificarInsumo.getinsumoSeleccionado()!= null){
            Insumo insumoSeleccionado = objJModificarInsumo.getinsumoSeleccionado();
            textID.setText(Integer.toString(insumoSeleccionado.getidInsumo()));
            textNombre.setText(insumoSeleccionado.getNombre());
            textCantidad.setText(Integer.toString(insumoSeleccionado.getCantidaMinima()));
            unidadMed um = insumoSeleccionado.getunidMed();
            String aux=null;
            if(um == unidadMed.kg)  aux= "KILOGRAMOS";
            else if(um == unidadMed.cajas) aux = "CAJAS";
            else if(um == unidadMed.lt) aux = "LITROS";
            else if(um == unidadMed.unid) aux = "UNIDADES";
            jComboBox2.setSelectedItem(aux.toUpperCase());
            textDescripcion.setText(insumoSeleccionado.getdescripcion());
            
            
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        //muestra id correlativo
        textID.setText(String.valueOf(logicaNegocio.obtenerID()));
        textNombre.setText("");
        textDescripcion.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelMedida;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTextField textCantidad;
    private javax.swing.JTextArea textDescripcion;
    private javax.swing.JTextField textID;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
