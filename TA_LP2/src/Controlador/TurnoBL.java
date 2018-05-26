/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import AccesoData.TurnoAD;
import java.util.ArrayList;
/**
 *
 * @author alfredo
 */
public class TurnoBL {
    private TurnoAD turnoAccesoAD;
    
    public TurnoBL(){
        turnoAccesoAD = new TurnoAD();
    }
    
    public ArrayList<String> listaTurnos(){
        return turnoAccesoAD.devolverTurnos();
    }
}
