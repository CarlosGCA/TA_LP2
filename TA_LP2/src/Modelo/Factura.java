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
public class Factura extends DocumentoPago {
    private int ruc;
    private String razonSocial;
    private float igv_Factura;
    
    public Factura(String idDoc,float total,int idPedido,Cliente cliente,boolean registrada, int ruc, String rz, float igv){
        super(idDoc, total, idPedido, cliente, registrada);
        setRuc(ruc);
        setRazonSocial(rz);
        setIgv_Factura(igv);
    }
    
    public Factura(){
    }
    
    /**
     * @return the ruc
     */
     public int getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the igv_Factura
     */
    public float getIgv_Factura() {
        return igv_Factura;
    }

    /**
     * @param igv_Factura the igv_Factura to set
     */
    public void setIgv_Factura(float igv_Factura) {
        this.igv_Factura = igv_Factura;
    }
    
}
