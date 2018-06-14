/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import Controlador.UsuarioBL;
import Controlador.PuestoBL;
import Controlador.TurnoBL;
import Modelo.CuentaUsuario;
import Modelo.Empleado;
import Modelo.Permiso;
import Modelo.Puesto;
import Modelo.Turno;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.image.ImageObserver.PROPERTIES;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Imagenes.ImagenesAD;

/**
 *
 * @author alulab14
 */
public class JFrameUsuarios extends javax.swing.JDialog {

    public File imgFile;
    public static int value;
    public static int num;
    private ImagenesAD imagenesAD;

    public class estadoFormulario extends Thread {

        public void run() {
            while (true) {
                try {
                    if (value == 1) {
//                        btnBuscarEmp.setEnabled(false);
//                        btnBuscarProducto.setEnabled(false);
//                        btnRegresar.setEnabled(false);
//                        btnRegresar1.setEnabled(false);
                    }
                    if (value == 0) {
                        btnBuscarEmp.setEnabled(true);
                        btnRegresar1.setEnabled(true);
                        btnRegresar2.setEnabled(true);
                        txtApellido.setEnabled(false);
                        txtContrasena.setEnabled(false);
                        txtDNI.setEnabled(false);
                        txtNombres.setEnabled(false);
                        txtUsuario.setEnabled(false);
                        txtID.setEnabled(false);
                        txtCorreo.setEnabled(false);
                    }
                    if (value == 2) {
                        Empleado empleadoSeleccionado = new Empleado();
                        empleadoSeleccionado = objeBuscarEmp.getEmpleadoSeleccionado();
                        txtDNI.setText(Integer.toString(empleadoSeleccionado.getDNI()));
                        txtNombres.setText(empleadoSeleccionado.getNombre());
                        txtID.setText(Integer.toString(empleadoSeleccionado.getID()));
                        txtApellido.setText(empleadoSeleccionado.getApellido());
                        if (empleadoSeleccionado.getSexo() == 'F') {
                            cbFem.setSelected(true);
                        } else if (empleadoSeleccionado.getSexo() == 'M') {
                            cbMas.setSelected(true);
                        }
                    }
                    Thread.sleep(30);
                } catch (Exception e) {
                }
            }
        }
    }
    private UsuarioBL logicaNeg;
    private Empleado emp;
    public static JFListarEmpleados objeBuscarEmp;

    private void inicializarDatos() {

        fechaNacimientoChooser.setDateFormatString("yyyy-MM-dd");
        // Con esto se pone como dia maximo , el dia de hoy
        fechaNacimientoChooser.setMaxSelectableDate(new Date());

        PuestoBL controllerPuestoBL = new PuestoBL();
        TurnoBL controllerTurnoBL = new TurnoBL();

        for (String element : controllerPuestoBL.listaPuesto()) {
            jcbRol.addItem(element);
        }

        for (String element : controllerTurnoBL.listaTurnos()) {
            jcbTurno.addItem(element);
        }

        controllerPuestoBL = null;
        controllerTurnoBL = null;

    }

