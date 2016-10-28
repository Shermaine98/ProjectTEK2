package dao.accounts;

import dao.accounts.*;
import db.DBConnectionFactory;
import static db.DBConnectionFactory.getInstance;
import model.accounts.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Geraldine Atayan
 */
public class Accounts {

    /**
     * Register User
     */
    public boolean registerOthers(User user) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "insert into users "
                        + "(userID, email, username, password, division, firstName, lastName, gender,"
                        + "birthdate, approved, reason)"
                        + "values (?,?,?,password(?),?,?,?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, user.getUserID());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, user.getPassword());
                pstmt.setString(5, "Others");
                pstmt.setString(6, user.getFirstName());
                pstmt.setString(7, user.getLastName());
                pstmt.setString(8, user.getGender());
                pstmt.setDate(9, user.getBirthdate());
                pstmt.setBoolean(10, false);
                pstmt.setString(11, user.getReason());
                rows = pstmt.executeUpdate();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean registerMembers(User user) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "insert into users "
                        + "(userID, email, username, password, firstName, lastName, gender,"
                        + "birthdate, approved)"
                        + "values (?,?,?,password(?),?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, user.getUserID());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, user.getPassword());
                pstmt.setString(5, user.getFirstName());
                pstmt.setString(6, user.getLastName());
                pstmt.setString(7, user.getGender());
                pstmt.setDate(8, user.getBirthdate());
                pstmt.setBoolean(9, false);
                rows = pstmt.executeUpdate();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Login
     *
     * @param User
     * @return
     */
    public boolean authenticate(String username, String pass) {
        boolean valid = false;
        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                String query = "select * from users where username = ? and password = password(?) and approved = 1";
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1, username);
                pstmt.setString(2, pass);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    valid = true;
                }
            }
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return valid;
    }

    public boolean ifUserExists(String username, String password) {
        int rows = -1;
        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("select * from users where "
                        + "username = ? and password = password(?) and approved = 1");
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                System.out.print(password);
                ResultSet rs = pstmt.executeQuery();
                while (rs.isFirst()) {

                }

                pstmt.close();
                rs.close();
            }
            return true;

        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public User getUser(String username, String password) throws ParseException {
        User User = new User();

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("select * from users where "
                        + "username = ? and password = password(?) and approved = 1");
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                System.out.print(password);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    User.setUserID(rs.getInt("userID"));
                    User.setEmail(rs.getString("email"));
                    User.setUsername(rs.getString("username"));
                    User.setDivision(rs.getString("division"));
                    User.setFirstName(rs.getString("firstName"));
                    User.setLastName(rs.getString("lastName"));
                    User.setGender(rs.getString("gender"));
                    User.setBirthdate(rs.getString("birthdate"));
                    User.setPosition(rs.getString("position"));

                }

                pstmt.close();
                rs.close();
            }

            return User;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Get last Employee number
     *
     * @return
     */
    public Integer getLastUserID() throws SQLException {
        DBConnectionFactory myFactory = getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 100000000;
            String query = "SELECT MAX(userID) as`MAXID` from users;";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int add = rs.getInt("MAXID");
                    System.out.print(add);
                    i = add + 1;
                    System.out.print(i);
                }
            }
            conn.close();
            rs.close();
        }
        return i;
    }

    public ArrayList<User> getUserForApprovalOthers() throws SQLException, ParseException {
        try {
            DBConnectionFactory myFactory = getInstance();
            Connection conn = myFactory.getConnection();
            ArrayList<User> usersforApproval = new ArrayList<User>();
            PreparedStatement pstmt = conn.prepareStatement("select * from users\n"
                    + "where approved = 0 and division = 'Others';");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User temp = new User();
                temp.setUserID(rs.getInt("userID"));
                temp.setEmail(rs.getString("email"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setDivision(rs.getString("division"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setGender(rs.getString("gender"));
                temp.setBirthdate(rs.getString("birthdate"));
                temp.setPosition(rs.getString("position"));
                temp.setReason("reason");
                usersforApproval.add(temp);
            }

            pstmt.close();
            rs.close();
            return usersforApproval;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getUserForApprovalMember() throws SQLException, ParseException {

        try {
            DBConnectionFactory myFactory = getInstance();
            Connection conn = myFactory.getConnection();
            ArrayList<User> usersforApproval = new ArrayList<>();
            PreparedStatement pstmt = conn.prepareStatement("select * from users\n"
                    + "where approved = 0 and division = '' or division IS NULL;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User temp = new User();
                temp.setUserID(rs.getInt("userID"));
                temp.setEmail(rs.getString("email"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setDivision(rs.getString("division"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setGender(rs.getString("gender"));
                temp.setBirthdate(rs.getString("birthdate"));
                temp.setPosition(rs.getString("position"));
                temp.setReason("reason");
                usersforApproval.add(temp);
            }

            pstmt.close();
            rs.close();
            return usersforApproval;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getApprovedInternalUsers() throws SQLException, ParseException {
        try {
            DBConnectionFactory myFactory = getInstance();
            Connection conn = myFactory.getConnection();
            ArrayList<User> users = new ArrayList<User>();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM USERS\n"
                    + "WHERE APPROVED = 1 AND DIVISION != 'OTHERS';");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User temp = new User();
                temp.setUserID(rs.getInt("userID"));
                temp.setEmail(rs.getString("email"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setDivision(rs.getString("division"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setGender(rs.getString("gender"));
                temp.setBirthdate(rs.getString("birthdate"));
                temp.setPosition(rs.getString("position"));
                temp.setReason("reason");
                users.add(temp);
            }

            pstmt.close();
            rs.close();
            return users;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean approveUser(int userID, String division, String position, Date employment) throws SQLException {
        try {
            DBConnectionFactory myFactory = getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE USERS SET APPROVED = ?, `DIVISION` = ?, `POSITION` = ?, `EMPLOYMENTDATE` = ? WHERE `USERID` = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setBoolean(1, true);
                pstmt.setString(2, division);
                pstmt.setString(3, position);
                pstmt.setDate(4, employment);
                pstmt.setInt(5, userID);
                rows = pstmt.executeUpdate();
            }
            pstmt.close();
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean approveUserExternal(int userID) throws SQLException {
        try {
            DBConnectionFactory myFactory = getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE USERS SET APPROVED= ?"
                        + " WHERE USERID = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setBoolean(1, true);
                pstmt.setInt(2, userID);
                rows = pstmt.executeUpdate();
            }
            pstmt.close();
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<User> getApprovedExternalUsers() throws SQLException, ParseException {
        try {
            DBConnectionFactory myFactory = getInstance();
            Connection conn = myFactory.getConnection();
            ArrayList<User> users = new ArrayList<User>();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM USERS\n"
                    + "WHERE APPROVED = 1 AND DIVISION = 'OTHERS';");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User temp = new User();
                temp.setUserID(rs.getInt("userID"));
                temp.setEmail(rs.getString("email"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setDivision(rs.getString("division"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setGender(rs.getString("gender"));
                temp.setBirthdate(rs.getString("birthdate"));
                temp.setPosition(rs.getString("position"));
                temp.setReason("reason");
                users.add(temp);
            }

            pstmt.close();
            rs.close();
            return users;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean diassapproveUser(int userID) throws SQLException {
        try {
            DBConnectionFactory myFactory = getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM users WHERE userID = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt.setInt(1, userID);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    pstmt.close();
                    conn.close();
                    return true;
                }

            }
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean changePassword(String username, String password) {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String changePass = "UPDATE USERS \n"
                        + "SET PASSWORD= password(?)\n"
                        + "WHERE USERNAME=?;";
                pstmt = conn.prepareStatement(changePass);
                pstmt.setString(1, password);
                pstmt.setString(2, username);
                int isChanged = pstmt.executeUpdate();
                if (isChanged > 0) {
                    rows = true;
                }
            }
            pstmt.close();
            return rows;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean userNameAvailability(String username) {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM USERS WHERE username = ?");
                pstmt.setString(1, username);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    rows = true;
                } else {
                    rows = false;
                }
                rs.close();
                pstmt.close();
            }

            return rows;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean emailAvailability(String email) {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT * FROM USERS WHERE EMAIL = ?");
                pstmt.setString(1, email);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    rows = true;
                } else {
                    rows = false;
                }
                rs.close();
                pstmt.close();
            }

            return rows;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateExternalUser(String firstName, String lastName, String email, String username){
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String update = "UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ? WHERE EMAIL = ?;";
                pstmt = conn.prepareStatement(update);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, username);
                pstmt.setString(4, email);
                int isChanged = pstmt.executeUpdate();
                if (isChanged > 0) {
                    rows = true;
                }
            }
            pstmt.close();
            return rows;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateInternalUser(String firstName, String lastName, String email, 
            String username, String division, String position){
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String update = "UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, "
                        + "USERNAME = ?, DIVISION = ?, POSITION = ? WHERE EMAIL = ?;";
                pstmt = conn.prepareStatement(update);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, username);
                pstmt.setString(4, division);
                pstmt.setString(5, position);
                pstmt.setString(6, email);
                int isChanged = pstmt.executeUpdate();
                if (isChanged > 0) {
                    rows = true;
                }
            }
            pstmt.close();
            return rows;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<User> getSignUpCounts() throws ParseException{
        ArrayList<User> user = new ArrayList<User>();
        try {
            DBConnectionFactory myFactory = getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String update = "Select COUNT(firstName) as 'count', dateCreated FROM accounts.users group by dateCreated;";
                pstmt = conn.prepareStatement(update);
                
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    User temp = new User();
                    temp.setCount(rs.getInt("COUNT"));
                    System.out.println(rs.getInt("COUNT"));
                    temp.setDateCreated(rs.getString("dateCreated"));
                    user.add(temp);
                }
            }
            pstmt.close();
           
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return user;
    }
}
