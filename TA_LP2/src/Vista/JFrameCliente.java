/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ClientesBL;
import Modelo.Empresa;
import Modelo.Natural;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Kathy Ruiz :)
 */
public class JFrameCliente extends javax.swing.JDialog {

    private Natural cliNat;
    private Empresa cliEmp;
    private ClientesBL logicaNeg;

    public static JFBuscarCliente objeBuscarCli;

    /**
     * Creates new form JFrameCliente
     */
    public JFrameCliente(Dialog f, boolean b) {
        super(f, b);
        initComponents();
        this.setBounds(30, 30, 1050, 690);
        //lblNombre2.setVisible(false);
        //txtNombre2.setVisible(false);
        btnModificar.setEnabled(false);
        lblImagEmpresa.setSize(200, 300);
        lblImagPersona.setSize(200, 300);
        lblImagPersona.setVisible(false);
        cliNat = new Natural();
        cliEmp = new Empresa();
        logicaNeg = new ClientesBL();
        lblNombre2.setText("Direccion:");
        lblDireccion.setText("Telefono");
        lblTelefono.setText("Cuenta Bancaria");
        lblCuentaBan.setText("Correo");
        lblCorreo.setVisible(false);
        txtEmail.setVisible(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                JFPrincipalVendedor.value = 0;
//            }
//        });
        setTitle("Gestion de Clientes");
    }

    private int validar() {
        int contador = 0;
        String ruc = txtRuc.getText();
        String nom1 = txtNombre1.getText();
        String ap = txtNombre2.getText();
        String dir = txtDireccion.getText();
        String tel = txtTelefono.getText();
        String cuentaB = txtCuentaBan.getText();
        String correo = txtEmail.getText();
        if (!nom1.matches("^[A-Za-z ]*$")) {//
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de " + lblNombre1.getText(), "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            Scanner sc = new Scanner(System.in);
            sc.equals(txtNombre1.getText());
            System.out.println("fd");
        } else if (!ruc.matches("^[0-9]*$")) {//
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del " + lblRUC.getText(), "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
        if (cboTipo.getSelectedItem() == "Empresa") {
            if (!ap.matches("^[A-Za-z 0-9 ]*$")) {// dir
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de la dirección ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!dir.matches("^[0-9 ]*$")) {//telef
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del telefono ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!tel.matches("^[A-Za-z ]*$")) {// cuentabAN
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de Cuenta Bancaria ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!cuentaB.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {// correo
                //^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de correo ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if (!ap.matches("^[A-Za-z]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del apellido ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!dir.matches("^[A-Za-z 0-9]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de la dirección", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!tel.matches("^[0-9]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de telefono ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!cuentaB.matches("^[A-Za-z]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de Cuenta Bancaria ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del correo ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return contador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lblRUC = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        lblNombre2 = new javax.swing.JLabel();
        txtNombre2 = new javax.swing.JTextField();
        btnAgregarCliente = new javax.swing.JButton();
        lblDireccion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblCuentaBan = new javax.swing.JLabel();
        txtCuentaBan = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        txtDireccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIDCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        lblImagEmpresa = new javax.swing.JLabel();
        lblImagPersona = new javax.swing.JLabel();
        btnCerrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tipo de Cliente:");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empresa", "Persona Natural" }));
        cboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRUC.setText("RUC:");

        lblNombre1.setText("Razon Social:");

        lblNombre2.setText("Apellidos:");
        lblNombre2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtNombre2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre2ActionPerformed(evt);
            }
        });

        btnAgregarCliente.setText("Agregar Cliente");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        lblDireccion.setText("Dirección:");

        lblTelefono.setText("Teléfono:");

        lblCuentaBan.setText("Cuenta Bancaria:");

        lblCorreo.setText("Correo:");

        btnModificar.setText("Modificar Cliente");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel2.setText("IDCliente:");

