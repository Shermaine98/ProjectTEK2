/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author shermainesy
 */
public class DBConnectionFactoryImplStorageDB extends DBConnectionFactoryStorageDB {
    
 /**
     *
     * @return
     */
    @Override
    public Connection getConnection() {
        try {
            forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            getLogger(DBConnectionFactoryImplStorageDB.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
