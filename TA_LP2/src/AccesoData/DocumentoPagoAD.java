/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

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
    private Connection con;
    public DocumentoPagoAD(){
        con = null;
    }
    private void openCon() throws Exception{
        con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
    }
    private void closeCon() throws Exception{
        if(con != null && !con.isClosed())
            con.close();
    }
    
    public void exportBoletaPDF(int idPedido, String ruta) throws Exception{
        openCon();
        JasperReport reporte =  (JasperReport) JRLoader.loadObjectFromFile("src/Reportes/Blank_Letter.jasper");Class.forName("com.mysql.jdbc.Driver");
            HashMap parametros = new HashMap();
            parametros.put("_idPedidoProductos",idPedido);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,con);
            JasperExportManager.exportReportToPdfFile(jasperPrint, ruta + "reporte4.pdf");
        closeCon();
    }
    public void exportFacturaPDF(int idPedido, String ruta) throws Exception{
        openCon();
            
        closeCon();
    }
    
}
