/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import Modelo.Cliente;
import Modelo.Empresa;
import java.util.ArrayList;
import Modelo.PedidoProducto;
import Modelo.LineaPedidoProducto;
import Modelo.EstadoPedido;
import Modelo.Natural;
import Modelo.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
            cs.setInt(4, 2);
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
    
    public ArrayList<PedidoProducto> listarPedidos(){
        ArrayList<PedidoProducto> lista = new ArrayList<PedidoProducto>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs = con.prepareCall("{call LISTAR_PEDIDOS_PRODUCTO()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PedidoProducto pp = new PedidoProducto();
                
                pp.setidPedido(rs.getInt(1));
                
                Date freg = rs.getDate(2);
                String fechReg=freg.toString();
                pp.setfechaRegPed(fechReg);
                
                Date fent = rs.getDate(3);
                String fechEnt=fent.toString();
                pp.setfechaEntrPed(fechEnt);
                
                String TipoCli = rs.getString(6);
                
                EstadoPedido estPed = EstadoPedido.values()[(rs.getInt(4))];
                pp.setestadoPedo(estPed);
                
                if(TipoCli.equals("Natural")){
                    Natural n = new Natural();
                    n.setId_cliente(rs.getInt(5));
                    String nombreCompleto = rs.getString(7);
                    String[] nombreApellido = nombreCompleto.split("-");
                    n.setNombre(nombreApellido[0]);
                    n.setApellidos(nombreApellido[1]);
                    n.setDNI(rs.getString(8));
                    pp.setcliente(n);
                }else{
                    Empresa e = new Empresa();
                    e.setId_cliente(rs.getInt(5));
                    e.setRazonSocial(rs.getString(7));
                    e.setRuc(rs.getString(8));
                    pp.setcliente(e);
                } 
                lista.add(pp);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public ArrayList<LineaPedidoProducto> listarLineasPedido(int idPed){
        ArrayList<LineaPedidoProducto> lista = new ArrayList<LineaPedidoProducto>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "LISTAR_LINEAS_PEDIDO(?)}"
                    );
            cs.setInt(1, idPed);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LineaPedidoProducto lpp = new LineaPedidoProducto();
                Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(4));
                lpp.setProducto(p);
                lpp.setCantidad(rs.getInt(3));
                lpp.setDescuento(rs.getFloat(5));
                lpp.setHabilitado(rs.getBoolean(6));
                lista.add(lpp);
            }
            con.close();
            return lista;
        }catch(Exception e){
              System.out.println(e.toString());  
        }
        return lista;
    }
    
    public void modificarPedido(PedidoProducto ped, int iduser){
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "MODIFICAR_PEDIDO_PRODUCTOS(?,?,?,?)}"
                    );
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechEntrega = dateFormat.format(ped.getfechaEntrPed());
            
            cs.setInt(1, ped.getidPedido());
            cs.setString(2, fechEntrega);
            cs.setInt(3, iduser);
            cs.setInt(4, ped.getcliente().getId_cliente());
            
            cs.execute();
            con.close();
            System.out.println("Pedido Modificado correctamente");
         }catch(Exception e){
             System.out.println(e.toString());
         }
    }
    
    public void modificarLineaPedido(LineaPedidoProducto lpp, int id_ped){
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "MODIFICAR_LINEA_PEDIDO_PRODUCTO(?,?,?,?,?)}"
                    );
            
            cs.setFloat(1, lpp.getCantidad());
            cs.setFloat(2, lpp.getDescuento());
            cs.setBoolean(3, lpp.getHabilitado());
            cs.setInt(4, lpp.getProducto().getidProducto());
            cs.setInt(5, id_ped);
            
            cs.executeUpdate();
            System.out.println("Se modifico la linea");
            con.close();
         }catch(Exception e){
             System.out.println(e.toString());
         }
    }
    
    public void cambiarEstadoPedido(int idped, int idest){
        try {
            //Registrar el Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            Statement sentencia = con.createStatement();
            String sql = "UPDATE PedidoProductos SET EstadoPedido_idEstadoPedido = "+ idest +" WHERE idPedidoProductos = " +idped+";";
            sentencia.executeUpdate(sql);
            System.out.println("Pedido Anulado Correctamente");
            con.close();
         }catch(Exception e){
             System.out.println(e.toString());
         }
    }
}
