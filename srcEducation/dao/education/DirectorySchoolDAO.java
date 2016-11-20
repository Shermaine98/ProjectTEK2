/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao.education;

import dao.RecordDAO;
import db.DBConnectionFactoryStorageDB;
import model.education.DirectorySchool;
import model.education.ElemClassrooms;
import model.education.ElemTeachers;
import model.education.Seats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class DirectorySchoolDAO {

    public ArrayList<DirectorySchool> ViewDirectorySchoolRecentPrivate() throws ParseException, SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<DirectorySchool> ArrDirectorySchool = new ArrayList<>();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select t.*\n"
                    + "from directory_school t\n"
                    + "inner join (\n"
                    + "    select schoolName, max(censusyear) as MaxDate\n"
                    + "    from directory_school\n"
                    + "    group by schoolName\n"
                    + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate and t.`active` = 1 \n"
                    + "WHERE t.classification = \"Private\";");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DirectorySchool temp = new DirectorySchool();
                temp.setFormID(rs.getInt("formID"));
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setSchoolName(rs.getString("schoolName"));
                temp.setSchoolID(rs.getInt("schoolID"));
                temp.setClassification(rs.getString("classification"));

                ArrayList<ElemClassrooms> arrelemCtemp = new ArrayList<ElemClassrooms>();
                ArrayList<Seats> arrseatstemp = new ArrayList<Seats>();
                ArrayList<ElemTeachers> arrelemTeacherstemp = new ArrayList<ElemTeachers>();

                PreparedStatement pstmt2 = conn.prepareStatement("select t.*\n"
                        + "from elem_classrooms t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from elem_classrooms\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt2.setString(1, temp.getSchoolName());
                ResultSet rs2 = pstmt2.executeQuery();

                while (rs2.next()) {
                    ElemClassrooms etemp = new ElemClassrooms();
                    etemp.setGradeLevel(rs2.getString("gradeLevel"));
                    etemp.setClassroomCount(rs2.getInt("classroomCount"));
                    arrelemCtemp.add(etemp);
                }

                PreparedStatement pstmt3 = conn.prepareStatement("select t.*\n"
                        + "from elem_teachers t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from elem_teachers\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt3.setString(1, temp.getSchoolName());
                ResultSet rs3 = pstmt3.executeQuery();

                while (rs3.next()) {
                    ElemTeachers eTecherTemp = new ElemTeachers();
                    eTecherTemp.setGradeLevel(rs3.getString("gradeLevel"));
                    eTecherTemp.setFemaleCount(rs3.getInt("femaleCount"));
                    eTecherTemp.setMaleCount(rs3.getInt("maleCount"));
                    arrelemTeacherstemp.add(eTecherTemp);
                }

                PreparedStatement pstmt4 = conn.prepareStatement("select t.*\n"
                        + "from seats t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from seats\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt4.setString(1, temp.getSchoolName());
                ResultSet rs4 = pstmt4.executeQuery();

                while (rs4.next()) {
                    Seats seats = new Seats();
                    seats.setGradeLevel(rs4.getString("gradeLevel"));
                    seats.setSeatCount(rs4.getInt("seatsCount"));
                    arrseatstemp.add(seats);
                }

                temp.setElemClassrooms(arrelemCtemp);
                temp.setSeats(arrseatstemp);
                temp.setTeacher(arrelemTeacherstemp);

                ArrDirectorySchool.add(temp);
                pstmt2.close();
                pstmt3.close();
                pstmt4.close();
            }
            pstmt.close();
            conn.close();

            return ArrDirectorySchool;
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<DirectorySchool> ViewDirectorySchoolRecentPublic() throws ParseException, SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<DirectorySchool> ArrDirectorySchool = new ArrayList<>();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select t.*\n"
                    + "from directory_school t\n"
                    + "inner join (\n"
                    + "    select schoolName, max(censusyear) as MaxDate\n"
                    + "    from directory_school\n"
                    + "    group by schoolName\n"
                    + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate and t.`active` = 1\n"
                    + "WHERE t.classification = 'PUBLIC';");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DirectorySchool temp = new DirectorySchool();
                temp.setFormID(rs.getInt("formID"));
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setSchoolName(rs.getString("schoolName"));
                temp.setSchoolID(rs.getInt("schoolID"));
                temp.setClassification(rs.getString("classification"));

                ArrayList<ElemClassrooms> arrelemCtemp = new ArrayList<ElemClassrooms>();
                ArrayList<Seats> arrseatstemp = new ArrayList<Seats>();
                ArrayList<ElemTeachers> arrelemTeacherstemp = new ArrayList<ElemTeachers>();

                PreparedStatement pstmt2 = conn.prepareStatement("select t.*\n"
                        + "from elem_classrooms t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from elem_classrooms\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt2.setString(1, temp.getSchoolName());
                ResultSet rs2 = pstmt2.executeQuery();

                while (rs2.next()) {
                    ElemClassrooms etemp = new ElemClassrooms();
                    etemp.setGradeLevel(rs2.getString("gradeLevel"));
                    etemp.setClassroomCount(rs2.getInt("classroomCount"));
                    arrelemCtemp.add(etemp);
                }

                PreparedStatement pstmt3 = conn.prepareStatement("select t.*\n"
                        + "from elem_teachers t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from elem_teachers\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt3.setString(1, temp.getSchoolName());
                ResultSet rs3 = pstmt3.executeQuery();

                while (rs3.next()) {
                    ElemTeachers eTecherTemp = new ElemTeachers();
                    eTecherTemp.setGradeLevel(rs3.getString("gradeLevel"));
                    eTecherTemp.setFemaleCount(rs3.getInt("femaleCount"));
                    eTecherTemp.setMaleCount(rs3.getInt("maleCount"));
                    arrelemTeacherstemp.add(eTecherTemp);
                }

                PreparedStatement pstmt4 = conn.prepareStatement("select t.*\n"
                        + "from seats t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from seats\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt4.setString(1, temp.getSchoolName());
                ResultSet rs4 = pstmt4.executeQuery();

                while (rs4.next()) {
                    Seats seats = new Seats();
                    seats.setGradeLevel(rs4.getString("gradeLevel"));
                    seats.setSeatCount(rs4.getInt("seatsCount"));
                    arrseatstemp.add(seats);
                }

                temp.setElemClassrooms(arrelemCtemp);
                temp.setSeats(arrseatstemp);
                temp.setTeacher(arrelemTeacherstemp);

                ArrDirectorySchool.add(temp);
                pstmt2.close();
                pstmt3.close();
                pstmt4.close();
            }
            pstmt.close();
            conn.close();
            return ArrDirectorySchool;
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<DirectorySchool> ViewDirectorySchoolRecentByName(String classification, String Name) throws ParseException, SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<DirectorySchool> ArrDirectorySchool = new ArrayList<>();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select t.*\n"
                    + "from directory_school t\n"
                    + "inner join (\n"
                    + "    select schoolName, max(censusyear) as MaxDate\n"
                    + "    from directory_school\n"
                    + "    group by schoolName\n"
                    + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate and t.`active` = 1 \n"
                    + "WHERE t.classification = ? AND t.schoolName = ?;");
            pstmt.setString(1, classification);
            pstmt.setString(2, Name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DirectorySchool temp = new DirectorySchool();
                temp.setFormID(rs.getInt("formID"));
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setSchoolName(rs.getString("schoolName"));
                temp.setSchoolID(rs.getInt("schoolID"));
                temp.setClassification(rs.getString("classification"));

                ArrayList<ElemClassrooms> arrelemCtemp = new ArrayList<ElemClassrooms>();
                ArrayList<Seats> arrseatstemp = new ArrayList<Seats>();
                ArrayList<ElemTeachers> arrelemTeacherstemp = new ArrayList<ElemTeachers>();

                PreparedStatement pstmt2 = conn.prepareStatement("select t.*\n"
                        + "from elem_classrooms t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from elem_classrooms\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt2.setString(1, temp.getSchoolName());
                ResultSet rs2 = pstmt2.executeQuery();

                while (rs2.next()) {
                    ElemClassrooms etemp = new ElemClassrooms();
                    etemp.setGradeLevel(rs2.getString("gradeLevel"));
                    etemp.setClassroomCount(rs2.getInt("classroomCount"));
                    arrelemCtemp.add(etemp);
                }

                PreparedStatement pstmt3 = conn.prepareStatement("select t.*\n"
                        + "from elem_teachers t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from elem_teachers\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt3.setString(1, temp.getSchoolName());
                ResultSet rs3 = pstmt3.executeQuery();

                while (rs3.next()) {
                    ElemTeachers eTecherTemp = new ElemTeachers();
                    eTecherTemp.setGradeLevel(rs3.getString("gradeLevel"));
                    eTecherTemp.setFemaleCount(rs3.getInt("femaleCount"));
                    eTecherTemp.setMaleCount(rs3.getInt("maleCount"));
                    arrelemTeacherstemp.add(eTecherTemp);
                }

                PreparedStatement pstmt4 = conn.prepareStatement("select t.*\n"
                        + "from seats t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from seats\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate AND t.schoolName = ?;");
                pstmt4.setString(1, temp.getSchoolName());
                ResultSet rs4 = pstmt4.executeQuery();

                while (rs4.next()) {
                    Seats seats = new Seats();
                    seats.setGradeLevel(rs4.getString("gradeLevel"));
                    seats.setSeatCount(rs4.getInt("seatsCount"));
                    arrseatstemp.add(seats);
                }

                temp.setElemClassrooms(arrelemCtemp);
                temp.setSeats(arrseatstemp);
                temp.setTeacher(arrelemTeacherstemp);

                ArrDirectorySchool.add(temp);
                pstmt2.close();
                pstmt3.close();
                pstmt4.close();
            }
            pstmt.close();
            conn.close();

            return ArrDirectorySchool;
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> SearchSchoolNamePrivate(String name) throws ParseException, SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<String> schoolNames = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("select t.schoolName\n"
                        + "from directory_school t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from directory_school\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate\n"
                        + "WHERE t.classification = 'Private' and t.schoolName LIKE ? ;");
                String search = name + "%";
                pstmt.setString(1, search);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String temp = new String();
                    temp = rs.getString("schoolName");
                    schoolNames.add(temp);
                }
                pstmt.close();
                conn.close();
                rs.close();
            }
            return schoolNames;
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkifRecordSubmitted(int year, String classification) throws SQLException {
        boolean rows = true;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {

                PreparedStatement pstmt = conn.prepareStatement("SELECT count(censusYear) as `count` FROM directory_school WHERE `active` = 1 AND classification = ? AND censusYear = ?;");
                pstmt.setString(1, classification);
                pstmt.setInt(2, year);
               
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int isDeleted2 = rs.getInt("count");

                    if (isDeleted2 > 0) {
                        rows = false;
                    }
                }
                conn.close();
                pstmt.close();
            }
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return rows;
    }

    public ArrayList<String> SearchSchoolNamePublic(String name) throws ParseException, SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<String> schoolNames = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("select t.schoolName\n"
                        + "from directory_school t\n"
                        + "inner join (\n"
                        + "    select schoolName, max(censusyear) as MaxDate\n"
                        + "    from directory_school\n"
                        + "    group by schoolName\n"
                        + ") tm on t.schoolName = tm.schoolName and t.censusYear = tm.MaxDate\n"
                        + "WHERE t.classification = 'Public' AND t.schoolName LIKE ? ;");
                String search = name + "%";
                pstmt.setString(1, search);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String temp = new String();
                    temp = rs.getString("schoolName");
                    schoolNames.add(temp);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return schoolNames;
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean setDirectoryInactive(String schoolName, String classification, int censusYear) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            Connection conn;
            conn = myFactory.getConnection();
            String updateValidation = "UPDATE directory_school  SET `active`= ? WHERE `classification` = ? and `schoolName` = ? and `censusYear` = ?;";
            PreparedStatement pstmt = conn.prepareStatement(updateValidation);
            pstmt.setBoolean(1, false);
            pstmt.setString(2, classification);
            pstmt.setString(3, schoolName);
            pstmt.setInt(4, censusYear);
            int rows = pstmt.executeUpdate();

            String updateValidation2 = "UPDATE elem_classrooms  SET `active`= ? WHERE `classification` = ? and `schoolName` = ? and `censusYear` = ?;";
            PreparedStatement pstmt2 = conn.prepareStatement(updateValidation2);
            pstmt2.setBoolean(1, false);
            pstmt2.setString(2, classification);
            pstmt2.setString(3, schoolName);
            pstmt2.setInt(4, censusYear);
            int rows2 = pstmt2.executeUpdate();

            String updateValidation3 = "UPDATE elem_teachers  SET `active`= ? WHERE `classification` = ? and `schoolName` = ? and `censusYear` = ?;";
            PreparedStatement pstmt3 = conn.prepareStatement(updateValidation3);
            pstmt3.setBoolean(1, false);
            pstmt3.setString(2, classification);
            pstmt3.setString(3, schoolName);
            pstmt3.setInt(4, censusYear);
            int rows3 = pstmt3.executeUpdate();

            String updateValidation4 = "UPDATE seats  SET `active`= ? WHERE `classification` = ? and `schoolName` = ? and `censusYear` = ?;";
            PreparedStatement pstmt4 = conn.prepareStatement(updateValidation4);
            pstmt4.setBoolean(1, false);
            pstmt4.setString(2, classification);
            pstmt4.setString(3, schoolName);
            pstmt4.setInt(4, censusYear);
            int rows4 = pstmt4.executeUpdate();

            pstmt.close();
            conn.close();
            if (rows >= 1 && rows2 >= 1 && rows3 >= 1 && rows4 >= 1) {
                return true;
            }

        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<DirectorySchool> ViewByDirectorySchool(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<DirectorySchool> ArrdirectorySchool = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM directory_school WHERE formID = ? and `active` = 1");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    DirectorySchool temp = new DirectorySchool();
                    temp.setFormID(rs.getInt("formID"));
                    ArrdirectorySchool.add(temp);
                }

                pstmt.close();
                rs.close();
                conn.close();
            }
            return ArrdirectorySchool;
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<DirectorySchool> ViewDirectoryHospital(int year) throws ParseException {

        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        ArrayList<DirectorySchool> ArrdirectorySchool = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM directory_school WHERE censusYear = ? and `active` = 1");
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DirectorySchool temp = new DirectorySchool();
                temp.setFormID(rs.getInt("formID"));
                ArrdirectorySchool.add(temp);
            }
            conn.close();
            rs.close();
            return ArrdirectorySchool;

        } catch (SQLException ex) {
            Logger.getLogger(DirectorySchoolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean AddNewEncodeDirectorySchool(DirectorySchool directorySchool) {

        int rows4 = 0;
        int rows3 = 0;
        int rows2 = 0;
        int rows = 0;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                String school = "INSERT INTO directory_school"
                        + "(censusYear, formID, schoolID, classification, schoolName, latitude, longitude, address, `active`, `alter`) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                String teacher = "INSERT INTO elem_teachers"
                        + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, maleCount, femaleCount, `active`, `alter`) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                String classroom = "INSERT INTO elem_classrooms"
                        + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, classroomCount, `active`, `alter`) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

                String seats = "INSERT INTO seats"
                        + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, seatsCount, `active`, `alter`) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement pstmtSchool = conn.prepareStatement(school);
                PreparedStatement pstmtTeacher = conn.prepareStatement(teacher);
                PreparedStatement pstmtClassroom = conn.prepareStatement(classroom);
                PreparedStatement pstmtSeats = conn.prepareStatement(seats);

                pstmtSchool.setInt(1, directorySchool.getCensusYear());
                pstmtSchool.setInt(2, directorySchool.getFormID());
                pstmtSchool.setInt(3, directorySchool.getSchoolID());
                pstmtSchool.setString(4, directorySchool.getClassification());
                pstmtSchool.setString(5, directorySchool.getSchoolName());
                pstmtSchool.setDouble(6, directorySchool.getLatitude());
                pstmtSchool.setDouble(7, directorySchool.getLongitude());
                pstmtSchool.setString(8, directorySchool.getAddress());
                pstmtSchool.setBoolean(9, true);
                pstmtSchool.setBoolean(10, true);
                rows = pstmtSchool.executeUpdate();

                for (int i = 0; i < directorySchool.getElemClassrooms().size(); i++) {
                    pstmtClassroom.setInt(1, directorySchool.getCensusYear());
                    pstmtClassroom.setInt(2, directorySchool.getFormID());
                    pstmtClassroom.setInt(3, directorySchool.getSchoolID());
                    pstmtClassroom.setString(4, directorySchool.getClassification());
                    pstmtClassroom.setString(5, directorySchool.getSchoolName());
                    pstmtClassroom.setString(6, directorySchool.getElemClassrooms().get(i).getGradeLevel());
                    pstmtClassroom.setDouble(7, directorySchool.getElemClassrooms().get(i).getClassroomCount());
                    pstmtClassroom.setBoolean(8, true);
                    pstmtClassroom.setBoolean(9, true);
                    rows4 = pstmtClassroom.executeUpdate();

                }

                for (int i = 0; i < directorySchool.getTeacher().size(); i++) {
                    pstmtTeacher.setInt(1, directorySchool.getCensusYear());
                    pstmtTeacher.setInt(2, directorySchool.getFormID());
                    pstmtTeacher.setInt(3, directorySchool.getSchoolID());
                    pstmtTeacher.setString(4, directorySchool.getClassification());
                    pstmtTeacher.setString(5, directorySchool.getSchoolName());

                    pstmtTeacher.setString(6, directorySchool.getTeacher().get(i).getGradeLevel());
                    pstmtTeacher.setDouble(7, directorySchool.getTeacher().get(i).getFemaleCount());
                    pstmtTeacher.setDouble(8, directorySchool.getTeacher().get(i).getMaleCount());
                    pstmtTeacher.setBoolean(9, true);
                    pstmtTeacher.setBoolean(10, true);
                    rows2 = pstmtTeacher.executeUpdate();
                }

                for (int i = 0; i < directorySchool.getSeats().size(); i++) {
                    pstmtSeats.setInt(1, directorySchool.getCensusYear());
                    pstmtSeats.setInt(2, directorySchool.getFormID());
                    pstmtSeats.setInt(3, directorySchool.getSchoolID());
                    pstmtSeats.setString(4, directorySchool.getClassification());
                    pstmtSeats.setString(5, directorySchool.getSchoolName());
                    pstmtSeats.setString(6, directorySchool.getSeats().get(i).getGradeLevel());
                    pstmtSeats.setDouble(7, directorySchool.getSeats().get(i).getSeatCount());
                    pstmtSeats.setBoolean(8, true);
                    pstmtSeats.setBoolean(9, true);
                    rows3 = pstmtSeats.executeUpdate();

                }

                pstmtSchool.close();
                pstmtSeats.close();
                pstmtTeacher.close();
                pstmtClassroom.close();
                conn.close();
                if (rows == 1 && rows2 == 1 && rows3 == 1 && rows4 == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public Integer getFormID(int year, String classification) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from directory_school where classification = ? ";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, classification);
                rs = pstmt.executeQuery();

                if (classification.equalsIgnoreCase("private")) {
                    i = 120000000;
                } else {
                    i = 190000000;
                }

                while (rs.next()) {
                    i += year;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public boolean SubtmitAllPrivate(int year, int uploadedBy) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int rows1 = 0, rows2 = 0, rows3 = 0, rows4 = 0;
            

                boolean x;
                RecordDAO recorddao = new RecordDAO();

                x = recorddao.checkExistRecord(120000000, year);
                if (x == false) {
                    x = recorddao.newRecord(120000000 + year, year, uploadedBy);
                } else {
                    x = deleteSchoolRecord(year, "private");
                }

                if (x) {
                 try (Connection conn = myFactory.getConnection()) {   
                    
                    String school = "INSERT INTO directory_school \n"
                            + "(censusYear, formID, schoolID, classification, schoolName, latitude, longitude, `alter`, address, active) \n"
                            + "SELECT ?, ?, schoolID, classification, schoolName, latitude, longitude, `alter`, address, active \n"
                            + "FROM directory_school WHERE `active` = 1 AND `alter` != '1' AND `classification` = 'Private'";

                    PreparedStatement pstmt = conn.prepareStatement(school);
                    pstmt.setInt(1, year);
                    pstmt.setInt(2, 120000000 + year);

                    //GET SCHOOLNAME
                    String teacher = "INSERT INTO elem_teachers\n"
                            + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, femaleCount, maleCount, active, `alter`)\n"
                            + "SELECT ? , ?, e.schoolID, e.classification, e.schoolName, e.gradeLevel, e.femaleCount, e.maleCount, e.active, 0\n"
                            + "FROM elem_teachers e  JOIN \n"
                            + "					(SELECT *\n"
                            + "					FROM directory_school d \n"
                            + "					WHERE d.active = 1  AND d.classification = 'Private' \n"
                            + "							AND  `alter` != '1'   AND d.censusYear = ?\n"
                            + "					) z \n"
                            + "			  ON e.schoolID  = z.schoolID \n"
                            + "              AND e.`active` = '1'\n"
                            + "              AND e.`alter` != '1';";

                    PreparedStatement pstmt1 = conn.prepareStatement(teacher);

                    pstmt1.setInt(1, year);
                    pstmt1.setInt(2, 120000000 + year);
                    pstmt1.setInt(3, year);
                    //GET SCHOOLNAME
                    String classroom = "INSERT INTO elem_classrooms\n"
                            + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, classroomCount , active, `alter`)\n"
                            + "SELECT ? , ?, e.schoolID, e.classification, e.schoolName, e.gradeLevel, e.classroomCount, e.active, 0\n"
                            + "FROM elem_classrooms e  JOIN \n"
                            + "					(SELECT *\n"
                            + "					FROM directory_school d \n"
                            + "					WHERE d.active = 1 AND  d.classification = 'Private' \n"
                            + "					AND  `alter` != '1'  AND d.censusYear = ?\n"
                            + "					) z \n"
                            + "			  ON e.schoolID  = z.schoolID AND e.`active` = '1'  AND e.`alter` != '1';  ";

                    PreparedStatement pstmt2 = conn.prepareStatement(classroom);
                    pstmt2.setInt(1, year);
                    pstmt2.setInt(2, 120000000 + year);
                    pstmt2.setInt(3, year);
                    //GET SCHOOLNAME
                    String seats = "INSERT INTO seats\n"
                            + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, seatsCount, active, `alter`)\n"
                            + "SELECT ? , ?, e.schoolID, e.classification, e.schoolName, e.gradeLevel, e.seatsCount, e.active, 0\n"
                            + "FROM seats e  JOIN \n"
                            + "					(SELECT *\n"
                            + "					FROM directory_school d \n"
                            + "					WHERE d.active = 1 AND   d.classification = 'Private' \n"
                            + "						AND  `alter` != '1' AND d.censusYear = ?\n"
                            + "					) z \n"
                            + "			  ON e.schoolID  = z.schoolID \n"
                            + "              AND e.`active` = '1' \n"
                            + "              AND e.`alter` != '1';";

                    PreparedStatement pstmt3 = conn.prepareStatement(seats);
                    pstmt3.setInt(1, year);
                    pstmt3.setInt(2, 120000000 + year);
                    pstmt3.setInt(3, year);

                    rows1 = pstmt.executeUpdate();
                    rows2 = pstmt1.executeUpdate();
                    rows3 = pstmt2.executeUpdate();
                    rows4 = pstmt3.executeUpdate();

                    conn.close();
                    pstmt.close();
                    pstmt2.close();
                    pstmt3.close();
                }

            }
            if (rows1 > 0 && rows2 > 0 && rows3 > 0 && rows4 > 0) {
                boolean y = setActiveOrInactive(year - 1, 0, "private");
                if (y) {
                    y = UpdateValidation(120000000 + year);
                }
                return y;
            }
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean SubtmitAllPublic(int year, int uploadedBy) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int rows1 = 0, rows2 = 0, rows3 = 0, rows4 = 0;
            boolean x;
            try (Connection conn = myFactory.getConnection()) {
                RecordDAO recorddao = new RecordDAO();

                x = recorddao.checkExistRecord(190000000, year);
                if (x == false) {
                    x = recorddao.newRecord(190000000 + year, year, uploadedBy);
                } else {
                    x = deleteSchoolRecord(year, "public");
                }

                if (x) {
                    String school = "INSERT INTO directory_school \n"
                            + "(censusYear, formID, schoolID, classification, schoolName, latitude, longitude, `alter`, address, active) \n"
                            + "SELECT ?, ?, schoolID, classification, schoolName, latitude, longitude, `alter`, address, active \n"
                            + "FROM directory_school WHERE `active` = 1  AND `alter` != '1'  AND `classification` = 'Public'";

                    PreparedStatement pstmt = conn.prepareStatement(school);
                    pstmt.setInt(1, year);
                    pstmt.setInt(2, 190000000 + year);

                    //GET SCHOOLNAME
                    String teacher = "INSERT INTO elem_teachers\n"
                            + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, femaleCount, maleCount, active, `alter`)\n"
                            + "SELECT ? , ?, e.schoolID, e.classification, e.schoolName, e.gradeLevel, e.femaleCount, e.maleCount, e.active, 0\n"
                            + "FROM elem_teachers e  JOIN \n"
                            + "					(SELECT *\n"
                            + "					FROM directory_school d \n"
                            + "					WHERE d.active = 1  AND d.classification = 'Public' \n"
                            + "					AND  `alter` != '1' AND d.censusYear = ?\n"
                            + "					) z \n"
                            + "			  ON e.schoolID  = z.schoolID \n"
                            + "              AND e.active = '1'\n"
                            + "              AND e.`alter` != '1';";

                    PreparedStatement pstmt1 = conn.prepareStatement(teacher);

                    pstmt1.setInt(1, year);
                    pstmt1.setInt(2, 190000000 + year);
                    pstmt1.setInt(3, year);
                    //GET SCHOOLNAME
                    String classroom = "INSERT INTO elem_classrooms\n"
                            + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, classroomCount , active, `alter`)\n"
                            + "SELECT ? , ?, e.schoolID, e.classification, e.schoolName, e.gradeLevel, e.classroomCount, e.active, 0\n"
                            + "FROM elem_classrooms e  JOIN \n"
                            + "					(SELECT *\n"
                            + "					FROM directory_school d \n"
                            + "					WHERE d.active = 1 AND  d.classification = 'Public' \n"
                            + "					AND  `alter` != '1'  AND d.censusYear = ?\n"
                            + "					) z \n"
                            + "			  ON e.schoolID  = z.schoolID \n"
                            + "              AND e.active = '1'\n"
                            + "              AND e.`alter` != '1';              ";

                    PreparedStatement pstmt2 = conn.prepareStatement(classroom);
                    pstmt2.setInt(1, year);
                    pstmt2.setInt(2, 190000000 + year);
                    pstmt2.setInt(3, year);
                    //GET SCHOOLNAME
                    String seats = "INSERT INTO seats\n"
                            + "(censusYear, formID, schoolID, classification, schoolName, gradeLevel, seatsCount, active, `alter`)\n"
                            + "SELECT ? , ?, e.schoolID, e.classification, e.schoolName, e.gradeLevel, e.seatsCount, e.active, 0\n"
                            + "FROM seats e  JOIN \n"
                            + "					(SELECT *\n"
                            + "					FROM directory_school d \n"
                            + "					WHERE d.active = 1 AND   d.classification = 'Public' \n"
                            + "							AND  `alter` != '1'   AND d.censusYear = ?\n"
                            + "					) z \n"
                            + "			  ON e.schoolID  = z.schoolID \n"
                            + "              AND e.active = '1'\n"
                            + "              AND e.`alter` != '1';";

                    PreparedStatement pstmt3 = conn.prepareStatement(seats);
                    pstmt3.setInt(1, year);
                    pstmt3.setInt(2, 190000000 + year);
                    pstmt3.setInt(3, year);

                    rows1 = pstmt.executeUpdate();
                    rows2 = pstmt1.executeUpdate();
                    rows3 = pstmt2.executeUpdate();
                    rows4 = pstmt3.executeUpdate();

                    conn.close();
                    pstmt.close();
                    pstmt2.close();
                    pstmt3.close();
                }

            }
            if (rows1 > 0 && rows2 > 0 && rows3 > 0 && rows4 > 0) {
                boolean y = setActiveOrInactive(year - 1, 0, "public");
                if (y) {
                    y = UpdateValidation(190000000 + year);
                }

                return y;
            }
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean UpdateDirectory(DirectorySchool directorySchool) throws SQLException {

        int rows = 0;

        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

        try (Connection conn = myFactory.getConnection()) {

            String updateValidation = "UPDATE directory_school "
                    + " SET schoolID = ?, classification = ?,"
                    + " latitude = 0, longitude = 0, `alter` = ?,"
                    + " address = ?, active = ?"
                    + " WHERE `schoolName` = ? and `censusYear` = ?;";

            String updateValidation4 = "UPDATE seats "
                    + " SET seatsCount = ?, active = ?, `alter` = ?"
                    + "  WHERE gradeLevel = ? and `schoolName` = ? and `censusYear` = ?;";

            String updateValidation2 = "UPDATE elem_classrooms "
                    + " SET classroomCount = ? , active = ?, `alter` = ?"
                    + "  WHERE gradeLevel = ? and `schoolName` = ? and `censusYear` = ?;";

            String updateValidation3 = "UPDATE elem_teachers "
                    + " SET femaleCount = ?, maleCount = ?, "
                    + " active = ?, `alter` = ? "
                    + "  WHERE gradeLevel = ? and `schoolName` = ? and `censusYear` = ?;";
            PreparedStatement pstmt = conn.prepareStatement(updateValidation);

            pstmt.setInt(1, directorySchool.getSchoolID());
            pstmt.setString(2, directorySchool.getClassification());
            pstmt.setBoolean(3, true);
            pstmt.setString(4, directorySchool.getAddress());
            pstmt.setBoolean(5, true);
            pstmt.setString(6, directorySchool.getSchoolName());
            pstmt.setInt(7, directorySchool.getCensusYear());

            //set
            rows = pstmt.executeUpdate();
            PreparedStatement pstmt2 = conn.prepareStatement(updateValidation2);

            for (int i = 0; i < directorySchool.getElemClassrooms().size(); i++) {
                pstmt2.setInt(1, directorySchool.getElemClassrooms().get(i).getClassroomCount());
                pstmt2.setBoolean(2, true);
                pstmt2.setBoolean(3, true);
                pstmt2.setString(4, directorySchool.getElemClassrooms().get(i).getGradeLevel());
                pstmt2.setString(5, directorySchool.getSchoolName());
                pstmt2.setInt(6, directorySchool.getCensusYear());
                pstmt2.addBatch();
            }

            PreparedStatement pstmt3 = conn.prepareStatement(updateValidation3);
            for (int i = 0; i < directorySchool.getTeacher().size(); i++) {
                pstmt3.setInt(1, directorySchool.getTeacher().get(i).getFemaleCount());
                pstmt3.setInt(2, directorySchool.getTeacher().get(i).getMaleCount());
                pstmt3.setBoolean(3, true);
                pstmt3.setBoolean(4, true);
                pstmt3.setString(5, directorySchool.getTeacher().get(i).getGradeLevel());
                pstmt3.setString(6, directorySchool.getSchoolName());
                pstmt3.setInt(7, directorySchool.getCensusYear());
          pstmt3.addBatch();
            }
            //set
            PreparedStatement pstmt4 = conn.prepareStatement(updateValidation4);
            for (int i = 0; i < directorySchool.getSeats().size(); i++) {
                pstmt4.setInt(1, directorySchool.getSeats().get(i).getSeatCount());
                pstmt4.setBoolean(2, true);
                pstmt4.setBoolean(3, true);
                pstmt4.setString(4, directorySchool.getSeats().get(i).getGradeLevel());
                pstmt4.setString(5, directorySchool.getSchoolName());
                pstmt4.setInt(6, directorySchool.getCensusYear());
             pstmt4.addBatch();
            }

             pstmt4.executeBatch();
            pstmt3.executeBatch();
            pstmt2.executeBatch();
            
            conn.close();
            pstmt.close();
            pstmt4.close();
            pstmt3.close();
            pstmt2.close();

        }

        return rows >= 1;

    }

    public boolean setActiveOrInactive(int year, int decision, String classification) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {

                //SET RECENT TO INACTIVE
                String updateS = "UPDATE directory_school  SET `active` = ? \n"
                        + "WHERE censusYear = ?  AND  classification = ?;";
                PreparedStatement pstmtS = conn.prepareStatement(updateS);
                pstmtS.setInt(1, decision);
                pstmtS.setInt(2, year);
                pstmtS.setString(3, classification);

//                String updateClassroom = "UPDATE elem_classrooms  SET `active` = ? \n"
//                        + "WHERE censusYear = ?  AND  classification = ?;";
//                PreparedStatement pstmtC = conn.prepareStatement(updateClassroom);
//
//                pstmtC.setInt(1, decision);
//                pstmtC.setInt(2, year);
//                pstmtC.setString(3, classification);
//
//                String updateTeacher = "UPDATE elem_teachers  SET `active` = ? \n"
//                        + "WHERE censusYear = ?  AND  classification = ?;";
//                PreparedStatement pstmtT = conn.prepareStatement(updateTeacher);
//
//                pstmtT.setInt(1, decision);
//                pstmtT.setInt(2, year);
//                pstmtT.setString(3, classification);
//
//                String updateseat = "UPDATE seats  SET `active` =  ? \n"
//                        + "WHERE censusYear = ? AND  classification = ?;";
//                PreparedStatement pstmtST = conn.prepareStatement(updateseat);
//
//                pstmtST.setInt(1, decision);
//                pstmtST.setInt(2, year);
//                pstmtST.setString(3, classification);
//
                int isDeleted = pstmtS.executeUpdate();
//                int isDeleted2 = pstmtC.executeUpdate();
//                int isDeleted3 = pstmtT.executeUpdate();
//                int isDeleted4 = pstmtST.executeUpdate();

//                if (isDeleted > 0 && isDeleted2 > 0 && isDeleted3 > 0 && isDeleted4 > 0) {
//                    conn.close();
//                    pstmtS.close();
//                    pstmtC.close();
//                    pstmtT.close();
//                    pstmtST.close();
//                    rows = true;
//                }

                if (isDeleted > 0) {
                    conn.close();
                    pstmtS.close();
                    rows = true;
                }

            }
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return rows;
    }

    public boolean deleteSchoolRecord(int year, String classification) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            PreparedStatement pstmt1;
            PreparedStatement pstmt2;
            PreparedStatement pstmt3;

            try (Connection conn = myFactory.getConnection()) {

                String elem_classrooms = "DELETE FROM elem_classrooms WHERE  censusYear = ? AND  classification = ? AND `alter`= 0";
                pstmt1 = conn.prepareStatement(elem_classrooms);
                pstmt1.setInt(1, year);
                pstmt1.setString(2, classification);

                String elem_teachers = "DELETE FROM elem_teachers WHERE  censusYear = ? AND  classification = ? AND `alter`= 0";
                pstmt2 = conn.prepareStatement(elem_teachers);
                pstmt2.setInt(1, year);
                pstmt2.setString(2, classification);

                String seats = "DELETE FROM seats WHERE  censusYear = ? AND  classification = ? AND `alter`= 0";
                pstmt3 = conn.prepareStatement(seats);
                pstmt3.setInt(1, year);
                pstmt3.setString(2, classification);

                String directory_school = "DELETE FROM directory_school WHERE  censusYear = ? AND  classification = ? AND `alter`= 0";
                pstmt = conn.prepareStatement(directory_school);
                pstmt.setInt(1, year);
                pstmt.setString(2, classification);

                int isDeleted2 = pstmt1.executeUpdate();
                int isDeleted3 = pstmt2.executeUpdate();
                int isDeleted4 = pstmt3.executeUpdate();
                int isDeleted = pstmt.executeUpdate();

           
                
                if (isDeleted > 0 && isDeleted2 > 0 && isDeleted3 > 0 && isDeleted4 > 0) {
                    conn.close();
                    pstmt1.close();
                    rows = true;
                } else if (isDeleted == 0 && isDeleted2 == 0 && isDeleted3 == 0 && isDeleted4 == 0) {
                    conn.close();
                    pstmt1.close();
                    rows = true;
                }

            }
        } catch (SQLException ex) {
            getLogger(DirectorySchoolDAO.class.getName()).log(SEVERE, null, ex);
        }
        return rows;
    }

    public boolean UpdateValidation(int formID) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE RECORDS  SET `validated`= 1 WHERE `FORMID` = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setInt(1, formID);

                rows = pstmt.executeUpdate();

                pstmt.close();
                conn.close();
            }
            if (rows > 0) {
                return rows == 1;
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
