/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author alfredo
 */
public class ImagenesAD {

    private Connection con = null;
    
    private void getConnectionDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
        }catch (Exception ex ){
            System.err.println(ex.getMessage());
        }
    }
    
    public void uploadFile(File file , int idEmpleado){
        if (con != null){
            try {
                InputStream inputStream = new FileInputStream(file);
 
                String sql = "INSERT INTO Foto (Foto,idEmpleado) values (?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setBlob(1, inputStream);
                statement.setInt(2, idEmpleado);
                statement.executeUpdate();
            } catch (Exception e) {
            }

        }
    }
    
    
    
    public File getFile(int idEmpleado){
        
        
        File file = new File("salida.jpg") ;
        InputStream binaryStream = null ; 
        if (con != null){
               try {
 
               PreparedStatement sql = con.prepareStatement("SELECT * FROM inf282g7.Foto WHERE idEmpleado=?");
               sql.setInt(1, idEmpleado);
               ResultSet  rs = sql.executeQuery();
               while(rs.next()){
                  //Blob blod = rs.getBlob("Foto");
                   binaryStream= rs.getBinaryStream("Foto");
               }
                   OutputStream outputStream = new FileOutputStream(file);
                   IOUtils.copy(binaryStream, outputStream);
                   outputStream.close();
            } catch (Exception e) {
                   System.err.println(e.getMessage());
            }
        }
        return file;
    }

    public ImagenesAD() {
        getConnectionDB();
    }
    
    

}
