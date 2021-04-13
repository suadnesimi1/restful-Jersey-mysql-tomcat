package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exam extends Exam_Grade {
    private String id;
    private String grade;
    private List<Exam_Grade>examGrades=new ArrayList<>();

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
    public List<Exam_Grade> getExamGrades() {
        return examGrades;
    }
    public void setExamGrades(List<Exam_Grade> examGrades) {
        this.examGrades = examGrades;
    }
}
