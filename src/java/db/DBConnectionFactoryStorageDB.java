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
public abstract class  DBConnectionFactoryStorageDB {
    String url = "jdbc:mysql://127.0.0.1:3306/storageDB";
    String username = "root";
    String password = "";

    /**
     *
     * @return
     */
    public static DBConnectionFactoryStorageDB getInstance() {
        return new DBConnectionFactoryImplStorageDB();
    }

    /**
     *
     * @return
     */
    public abstract Connection getConnection();
}
