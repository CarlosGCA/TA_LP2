/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import AccesoData.ProductoAD;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.Date;
import javafx.util.Pair;
/**
 *
 * @author LENOVO
 */
public class ProductoBL {
    private ProductoAD accesoDatos;
    
    public ProductoBL(){
        accesoDatos = new ProductoAD();
    }
    
    public ArrayList<Producto> listarProducto(){
        return accesoDatos.listarProductos();
    }
    
    public ArrayList<Pair<String,Pair<Integer,Integer>>> listarRankingProductos(){
        return accesoDatos.obtenerRankingProductos();
    }
    
    public ArrayList<Pair<String,Pair<Integer,Integer>>> listarRankingProductosFechas(Date fechaMinimo,Date fechaMaximo){
        return accesoDatos.obtenerRankingProductosFechas(fechaMinimo , fechaMaximo);
    }
}
