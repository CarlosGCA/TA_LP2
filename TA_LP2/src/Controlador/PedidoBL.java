/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
//import Modelo.Pedido;
import AccesoData.PedidoAD;
import Modelo.LineaPedidoProducto;
import Modelo.Natural;
import Modelo.PedidoProducto;
/**
 *
 * @author Kathy Ruiz :)
 */
public class PedidoBL {
    private PedidoAD accesoDatos;
    
    public PedidoBL(){
        accesoDatos=new PedidoAD();
    }
    
    public int registrarPedido(PedidoProducto ped, int id_user){
        int idped = accesoDatos.registrarPedido(ped, id_user);
        
        if(idped!=0){
            for(LineaPedidoProducto lpp : ped.getListaLineasPedido())
                if(lpp.getHabilitado())
                    accesoDatos.registrarLineaPedido(lpp, idped);
        }
        return idped;
    }
    
    public ArrayList<PedidoProducto> listarPedidos(){
        ArrayList<PedidoProducto> lista = new ArrayList<PedidoProducto>(accesoDatos.listarPedidos());
        return lista;
    }
    
    public ArrayList<LineaPedidoProducto> listarLineasPedido(int idped){
        return accesoDatos.listarLineasPedido(idped);
    }
    
    public void modificarPedido(PedidoProducto ped, int id_user){
        accesoDatos.modificarPedido(ped, id_user);
        
        int idped = ped.getidPedido();
        for(LineaPedidoProducto lpp : ped.getListaLineasPedido())
            accesoDatos.modificarLineaPedido(lpp, idped);
    }
    
    public void cambiarEstadoPedido(int idped, int idest){
        accesoDatos.cambiarEstadoPedido(idped, idest);
    }
}
