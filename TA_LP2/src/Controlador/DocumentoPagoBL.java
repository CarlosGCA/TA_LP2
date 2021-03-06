/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoData.DocumentoPagoAD;
import Modelo.Natural;
import Modelo.PedidoProducto;
import com.mysql.jdbc.NotImplemented;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Victor
 */
public class DocumentoPagoBL {
    private DocumentoPagoAD documentoPagoAD;
    public DocumentoPagoBL(){
        documentoPagoAD = new DocumentoPagoAD();
    }
    
    
    public int generarBoleta(PedidoProducto pedido) throws Exception{
        return documentoPagoAD.generarBoleta(pedido.getTotalPagar(), pedido.getidPedido(), 18);
    }
    public int generarFactura(PedidoProducto pedido) throws Exception{
        return documentoPagoAD.generarFactura(pedido.getTotalPagar(), pedido.getidPedido(), 18);
    }
    
    
    public int generarDocPago(PedidoProducto pedido) throws Exception{
        if(pedido.getcliente() instanceof Natural){
            return generarBoleta(pedido);
        }
        else{
            return generarFactura(pedido);
        }
    }
    
    public void exportarBoletaPDF (int idPedido, String nombArch)throws Exception{
        documentoPagoAD.exportBoletaPDF(idPedido, nombArch);
    }
    public void exportarFacturaPDF (int idPedido, String nombArch)throws Exception{
        documentoPagoAD.exportFacturaPDF(idPedido, nombArch);
    }
    
    public void exportarDocPagoPDF(PedidoProducto pedido, String nombArch) throws Exception{
        if(pedido.getcliente() instanceof Natural){
            exportarBoletaPDF(pedido.getidPedido(), nombArch);
        }
        else{
            exportarFacturaPDF(pedido.getidPedido(), nombArch);
        }
    }
    
    public void exportarOrdenPedidoPDF (int idPedido, String nombArch)throws Exception{
        documentoPagoAD.exportOrdenPedidoPDF(idPedido, nombArch);
    }
    public JasperPrint exportarBoleta (int idPedido, String nombArch)throws Exception{
        return documentoPagoAD.exportBoleta(idPedido, nombArch);
    }
    public JasperPrint exportarFactura (int idPedido, String nombArch)throws Exception{
        return documentoPagoAD.exportFactura(idPedido, nombArch);
    }
    
    
    public ArrayList<Object> listarDocumentosPagoYExtra() throws Exception{
        return documentoPagoAD.listarDocumentosPagoYExtra();
    }
    
}
