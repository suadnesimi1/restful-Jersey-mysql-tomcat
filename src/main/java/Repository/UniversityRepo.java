package Repository;

import Connection.DbConnection;
import Domain.University;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UniversityRepo {
    private PreparedStatement ps;
    private ResultSet rs;


    public List<University> getUniversity() {
        List<University> universityList = new ArrayList<>();
        String sql = "select * from universities";

        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                University university = new University();
                university.setId(rs.getString(1));
                university.setName(rs.getString(2));
                universityList.add(university);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return universityList;
    }
    public University createUni(University university){
        String query = "insert into universities(id, name) values(?,?)";
        try{
            ps = DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,university.getId());
            ps.setString(2,university.getName());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return university;
    }
    public University deleteUni(University university){
        String query="";
        try{
            ps =DbConnection.getConnection().prepareStatement(query);
            ps.setString(1,university.getId());
            ps.setString(2,university.getName());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbConnection.closeAll(ps);
        }
        return university;
    }

}
