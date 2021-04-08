package Repository;

import Connection.DbConnection;
import Domain.Faculty_Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacultyCourseRepo {
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Faculty_Course> getFacultyCourse() {
        List<Faculty_Course> facultycourseList = new ArrayList<>();
        String sql = "select * from faculty_courses";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Faculty_Course facultyCourse = new Faculty_Course();
                facultyCourse.setFaculty_id(rs.getString(1));
                facultyCourse.setCourse_id(rs.getString(2));
                facultycourseList.add(facultyCourse);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return facultycourseList;


    }
}
