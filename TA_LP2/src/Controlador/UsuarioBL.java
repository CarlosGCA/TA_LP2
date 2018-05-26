/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoData.UsuarioAD;
import Modelo.CuentaUsuario;
import Modelo.Empleado;

/**
 *
 * @author Kathy Ruiz :)
 */
public class UsuarioBL {
    private UsuarioAD accesoDatos;
    
    public UsuarioBL(){
        accesoDatos = new UsuarioAD();
    }
    
    public void registrarProfesor(Empleado emp){
        accesoDatos.registrar(emp);
    }
    
    public CuentaUsuario buscarUsuarioLogin(String nombre){
        return accesoDatos.buscarUsuarioLogin(nombre);
    }
    
    public void bloquearUsuario(int id){
       accesoDatos.bloquearUsuario(id);
    }
}
