package AccesoData;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import Modelo.*;
/**
 *
 * @author Sebastian
 */
public class ProductoAdmiAD {
    
    public int id_correlativo (){
        int maxid=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            Statement sentencia = con.createStatement();
            String query = "select max(idProducto) as id from Producto";
            ResultSet rs = sentencia.executeQuery(query);
            while(rs.next()){
                maxid = rs.getInt("id") ; 
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        maxid +=1;
        return maxid;        
    }
     
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
    
    public int registarProducto(int id,String nombre,float precio){
        int aux=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call REGISTRAR_PRODUCTO(?,?,?,?)}");
            sentencia.registerOutParameter("idregistrado", java.sql.Types.INTEGER);
            sentencia.setInt("id", id);
            sentencia.setString("nombre", nombre);
            sentencia.setFloat("precio", precio);
            sentencia.execute();
            aux = sentencia.getInt("idregistrado");
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id;
    }
    
    public int registarIngrediente(int cantidad,int idInsumo){
        int aux=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call REGISTRAR_INGREDIENTE_RECETA(?,?,?)}");
            sentencia.registerOutParameter("idregistrado", java.sql.Types.INTEGER);
            sentencia.setInt("cantidad", cantidad);
            sentencia.setInt("idInsumo", idInsumo);
            sentencia.execute();
            aux = sentencia.getInt("idregistrado");
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }     
        
        return aux;
    }
    
    public int registrarIngredientesxProducto(int idInsumo,int idProducto){
        int aux=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call REGISTRAR_INGREDIENTEXPRODUCTO(?,?,?)}");
            sentencia.registerOutParameter("idregistrado", java.sql.Types.INTEGER);
            sentencia.setInt("idIngrediente", idInsumo);
            sentencia.setInt("idProducto", idProducto);
            sentencia.execute();
            aux = sentencia.getInt("idregistrado");
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return aux;
    }
    
     
}
