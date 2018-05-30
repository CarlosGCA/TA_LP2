/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;

/**
 *
 * @author alfredo
 */
public class EmailAD {
    
    public String returnContrasena(String userName){
        String salida = new String();
        try{
             Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs = con.prepareCall("{call " + "OBTENER_CONTRASENA(?,?)}");
            
            cs.setString(1, userName);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            
            cs.execute();
            
             salida = cs.getString(2);
            con.close();
        } catch (Exception ex){
            System.err.println(ex.toString());
        }
        
        return salida;
    }
   
    
    public String returnEmail(String userName){
        String salida = new String();
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs = con.prepareCall("{call " + "OBTENER_CORREO(?,?)}");
            
            cs.setString(1, userName);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            
            cs.execute();
            
             salida = cs.getString(2);
            con.close();
            
         }catch(Exception e){
             System.out.println(e.toString());
         }
        return salida;
            
    }
    
}
