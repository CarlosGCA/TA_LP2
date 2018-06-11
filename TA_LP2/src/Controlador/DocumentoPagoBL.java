/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoData.DocumentoPagoAD;

/**
 *
 * @author Victor
 */
public class DocumentoPagoBL {
    private DocumentoPagoAD documentoPagoAD;
    public DocumentoPagoBL(){
        documentoPagoAD = new DocumentoPagoAD();
    }
    
    public void exportarBoletaPDF(int idPedido, String nombArch){
        try{
            documentoPagoAD.exportBoletaPDF(idPedido, nombArch);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
