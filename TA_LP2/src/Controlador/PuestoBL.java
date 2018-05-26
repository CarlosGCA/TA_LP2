/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import AccesoData.PuestoAD;
import java.util.ArrayList;
/**
 *
 * @author alfredo
 */
public class PuestoBL {
    private PuestoAD accPuestoAD ;
    public PuestoBL() {
        this.accPuestoAD = new PuestoAD();
    }
    
    public ArrayList<String> listaPuesto(){
        return this.accPuestoAD.devolverPuestos();
    }
    
}
