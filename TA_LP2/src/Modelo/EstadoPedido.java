/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
//El estadonulo es solo para que los indices del enum coincidan exacatamente con los ids de estadosPedido en la Base de datos
public enum EstadoPedido {estadonulo,Aprobado,Pendiente,Listo,Cancelado
,EnProceso{
    @Override
    public String toString(){
        return "En Proceso";
    }
}
,Finalizado ;


    public static EstadoPedido getValue(String values){
     if (values.equals("En Proceso")){
         return EnProceso;
     }
        return EstadoPedido.valueOf(values);
    }
}