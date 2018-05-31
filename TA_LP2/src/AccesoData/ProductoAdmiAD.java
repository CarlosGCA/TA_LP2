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
    
     
}
