package Repository;

import Connection.DbConnection;
import Domain.Studentrelation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRelationRepo {

    private PreparedStatement ps;
    private ResultSet rs;


    public List<Studentrelation> getStudentrelation() {
        List<Studentrelation> studentrelationsList = new ArrayList<>();
        String sql = "select * from studentrelation";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Studentrelation studentrelation = new Studentrelation();
                studentrelation.setStudent_id(rs.getString(1));
                studentrelation.setCourse_id(rs.getString(2));
                studentrelationsList.add(studentrelation);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return studentrelationsList;


    }
}
