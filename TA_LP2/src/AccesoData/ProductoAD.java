/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import java.sql.Connection;
import java.sql.DriverManager;
import Modelo.Producto;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javafx.util.Pair;
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
                p.setDescripcion(rs.getString("Descripcion"));
                lista.add(p);
            }
            con.close();
        }catch(Exception e){
            System.err.println(e.toString());
        }
        return lista;
    }
    
    public ArrayList<Pair<String,Pair<Integer,Integer>>> obtenerRankingProductos(){
        ArrayList<Pair<String,Pair<Integer,Integer>>> listaProductos = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7","inf282g7","0mvK88");
            CallableStatement cs = con.prepareCall("{call LISTA_RANKING_PRODUCTOS()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Integer cantidad = rs.getInt("Veces");
                Integer totalComprado = rs.getInt("Total");
                String nombre = rs.getString("Nombre");
                listaProductos.add(new Pair<>(nombre , new Pair<>(cantidad, totalComprado)));            }
            con.close();
        } catch(Exception e ) {
            System.err.println(e.getMessage());
            
        }
        return listaProductos;
    }
    
    public  ArrayList<Pair<String,Pair<Integer,Integer>>> obtenerRankingProductosFechas(Date fechaInicio , Date fechaMaximo) {
     ArrayList<Pair<String,Pair<Integer,Integer>>> listaProductos = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7","inf282g7","0mvK88");
            CallableStatement cs = con.prepareCall("{call LISTA_RANKING_PRODUCTOS()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Integer cantidad = rs.getInt("Veces");
                Integer totalComprado = rs.getInt("Total");
                String nombre = rs.getString("Nombre");
                Date fecha = rs.getDate("Fecha");
                if (!fechaInicio.after(fecha) && !fechaMaximo.before(fecha)){
                    listaProductos.add(new Pair<>(nombre , new Pair<>(cantidad, totalComprado)));
                }
            }
            con.close();
        } catch(Exception e ) {
            System.err.println(e.getMessage());
            
        }
        return listaProductos;
    }
}
