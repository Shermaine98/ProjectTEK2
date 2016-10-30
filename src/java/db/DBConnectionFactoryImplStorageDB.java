/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package db;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class DBConnectionFactoryImplStorageDB extends DBConnectionFactoryStorageDB {
    
 /**
     *
     * @return connection
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
