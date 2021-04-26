package TaskManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    private String url = "jdbc:postgresql://localhost:5432/taskmanager";
    private String username = "taskmanager";
    private String password = "password123";
    Connection connection;

    public UserDao(){
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int registerNewUser(User user) {
        try {
            PreparedStatement pStatement = this.connection.prepareStatement("insert into users (username, password, firstname, lastname, email) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, user.getUsername());
            pStatement.setString(2, user.getPassword());
            pStatement.setString(3, user.getFirstName());
            pStatement.setString(4, user.getLastName());
            pStatement.setString(5, user.getEmail());
            pStatement.executeUpdate();
            if (pStatement.getGeneratedKeys().next()){
                return pStatement.getGeneratedKeys().getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public User authenticateUser(User user){
        try {
            PreparedStatement pStatement = this.connection.prepareStatement("select * from users where username = ? and password = ?");
            pStatement.setString(1, user.getUsername());
            pStatement.setString(2, user.getPassword());
            ResultSet result = pStatement.executeQuery();
            if(result.next()){
                user.setUserId(result.getInt("id"));;
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
