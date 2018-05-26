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
public class Boleta extends DocumentoPago{
    private int dni;
    private String nombre;
    private float igv_Boleta;
    public Boleta(String idDoc,float total,int idPedido,Cliente cliente,boolean registrada, int dni, String nombre, float igv){
        super(idDoc, total, idPedido, cliente, registrada);
        setDni(dni);
        setNombre(nombre);
        setIgv_Boleta(igv);
    }
    
    public Boleta(){
    }
    /**
     * @return the dni
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the igv_Boleta
     */
    public float getIgv_Boleta() {
        return igv_Boleta;
    }

    /**
     * @param igv_Boleta the igv_Boleta to set
     */
    public void setIgv_Boleta(float igv_Boleta) {
        this.igv_Boleta = igv_Boleta;
    }

}
