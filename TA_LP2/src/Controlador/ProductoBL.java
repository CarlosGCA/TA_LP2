/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import AccesoData.ProductoAD;
import Modelo.Producto;
import java.util.ArrayList;
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
}
