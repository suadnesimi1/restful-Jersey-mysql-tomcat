package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private String facultyId;
    private List<Course> courses = new ArrayList<>();
    private List<Exam> exams = new ArrayList<>();
    private List<ExamGrade>examGrades=new ArrayList<>();


    public Student(){

    }
    public Student(ResultSet rs ) throws SQLException{
        setId(rs.getString(1));
        setName(rs.getString(2));
        setFacultyId(rs.getString(3));
        courses = new ArrayList<>();
        exams = new ArrayList<>();
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

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId=facultyId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Exam>getExam(){
        return exams;
    }

    public void setExamGrades(List<Exam>exams){
        this.exams = exams;
    }
    public List<ExamGrade> getExamGrades() {
        return examGrades;
    }


}
