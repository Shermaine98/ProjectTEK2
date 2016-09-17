package db;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class DBConnectionFactoryImplStarSchema extends DBConnectionFactoryStarSchema {

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
            getLogger(DBConnectionFactoryImpl.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

}
