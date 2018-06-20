/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.DocumentoPagoBL;
import Modelo.Boleta;
import Modelo.Cliente;
import Modelo.DocumentoPago;
import Modelo.Factura;
import Modelo.Natural;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Victor
 */
public class JFBuscarDocumentosPago extends javax.swing.JDialog {

    private DocumentoPagoBL documentoPagoBL;
    private DocumentoPago documentoSeleccionado;
    private int idPedidoSeleccionado;
    private ArrayList<Object> documentosConExtra;
    private ArrayList<Object> documentosConExtraFiltrados;
    /**
     * Creates new form JFBuscarDocumentosPago
     */
    public JFBuscarDocumentosPago(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initMyComponents();
    }
    
    private void initMyComponents(){
        documentoPagoBL = new DocumentoPagoBL();
        documentosConExtra = new ArrayList<>(); //estos son todos los documentos de la BD
        documentosConExtraFiltrados = new ArrayList<>(); //estos seran por defecto los documentos que se mostraran
        cargarTabla();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaDocsPago.getModel());
        tablaDocsPago.setRowSorter(sorter);
        
    }
    private Object[] obtenerFilaDocumento(Object objTupla){
        Object[] fila = new Object[8]; //columnas: [0]NumDoc [1]TipoDoc [2]ID Pedido [3]FechaR [4]FechaE [5]Cli [6]tipoCli [7]Tot
        Object[] tupla = (Object[]) objTupla; //tupla: [0]=DocPago [1]=FechaRegistro [2]=FechaEntrega [3]=EstadoPedido
                
                
        DocumentoPago documentoPago = (DocumentoPago)tupla[0];
        Cliente cliente = documentoPago.getcliente();
        String tipoDocPago;
        String tipoCliente;
        String nombreCliente;
//                int idDocCliente;

        if(cliente instanceof Natural){
            tipoDocPago = "Boleta";
            tipoCliente = "Natural";
            nombreCliente = ((Boleta)documentoPago).getNombre();
//                    idDocCliente = ((Boleta)documentoPago).getDni();
        }else{
            tipoDocPago = "Factura";
            tipoCliente = "Empresa";
            nombreCliente = ((Factura) documentoPago).getRazonSocial();
//                    idDocCliente = ((Factura) documentoPago).getRuc();

        }

        Date fechaRegistro = (Date)tupla[1];
        Date fechaEntrega = (Date)tupla[2];
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        fila[0] = documentoPago.getidDoc();
        fila[1] = tipoDocPago;
        fila[2] = documentoPago.getidPedido();
//                fila[3] = formatoFecha.format(fechaRegistro); //esto es un string y se ordena como string
//                fila[4] = formatoFecha.format(fechaEntrega);
        fila[3] = fechaRegistro;
        fila[4] = fechaEntrega;
        fila[5] = nombreCliente;
        fila[6] = tipoCliente;
        fila[7] = documentoPago.gettotal();
                
        return fila;
        
    }
    private void cargarTabla(){
        try{
            documentosConExtra = documentoPagoBL.listarDocumentosPagoYExtra();
            documentosConExtraFiltrados = new ArrayList<>(documentosConExtra);
            DefaultTableModel modelo = (DefaultTableModel)tablaDocsPago.getModel();
            for(Object objTupla : documentosConExtra){
                modelo.addRow(obtenerFilaDocumento(objTupla));
            }            
        }catch (Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    public boolean cumpleRequisitos(Object objTupla, ArrayList<String> requisitos) throws Exception{
        //Aun no es generico
        Object[] fila = (Object[])objTupla; //columnas: tupla: [0]=DocPago [1]=FechaRegistro [2]=FechaEntrega [3]=EstadoPedido
        String filtroDocPago = requisitos.get(0);
        String strIdPedido = requisitos.get(1);
        String filtroCliente = requisitos.get(2);
        DocumentoPago docPago = (DocumentoPago)fila[0];
        
        if(!filtroDocPago.equals("")){
            String idDocPago = docPago.getidDoc();
            if(!idDocPago.contains(filtroDocPago))
                return false;
        }
        if(!strIdPedido.equals("")){
            String idPedido = Integer.toString(docPago.getidPedido());
            if(!idPedido.contains(strIdPedido))
                return false;
        }
        
        
        if(!filtroCliente.equals("")){
            String nombre;
            if(docPago instanceof Boleta)
                nombre = ((Boleta)docPago).getNombre();
            else
                nombre = ((Factura)docPago).getRazonSocial();
            if(!nombre.toLowerCase().contains(filtroCliente.toLowerCase()))
                return false;
        }
            
        return true;
    }
    
    private void aplicarFiltro() throws Exception{
        String filtroDocPago = txtNumDocPago.getText().trim();
        String strIdPedido = txtIdPedido.getText().trim();
        String filtroCliente = txtCliente.getText().trim();
        //El filtro es sencillo: se aplica todos los filtros no vacios a la lista original. No es acumulativo
        documentosConExtraFiltrados = new ArrayList<>();
        
        if(filtroDocPago.equals("") && strIdPedido.equals("") && filtroCliente.equals("")){
            DefaultTableModel modelo = (DefaultTableModel)tablaDocsPago.getModel();
            modelo.setRowCount(0);
            for(Object objTupla : documentosConExtra){
                modelo.addRow(obtenerFilaDocumento(objTupla));
            }
            return;
        }
        
        
        

        ArrayList<String> filtros = new ArrayList<>();
        filtros.add(filtroDocPago);
        filtros.add(strIdPedido);
        filtros.add(filtroCliente);
            
        
        for(Object objTupla: documentosConExtra){
            if(cumpleRequisitos(objTupla, filtros)){
                documentosConExtraFiltrados.add(objTupla);
            }
        }
        
        
        DefaultTableModel modelo = (DefaultTableModel)tablaDocsPago.getModel();
        modelo.setRowCount(0);
        for(Object objTupla: documentosConExtraFiltrados){
            modelo.addRow(obtenerFilaDocumento(objTupla));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDocsPago = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNumDocPago = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Documento de Pago");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        tablaDocsPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. Doc. Pago", "Tipo Doc. Pago", "ID Pedido", "Fecha Registro", "Fecha Entrega", "Cliente", "Tipo Cliente", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDocsPago.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaDocsPago);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnMostrarTodo.setText("Mostrar Todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Documentos de Pago");

        jLabel2.setText("Haga click sobre la fila del documento de pago que quiera visualizar y luego presione \"Seleccionar\".");

        jLabel3.setText("Puede utilizar los siguientes filtros de búsqueda:");

        jLabel4.setText("Num. Doc. Pago");

        txtNumDocPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumDocPagoKeyPressed(evt);
            }
        });

        jLabel5.setText("ID Pedido");

        txtIdPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdPedidoKeyPressed(evt);
            }
        });

        jLabel6.setText("Cliente");

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
        });

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMostrarTodo)
                        .addGap(33, 33, 33)
                        .addComponent(btnSeleccionar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNumDocPago)
                                    .addComponent(txtIdPedido)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addComponent(btnFiltrar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNumDocPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnMostrarTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        try{
            Object filaSeleccionada = documentosConExtraFiltrados.get(tablaDocsPago.convertRowIndexToModel(tablaDocsPago.getSelectedRow()));
            
                
            Object[] tupla = (Object[])filaSeleccionada;
            documentoSeleccionado =  (DocumentoPago)tupla[0];
            idPedidoSeleccionado = documentoSeleccionado.getidPedido();
            super.dispose();
        }catch (IndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            
        }catch (Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage() + " del tipo " + e.getClass().getCanonicalName(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        super.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        try{
            DefaultTableModel modelo = (DefaultTableModel)tablaDocsPago.getModel();
            modelo.setRowCount(0);
            for(Object objTupla : documentosConExtra){
                modelo.addRow(obtenerFilaDocumento(objTupla));
            }
        }catch (Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        try{
            aplicarFiltro();
        }catch(Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtNumDocPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumDocPagoKeyPressed
        // TODO add your handling code here:
        try{
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                aplicarFiltro();
        }catch(Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txtNumDocPagoKeyPressed

    private void txtIdPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdPedidoKeyPressed
        // TODO add your handling code here:
        try{
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                aplicarFiltro();
        }catch(Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txtIdPedidoKeyPressed

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
        // TODO add your handling code here:
        try{
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                aplicarFiltro();
        }catch(Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txtClienteKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        try{
            if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
                super.dispose();
        }catch(Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(JFBuscarDocumentosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFBuscarDocumentosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFBuscarDocumentosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFBuscarDocumentosPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                JFBuscarDocumentosPago dialog = new JFBuscarDocumentosPago(null, true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
                dialog.setVisible(true);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDocsPago;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtNumDocPago;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the documentoSeleccionado
     */
    public DocumentoPago getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    /**
     * @param documentoSeleccionado the documentoSeleccionado to set
     */
    public void setDocumentoSeleccionado(DocumentoPago documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    /**
     * @return the idPedidoSeleccionado
     */
    public int getIdPedidoSeleccionado() {
        return idPedidoSeleccionado;
    }

    /**
     * @param idPedidoSeleccionado the idPedidoSeleccionado to set
     */
    public void setIdPedidoSeleccionado(int idPedidoSeleccionado) {
        this.idPedidoSeleccionado = idPedidoSeleccionado;
    }
}
