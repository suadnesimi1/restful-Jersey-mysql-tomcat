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

        String query = "select * from exams";
        try {
            ps = DbConnection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getString(1));
                examsList.add(exam);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeAll(ps);

        }
        return examsList;
    }
    public Exam createExam(){

    }
}