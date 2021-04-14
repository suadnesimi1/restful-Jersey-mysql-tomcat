package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private String finalGrade;
    private List<Student> students = new ArrayList<>();
    private List<Staff> staff = new ArrayList<>();



    public Course() {

    }
    public Course(ResultSet rs) throws SQLException {
        setId(rs.getString(1));
        setName(rs.getString(2));
        students = new ArrayList<>();
        staff = new ArrayList<>();

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
    public String getFinal_grade(){
        return finalGrade;
    }
    public void setFinal_grade(String finalGrade){
        this.finalGrade=finalGrade;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public List<Staff>getStaff(){
        return staff;
    }
    public void setStaff(List<Staff>staff){
        this.staff=staff;
    }

}