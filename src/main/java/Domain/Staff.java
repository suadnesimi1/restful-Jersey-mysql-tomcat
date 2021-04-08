package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Staff {
    private String id;
    private String name;
    private String facilty_id;
    private String university_id;
    private List<Course> courses = new ArrayList<>();

    public Staff() {
    }
    public Staff(ResultSet rs) throws SQLException {
        setId(rs.getString("s.id"));
        setName(rs.getString("s.name"));
        setFacilty_id("s.faculty_id");
        setUniversity_id(rs.getString("s.university_id"));
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

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getFacilty_id() {
        return facilty_id;
    }

    public void setFacilty_id(String facilty_id) {
        this.facilty_id = facilty_id;
    }
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

