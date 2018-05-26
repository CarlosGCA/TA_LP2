/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alfredo
 */
public class TurnoAD {

    private Connection con;

    public TurnoAD() {
        this.con = null;
    }

    private void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g7", "inf282g7", "0mvK88");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Can´t connect to the data base ");
        }
    }

    public ArrayList<String> devolverTurnos() {
        ArrayList<String> listaTurnos = new ArrayList<>();
        if (this.con == null) {
            getConnection();
        }

        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery("select nombre from `inf282g7`.`Turno`");

            while (rs.next()) {

                listaTurnos.add(rs.getString("nombre"));
            }

            this.con.close();
        } catch (SQLException ex) {

        }
        return listaTurnos;
    }

}
