/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
        CallableStatement cs = connection.prepareCall("{call CREAR_BOLETA_O_EXISTENTE(?,?,?,?)}");
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
    
}
