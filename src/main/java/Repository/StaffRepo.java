package Repository;

import Connection.DbConnection;
import Domain.Course;
import Domain.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffRepo {
    private PreparedStatement ps;
    private ResultSet rs;

    public Staff createStaff(Staff staff){
        String query = "insert into staff(id,name,faculty_id,university_id)" +
                "values" +
                "(?,?,?,?)";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,staff.getId());
            ps.setString(2,staff.getName());
            ps.setString(3,staff.getFaciltyId());
            ps.setString(4,staff.getUniversityId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return staff;
    }
    public Staff updateStaff(Staff staff){
        String query = "update staff\n" +
                "set name = ?\n" +
                "where id  = ? and\n" +
                "faculty_id = ?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(2,staff.getId());
            ps.setString(1,staff.getName());
            ps.setString(3,staff.getFaciltyId());

            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return staff;
    }
    public Staff deleteStaff(Staff staff){
        String query = "delete from staff where id = ?";
        try {
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,staff.getId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return staff;
    }
    public List<Staff> getStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "select * from staff ";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getString(1));
                staff.setName(rs.getString(2));
                staff.setFaciltyId(rs.getString(3));
                staff.setUniversityId(rs.getString(4));
                staffList.add(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return staffList;
    }
    public Staff getStaffByName(String name){
        Staff staff = null;
        String sql = "select s.id, s.name, s.faculty_id,s.university_id, c.* from staff s\n" +
                "left join staffcourses sc on s.id = sc.staff_id\n" +
                "left join courses c on sc.course_id = c.id where s.name=? ";
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (staff == null) {
                    staff = new Staff(rs);
                }
                String courseId = rs.getString("c.id");
                if (courseId != null) {
                    Course c = new Course();
                    c.setId(courseId);
                    c.setName(rs.getString("c.name"));
                    staff.getCourses().add(c);
                }
            }
    }catch (Exception e ){
            e.printStackTrace();
        }finally {
            DbConnection.closeConnection(connection);
            DbConnection.closeAll(ps);
        }
        return staff;
        }
}

