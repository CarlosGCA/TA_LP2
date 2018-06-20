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
public class InsumoAD {
    
    public int id_correlativo (){
        int maxid=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            Statement sentencia = con.createStatement();
            String query = "select max(idInsumo) as id from Insumo";
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
    
    public ArrayList<String> listarMedidas (){
        ArrayList<String> lista = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            Statement sentencia = con.createStatement();
            String query ="select * from UnidadDeMedida;";
            ResultSet rs = sentencia.executeQuery(query);           
            while(rs.next()){
                String uMedid=rs.getString("nombre");
                lista.add(uMedid);                
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
        
    }
    
    public ArrayList<Insumo> listarInsumos(){
        ArrayList<Insumo> lista = new ArrayList<Insumo>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call LISTAR_INSUMOS()}");            
            ResultSet rs = sentencia.executeQuery();           
            while(rs.next()){
                Insumo in = new Insumo();                
                in.setidInsumo(rs.getInt("idInsumo"));
                in.setNombre(rs.getString("Nombre"));                
                String aux = rs.getString("Medida");                
                if(aux.equals("UNIDADES")) in.setunidMed(unidadMed.unid);
                else if(aux.equals("CAJAS")) in.setunidMed(unidadMed.cajas);
                else if(aux.equals("LITROS")) in.setunidMed(unidadMed.lt);
                else if(aux.equals("KILOGRAMOS")) in.setunidMed(unidadMed.kg);
                in.setdescripcionm(rs.getString("Descripcion"));
                in.setCantidaMinima(rs.getInt("CantidadMinima"));
                String aux1 = rs.getString("Descripcion");
                
                lista.add(in);                                
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public int registarInsumo(int _id,String _nombre,int _medida,String _descripcion,int cantidad){
        int id=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call REGISTRAR_INSUMO(?,?,?,?,?,?)}");
            sentencia.registerOutParameter("_idregistrado", java.sql.Types.INTEGER);
            sentencia.setInt("_id", _id);
            sentencia.setString("_nombre", _nombre);
            sentencia.setInt("_idmedida", _medida);
            sentencia.setString("_descripcion", _descripcion);
            sentencia.setInt("cant",cantidad);
            sentencia.execute();
            id = sentencia.getInt("_idregistrado");
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id;
    }
    
    public int eliminarInsumo(int _id){
        int auxID=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            CallableStatement sentencia = con.prepareCall("{call ELIMINAR_INSUMO(?,?)}");
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
    
    public int modificarInsumo(int id,String nombre,int medida,String descripcion,int cantidad){
        int auxID=0;
        int gg=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
            gg+=1;
            CallableStatement sentencia = con.prepareCall("{call MODIFICAR_INSUMO(?,?,?,?,?,?)}");
            gg+=1;
            sentencia.registerOutParameter("idModificado", java.sql.Types.INTEGER);
            sentencia.setInt("id", id);
            sentencia.setString("nombre", nombre);
            sentencia.setInt("medida", medida);
            sentencia.setString("descripcion", descripcion);
            sentencia.setInt("cant", cantidad);
            sentencia.execute();
            auxID = sentencia.getInt("idModificado");
            con.close();             
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return auxID;
    }
    
}
