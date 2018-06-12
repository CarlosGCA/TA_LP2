/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import AccesoData.DocumentoPagoAD;
import Controlador.DocumentoPagoBL;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import Modelo.PedidoProducto;
import Modelo.Producto;
import Modelo.LineaPedidoProducto;
import Modelo.EstadoPedido;
import Controlador.PedidoBL;
import Modelo.Cliente;
import Modelo.CuentaUsuario;
import Modelo.Empresa;
import Modelo.Natural;
import java.awt.Desktop;
import java.awt.Dialog;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import Email.Email;
/**
 *
 * @author Kathy Ruiz :)
 */
enum operacion {Crear, Modificar};

public class JFramePedidos extends javax.swing.JDialog {
    private PedidoBL logicaNegocio;
    private PedidoProducto Pedido;
    private Producto productoSeleccionado;
    private CuentaUsuario userLogin;
    private operacion accion;
    DefaultTableModel modelo;
    private DocumentoPagoBL documentoPagoBL;
    /**
     * @return the accion
     */
    public operacion getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(operacion accion) {
        this.accion = accion;
    }
        /**
     * @return the Pedido
     */
    public PedidoProducto getPedido() {
        return Pedido;
    }

    /**
     * @param Pedido the Pedido to set
     */
    public void setPedido(PedidoProducto Pedido) {
        this.Pedido = Pedido;
    }
    
        /**
     * @return the ultimoProducto
     */
    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    /**
     * @param ultimoProducto the ultimoProducto to set
     */
    public void setProductoSeleccionado(Producto ultimoProducto) {
        this.productoSeleccionado = ultimoProducto;
    }
    
    /**
     * @return the userLogin
     */
    public CuentaUsuario getUserLogin() {
        return userLogin;
    }

    /**
     * @param userLogin the userLogin to set
     */
    public void setUserLogin(CuentaUsuario userLogin) {
        this.userLogin = userLogin;
    }
    
