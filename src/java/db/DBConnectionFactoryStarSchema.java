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
public abstract class  DBConnectionFactoryStarSchema {
    String url = "jdbc:mysql://127.0.0.1:3306/starschema";
    String username = "root";
    String password = "";
    

    /**
     *
     * @return connection
     */
    public static DBConnectionFactoryStarSchema getInstance() {
        return new DBConnectionFactoryImplStarSchema();
    }

    /**
     *
     * @return new connection
     */
    public abstract Connection getConnection();
}
