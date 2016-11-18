/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao.education;

import db.DBConnectionFactoryStorageDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class EducationRecordDAO {

    public boolean runEnrollmentDAO(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            try (Connection conn = myFactory.getConnection()) {
                String update = "SELECT IF(`VALIDATION` = 1, 'TRUE', 'FALSE') AS `RESULT` \n"
                        + " FROM enrollment_det \n"
                        + " WHERE FORMID = ? \n"
                        + " ORDER BY `VALIDATION` \n"
                        + " LIMIT 1;";
                PreparedStatement pstmt2 = conn.prepareStatement(update);
                pstmt2.setInt(1, formID);
                ResultSet rs2 = pstmt2.executeQuery();

                String update2 = "SELECT IF(`VALIDATION` = 1, 'TRUE', 'FALSE') AS `RESULT` \n"
                        + " FROM enrollment \n"
                        + " WHERE FORMID = ? \n"
                        + " ORDER BY `VALIDATION` \n"
                        + " LIMIT 1;";
                PreparedStatement pstmt4 = conn.prepareStatement(update2);
                pstmt4.setInt(1, formID);
                ResultSet rs4 = pstmt4.executeQuery();

                String validation = "true";
                 String one = "true";
                    String two = "true";
                while (rs2.next()) {
                     one = rs2.getString("RESULT");
                }

                while (rs4.next()) {
                     two = rs4.getString("RESULT");
                }
                
                
                if(one.equalsIgnoreCase("false") || two.equalsIgnoreCase("false") ){
                    validation = "false";
                }

                String updateValidation = " UPDATE RECORDS \n"
                        + " SET `validated` = ? \n"
                        + " WHERE FORMID = ?;";
                //UPDATE IF ALL ARE TRUE
                try (PreparedStatement pstmt3 = conn.prepareStatement(updateValidation)) {
                    //UPDATE IF ALL ARE TRUE

                    if (validation.equalsIgnoreCase("true")) {
                        pstmt3.setBoolean(1, true);
                        pstmt3.setInt(2, formID);
                        pstmt3.executeUpdate();
                        return true;
                        //UPDATE IF ALL ARE FALSE
                    } else if (validation.equalsIgnoreCase("false")) {
                        pstmt3.setBoolean(1, false);
                        pstmt3.setInt(2, formID);
                        pstmt3.executeUpdate();
                        return false;
                    }
                    pstmt2.close();
                }

            }
        } catch (SQLException ex) {
            getLogger(EducationRecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

}