    public JFramePedidos(Dialog f, boolean b, CuentaUsuario user) {
        super(f, b);
        initComponents();
        
        logicaNegocio = new PedidoBL();
        setUserLogin(user);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ventana Principal");
        
        txtFechaPed.setEnabled(false);
        txtProducto.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtTotal.setEnabled(false);
        txtIDDPago.setEnabled(false);
        txtIDProd.setEnabled(false);
        txtIDCLI.setEnabled(false);
        txtRuc1.setEnabled(false);
        txtRazonS1.setEnabled(false);
        txtIDPedido.setEnabled(false);
        estadoCampos(1);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });
        
        Pedido = new PedidoProducto();
        modelo = (DefaultTableModel) JTablePedidos.getModel();
        documentoPagoBL = new DocumentoPagoBL();
    }
    
    public void estadoCampos(int est){
        switch(est){
            //inicial
            case 1:
                btnGrabar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnAnular.setEnabled(false);
                txtFechaPed.setText(fechaActual());
                btnBuscarDNI.setEnabled(false);
                btnBuscarProducto.setEnabled(false);
                txtCantidad.setEnabled(false);
                btnAgrega.setEnabled(false);
                btnElimina.setEnabled(false);
                jDateChooser1.setEnabled(false);
                btnGenerarDocumentoPago.setEnabled(false);
                JTablePedidos.setEnabled(false);
                cboTipoCliente.setEnabled(false);
                break;
                
            //nuevo
            case 2:
                btnGrabar.setEnabled(true);
                btnBuscarDNI.setEnabled(true);
                btnModificar.setEnabled(false);
                btnAnular.setEnabled(false);
                btnBuscarProducto.setEnabled(true);
                txtCantidad.setEnabled(true);
                btnAgrega.setEnabled(true);
                btnElimina.setEnabled(true);
                jDateChooser1.setEnabled(true);
                cboTipoCliente.setEnabled(true);
                btnGenerarDocumentoPago.setEnabled(false);
                
                txtIDPedido.setText("");
                jLEstado.setText("");
                txtProducto.setText("");
                txtCantidad.setText("");
                txtPrecio.setText("");
                txtIDProd.setText("");
                txtRuc1.setText("");
                txtRazonS1.setText("");
                txtIDCLI.setText("");
                txtFechaPed.setText(fechaActual());
                jDateChooser1.setCalendar(null);
                JTablePedidos.setEnabled(true);
                break;
                
            //visualizar
            case 3:
                btnGrabar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnAnular.setEnabled(false);
                btnBuscarDNI.setEnabled(false);
                btnBuscarProducto.setEnabled(false);
                txtCantidad.setEnabled(false);
                btnAgrega.setEnabled(false);
                btnElimina.setEnabled(false);
                jDateChooser1.setEnabled(false);
                btnGenerarDocumentoPago.setEnabled(false);
                JTablePedidos.setEnabled(false);
                cboTipoCliente.setEnabled(false);
                break;
            
            //Modificar
            case 4:
                btnGrabar.setEnabled(true);
                btnBuscarDNI.setEnabled(true);
                btnModificar.setEnabled(false);
                btnAnular.setEnabled(true);
                btnBuscarProducto.setEnabled(true);
                txtCantidad.setEnabled(true);
                btnAgrega.setEnabled(true);
                btnElimina.setEnabled(true);
                jDateChooser1.setEnabled(true);
                cboTipoCliente.setEnabled(true);
                btnGenerarDocumentoPago.setEnabled(true);
                JTablePedidos.setEnabled(true);
                cboTipoCliente.setEnabled(true);
        }
    }
    
    public void actualizarTabla(){
        modelo.setRowCount(0);
        Object[] fila = new Object[5];
        for(int i=0; i<Pedido.getListaLineasPedido().size(); i++){
            if(Pedido.getListaLineasPedido().get(i).getHabilitado()){
                fila[0] = Pedido.getListaLineasPedido().get(i).getProducto().getidProducto();
                fila[1] = Pedido.getListaLineasPedido().get(i).getProducto().getnombProducto();
                fila[2] = Pedido.getListaLineasPedido().get(i).getCantidad();
                fila[3] = Pedido.getListaLineasPedido().get(i).getProducto().getprecio();
                fila[4] = Pedido.getListaLineasPedido().get(i).getCantidad() *
                        Pedido.getListaLineasPedido().get(i).getProducto().getprecio();
                modelo.addRow(fila);
            }
        }
        txtTotal.setText(Float.toString(Pedido.getTotalPagar()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public static String fechaActual(){
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog2 = new javax.swing.JDialog();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        btnAgrega = new javax.swing.JButton();
        btnElimina = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablePedidos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblRuc1 = new javax.swing.JLabel();
        lblRazonS1 = new javax.swing.JLabel();
        txtRuc1 = new javax.swing.JTextField();
        txtRazonS1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboTipoCliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIDCLI = new javax.swing.JTextField();
        btnBuscarDNI = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        lbltotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblProducto = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtIDProd = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtFechaPed = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnGenerarDocumentoPago = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtIDDPago = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtIDPedido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLEstado = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 11)); // NOI18N
        jLabel1.setText("Pedidos de Productos");

        btnAgrega.setText("Agregar");
        btnAgrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregaActionPerformed(evt);
            }
        });

        btnElimina.setText("Eliminar");
        btnElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminaActionPerformed(evt);
            }
        });

        btnRegresar.setText("Volver");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        JTablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Producto", "Nombre", "Cantidad", "Precio", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTablePedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(JTablePedidos);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRuc1.setText("RUC:");

        lblRazonS1.setText("Razon Social: ");

        txtRuc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuc1ActionPerformed(evt);
            }
        });

        txtRazonS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonS1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo:");

        cboTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empresa", "Persona Natural" }));
        cboTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Cargar Cliente:");

        jLabel16.setText("ID:");

        txtIDCLI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDCLIActionPerformed(evt);
            }
        });

        btnBuscarDNI.setText("Buscar Cliente");
        btnBuscarDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDNIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel3)
                            .addComponent(lblRuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIDCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRuc1)
                                    .addComponent(cboTipoCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblRazonS1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRazonS1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtIDCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarDNI))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRuc1))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRazonS1)
                    .addComponent(txtRazonS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        lbltotal.setText("TOTAL:");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProducto.setText("Nombre:");

        txtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoActionPerformed(evt);
            }
        });

        jLabel5.setText("Precio:");

        jLabel4.setText("Cantidad:");

        jLabel6.setText("Cargar Productos:");

        jLabel15.setText("ID:");

        btnBuscarProducto.setText("Buscar Producto");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtIDProd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(btnBuscarProducto))
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnBuscarProducto)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIDProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Fecha de Pedido:");

        txtFechaPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaPedActionPerformed(evt);
            }
        });

        jLabel9.setText("Fecha de Entrega:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaPed, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Contabilizar:");

        btnGenerarDocumentoPago.setText("Generar Documento de Pago");
        btnGenerarDocumentoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarDocumentoPagoActionPerformed(evt);
            }
        });

        jLabel14.setText("Nro. Doc:");

        txtIDDPago.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIDDPago, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarDocumentoPago))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerarDocumentoPago)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIDDPago)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("ID Pedido:");

        txtIDPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDPedidoActionPerformed(evt);
            }
        });

        jLabel11.setText("Estado Pedido:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIDPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNuevo)
                                .addGap(31, 31, 31)
                                .addComponent(btnBuscar)
                                .addGap(10, 10, 10))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnElimina, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgrega, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGrabar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAnular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnular)
                    .addComponent(btnModificar)
                    .addComponent(jLabel1)
                    .addComponent(btnNuevo)
                    .addComponent(btnRegresar)
                    .addComponent(btnGrabar)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgrega)
                        .addGap(18, 18, 18)
                        .addComponent(btnElimina))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltotal))
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        super.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void txtRuc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRuc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuc1ActionPerformed

    private void btnBuscarDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDNIActionPerformed
        // TODO add your handling code here:
        
        JFBuscarCliente objeBuscarCli;
        String tipo = (String) cboTipoCliente.getSelectedItem();
        objeBuscarCli = new JFBuscarCliente(tipo,this,true);
        objeBuscarCli.setVisible(true);
        
        
        if(cboTipoCliente.getSelectedItem()=="Empresa"){
            if(objeBuscarCli.getEmpresaSeleccionada()!=null){
                Pedido.setcliente(objeBuscarCli.getEmpresaSeleccionada());
                txtIDCLI.setText(Integer.toString(objeBuscarCli.getEmpresaSeleccionada().getId_cliente()));
                txtRuc1.setText(objeBuscarCli.getEmpresaSeleccionada().getRuc());
                txtRazonS1.setText(objeBuscarCli.getEmpresaSeleccionada().getRazonSocial());
            }         
        }else{
            if(objeBuscarCli.getNaturalSeleccionado()!=null){
                Pedido.setcliente(objeBuscarCli.getNaturalSeleccionado());
                txtIDCLI.setText(Integer.toString(objeBuscarCli.getNaturalSeleccionado().getId_cliente()));
                txtRuc1.setText(objeBuscarCli.getNaturalSeleccionado().getDNI());
                txtRazonS1.setText(objeBuscarCli.getNaturalSeleccionado().getNombre());
            }
        }
        
        //value = 1;
    }//GEN-LAST:event_btnBuscarDNIActionPerformed

    private void txtFechaPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaPedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaPedActionPerformed

    private void cboTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoClienteActionPerformed
        // TODO add your handling code here:
         if(cboTipoCliente.getSelectedItem()=="Empresa"){
            lblRuc1.setText("RUC:");
            lblRazonS1.setText("Razon Social:");
            //lblNombre2.setVisible(false);
            //txtNombre2.setVisible(false);
//            lblImagEmpresa.setVisible(true);
//            lblImagPersona.setVisible(false);
        }else{
            lblRuc1.setText("DNI:");
            lblRazonS1.setText("Nombre:");
//            lblNombre2.setVisible(true);
//            txtNombre2.setVisible(true);
//            lblImagEmpresa.setVisible(false);
//            lblImagPersona.setVisible(true);
        }
        txtRuc1.setText("");
        txtRazonS1.setText("");
        txtIDCLI.setText("");
    }//GEN-LAST:event_cboTipoClienteActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        // TODO add your handling code here:
        JBuscarProducto objeBuscarPro;
        objeBuscarPro= new JBuscarProducto(this,true);
        objeBuscarPro.setVisible(true);
        
        if(!(objeBuscarPro.getProductoElegido()==null)){
            productoSeleccionado = objeBuscarPro.getProductoElegido();
            txtIDProd.setText(Integer.toString(productoSeleccionado.getidProducto()));
            txtProducto.setText(productoSeleccionado.getnombProducto());
            txtPrecio.setText(Float.toString(productoSeleccionado.getprecio()));
            System.out.println(productoSeleccionado.getnombProducto());         
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        //value = 1;
        try{
            if(Pedido.getestadoPed()==EstadoPedido.EnProceso)
                throw new Exception("No se puede modificar un pedido En Proceso");
            if(Pedido.getestadoPed()==EstadoPedido.Cancelado)
                throw new Exception("No se puede modificar un pedido Cancelado");
            setAccion(operacion.Modificar);
            estadoCampos(4);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        // TODO add your handling code here:
        if(accion==operacion.Crear){
            try{
                Date dateEntrega = jDateChooser1.getDate();
                Date dateRegistro = new Date();

                if(dateEntrega == null)
                    throw new Exception("Coloque una fecha de entrega");
                if(dateEntrega.before(dateRegistro))
                    throw new Exception("La Fecha de Entrega no puede ser menor a la Fecha de Fedido");
                if(Pedido.getListaLineasPedido().size()==0)
                    throw new Exception("El pedido no tiene ningun producto agregado");
                if(Pedido.getcliente()==null)
                    throw new Exception("El pedido no tiene Cliente");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fechEntrega = dateFormat.format(dateEntrega);

                Pedido.setfechaEntrPed(fechEntrega);

                EstadoPedido estPed = EstadoPedido.Pendiente;
                Pedido.setestadoPedo(estPed);

                int idped=logicaNegocio.registrarPedido(Pedido, userLogin.getidUsuario());
                if(idped==0)
                    throw new Exception("Error al registrar pedido");
                try{
                    Email controllerEmail = new Email();
                    controllerEmail.prepareConection();
                    String correoAdmin = controllerEmail.getEmailDB("admin");
                    controllerEmail.sendEmail("Nuevo pedido registrado", "Se ha registrado el pedido " + txtIDPedido.getText() + "\n\n\t Para el cliente "+ this.txtRazonS1.getText()  , correoAdmin);
                } catch(Exception ex){}
                txtIDPedido.setText(Integer.toString(idped));
                jLEstado.setText(estPed.toString());
                estadoCampos(3);
                JOptionPane.showMessageDialog(null, "Pedido registrado correctamente con id " + idped, "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(accion==operacion.Modificar){
            try{
                Date dateEntrega = jDateChooser1.getDate();
                Date dateRegistro = Pedido.getfechaRegPed();
                Date datehoy = new Date();

                if(dateEntrega == null)
                    throw new Exception("Coloque una fecha de entrega");
                if(dateEntrega.before(dateRegistro))
                    throw new Exception("La Fecha de Entrega no puede ser menor a la Fecha de Fedido");
                if(dateEntrega.before(datehoy))
                    throw new Exception("La Fecha de Entrega no puede ser menor a la Fecha de hoy");
                if(Pedido.getListaLineasPedido().size()==0)
                    throw new Exception("El pedido no tiene ningun producto agregado");
                if(Pedido.getcliente()==null)
                    throw new Exception("El pedido no tiene Cliente");
                if(Pedido.getTotalPagar()==0)
                    throw new Exception("El pedido no puede tener precio 0");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fechEntrega = dateFormat.format(dateEntrega);

                Pedido.setfechaEntrPed(fechEntrega);
                
                logicaNegocio.modificarPedido(Pedido, userLogin.getidUsuario());
//                try{
//                    Email controllerEmail = new Email();
//                    controllerEmail.prepareConection();
//                    String correoAdmin = controllerEmail.getEmailDB("admin");
//                    controllerEmail.sendEmail("Se ha modificado un pedido", "Se ha modificado el pedido " + txtIDPedido.getText() + "\n\n\t Para el cliente "+ this.txtRazonS1.getText()  , correoAdmin);
//                } catch(Exception ex){}
                estadoCampos(3);
                JOptionPane.showMessageDialog(null, "Pedido " + txtIDPedido.getText() + " modificado correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            }
        }   
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoActionPerformed

    private void txtRazonS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonS1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonS1ActionPerformed

    private void btnGenerarDocumentoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDocumentoPagoActionPerformed
        try {

            
//            int idPedido = Integer.parseInt(strPedido);
            String tipoDoc = (Pedido.getcliente() instanceof Natural)?"Boleta":"Factura";
            String nombArchRep = tipoDoc + " pedido "+txtIDPedido.getText()+".pdf";
            
            File archDocPago = new File(nombArchRep);
            if(archDocPago.isFile()) //borramos por defecto
                archDocPago.delete();
            
//            float total = Float.parseFloat(txtTotal.getText());
            
//            int idBoleta = documentoPagoBL.generarBoleta(total, idPedido, 18);
            int idBoleta = documentoPagoBL.generarDocPago(Pedido);
            txtIDDPago.setText(Integer.toString(idBoleta));
            //documentoPagoBL.generarDocumentoPago(Pedido); //IMPLEMENTAR
            documentoPagoBL.exportarDocPagoPDF(Pedido, nombArchRep);



            int seleccion = JOptionPane.showConfirmDialog(null, "Documento generado exitosamente,\n¿desea abrirlo?",
                                          "Mensaje",JOptionPane.YES_NO_OPTION);
            if(seleccion == JOptionPane.YES_OPTION)
                Desktop.getDesktop().open(archDocPago);



        } catch (Exception e){
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarDocumentoPagoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        JFBuscarPedidos objet= new JFBuscarPedidos(this,true);
        objet.setVisible(true);
        if(objet.getPedidoElegido()!=null){
            estadoCampos(3);
            PedidoProducto ped = objet.getPedidoElegido();
            txtIDPedido.setText(Integer.toString(ped.getidPedido()));
            jLEstado.setText(ped.getestadoPed().toString());
            if(ped.getcliente() instanceof Natural){
                cboTipoCliente.setSelectedIndex(1);
                Natural nat = (Natural)ped.getcliente();
                txtIDCLI.setText(Integer.toString(nat.getId_cliente()));
                txtRuc1.setText(nat.getDNI());
                txtRazonS1.setText(nat.getNombre()+" "+nat.getApellidos());
            }else{
                Empresa emp = (Empresa)ped.getcliente();
                cboTipoCliente.setSelectedIndex(0);
                txtIDCLI.setText(Integer.toString(emp.getId_cliente()));
                txtRuc1.setText(emp.getRuc());
                txtRazonS1.setText(emp.getRazonSocial());   
            }
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String fechReg = df.format(ped.getfechaRegPed());
            String fechEnt = df.format(ped.getfechaEntrPed());
            txtFechaPed.setText(fechReg);
            jDateChooser1.setDate(ped.getfechaEntrPed());
            
            Pedido = ped;
            
            actualizarTabla();
            txtProducto.setText("");
            txtCantidad.setText("");
            txtPrecio.setText("");
            txtIDProd.setText("");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Pedido=new PedidoProducto();
        estadoCampos(2);
        actualizarTabla();
        setAccion(operacion.Crear);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtIDCLIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDCLIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCLIActionPerformed

    private void txtIDPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDPedidoActionPerformed

    private void btnAgregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregaActionPerformed
        // TODO add your handling code here:
        try{
            float cant = Float.parseFloat(txtCantidad.getText());
            float subt = cant*Float.parseFloat(txtPrecio.getText());

            int indx=0;
            int encontrado=0;
            for (LineaPedidoProducto lb: Pedido.getListaLineasPedido()){
                if(lb.getProducto().getidProducto()==productoSeleccionado.getidProducto()){
                    encontrado=1;
                    break;
                }
                indx++;
            }
            if(encontrado==0){
                LineaPedidoProducto lpp=new LineaPedidoProducto(productoSeleccionado,cant,0);
                Pedido.agregarLinea(lpp);
            }else{
                if(Pedido.getListaLineasPedido().get(indx).getHabilitado()){
                    float nuevaCant=Pedido.getListaLineasPedido().get(indx).getCantidad()+cant;
                    Pedido.getListaLineasPedido().get(indx).setCantidad(nuevaCant);
                }else{
                    Pedido.getListaLineasPedido().get(indx).setCantidad(cant);
                    Pedido.getListaLineasPedido().get(indx).setHabilitado(true);
                }
            }
            Pedido.setTotalPagar(Pedido.getTotalPagar());
            actualizarTabla();
        }catch(Exception e){
            if(txtPrecio.getText().isEmpty())
            JOptionPane.showMessageDialog(null, "¡Seleccione un producto!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            else
            JOptionPane.showMessageDialog(null, "¡Indique una cantidad correcta!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnAgregaActionPerformed

    private void btnEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminaActionPerformed
        // TODO add your handling code here:
        try{
            int indx = JTablePedidos.getSelectedRow();
            int idPro = (int) JTablePedidos.getModel().getValueAt(indx, 0);
            
            int indxP=0;
            for (LineaPedidoProducto lb: Pedido.getListaLineasPedido()){
                if(lb.getProducto().getidProducto()==idPro){
                    break;
                }
                indxP++;
            }
            
            Pedido.getListaLineasPedido().get(indxP).setHabilitado(false);
            Pedido.setTotalPagar(Pedido.getTotalPagar());
            txtTotal.setText(Float.toString(Pedido.getTotalPagar()));
            actualizarTabla();
        }catch(Exception e){
            if(Pedido.getListaLineasPedido().isEmpty())
            JOptionPane.showMessageDialog(null, "No hay lineas que eliminar", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            else
            JOptionPane.showMessageDialog(null, "Seleccione una linea del pedido", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminaActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        int decision = JOptionPane.showConfirmDialog(null, "¿Esta seguro de anular el pedido?", "MENSAJE", JOptionPane.YES_NO_OPTION);
        if(decision==JOptionPane.YES_OPTION){
            logicaNegocio.anularPedido(Pedido.getidPedido());
            jLEstado.setText(EstadoPedido.Cancelado.toString());
            estadoCampos(3);
            JOptionPane.showMessageDialog(null, "Pedido con id " + Pedido.getidPedido() +" anulado correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAnularActionPerformed

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
            java.util.logging.Logger.getLogger(JFramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    new JFramePedidos(null,false,null).setVisible(true);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                //new JFramePedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTablePedidos;
    private javax.swing.JButton btnAgrega;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarDNI;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnElimina;
    private javax.swing.JButton btnGenerarDocumentoPago;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cboTipoCliente;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblRazonS1;
    private javax.swing.JLabel lblRuc1;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtFechaPed;
    private javax.swing.JTextField txtIDCLI;
    private javax.swing.JTextField txtIDDPago;
    private javax.swing.JTextField txtIDPedido;
    private javax.swing.JTextField txtIDProd;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtRazonS1;
    private javax.swing.JTextField txtRuc1;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
