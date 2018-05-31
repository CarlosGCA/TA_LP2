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
    
    public ProductoAdmiBL(){
        accesoDatos = new ProductoAdmiAD();
    }
    
    public int obtenerID(){
        return accesoDatos.id_correlativo();
    }
    
    public ArrayList<Producto> listarProductos(){
        return accesoDatos.listarProductos();
    }
    
    public int registrarProducto(int id,String nombre,float precio){
        return accesoDatos.registarProducto(id, nombre, precio);
    }
    
    public int registrarIngrediente(int cantidad,int idInsumo){
        return accesoDatos.registarIngrediente(cantidad, idInsumo);
    }
    
    public int registarIngredientexProducto(int a,int b){
        return accesoDatos.registrarIngredientesxProducto(a, b);
    }
}
