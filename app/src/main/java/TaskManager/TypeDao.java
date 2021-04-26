package TaskManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class TypeDao {
    private String url = "jdbc:postgresql://localhost:5432/taskmanager";
    private String username = "taskmanager";
    private String password = "password123";
    Connection connection;

    public TypeDao() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertType(Type type) {
        try {
            PreparedStatement pStatement = this.connection.prepareStatement("insert into types (tasktype) values (?)", Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, type.getTaskType());
            pStatement.executeUpdate();
            if (pStatement.getGeneratedKeys().next()) {
                return pStatement.getGeneratedKeys().getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error with insert type");
            e.printStackTrace();
        }
        return 0;
    }

    public HashMap<Integer, String> getAllTypes() {
        HashMap<Integer, String> types = new HashMap<Integer, String>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("select * from types");
            while (result.next()) {
                types.put(result.getInt("id"), result.getString("tasktype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
