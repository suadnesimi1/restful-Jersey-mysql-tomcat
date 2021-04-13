package Repository;

import Connection.DbConnection;
import Domain.Exam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExamRepo {
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Exam> getExam() {
        List<Exam> examsList = new ArrayList<>();
        String query = "select e.id,eg.student_id,eg.grade, eg.exam_id from exams e\n" +
                "left join exam_grade eg on e.id = eg.exam_id;";
        try {
            ps = DbConnection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getString("e.id"));
                exam.setGrade(rs.getString("eg.grade"));
                examsList.add(exam);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeAll(ps);

        }
        return examsList;
    }
    public Exam createExam(Exam exam){
        String query ="insert into exam_grade(STUDENT_ID, EXAM_ID, GRADE) VALUE (?,?,?);";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,exam.getId());
            ps.setString(2,exam.getGrade());

            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return exam;
    }
    public Exam updateExam(Exam exam){
        String query="update exams set id = ? where grade = ?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,exam.getId());
            ps.setString(2,exam.getGrade());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return exam;
    }
    public Exam deleteExam(Exam exam){
        String query = "delete from exams where id = ?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,exam.getId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return exam;
    }
}