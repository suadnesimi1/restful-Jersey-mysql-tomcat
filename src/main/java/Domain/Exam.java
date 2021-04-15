package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exam extends ExamGrade {
    private String id;
    private List<ExamGrade>examGrades=new ArrayList<>();

    public Exam(){

    }
    public Exam(ResultSet rs) throws SQLException {
        setId(rs.getString("e.id"));
        examGrades = new ArrayList<>();
    }

    public String getGrade(){
        return grade;
    }
    public void setGrade(String grade){
        this.grade=grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public List<ExamGrade> getExamGrades() {
        return examGrades;
    }
    public void setExamGrades(List<ExamGrade> examGrades) {
        this.examGrades = examGrades;
    }
}
