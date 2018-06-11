/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LENOVO
 */
public class LineaPedidoProducto {

    private Producto producto;
    private float cantidad;
    private float descuento;
    private Boolean habilitado;
    
    /**
     * @return the habilitado
     */
    public Boolean getHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }
    /**
     * @return the producto
     */
    
    public LineaPedidoProducto(){
    }
    
    public LineaPedidoProducto(Producto prod, float cant, float desc){
        setProducto(prod);
        setCantidad(cant);
        setDescuento(desc);
        setHabilitado(true);
    }
    
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the cantidad
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the descuento
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    } 
    
    public float getSubtotal(){
        return getCantidad()*getProducto().getprecio() - getDescuento();
    }
}
