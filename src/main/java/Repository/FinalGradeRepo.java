package Repository;

import Connection.DbConnection;
import Domain.FinalGrade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FinalGradeRepo {
    private PreparedStatement ps;
    private ResultSet rs;

    public FinalGrade createFinalGrade(FinalGrade finalGrade){
        String query = "insert into final_grade(grade,course_id,student_id) values" +
                "(?,?,?)";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,finalGrade.getGrade());
            ps.setString(2,finalGrade.getCourse_id());
            ps.setString(3,finalGrade.getStudent_id());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return finalGrade;
    }
    public List<FinalGrade> getFinalGrade() {
        List<FinalGrade> Final_GradeList = new ArrayList<>();
        String sql = "select * from final_grade";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FinalGrade final_grade = new FinalGrade();
                final_grade.setGrade(rs.getString(1));
                final_grade.setCourse_id(rs.getString(2));
                final_grade.setStudent_id(rs.getString(3));
                Final_GradeList.add(final_grade);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return Final_GradeList;
    }
    public FinalGrade updateFinalGrade(FinalGrade finalGrade){
        String query = "update final_grade\n" +
                "set grade = ?\n" +
                "where student_id = ? and course_id = ?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,finalGrade.getGrade());
            ps.setString(2,finalGrade.getStudent_id());
            ps.setString(3,finalGrade.getCourse_id());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return finalGrade;
    }
    public FinalGrade deleteFinalGrade(FinalGrade finalGrade){
        String query = "delete from final_grade where student_id = ? and course_id = ?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,finalGrade.getStudent_id());
            ps.setString(2,finalGrade.getCourse_id());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return finalGrade;

    }

}
