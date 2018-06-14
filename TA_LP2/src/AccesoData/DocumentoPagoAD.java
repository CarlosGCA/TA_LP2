/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import Modelo.Boleta;
import Modelo.DocumentoPago;
import Modelo.Cliente;
import Modelo.Empresa;
import Modelo.Factura;
import Modelo.Natural;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Victor
 */
public class DocumentoPagoAD {
    private Connection connection;
    public DocumentoPagoAD(){
        connection = null;
    }
    private void openCon() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
    }
    private void closeCon() throws Exception{
        if(connection != null && !connection.isClosed())
            connection.close();
    }
    
    public int generarBoleta(float total, int idPedido, int igv) throws Exception{
        openCon();
        CallableStatement cs = connection.prepareCall("{call CREAR_BOLETA_O_MODIFICAR(?,?,?,?)}");
        cs.setFloat("_total", total);
        cs.setInt("_idPedidoProductos", idPedido);
        cs.setInt("_igv", igv);
        cs.registerOutParameter("_idBoleta", java.sql.Types.INTEGER);
        
        cs.execute();
        int idBoleta = cs.getInt("_idBoleta");
        closeCon();
        return idBoleta;
    }
    public int generarFactura(float total, int idPedido, int igv) throws Exception{
        openCon();
        CallableStatement cs = connection.prepareCall("{call CREAR_FACTURA_O_MODIFICAR(?,?,?,?)}");
        cs.setFloat("_total", total);
        cs.setInt("_idPedidoProductos", idPedido);
        cs.setInt("_igv", igv);
        cs.registerOutParameter("_idFactura", java.sql.Types.INTEGER);
        
        cs.execute();
        int idFactura = cs.getInt("_idFactura");
        closeCon();
        return idFactura;
    }
    
    
    public void exportBoletaPDF(int idPedido, String nombArch) throws Exception{
        openCon();
        JasperReport reporte =  (JasperReport) JRLoader.loadObjectFromFile("src/Reportes/Boleta.jasper");
        HashMap parametros = new HashMap();
        parametros.put("_idPedidoProductos",idPedido);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,connection);
        JasperExportManager.exportReportToPdfFile(jasperPrint, nombArch);
        closeCon();
    }
    public void exportFacturaPDF(int idPedido, String nombArch) throws Exception{
        openCon();
        JasperReport reporte =  (JasperReport) JRLoader.loadObjectFromFile("src/Reportes/Factura.jasper");
        HashMap parametros = new HashMap();
        parametros.put("_idPedidoProductos",idPedido);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,connection);
        JasperExportManager.exportReportToPdfFile(jasperPrint, nombArch);
        closeCon();
    }
    
    public void exportOrdenPedidoPDF(int idPedido, String nombArch) throws Exception{
        openCon();
        JasperReport reporte =  (JasperReport) JRLoader.loadObjectFromFile("src/Reportes/OrdenPedido.jasper");
        HashMap parametros = new HashMap();
        parametros.put("_idPedidoProductos",idPedido);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,connection);
        JasperExportManager.exportReportToPdfFile(jasperPrint, nombArch);
        closeCon();
    }
    
    public JasperPrint exportBoleta(int idPedido, String nombArch) throws Exception{
        openCon();
        JasperReport reporte =  (JasperReport) JRLoader.loadObjectFromFile("src/Reportes/Boleta.jasper");
        HashMap parametros = new HashMap();
        parametros.put("_idPedidoProductos",idPedido);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,connection);
        //JasperExportManager.exportReportToPdfFile(jasperPrint, nombArch);
        closeCon();
        return jasperPrint;
    }
    public JasperPrint exportFactura(int idPedido, String nombArch) throws Exception{
        openCon();
        JasperReport reporte =  (JasperReport) JRLoader.loadObjectFromFile("src/Reportes/Factura.jasper");
        HashMap parametros = new HashMap();
        parametros.put("_idPedidoProductos",idPedido);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,connection);
        //JasperExportManager.exportReportToPdfFile(jasperPrint, nombArch);
        closeCon();
        return jasperPrint;
    }
    
    public JasperPrint exportOrdenPedido(int idPedido, String nombArch) throws Exception{
        openCon();
        JasperReport reporte =  (JasperReport) JRLoader.loadObjectFromFile("src/Reportes/OrdenPedido.jasper");
        HashMap parametros = new HashMap();
        parametros.put("_idPedidoProductos",idPedido);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,connection);
//        JasperExportManager.exportReportToPdfFile(jasperPrint, nombArch);
        closeCon();
        return jasperPrint;
    }
    
    public ArrayList<Object> listarDocumentosPagoYExtra() throws Exception{
        //Devuelve una tupla: [0]=DocPago [1]=FechaRegistro [2]=FechaEntrega [3]=EstadoPedido
        
        openCon();
        ArrayList<Object> documentosConExtra = new ArrayList<>();
        CallableStatement cs = connection.prepareCall("{call LISTAR_DOCUMENTOS_PAGO()}");
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            String tipoCliente = rs.getString("tipo");
            DocumentoPago documento;
//            Cliente cliente;
            if(tipoCliente.equals("Natural")){
                Boleta boleta = new Boleta();
                
//                cliente = new Natural();
                boleta.setDni(rs.getInt("DOC_ID"));
                boleta.setIgv_Boleta(rs.getInt("IGV"));
                boleta.setNombre(rs.getString("Cliente"));
                
                Natural cliente = new Natural();
                cliente.setId_cliente(rs.getInt("Cliente_idCliente"));
                boleta.setcliente(cliente);
                
                documento = boleta;
            }else{
                Factura factura = new Factura();
//                cliente = new Empresa();
                factura.setRuc(rs.getInt("DOC_ID"));
                factura.setIgv_Factura(rs.getInt("IGV"));
                factura.setRazonSocial(rs.getString("Cliente"));
                
                Empresa cliente = new Empresa();
                cliente.setId_cliente(rs.getInt("Cliente_idCliente"));
                factura.setcliente(cliente);
                documento = factura;
            }
            
            
            documento.setidDoc(Integer.toString(rs.getInt("idDocumentoPago")));
            documento.settotal(rs.getFloat("Total"));
            documento.setidPedido(rs.getInt("idPedidoProductos"));
            
            documento.setregistrada(rs.getInt("Registrada")!=0);
//            documento.setregistrada(rs.getInt("Registrada")); //incompatible types
            
            
            Date fechaRegistro = rs.getDate("FechaRegistro");
            Date fechaEntrega = rs.getDate("FechaEntrega");
            int estadoPedido = rs.getInt("EstadoPedido_idEstadoPedido");
            
            Object[] tuple = new Object[4];
            tuple[0] = documento;
            tuple[1] = fechaRegistro;
            tuple[2] = fechaEntrega;
            tuple[3] = estadoPedido;
            
            documentosConExtra.add(tuple);
        }
        
        closeCon();
        return documentosConExtra;
    }
}
