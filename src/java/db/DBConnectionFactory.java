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

//    String url = "jdbc:mysql://112.207.11.186:3306/accounts";
//    String username = "fooUser";
//    String password = "1234";
    /**
     *
     * @return
     */
    public static DBConnectionFactory getInstance() {
        return new DBConnectionFactoryImpl();
    }

    /**
     *
     * @return
     */
    public abstract Connection getConnection();
}
