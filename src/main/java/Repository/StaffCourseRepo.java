package Repository;

import Connection.DbConnection;
import Domain.StaffCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffCourseRepo {
    private PreparedStatement ps;
    private ResultSet rs;


    public List<StaffCourse> getStaffCourse() {
        List<StaffCourse> staffCourseList = new ArrayList<>();
        String sql = "select * from staffcourses order by course_id DESC ";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StaffCourse staffCourse = new StaffCourse();
                staffCourse.setStaff_id(rs.getString(1));
                staffCourse.setCourse_id(rs.getString(2));
                staffCourseList.add(staffCourse);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return staffCourseList;


    }
}
