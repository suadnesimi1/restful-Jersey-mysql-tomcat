package Connection;
/*
    @author SuadNesimi
    14/04/2021
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static final String DBNAME = "unidb";
    private static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVERNAME);
        return DriverManager.getConnection(URL + DBNAME, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeAll(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed())
                ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
