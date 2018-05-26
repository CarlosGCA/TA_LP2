/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoData.ClientesAD;
import Modelo.Empresa;
import Modelo.Natural;
import java.util.ArrayList;

/**
 *
 * @author Kathy Ruiz :)
 */
public class ClientesBL {
     private ClientesAD accesoDatos;
    
    public ClientesBL(){
        accesoDatos = new ClientesAD();
    }
    
    public void registrarNatural(Natural nat){
        accesoDatos.registrarNat(nat);
    }
    public void registrarEmpresa(Empresa emp){
        accesoDatos.registrarEmp(emp);
    }
    
    public ArrayList<Natural> listarNatural(){
        return accesoDatos.listarNaturales();
    }
    
    public ArrayList<Empresa> listarEmpresa(){
        return accesoDatos.listarEmpresa();
    }
}