        txtIDCliente.setEnabled(false);
        txtIDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDClienteActionPerformed(evt);
            }
        });

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Lupa1.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblCuentaBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRUC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNombre2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRuc)
                            .addComponent(txtNombre1)
                            .addComponent(txtNombre2)
                            .addComponent(txtTelefono)
                            .addComponent(txtCuentaBan)
                            .addComponent(txtEmail)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarCliente)
                        .addGap(79, 79, 79)
                        .addComponent(btnModificar)
                        .addGap(69, 69, 69))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRUC)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre2)
                    .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCuentaBan)
                    .addComponent(txtCuentaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarCliente)
                    .addComponent(btnModificar))
                .addGap(37, 37, 37))
        );

        lblImagEmpresa.setBackground(new java.awt.Color(200, 240, 200));
        lblImagEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/edificio.jpg"))); // NOI18N
        lblImagEmpresa.setText("jLabel4");
        lblImagEmpresa.setMaximumSize(new java.awt.Dimension(120, 120));
        lblImagEmpresa.setMinimumSize(new java.awt.Dimension(50, 50));
        lblImagEmpresa.setPreferredSize(new java.awt.Dimension(50, 50));

        lblImagPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/personita1.png"))); // NOI18N

        btnCerrar1.setText("Cerrar");
        btnCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(lblImagPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar1)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(lblImagPersona)
                        .addGap(209, 209, 209))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoActionPerformed
        // TODO add your handling code here:
        if (cboTipo.getSelectedItem() == "Empresa") {
            lblRUC.setText("RUC");
            lblNombre1.setText("Razon Social");
            //lblNombre2.setVisible(false);
            //txtNombre2.setVisible(false);
            this.setBounds(30, 30, 1050, 690);
            lblImagEmpresa.setSize(200, 300);
            lblImagEmpresa.setVisible(true);
            lblImagPersona.setVisible(false);
            lblNombre2.setText("Direccion:");
            lblDireccion.setText("Telefono");
            lblTelefono.setText("Cuenta Bancaria");
            lblCuentaBan.setText("Correo");
            lblCorreo.setVisible(false);
            txtEmail.setVisible(false);
        } else {
            lblRUC.setText("DNI:");
            lblNombre1.setText("Nombre:");
            lblNombre2.setText("Apellido:");
            lblDireccion.setText("Direccion:");
            lblTelefono.setText("Telefono:");
            lblCuentaBan.setText("Cuenta Bancaria:");
            lblCorreo.setText("Correo:");
            txtEmail.setVisible(true);
            lblNombre2.setVisible(true);
            txtNombre2.setVisible(true);
            lblNombre2.setEnabled(true);
            lblCorreo.setVisible(true);
            lblImagEmpresa.setVisible(false);
            lblImagPersona.setSize(200, 300);
            lblImagPersona.setVisible(true);
            this.setBounds(30, 30, 1000, 690);
        }

    }//GEN-LAST:event_cboTipoActionPerformed

    private void txtNombre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre2ActionPerformed

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        // TODO add your handling code here:
        int contador = 0;
        String ruc = txtRuc.getText();
        String nom1 = txtNombre1.getText();
        String ap = txtNombre2.getText();
        String dir = txtDireccion.getText();
        String tel = txtTelefono.getText();
        String cuentaB = txtCuentaBan.getText();
        String correo = txtEmail.getText();
        if (!nom1.matches("^[A-Za-z ]*$")) {//
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de " + lblNombre1.getText(), "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            Scanner sc = new Scanner(System.in);
            sc.equals(txtNombre1.getText());
            System.out.println("fd");
        } else if (!ruc.matches("^[0-9]*$")) {//
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del " + lblRUC.getText(), "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
        if (cboTipo.getSelectedItem() == "Empresa") {
            if (!ap.matches("^[A-Za-z 0-9 ]*$")) {// dir
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de la dirección ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!dir.matches("^[0-9 ]*$")) {//telef
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del telefono ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!tel.matches("^[A-Za-z ]*$")) {// cuentabAN
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de Cuenta Bancaria ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!cuentaB.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {// correo
                //^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de correo ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if (!ap.matches("^[A-Za-z]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del apellido ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!dir.matches("^[A-Za-z 0-9]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de la dirección", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!tel.matches("^[0-9]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de telefono ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!cuentaB.matches("^[A-Za-z]*$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada de Cuenta Bancaria ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            } else if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                contador++;
                JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del correo ", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (contador == 0) {
            if (cboTipo.getSelectedItem() == "Empresa") {
                cliEmp.setRuc(txtRuc.getText());
                cliEmp.setRazonSocial(txtNombre1.getText());
                cliEmp.setDireccion(txtNombre2.getText());
                cliEmp.setTelefono(Integer.parseInt(txtDireccion.getText()));
                cliEmp.setCuentaBancaria(txtTelefono.getText());
                cliEmp.setCorreo(txtCuentaBan.getText());
//            System.out.println("nom: " + cliEmp.getCuentaBancaria());
//            System.out.println("ap: " + cliEmp.getApellido());
//            System.out.println("dni: " + cliEmp.getDNI());
//            System.out.println("turno: " + cliEmp.getTurno().toString());
//            System.out.println("fNac: " + cliEmp.getFechaNac());
                logicaNeg.registrarEmpresa(cliEmp);
            } else {
                cliNat.setDNI(txtRuc.getText());
                cliNat.setNombre(txtNombre1.getText());
                cliNat.setApellidos(txtNombre2.getText());
                cliNat.setDireccion(txtDireccion.getText());
                cliNat.setTelefono(Integer.parseInt(txtTelefono.getText()));
                cliNat.setCuentaBancaria(txtCuentaBan.getText());
                cliNat.setCorreo(txtEmail.getText());
                logicaNeg.registrarNatural(cliNat);
                System.out.println("nom: " + cliNat.getNombre());
                System.out.println("ap: " + cliNat.getApellidos());
                System.out.println("dni: " + cliNat.getDNI());
                System.out.println("corre: " + cliNat.getCorreo());
                System.out.println("cBan: " + cliNat.getCuentaBancaria());
            }
            JOptionPane.showMessageDialog(null, "Se ha agregado con éxito", "Ventana Clientes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.enableInputMethods(true);
        }
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrar1ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if (cboTipo.getSelectedItem() == "Empresa") {
            cliEmp = objeBuscarCli.getEmpresaSeleccionada();
            //num = 1;
            int cont = validar();
            System.out.println(cont);
            System.out.println("asdf");
            if ((validar() == 0)) {
                try {
                    cliEmp.setRuc(txtRuc.getText());
                    cliEmp.setRazonSocial(txtNombre1.getText());
                    cliEmp.setDireccion(txtNombre2.getText());
                    cliEmp.setTelefono(Integer.parseInt(txtDireccion.getText()));
                    cliEmp.setCuentaBancaria(txtTelefono.getText());
                    cliEmp.setCorreo(txtCuentaBan.getText());
                    System.out.println("asdf");
  
                    logicaNeg.modificarCliEmpresa(cliEmp);
                    JOptionPane.showMessageDialog(null, "El usuario: " + Integer.toString(cliEmp.getId_cliente()) + " ha sido modificado correctamente", "Ventana Clientes", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    System.out.println("NO SE PUDO AGREGAR");
                }
            } else {
                this.enableInputMethods(true);
            }
        }else{
            cliNat = objeBuscarCli.getNaturalSeleccionado();
            //num = 1;
            int conta = validar();
            System.out.println(conta);
            System.out.println("asdf");
            if ((validar() == 0)) {
                try {
                    cliNat.setDNI(txtRuc.getText());
                    cliNat.setNombre(txtNombre1.getText());
                    cliNat.setApellidos(txtNombre2.getText());
                    cliNat.setDireccion(txtDireccion.getText());
                    cliNat.setTelefono(Integer.parseInt(txtTelefono.getText()));
                    cliNat.setCuentaBancaria(txtCuentaBan.getText());
                    cliNat.setCorreo(txtEmail.getText());
                    System.out.println(cliNat.getCorreo());
  
                    logicaNeg.modificarCliNatural(cliNat);
                    JOptionPane.showMessageDialog(null, "El usuario: " + Integer.toString(cliNat.getId_cliente()) + " ha sido modificado correctamente", "Ventana Clientes", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    System.out.println("NO SE PUDO AGREGAR");
                }
            } else {
                this.enableInputMethods(true);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtIDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDClienteActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
        if (cboTipo.getSelectedItem() == "Empresa") {
            objeBuscarCli = new JFBuscarCliente("Empresa", this, true);
            //objeBuscarEmp.setAlwaysOnTop(true);
            objeBuscarCli.setVisible(true);
            if(objeBuscarCli.getEmpresaSeleccionada()!=null){
                Empresa empresaSeleccionada = objeBuscarCli.getEmpresaSeleccionada();
                txtIDCliente.setText(Integer.toString(empresaSeleccionada.getId_cliente()));
                txtRuc.setText(empresaSeleccionada.getRuc());
                txtNombre1.setText(empresaSeleccionada.getRazonSocial());
                txtNombre2.setText(empresaSeleccionada.getDireccion());
                txtDireccion.setText(Integer.toString(empresaSeleccionada.getTelefono()));
                txtTelefono.setText(empresaSeleccionada.getCuentaBancaria());
                txtCuentaBan.setText(empresaSeleccionada.getCorreo());
            }
        } else {
            objeBuscarCli = new JFBuscarCliente("Natural", this, true);
            objeBuscarCli.setVisible(true);
            if(objeBuscarCli.getNaturalSeleccionado()!=null){
               Natural naturalSeleccionado= objeBuscarCli.getNaturalSeleccionado();
                txtRuc.setText(naturalSeleccionado.getDNI());
                txtNombre1.setText(naturalSeleccionado.getNombre());
                txtNombre2.setText(naturalSeleccionado.getApellidos());
                txtDireccion.setText(naturalSeleccionado.getDireccion());
                txtTelefono.setText(Integer.toString(naturalSeleccionado.getTelefono()));
                txtCuentaBan.setText(naturalSeleccionado.getCuentaBancaria());
                txtEmail.setText(naturalSeleccionado.getCorreo()); 
            }
        }
        btnModificar.setEnabled(true);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrameCliente(null, false).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                //new JFrameCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCerrar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblCuentaBan;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblImagEmpresa;
    private javax.swing.JLabel lblImagPersona;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblRUC;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtCuentaBan;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
