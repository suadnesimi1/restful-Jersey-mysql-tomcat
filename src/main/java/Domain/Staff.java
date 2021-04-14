package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Staff {
    private String id;
    private String name;
    private String facultyId;
    private String universityId;
    private List<Course> courses = new ArrayList<>();

    public Staff() {
    }
    public Staff(ResultSet rs) throws SQLException {
        setId(rs.getString("s.id"));
        setName(rs.getString("s.name"));
        setFaciltyId("s.faculty_id");
        setUniversityId(rs.getString("s.university_id"));
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
        this.universityId=universityId;
    }

    public String getFaciltyId() {
        return facultyId;
    }

    public void setFaciltyId(String faciltyId) {
        this.facultyId=faciltyId;
    }
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

