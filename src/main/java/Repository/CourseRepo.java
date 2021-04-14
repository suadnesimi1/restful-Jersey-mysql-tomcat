package Repository;

import Connection.DbConnection;
import Domain.Course;
import Domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseRepo {
    private PreparedStatement ps;
    private ResultSet rs;

    public Course createCourse(Course course){
        String query = "insert into courses(id,name)values(?,?)";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,course.getId());
            ps.setString(2,course.getName());
            ps.executeUpdate();

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return course;
    }
    public List<Course> getCourse() {
        List<Course> courseList = new ArrayList<>();
        String sql = "select * from courses";
        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getString(1));
                course.setName(rs.getString(2));
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return courseList;
    }
    public Course getCourseByName(String name) {
        Course course = null;
        String sql = "select st.id,st.name,st.faculty_id,c.name from students st\n" +
                "inner join studentrelation s on st.id = s.student_id\n" +
                "inner join courses c on s.course_id = c.id where c.name=?";
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (course == null) {
                    course = new Course(rs);
                }
                String studentId = rs.getString("st.id");
                if (studentId != null) {
                    Student student = new Student();
                    student.setId(studentId);
                    student.setName(rs.getString("st.name"));
                    student.setFacultyId(rs.getString("st.faculty_id"));
                    course.getStudents().add(student);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(connection);
            DbConnection.closeAll(ps);
        }
        return course;
    }
    public Course updateCourse(Course course){
        String query = "update courses set name = ? where id =?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(2,course.getId());
            ps.setString(1,course.getName());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return course;
    }
    public Course deleteCourse(Course course){
        String query = "Delete from courses where id = ?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,course.getId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return course;
    }
}



