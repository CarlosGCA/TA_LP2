package Controlador;

/**
 *
 * @author Sebastian
 */

import AccesoData.InsumoAD;
import Modelo.*;
import java.util.ArrayList;

public class InsumoBL {
    private InsumoAD accesoDatos;
    
    public InsumoBL(){
        accesoDatos= new InsumoAD();
    }
    
    public int obtenerID(){
        return accesoDatos.id_correlativo();
    }
    
    public ArrayList<String> listarMedidas(){
        return accesoDatos.listarMedidas();
    }
    
    public ArrayList<Insumo> listarInsumo(){
        return accesoDatos.listarInsumos();
    }
    
    public int registrarInsumo(int id,String nombre,int medida,String descripcion){
        return accesoDatos.registarInsumo(id, nombre, medida,descripcion);
    }
    
    public int eliminarInsumo(int id){
        return accesoDatos.eliminarInsumo(id);
    }
    
    public int modificarInsumo(int id,String nombre,int medida,String descripcion){
        return accesoDatos.modificarInsumo(id, nombre, medida, descripcion);
    }
}
