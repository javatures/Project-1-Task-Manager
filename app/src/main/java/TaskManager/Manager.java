package TaskManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Manager {
    private String url = "jdbc:postgresql://localhost:5432/taskmanager";
    private String username = "taskmanager";
    private String password = "password123";
    
    public Manager(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Dao dao = new Dao(connection);
            // dao.insertType(type);
            // dao.insertTask(date, task, type);
        } catch (SQLException e) {
            System.out.println("SQLException with Task");
            e.printStackTrace();
        }
    }
}
