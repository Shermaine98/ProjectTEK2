/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package db;

import java.sql.Connection;
/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */

public abstract class DBConnectionFactory {

    String url = "jdbc:mysql://127.0.0.1:3306/accounts";
    String username = "root";
    String password = "";
    /**
     * 
     * @return instance
     */
    public static DBConnectionFactory getInstance() {
        return new DBConnectionFactoryImpl();
    }

    /**
     *
     * @return connection
     */
    public abstract Connection getConnection();
}