    public JFrameUsuarios(Dialog f, Boolean b) {
        super(f, b);
        initComponents();
        RestrictedTextField restricted = new RestrictedTextField(txtDNI);
        restricted.setLimit(8);
        //restricted.setOnlyNums(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        logicaNeg = new UsuarioBL();
        imagenesAD = new ImagenesAD();
        inicializarDatos();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //JFPrincipalVendedor.value = 0;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private int validar() {
        int contador = 0;
        String nom1 = txtNombres.getText();//emp.getNombre();
        String ap = txtApellido.getText();//emp.getApellido();//
        String auxdni = txtDNI.getText();//teger.toString(emp.getDNI());//
        String nomUs = txtUsuario.getText();//emp.getUsuario().getnombreUsuario();//

        if (!nom1.matches("^[A-Za-z ]*$")) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del nombre", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            Scanner sc = new Scanner(System.in);
            sc.equals(txtNombres.getText());
            System.out.println("fd");
        } else if (!ap.matches("^[A-Za-z ]*$")) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del apellido", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        } else if (!auxdni.matches("^[0-9]*$") || (auxdni.length() != 8)) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del DNI", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        } else if (!nomUs.matches("^[A-Za-z0-9 ]*$")) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del usuario", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
        return contador;
    }

    public void registrarUsuario() {
        int usId;
        num = 0;
        emp = new Empleado();
        int contador = 0;// validar();

        String nom1 = txtNombres.getText();
        String ap = txtApellido.getText();
        String auxdni = txtDNI.getText();
        String nomUs = txtUsuario.getText();
        String auxCorr = txtCorreo.getText();
        System.out.println("lendni: " + auxdni.length());
        
        if(nom1.equals("") || ap.equals("") || auxdni.equals("") || nomUs.equals("") || auxCorr.equals("")){
            JOptionPane.showMessageDialog(null, "Campos incompletos", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }else if (!nom1.matches("^[A-Za-z ]*$")) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del nombre", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
            Scanner sc = new Scanner(System.in);
            sc.equals(txtNombres.getText());
            System.out.println("fd");
        } else if (!ap.matches("^[A-Za-z ]*$")) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del apellido", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        } else if (!auxdni.matches("^[0-9]*$") || (auxdni.length() != 8)) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del DNI", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        } else if (!nomUs.matches("^[A-Za-z0-9 ]*$")) {
            contador++;
            JOptionPane.showMessageDialog(null, "Ocurre error en la entrada del usuario", "Ventana Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
        if (contador == 0) {

            emp.setNombre(txtNombres.getText());

            emp.setApellido(txtApellido.getText());

            Date dateChooser = fechaNacimientoChooser.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaString = dateFormat.format(dateChooser);

            emp.setFechaNac(fechaString);
            if (cbFem.isSelected()) {
                emp.setSexo('F');
            } else {
                emp.setSexo('M');
            }
            String turn = (String) jcbTurno.getSelectedItem();
            Turno tur;
            if (turn
                    == "Mañana") {
                emp.setTurno(Turno.Mañana);
            } else if (turn
                    == "Tarde") {
                emp.setTurno(Turno.Tarde);
            } else {
                emp.setTurno(Turno.Noche);
            }
            //emp.setTurno(tur);
            int ddni = Integer.parseInt(txtDNI.getText());

            emp.setDNI(ddni);
            String cont = new String(txtContrasena.getPassword());
            CuentaUsuario cuenU = new CuentaUsuario();

            cuenU.setnombreUsuario(txtUsuario.getText());
            cuenU.setcontrasenha(cont);
            cuenU.setCorreo(auxCorr);
            String auxRol = (String) jcbRol.getSelectedItem();
            Permiso perm = new Permiso();
            perm.setNombre(auxRol);
            cuenU.setpermise(perm);
            emp.setUsuario(cuenU);
            //Byte[] imageFile = Path;

            byte[] fileContent = null;
            // initialize string buffer to hold contents of file
            StringBuffer fileContentStr = new StringBuffer("");
            BufferedReader reader = null;
            if (imgFile != null) {
                try {
                    // initialize buffered reader
                    reader = new BufferedReader(new FileReader(imgFile.getPath()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JFrameUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                String line = null;
                try {
                    // read lines of file
                    while ((line = reader.readLine()) != null) {
                        //append line to string buffer
                        fileContentStr.append(line).append("\n");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JFrameUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                fileContent = fileContentStr.toString().trim().getBytes();

            } else {
                fileContent = null;
            }
            // convert string to byte array
            emp.setImageFile(fileContent);

            System.out.println(
                    "nom: " + emp.getNombre());
            System.out.println(
                    "ap: " + emp.getApellido());
            System.out.println(
                    "dni: " + emp.getDNI());
            System.out.println(
                    "sexo: " + emp.getSexo());
            System.out.println(
                    "turno: " + emp.getTurno().toString());
            System.out.println(
                    "fNac: " + emp.getFechaNac());
            System.out.println(
                    "nomPer: " + emp.getUsuario().getpermise().getNombre());
            System.out.println(
                    "contra: " + emp.getUsuario().getcontrasenha());
            System.out.println(
                    "correo: " + emp.getUsuario().getCorreo());
            System.out.println(
                    "imag: " + emp.getImageFile());
            logicaNeg.registrarProfesor(emp);

            txtID.setText(Integer.toString(emp.getID()));
            
            if(emp.getID()==0)
                JOptionPane.showMessageDialog(null, "Error al registrar usuario", "Ventana Clientes", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "El usuario: " + emp.getID() + " ha sido agregado correctamente", "Ventana Clientes", JOptionPane.INFORMATION_MESSAGE);

        } else {
            this.enableInputMethods(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        cbFem = new javax.swing.JCheckBox();
        cbMas = new javax.swing.JCheckBox();
        jcbTurno = new javax.swing.JComboBox<>();
        jcbRol = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnSubirImag = new javax.swing.JButton();
        cbUpImag = new javax.swing.JCheckBox();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtContrasena = new javax.swing.JPasswordField();
        fechaNacimientoChooser = new com.toedter.calendar.JDateChooser();
        btnRegresar2 = new javax.swing.JButton();
        btnRegresar1 = new javax.swing.JButton();
        btnRegistrar1 = new javax.swing.JButton();
        btnBuscarEmp = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Usuarios");

        jLabel1.setText("Datos De Empleado");

        jLabel2.setText("ID:");

        jLabel3.setText("Usuario:");

        jLabel4.setText("Contraseña:");

        jLabel5.setText("DNI:");

        jLabel6.setText("Nombres:");

        jLabel7.setText("Apellidos:");

        jLabel8.setText("Fecha de Nacimiento:");

        jLabel9.setText("Sexo:");

        jLabel10.setText("Puesto:");

        jLabel11.setText("Turno:");

        txtID.setEnabled(false);

        cbFem.setText("F");
        cbFem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFemActionPerformed(evt);
            }
        });

        cbMas.setText("M");
        cbMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMasActionPerformed(evt);
            }
        });

        jcbRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRolActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconRegistrarUs.jpg"))); // NOI18N

        btnSubirImag.setText("Subir Imagen");
        btnSubirImag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirImagActionPerformed(evt);
            }
        });

        cbUpImag.setText("No Por El Momento");
        cbUpImag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUpImagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 106, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSubirImag)
                        .addGap(131, 131, 131))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbUpImag)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel12)
                .addGap(46, 46, 46)
                .addComponent(btnSubirImag)
                .addGap(18, 18, 18)
                .addComponent(cbUpImag)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegresar2.setText("Regresar");
        btnRegresar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar2ActionPerformed(evt);
            }
        });

        btnRegresar1.setText("Regresar");
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });

        btnRegistrar1.setText("Registrar");
        btnRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar1ActionPerformed(evt);
            }
        });

        btnBuscarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Lupa1.png"))); // NOI18N
        btnBuscarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpActionPerformed(evt);
            }
        });

        jLabel13.setText("Correo:");

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(cbFem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbMas))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                            .addComponent(txtCorreo))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jcbTurno, javax.swing.GroupLayout.Alignment.LEADING, 0, 122, Short.MAX_VALUE)
                                            .addComponent(jcbRol, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(fechaNacimientoChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28)
                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(41, 41, 41))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(40, 40, 40)))
                .addGap(11, 11, 11)
                .addComponent(btnBuscarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(31, 31, 31)
                        .addComponent(btnEliminar)
                        .addGap(26, 26, 26)
                        .addComponent(btnRegresar1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegresar2)
                            .addComponent(btnRegistrar1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(btnBuscarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaNacimientoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbFem)
                            .addComponent(cbMas))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jcbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jcbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRegistrar)
                                    .addComponent(btnModificar)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnRegresar1))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btnRegistrar1)
                        .addGap(29, 29, 29)
                        .addComponent(btnRegresar2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        registrarUsuario();

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void jcbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbRolActionPerformed

    private void jcbTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbTurnoActionPerformed

    private void cbFemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFemActionPerformed
        // TODO add your handling code here:
        //emp.setSexo('F');
        if (num == 0) {
            cbMas.setSelected(false);
        }
    }//GEN-LAST:event_cbFemActionPerformed

    private void cbMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMasActionPerformed
        // TODO add your handling code here:
        //emp.setSexo('M');
        if (num == 0) {
            cbFem.setSelected(false);
        }
    }//GEN-LAST:event_cbMasActionPerformed

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        // TODO add your handling code here:
        super.dispose();
    }//GEN-LAST:event_btnRegresar1ActionPerformed

    private void btnRegresar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar2ActionPerformed
        // TODO add your handling code here:
        super.dispose();;
    }//GEN-LAST:event_btnRegresar2ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:

        //Empleado emple = new Empleado();
        emp = objeBuscarEmp.getEmpleadoSeleccionado();
        num = 1;
        int cont = validar();
        System.out.println(cont);
        System.out.println("asdf");
        if ((validar() == 0)) {
            try {
                emp.setNombre(txtNombres.getText());
                System.out.println("asdf");
                emp.setApellido(txtApellido.getText());
                System.out.println("asdf");
                Date dateChooser = fechaNacimientoChooser.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fechaString = dateFormat.format(dateChooser);
                System.out.println("asdf");
                emp.setFechaNac(fechaString);
                System.out.println("asdf");
                System.out.println(cbFem.getText());
                String nm = cbFem.getText();
                if (nm == "F") {
                    emp.setSexo('F');
                } else {
                    emp.setSexo('M');
                }
                System.out.println(emp.getSexo());
//
                int ddni = Integer.parseInt(txtDNI.getText());
                emp.setDNI(ddni);
                String nomP = (String) jcbRol.getSelectedItem();
                System.out.println(nomP);
                Puesto p = new Puesto();
                p.setNombPuesto(nomP);
                emp.setPuesto(p);
                String nomT = (String) jcbTurno.getSelectedItem();
                System.out.println(nomT);
                if (nomT.equals("MAÑANA")) {
                    emp.setTurno(Turno.Mañana);
                } else if (nomT.equals("TARDE")) {
                    emp.setTurno(Turno.Tarde);
                } else if (nomT.equals("NOCHE")) {
                    emp.setTurno(Turno.Noche);
                }
                
                 
                //emp.setTurno(Turno.Tarde);
                //emp.setPuesto(puesto);
//                byte[] fileContent = null;
//
//                StringBuffer fileContentStr = new StringBuffer("");
//                BufferedReader reader = null;
//                if (imgFile != null) {
//                    try {
//                        reader = new BufferedReader(new FileReader(imgFile.getPath()));
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(JFrameUsuarios.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    String line = null;
//                    try {
//                        while ((line = reader.readLine()) != null) {
//                            fileContentStr.append(line).append("\n");
//                        }
//                    } catch (IOException ex) {
//                        System.out.println("no hay imagen");
//                    }
//
//                    fileContent = fileContentStr.toString().trim().getBytes();
//                } else {
//                    fileContent = null;
//                }
//                System.out.println(fileContent);    
//                emp.setImageFile(fileContent);
               
                logicaNeg.modificarEmp(emp);
                JOptionPane.showMessageDialog(null, "El usuario: " + Integer.toString(emp.getID()) + " ha sido modificado correctamente", "Ventana Clientes", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                System.out.println(emp.getSexo());
            }
        } else {
            this.enableInputMethods(true);
        }
    }//GEN-LAST:event_btnModificarActionPerformed
    private void limpiarDatos() {
        txtApellido.setText("");
        txtContrasena.setText("");
        txtCorreo.setText("");
        txtDNI.setText(" ");
        txtID.setText("");
        txtNombres.setText("");
        txtUsuario.setText("");
        cbFem.setSelected(false);
        cbMas.setSelected(false);
    }
    private void btnBuscarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpActionPerformed
        // TODO add your handling code here:

        limpiarDatos();
        objeBuscarEmp = new JFListarEmpleados(this, true);
        //objeBuscarEmp.setAlwaysOnTop(true);
        objeBuscarEmp.setVisible(true);

        if (objeBuscarEmp.getEmpleadoSeleccionado() != null) {
            Empleado empleadoSeleccionado = objeBuscarEmp.getEmpleadoSeleccionado();
            txtNombres.setText(empleadoSeleccionado.getNombre());
            txtDNI.setText(Integer.toString(empleadoSeleccionado.getDNI()));
            txtID.setText(Integer.toString(empleadoSeleccionado.getID()));
            txtApellido.setText(empleadoSeleccionado.getApellido());
            String nn = empleadoSeleccionado.getPuesto().getNombPuesto();
            System.out.println("aaaaaaaa");
            System.out.println(nn);
            jcbRol.setSelectedItem(nn.toUpperCase());
            System.out.println(empleadoSeleccionado.getTurno().toString().toUpperCase());
            jcbTurno.setSelectedItem(empleadoSeleccionado.getTurno().toString().toUpperCase());

//            Turno tur=empleadoSeleccionado.getTurno();
//            if (tur.equals("MAÑANA")){
//                jcbTurno.setSelectedItem("MAÑANA");
//            }else if (tur.equals("TARDE")){
//                jcbTurno.setSelectedItem("TARDE");
//            }else if (tur.equals("NOCHE")){
//                jcbTurno.setSelectedItem("NOCHE");
//            }
            //jcbTurno.setSelectedItem(empleadoSeleccionado.getTurno());
            //jcbRol.setSelectedItem("GERENTE");//esta es la forma de jalar los datos en jcbrol
            //jcbTurno.setSelectedItem("TARDE");
            //txtCorreo.setText(empleadoSeleccionado.get);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = empleadoSeleccionado.getFechaNac();
            System.out.println(dateInString);
            Date date;
            try {
                date = formatter.parse(dateInString);
                fechaNacimientoChooser.setDate((date));
            } catch (ParseException ex) {
                System.out.println(ex.toString());
            }
            //fechaNacimientoChooser.setDate(date);

            if (empleadoSeleccionado.getSexo() == 'F') {
                cbFem.setSelected(true);
            } else if (empleadoSeleccionado.getSexo() == 'M') {
                cbMas.setSelected(true);
            }
            txtUsuario.setEnabled(false);
            txtContrasena.setEnabled(false);
            txtCorreo.setEnabled(false);
//            byte[] fileC = null;
//            if (empleadoSeleccionado.getImageFile() == null) {
//                cbUpImag.setSelected(true);
//            } else {
//                System.out.println("a");
//                File file = new File("C:\\Users\\kathe\\Pictures\\Monigote.png");
//                try {
//                    fileC = empleadoSeleccionado.getImageFile();
//                    System.out.println(fileC);
//
//                    Blob blob = new SerialBlob(empleadoSeleccionado.getImageFile());
//                    System.out.println("BLOB ES:" + blob.toString());
//                    int lon = (int) blob.length();
//                    byte[] vf = blob.getBytes(1L, lon);
//
//                    System.out.println(vf);
//
//                    ImageIcon imageIcon = new ImageIcon(vf);
//                    System.out.println(imageIcon);
//
//                    jLabel12.setLocation(100, 100);
//                    jLabel12.setIcon(imageIcon);
//                } catch (Exception ex) {
//                    System.out.println("no va");
//                }
//            }
        System.out.println("SALIDA GG");
                   try{
                       File file  = imagenesAD.getFile(empleadoSeleccionado.getID());
                         System.err.println(file.getPath());
        ImageIcon image = new ImageIcon(file.getPath());

        jLabel12.setIcon(image);
                   }catch(Exception ex ){
                   }
        }
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        //System.out.println(fechaC);
    }//GEN-LAST:event_btnBuscarEmpActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        Empleado empSelec = new Empleado();
        empSelec = objeBuscarEmp.getEmpleadoSeleccionado();
        logicaNeg.elimEmpl(empSelec);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
        // TODO add your handling code here:
        registrarUsuario();
    }//GEN-LAST:event_btnRegistrar1ActionPerformed

    private void btnSubirImagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirImagActionPerformed
        //funcion subir imagen
        JFileChooser chooser;

        chooser = new JFileChooser();

        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Archivo de Imagenes");

        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
            
            try{
                int idEmpleado = Integer.parseInt(txtID.getText());
                imagenesAD.uploadFile(chooser.getSelectedFile(), idEmpleado);
                
            } catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("No Selection ");
        }
        imgFile = chooser.getSelectedFile();
        ImageIcon image = new ImageIcon(chooser.getSelectedFile() + "");

        jLabel12.setIcon(image);

    }//GEN-LAST:event_btnSubirImagActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void cbUpImagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUpImagActionPerformed
        // TODO add your handling code here:
        imgFile = null;
    }//GEN-LAST:event_cbUpImagActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameUsuarios(null, false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEmp;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrar1;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JButton btnRegresar2;
    private javax.swing.JButton btnSubirImag;
    private javax.swing.JCheckBox cbFem;
    private javax.swing.JCheckBox cbMas;
    private javax.swing.JCheckBox cbUpImag;
    private com.toedter.calendar.JDateChooser fechaNacimientoChooser;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jcbRol;
    private javax.swing.JComboBox<String> jcbTurno;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
