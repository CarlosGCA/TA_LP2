/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import java.util.ArrayList;
import Modelo.PedidoProducto;
import Modelo.LineaPedidoProducto;
import Modelo.EstadoPedido;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;

/**
 *
 * @author Kathy Ruiz :)
 */
public class PedidoAD {

    //public ArrayList<Pedido> obtenerLista() {
//        return;
//    }
    
    public int registrarPedido(PedidoProducto ped, int iduser){
         int idreg=0;
         try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "REGISTRAR_PEDIDO_PRODUCTO(?,?,?,?,?)}"
                    );
            
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechEntrega = dateFormat.format(ped.getfechaEntrPed());
            
            cs.setString(2, fechEntrega);
            cs.setInt(3, iduser);
            cs.setInt(4, 5);
            cs.setInt(5, ped.getcliente().getId_cliente());
            
            cs.execute();
            idreg=cs.getInt(1);
            con.close();
         }catch(Exception e){
             System.out.println(e.toString());
         }
         return idreg;
    }
    
    public void registrarLineaPedido (LineaPedidoProducto lpp, int id_ped){
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "REGISTRAR_LINEA_PEDIDO_PRODUCTO(?,?,?,?)}"
                    );
            
            cs.setFloat(1, lpp.getCantidad());
            cs.setFloat(2, lpp.getDescuento());
            cs.setInt(3, lpp.getProducto().getidProducto());
            cs.setInt(4, id_ped);
            
            cs.executeUpdate();
            System.out.println("Se registro la linea");
            con.close();
         }catch(Exception e){
             System.out.println(e.toString());
         }
    }
}
