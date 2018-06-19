/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ClientesBL;
import Modelo.Empresa;
import Modelo.EstadoPedido;
import Modelo.Natural;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javafx.util.Pair;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alfredo
 */
public class jFrameRankingClienteDialog extends javax.swing.JDialog {

    /**
     * Creates new form jFrameRankingClienteDialog
     */
    
    private ClientesBL logicaClientesBL;

    private void _llenarTablaNatural(EstadoPedido estado) {
        for (Pair<Integer, Natural> objPair : logicaClientesBL.obtenerRankingNatural(estado)) {
            Vector nuevoVector = new Vector();
            nuevoVector.add(objPair.getKey());
            Natural nuevoNatural = (Natural) objPair.getValue();

            nuevoVector.add(nuevoNatural.getDNI());
            nuevoVector.add(nuevoNatural.getNombre());
            nuevoVector.add(nuevoNatural.getApellidos());
            nuevoVector.add(nuevoNatural.getTelefono());
            nuevoVector.add(nuevoNatural.getDireccion());
            nuevoVector.add(nuevoNatural.getCuentaBancaria());
            nuevoVector.add(nuevoNatural.getCorreo());

            DefaultTableModel model = (DefaultTableModel) tbNatural.getModel();
            model.addRow(nuevoVector);
        }
    }

    private void _llenarTablaEmpresa(EstadoPedido estado){
         for (Pair<Integer, Empresa> objPair : logicaClientesBL.obtenerRankingEmpresa(estado)) {
            Vector nuevoVector = new Vector();
            nuevoVector.add(objPair.getKey());
            Empresa nuevoNatural = (Empresa) objPair.getValue();
            nuevoVector.add(nuevoNatural.getRuc());
            nuevoVector.add(nuevoNatural.getRazonSocial());
            nuevoVector.add(nuevoNatural.getTelefono());
            nuevoVector.add(nuevoNatural.getDireccion());
            nuevoVector.add(nuevoNatural.getCuentaBancaria());
            nuevoVector.add(nuevoNatural.getCorreo());

            DefaultTableModel model = (DefaultTableModel) tbEmpresa.getModel();
            model.addRow(nuevoVector);
        }
    }
    
    private void llenarRanking(EstadoPedido estado, String tipo) {
        if (tipo.equals("Empresa")){
            _llenarTablaEmpresa(estado);
        } else {
            _llenarTablaNatural(estado);
        }
    }
    
    private void agregarActionCambioComboBox(){
        cbEstado.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                JComboBox combo = (JComboBox) event.getSource();
                String seleccionado =  (String) combo.getSelectedItem();
                int indexTabSeleccionado = tabPanel.getSelectedIndex();
                if (indexTabSeleccionado == 0){
                    DefaultTableModel model = (DefaultTableModel) tbNatural.getModel();
                    model.setRowCount(0);
                    llenarRanking(EstadoPedido.getValue(seleccionado), "Natural");
                
                } else {
                    DefaultTableModel model = (DefaultTableModel) tbEmpresa.getModel();
                    model.setRowCount(0);
                    llenarRanking(EstadoPedido.getValue(seleccionado), "Empresa");
                }
                
                
            }
        });
    
    }
    
    private void agregarActionCambioTab(){
     ChangeListener changeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent changeEvent) {
                  JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                  int index = sourceTabbedPane.getSelectedIndex();
                  String seleccionado =  (String) cbEstado.getSelectedItem();
                  if (index == 0){
                      DefaultTableModel model = (DefaultTableModel) tbNatural.getModel();
                        model.setRowCount(0);
                        llenarRanking(EstadoPedido.getValue(seleccionado), "Natural");
                  } else {
                  DefaultTableModel model = (DefaultTableModel) tbEmpresa.getModel();
                    model.setRowCount(0);
                    llenarRanking(EstadoPedido.getValue(seleccionado), "Empresa");
                  }
                  
                }
            };
            tabPanel.addChangeListener(changeListener);
    }
    
    
    public jFrameRankingClienteDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ranking de pedidos");
         for (EstadoPedido objEstadoPedido : EstadoPedido.values()) {
            if(objEstadoPedido!=EstadoPedido.estadonulo)
                cbEstado.addItem(objEstadoPedido.toString());
        }
        logicaClientesBL = new ClientesBL();
        
        llenarRanking(EstadoPedido.valueOf(cbEstado.getSelectedItem().toString()), "Natural");
        
        agregarActionCambioComboBox();
        agregarActionCambioTab();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        panelNatural = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNatural = new javax.swing.JTable();
        panelEmpresa = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEmpresa = new javax.swing.JTable();
        lblEstado = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        lbTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbNatural.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "DNI", "Nombre", "Apellido", "Telefono", "Direccion", "Cuenta Bancaria", "Correo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbNatural);

        javax.swing.GroupLayout panelNaturalLayout = new javax.swing.GroupLayout(panelNatural);
        panelNatural.setLayout(panelNaturalLayout);
        panelNaturalLayout.setHorizontalGroup(
            panelNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNaturalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelNaturalLayout.setVerticalGroup(
            panelNaturalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNaturalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Natural", panelNatural);

        tbEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "RUC", "Razon Social", "Telefono", "Direccion", "Cuenta Bancaria", "Correo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbEmpresa);

        javax.swing.GroupLayout panelEmpresaLayout = new javax.swing.GroupLayout(panelEmpresa);
        panelEmpresa.setLayout(panelEmpresaLayout);
        panelEmpresaLayout.setHorizontalGroup(
            panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE))
        );
        panelEmpresaLayout.setVerticalGroup(
            panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Empresa", panelEmpresa);

        lblEstado.setText("Estado Pedido");

        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTitulo.setText("Ranking de Cliente por estado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanel, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstado)
                .addGap(18, 18, 18)
                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstado)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbTitulo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed

   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel panelEmpresa;
    private javax.swing.JPanel panelNatural;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTable tbEmpresa;
    private javax.swing.JTable tbNatural;
    // End of variables declaration//GEN-END:variables
}
