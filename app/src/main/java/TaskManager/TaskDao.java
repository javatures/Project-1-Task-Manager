package TaskManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskDao {
    private String url = "jdbc:postgresql://localhost:5432/taskmanager";
    private String username = "taskmanager";
    private String password = "password123";
    private Connection connection;

    public TaskDao(){
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTask(Date date, String task, int typeId, int userId){
        try {
            PreparedStatement pstatement = this.connection.prepareStatement("insert into tasks (taskdate, task, typeid, userid) values (?, ?, ?, ?)");
            pstatement.setDate(1, date);
            pstatement.setString(2, task);
            pstatement.setInt(3, typeId);
            pstatement.setInt(4, userId);
            pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error with insert task");
            e.printStackTrace();
        }
    }
    
    public List<Task> getAllTasks(HashMap<Integer, String> types, int userId){
        List<Task> tasks = new ArrayList<Task>();
        try {
            PreparedStatement pstatement = this.connection.prepareStatement("select * from tasks where userid = ?");
            pstatement.setInt(1, userId);
            ResultSet result = pstatement.executeQuery();
            while (result.next()){
                Task task = new Task();
                task.setId(result.getInt("id"));
                task.setDate(result.getDate("taskdate"));
                task.setTask(result.getString("task"));
                task.setTypeId(result.getInt("typeid"));
                task.setTaskType(types.get(task.getTypeId()));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tasks;
    }
    
    public List<Task> getAllCompletedTasks(HashMap<Integer, String> types){
        List<Task> tasks = new ArrayList<Task>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("select * from completedTasks");
            while (result.next()){
                Task task = new Task();
                task.setId(result.getInt("id"));
                task.setDate(result.getDate("taskdate"));
                task.setTask(result.getString("task"));
                task.setTypeId(result.getInt("typeid"));
                task.setTaskType(types.get(task.getTypeId()));
                task.setComplete(true);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
