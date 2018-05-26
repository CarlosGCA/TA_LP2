/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author alulab14
 */
public class Puesto {

    private int idPuesto;
    private String nombPuesto;

    public Puesto(){
    
    }
    public void setNombPuesto(String nombPuesto) {
        this.nombPuesto = nombPuesto;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombPuesto() {
        return nombPuesto;
    }

}
