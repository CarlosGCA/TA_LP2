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
                            + "CREAR_NATURAL(?,?,?,?,?,?,?,?)}"
                    );

            String nomE1, nomE2;
            cs.setString(1, nat.getDNI());
            cs.setString(2, nat.getNombre());
            cs.setString(3, nat.getApellidos());
            cs.setInt(4, nat.getTelefono());
            cs.setString(5, nat.getDireccion());
            cs.setString(6, nat.getCuentaBancaria());
            cs.setString(7, nat.getCorreo());
            int u = 1;
            cs.setInt(8, u);

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
                            + "CREAR_EMPRESA(?,?,?,?,?,?,?)}"
                    );

            cs.setString(1, emp.getRuc());
            cs.setString(2, emp.getRazonSocial());
            cs.setInt(3, emp.getTelefono());
            cs.setString(4, emp.getDireccion());
            cs.setString(5, emp.getCuentaBancaria());
            cs.setString(6, emp.getCorreo());
            int i = 1;
            cs.setInt(7, i);

            cs.executeUpdate();
            //int numM1;
            //numM1 = cs.getInt(1);

            System.out.println("El Cliente ha sido registrado correctamente");
            con.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<Natural> listarNaturales() {
        ArrayList<Natural> lista = new ArrayList<Natural>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            Statement sentencia = con.createStatement();
            String sql = "select * from inf282g7.Natural inner join Cliente on inf282g7.Natural.Cliente_idCliente=Cliente.idCliente;";
            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                Natural n = new Natural();
                n.setId_cliente(rs.getInt("idCliente"));
                n.setNombre(rs.getString("Nombre"));
                n.setDNI(rs.getString("DNI"));
                n.setApellidos(rs.getString("Apellido"));
                n.setDireccion(rs.getString("direccion"));
                n.setTelefono(Integer.parseInt(rs.getString("telefono")));
                n.setCuentaBancaria(rs.getString("CuentaBancaria"));
                n.setCorreo(rs.getString("correo"));
                lista.add(n);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public ArrayList<Empresa> listarEmpresa() {
        ArrayList<Empresa> lista = new ArrayList<Empresa>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            Statement sentencia = con.createStatement();
            String sql = "select * from inf282g7.Empresa inner join Cliente on inf282g7.Empresa.Cliente_idCliente=Cliente.idCliente;";
            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                Empresa e = new Empresa();
                e.setId_cliente(rs.getInt("idCliente"));
                e.setRazonSocial(rs.getString("RazonSocial"));
                e.setRuc(rs.getString("RUC"));
                e.setDireccion(rs.getString("direccion"));
                e.setTelefono(Integer.parseInt(rs.getString("telefono")));
                e.setCuentaBancaria(rs.getString("CuentaBancaria"));
                e.setCorreo(rs.getString("correo"));
                lista.add(e);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public void modificarCliEmpresa(Empresa emp) {//
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "MODIFICAREMPRESA(?,?,?,?,?,?,?)}"
                    );
            cs.setString(1, Integer.toString(emp.getId_cliente()));
            cs.setString(2, emp.getRuc());
            cs.setString(3, emp.getRazonSocial());
            cs.setString(5, emp.getDireccion());
            cs.setString(4, Integer.toString(emp.getTelefono()));
            cs.setString(6, emp.getCuentaBancaria());
            cs.setString(7, emp.getCorreo());

            cs.executeUpdate();

            System.out.println("El Usuario: " + Integer.toString(emp.getId_cliente()) + " ha sido modificado");
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void modificarCliNatural(Natural nat) {
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "MODIFICARNATURAL(?,?,?,?,?,?,?,?)}"
                    );
            cs.setInt(1, nat.getId_cliente());
            System.out.println(nat.getId_cliente());
            cs.setInt(2,Integer.parseInt(nat.getDNI()));
            System.out.println(nat.getDNI());
            cs.setString(3, nat.getNombre());
            cs.setString(4, nat.getApellidos());
            cs.setInt(5, nat.getTelefono());
            cs.setString(6, nat.getDireccion());
            cs.setString(7, nat.getCuentaBancaria());
            cs.setString(8, nat.getCorreo());

            cs.executeUpdate();

            System.out.println("El Usuario: " + Integer.toString(nat.getId_cliente()) + " ha sido modificado");
            con.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
