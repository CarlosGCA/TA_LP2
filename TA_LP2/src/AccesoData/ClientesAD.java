/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import Modelo.Empresa;
import Modelo.Natural;
import Modelo.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kathy Ruiz :)
 */
public class ClientesAD {

    public void registrarNat(Natural nat) {
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "CREAR_NATURAL(?,?,?,?,?,?)}"
                    );

            String nomE1, nomE2;
            cs.setString(1, nat.getDNI());
            cs.setString(2, nat.getNombre());
            cs.setInt(3, nat.getTelefono());
            cs.setString(4,nat.getDireccion());
            cs.setString(5,nat.getCuentaBancaria());
            int u=1;
            cs.setInt(6,u);

            cs.executeUpdate();
//            int numM1;
//            numM1 = cs.getInt(1);

            System.out.println("El Cliente ha sido registrado correctamente");
            con.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void registrarEmp(Empresa emp) {
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "CREAR_EMPRESA(?,?,?,?,?,?)}"
                    );

          
            cs.setString(1, emp.getRuc());
            cs.setString(2, emp.getRazonSocial());
            cs.setInt(3, emp.getTelefono());
            cs.setString(4, emp.getDireccion());
            cs.setString(5, emp.getCuentaBancaria());
            int i=1;
            cs.setInt(6, i);

            cs.executeUpdate();
            //int numM1;
            //numM1 = cs.getInt(1);

            System.out.println("El Cliente ha sido registrado correctamente");
            con.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public ArrayList<Natural> listarNaturales(){
        ArrayList<Natural> lista = new ArrayList<Natural>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7","inf282g7","0mvK88");
        
            Statement sentencia = con.createStatement();
            String sql ="Select * from inf282g7.Natural;";
            ResultSet rs = sentencia.executeQuery(sql);
            while(rs.next()){
                Natural n = new Natural();
                n.setNombre(rs.getString("Nombre"));
                n.setDNI(rs.getString("DNI"));
                lista.add(n);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return lista;
    } 
    
    public ArrayList<Empresa> listarEmpresa(){
        ArrayList<Empresa> lista = new ArrayList<Empresa>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7","inf282g7","0mvK88");
        
            Statement sentencia = con.createStatement();
            String sql ="Select * from inf282g7.Empresa;";
            ResultSet rs = sentencia.executeQuery(sql);
            while(rs.next()){
                Empresa e = new Empresa();
                e.setRazonSocial(rs.getString("RazonSocial"));
                e.setRuc(rs.getString("RUC"));
                lista.add(e);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return lista;
    } 
}
