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

import org.postgresql.jdbc.PgStatement;

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

    public void insertCompletedTask(HashMap<Integer, String> types, int taskId){
        try {
            PreparedStatement deleteStatement = this.connection.prepareStatement("delete from tasks where id = ? returning *");
            deleteStatement.setInt(1, taskId);
            ResultSet result = deleteStatement.executeQuery();
            while (result.next()){
                PreparedStatement insertStatement = this.connection.prepareStatement("insert into completedTasks (taskdate, task, typeid, userid) values (?,?,?,?)");
                insertStatement.setDate(1, result.getDate("taskdate"));
                insertStatement.setString(2, result.getString("task"));
                insertStatement.setInt(3, result.getInt("typeid"));
                insertStatement.setInt(4, result.getInt("userid"));
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
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
    
    public List<Task> getAllCompletedTasks(HashMap<Integer, String> types, int userId){
        List<Task> tasks = new ArrayList<Task>();
        try {
            PreparedStatement pstatement = this.connection.prepareStatement("select * from completedTasks where userId = ?");
            pstatement.setInt(1, userId);
            ResultSet result = pstatement.executeQuery();
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
