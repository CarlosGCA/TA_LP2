/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoData.ProductoAdmiAD;
import Modelo.*;
import java.util.ArrayList;
/**
 *
 * @author Sebastian
 */
public class ProductoAdmiBL {
    private ProductoAdmiAD accesoDatos;
    
    private ProductoAdmiBL(){
        accesoDatos = new ProductoAdmiAD();
    }
    
    public int obtenerID(){
        return accesoDatos.id_correlativo();
    }
    
    public ArrayList<Producto> listarProductos(){
        return accesoDatos.listarProductos();
    }
}
