/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import java.sql.Connection;
import java.sql.DriverManager;
import Modelo.Producto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author LENOVO
 */
public class ProductoAD {
    public ArrayList<Producto> listarProductos(){
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7","inf282g7","0mvK88");
        
            Statement sentencia = con.createStatement();
            String sql ="Select * from inf282g7.Producto;";
            ResultSet rs = sentencia.executeQuery(sql);
            while(rs.next()){
                Producto p = new Producto();
                p.setidProducto(rs.getInt("idProducto"));
                p.setnombProducto(rs.getString("NombreProducto"));
                p.setprecio(rs.getFloat("Precio"));
                lista.add(p);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return lista;
    }
}
