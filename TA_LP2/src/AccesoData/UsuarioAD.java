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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;

public class UsuarioAD {

    public void registrar(Empleado emp) {
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "REGISTRAR_USUARIO(?,?,?,?,?,?,?,?,?,?)}"
                    );



            String nomE1, nomE2;
            cs.setString(2, Integer.toString(emp.getDNI()));
            cs.setString(3, emp.getNombre());
            cs.setString(4, emp.getApellido());
            cs.setString(5, String.valueOf(emp.getSexo()));
            cs.setDate(6, Date.valueOf(emp.getFechaNac()));
            cs.setString(7, emp.getUsuario().getnombreUsuario());
            cs.setString(8, emp.getUsuario().getcontrasenha());
            cs.setString(9, emp.getUsuario().getpermise().getNombre());
            cs.setString(10, emp.getTurno().toString());

            cs.executeUpdate();
            int numM1;
            numM1 = cs.getInt(1);

            System.out.println("El Usuario ha sido registrado correctamente");
            con.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public CuentaUsuario buscarUsuarioLogin(String nombre){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "BUSCAR_USUARIO_LOGUEO(?)}"
                    );
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                CuentaUsuario cu = new CuentaUsuario();
                cu.setidUsuario(rs.getInt("idCuentaUsuario"));
                cu.setcontrasenha(rs.getString("Contrasena"));
                Permiso per = new Permiso();
                per.setIdPermiso(rs.getInt("Permiso_idPermiso"));
                cu.setpermise(per);
                if(rs.getInt("bloqueado")==1)
                    cu.setBloqueado(true);
                else
                    cu.setBloqueado(false);
                return cu;
            }          
            return null;         
            
        }catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public void bloquearUsuario(int id){
        try{
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
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
