package Repository;

import Connection.DbConnection;
import Domain.Course;
import Domain.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FacultyRepo {
    private PreparedStatement ps;
    private ResultSet rs;


    public Faculty createFaculty(Faculty faculty) {
        String sql = "insert into faculties(id, name, university_id) VALUE (?,?,?)";
        try {
            ps=DbConnection.getConnection().prepareStatement(sql);
            ps.setString(1, faculty.getId());
            ps.setString(2, faculty.getName());
            ps.setString(3, faculty.getUniversityId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeAll(ps);
        }
        return faculty;
    }
    public Faculty updateFaculty(Faculty faculty){
        String query ="update faculties\n" +
                "set name =?\n" +
                "where id= ?";
        try{
            ps=DbConnection.getConnection().prepareStatement(query);
            ps.setString(2,faculty.getId());
            ps.setString(1,faculty.getName());
            ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return faculty;
    }
    public Faculty deleteFaculty(Faculty faculty){
        String query = "delete from faculties\n" +
                "where id = ?";
        try{
            ps=DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,faculty.getId());


            ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return faculty;
    }

    public List<Faculty> getFaculties() {
        List<Faculty> facultyList = new ArrayList<>();

        String sql = "select f.* from faculties f";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Faculty faculty = new Faculty(rs);
                facultyList.add(faculty);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return facultyList;
    }

    public Faculty getFacultyByName(String name) {
        Faculty faculty = null;
        String sql = "select f.*, c.* from faculties f \n" +
                "    left join faculty_courses fc on f.id = fc.faculty_id\n" +
                "    left join courses c on fc.course_id = c.id\n" +
                "where f.name = ?";
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (faculty == null) {
                    faculty = new Faculty(rs);
                }
                String courseId = rs.getString("c.id");
                if (courseId != null) {
                    Course c = new Course();
                    c.setId(courseId);
                    c.setName(rs.getString("c.name"));
                    faculty.getCourses().add(c);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(connection);
            DbConnection.closeAll(ps);
        }
        return faculty;
    }
    /*
    get faculties,courses
     */
    public List<Faculty>getAllFaculties(){
        HashMap<String,Faculty>facultyHashMap = new HashMap<>();
        String query = "select f.id,f.name, c.name from faculties f\n" +
                "inner join faculty_courses fc on f.id = fc.faculty_id\n" +
                "inner join courses c on fc.course_id = c.id";
        Connection connection = null;
        try{
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                String facultyId = rs.getString("f.id");
                Faculty faculty = facultyHashMap.get(facultyId);
                if (faculty == null){
                    faculty = new Faculty();
                    faculty.setId(facultyId);
                    faculty.setName(rs.getString("f.name"));
                    faculty.setCourses(new ArrayList<>());
                    facultyHashMap.put(facultyId,faculty);
                }
                String courseId = rs.getString("c.id");
                if (courseId!=null){
                    Course course = new Course();
                    course.setId(courseId);
                    course.setName(rs.getString("c.name"));
                    faculty.getCourses().add(course);

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
        }catch (SQLException throwables){
                throwables.printStackTrace();
            }
            DbConnection.closeAll(ps);
            DbConnection.closeConnection(connection);
        }
        return new ArrayList<>(facultyHashMap.values());
    }




}
