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
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call LISTAR_PRODUCTOS()}");            
            ResultSet rs = sentencia.executeQuery();           
            while(rs.next()){
                Producto in;
                in= new Producto();
                in.setidProducto(Integer.parseInt(rs.getString("idProducto")));
                in.setnombProducto(rs.getString("NombreProducto"));
                in.setprecio(Float.parseFloat(rs.getString("precio")));
                in.setDescripcion(rs.getString("Descripcion"));
                
                lista.add(in);                                
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }        
        return lista;
    }
    
    public int registarProducto(int id,String nombre,float precio,String descripcion){
        int aux=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call REGISTRAR_PRODUCTO(?,?,?,?,?)}");
            sentencia.registerOutParameter("idregistrado", java.sql.Types.INTEGER);
            sentencia.setInt("id", id);
            sentencia.setString("nombre", nombre);
            sentencia.setFloat("precio", precio);
            sentencia.setString("descripcion", descripcion);
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
    
    public int eliminarProducto(int _id){
        int auxID=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call ELIMINAR_PRODUCTO(?,?)}");
            sentencia.registerOutParameter("idEliminado", java.sql.Types.INTEGER);
            sentencia.setInt("id", _id);
            sentencia.execute();
            auxID = sentencia.getInt("idEliminado");
            con.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return auxID;
    } 
    
    public ArrayList<Ingrediente> listarIngredientesxProducto(int id){
        ArrayList<Ingrediente> lista = new ArrayList<Ingrediente> ();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call `LISTAR_INGREDIENTES_PRODUCTO_ADMI(?)}");
            sentencia.setInt("id", id);
            ResultSet rs = sentencia.executeQuery();           
            while(rs.next()){
                Ingrediente ig;
                Insumo i;
                ig= new Ingrediente();
                i = new Insumo();
                ig.setidIngrediente(rs.getInt("idIngrediente"));
                i.setNombre(rs.getString("Nombre"));
                ig.setcantidad(rs.getInt("Cantidad"));
                String aux = rs.getString("Medida");                
                if(aux.equals("UNIDADES")) i.setunidMed(unidadMed.unid);
                else if(aux.equals("CAJAS")) i.setunidMed(unidadMed.cajas);
                else if(aux.equals("LITROS")) i.setunidMed(unidadMed.lt);
                else if(aux.equals("KILOGRAMOS")) i.setunidMed(unidadMed.kg);
                ig.setinsumo(i);              
                lista.add(ig);                               
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return lista;
    }
}
