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
            ps.setString(2,finalGrade.getCourseId());
            ps.setString(3,finalGrade.getStudentId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return finalGrade;
    }
    public List<FinalGrade> getFinalGrade() {
        List<FinalGrade> FinalGradeList = new ArrayList<>();
        String sql = "select * from final_grade";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FinalGrade finalGrade = new FinalGrade();
                finalGrade.setGrade(rs.getString(1));
                finalGrade.setCourseId(rs.getString(2));
                finalGrade.setStudentId(rs.getString(3));
                FinalGradeList.add(finalGrade);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
       return FinalGradeList;
    }
    public FinalGrade updateFinalGrade(FinalGrade finalGrade){
        String query = "update final_grade\n" +
                "set grade = ?\n" +
                "where student_id = ? and course_id = ?";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,finalGrade.getGrade());
            ps.setString(2,finalGrade.getStudentId());
            ps.setString(3,finalGrade.getCourseId());
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
            ps.setString(1,finalGrade.getStudentId());
            ps.setString(2,finalGrade.getCourseId());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return finalGrade;

    }

}
