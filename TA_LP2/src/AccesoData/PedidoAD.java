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
    
    public ArrayList<PedidoProducto> listarPedidos(){
        ArrayList<PedidoProducto> lista = new ArrayList<PedidoProducto>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            Statement sentencia = con.createStatement();
            String sql = "select idPedidoProductos,FechaRegistro,FechaEntrega,EstadoPedido_idEstadoPedido,Cliente_idCliente,tipo from PedidoProductos inner join Cliente where PedidoProductos.Cliente_idCliente=Cliente.idCliente;";
            ResultSet rs = sentencia.executeQuery(sql);
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
                
                EstadoPedido estPed = EstadoPedido.values()[(rs.getInt(4)-1)];
                pp.setestadoPedo(estPed);
                
                if(TipoCli.equals("Natural")){
                    Natural n = new Natural();
                    n.setId_cliente(rs.getInt(5));
                    pp.setcliente(n);
                }else{
                    Empresa e = new Empresa();
                    e.setId_cliente(rs.getInt(5));
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
    
    public Natural buscarNatural(int idNat){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "BUSCAR_NATURAL(?)}"
                    );
            cs.setInt(1, idNat);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Natural nat = new Natural();
                nat.setId_cliente(idNat);
                nat.setDNI(rs.getString(2));
                nat.setNombre(rs.getString(3));
                nat.setApellidos(rs.getString(4));
                con.close();
                return nat;
            }
            con.close();
            return null;
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public Empresa buscarEmpresa(int idEmp){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");

            CallableStatement cs
                    = con.prepareCall("{call "
                            + "BUSCAR_EMPRESA(?)}"
                    );
            cs.setInt(1, idEmp);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Empresa nat = new Empresa();
                nat.setId_cliente(idEmp);
                nat.setRuc(rs.getString(2));
                nat.setRazonSocial(rs.getString(3));
                con.close();
                return nat;
            }
            con.close();
            return null;
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
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
                lista.add(lpp);
            }
            con.close();
            return lista;
        }catch(Exception e){
              System.out.println(e.toString());  
        }
        return lista;
    }
}
