/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kathy Ruiz :)
 */
package AccesoData;

import Modelo.CuentaUsuario;
import Modelo.Empleado;
import Modelo.Permiso;
import Modelo.Puesto;
import Modelo.Turno;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;
//import java.util.Date;

public class UsuarioAD {

    public void registrar(Empleado emp) {
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "REGISTRAR_USUARIO(?,?,?,?,?,?,?,?,?,?,?,?)}"
                    );

            String nomE1, nomE2;
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, Integer.toString(emp.getDNI()));
            cs.setString(3, emp.getNombre());
            cs.setString(4, emp.getApellido());
            cs.setString(5, String.valueOf(emp.getSexo()));
            cs.setDate(6, Date.valueOf(emp.getFechaNac()));
            cs.setString(7, emp.getUsuario().getnombreUsuario());
            cs.setString(8, emp.getUsuario().getcontrasenha());
            cs.setString(9, emp.getUsuario().getpermise().getNombre());
            cs.setString(10, emp.getTurno().toString());
            cs.setString(11, emp.getUsuario().getCorreo());
            if (emp.getImageFile() != null) {
                Blob blob = new SerialBlob(emp.getImageFile());
                System.out.println("BLOB ES:" + blob.toString());
                cs.setBlob(12, blob);
            } else {
                Blob b = null;
                cs.setBlob(12, b);
            }
            cs.executeUpdate();
            int numM1;
            numM1 = cs.getInt(1);
            System.out.println(numM1);
            emp.setID(numM1);
            System.out.println("El Usuario " + numM1 + " ha sido registrado correctamente");
            con.close();

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public CuentaUsuario buscarUsuarioLogin(String nombre) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "BUSCAR_USUARIO_LOGUEO(?)}"
                    );
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                CuentaUsuario cu = new CuentaUsuario();
                cu.setidUsuario(rs.getInt("idCuentaUsuario"));
                cu.setcontrasenha(rs.getString("Contrasena"));
                Permiso per = new Permiso();
                per.setIdPermiso(rs.getInt("Permiso_idPermiso"));
                cu.setpermise(per);
                if (rs.getInt("bloqueado") == 1) {
                    cu.setBloqueado(true);
                } else {
                    cu.setBloqueado(false);
                }
                con.close();
                return cu;
            }
            con.close();
            return null;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void bloquearUsuario(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "USUARIO_BLOQUEAR(?)}"
                    );
            cs.setInt(1, id);
            cs.executeUpdate();

            System.out.println("El Usuario con id " + id + " ha sido bloqueado");
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            //Statement sentencia = con.createStatement();
            //String sql = "LISTAR_USUARIO(?,?,?,?,?,?,?,?,?)";
            //ResultSet rs = sentencia.executeQuery(sql);
            CallableStatement cs
                    = con.prepareCall("{call "
                            + "LISTAR_EMPLEADO()}"
                    );

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Empleado emp = new Empleado();

                emp.setID(rs.getInt(1));
                String auxdni = rs.getString(5);
                emp.setNombre(rs.getString(2));
                emp.setDNI(Integer.parseInt(auxdni));
                emp.setApellido(rs.getString(6));
                emp.setSexo((rs.getString(8)).charAt(0));
//                Blob b = rs.getBlob("imagen_usuario");
//                Byte im = b.emp.setImageFile();
                Turno tur;
                String nomTur = rs.getString(11);
                System.out.println("ttt" + nomTur);
                if (nomTur.equals("MAÑANA")) {
                    emp.setTurno(Turno.Mañana);
                } else if (nomTur.equals("TARDE")) {
                    emp.setTurno(Turno.Tarde);
                } else if (nomTur.equals("NOCHE")) {
                    emp.setTurno(Turno.Noche);
                }
                Puesto p = new Puesto();
                p.setNombPuesto(rs.getString(13));
                emp.setPuesto(p);
                Blob blob = rs.getBlob(8);

                if (blob != null) {
                    System.out.println(blob.toString());
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    //System.out.println(blobAsBytes);
                    System.out.println("a");
                    emp.setImageFile(blobAsBytes);
                    blob.free();

                } else {
                    System.out.println("b");
                    emp.setImageFile(null);
                }
                //System.out.println(rs.getDate("Fecha_Nacimiento"));
                Date fN = rs.getDate(7);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaComoCadena = sdf.format(fN);
                //System.out.println(fechaComoCadena);
                //System.out.println(rs.getString("Sexo").charAt(0));
                emp.setFechaNac(fechaComoCadena);
                lista.add(emp);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public void eliminarEmp(Empleado emp) {
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "ELIMINAREMP(?)}"
                    );
            cs.setString(1, Integer.toString(emp.getID()));

            cs.executeUpdate();

            System.out.println("El Usuario: " + Integer.toString(emp.getID()) + " ha sido eliminado");
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void modificarEmp(Empleado emp) {
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "MODIFICAREMP(?,?,?,?,?,?,?,?,?)}"
                    );
            cs.setString(1, Integer.toString(emp.getID()));
            cs.setString(2, Integer.toString(emp.getDNI()));
            cs.setString(3, emp.getNombre());
            cs.setString(4, emp.getApellido());
            cs.setString(5, emp.getFechaNac());
            cs.setString(6, String.valueOf(emp.getSexo()));
            System.out.println("ss" + emp.getPuesto().getNombPuesto().toUpperCase());
            System.out.println("sf" + emp.getTurno().toString().toUpperCase());
            cs.setString(7, emp.getPuesto().getNombPuesto().toUpperCase());
            cs.setString(8, emp.getTurno().toString().toUpperCase());
            if (emp.getImageFile() != null) {
                Blob blob = new SerialBlob(emp.getImageFile());
                System.out.println("BLOB ES:" + blob.toString());
                cs.setBlob(9, blob);
            } else {
                Blob b = null;
                cs.setBlob(9, b);
            }
            cs.executeUpdate();

            System.out.println("El Usuario: " + Integer.toString(emp.getID()) + " ha sido modificado");
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
