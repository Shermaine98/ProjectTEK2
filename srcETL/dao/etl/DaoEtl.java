/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.etl;

import db.DBConnectionFactoryStarSchema;
import etl.Etl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Shermaine
 */
public class DaoEtl {

    public boolean runETLDAO(int year) {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = " INSERT INTO dim_cencusyear "
                        + " (cencusYear) "
                        + " VALUES (?); ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, year);
                rows = pstmt.executeUpdate();
                System.out.println("NUMROWS: "+rows);
            }
            System.out.println("ETL?");
            if (rows > 0) {
                Etl etl = new Etl();
                System.out.println("GOING TO ETL    ");
                return etl.run();
            }
        } catch (SQLException ex) {
            getLogger(DaoEtl.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
