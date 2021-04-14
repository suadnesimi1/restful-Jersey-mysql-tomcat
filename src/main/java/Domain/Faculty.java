package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String id;
    private String name;
    private String universityId;
    private List<Course> courses = new ArrayList<>();


    public Faculty() {
    }

    public Faculty(ResultSet rs) throws SQLException {
        setId(rs.getString("f.id"));
        setName(rs.getString("f.name"));
        setUniversityId(rs.getString("f.university_id"));
        courses = new ArrayList<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}