package TaskManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private Connection connection;

    public Dao(Connection connection){
        this.connection = connection;
    }
    
    public void insertTask(Date date, String task, int typeId){
        try {
            PreparedStatement pstatement = this.connection.prepareStatement("insert into tasks (taskdate, task, typeid) values (?, ? ,?)");
            pstatement.setDate(1, date);
            pstatement.setString(2, task);
            pstatement.setInt(3, typeId);
            pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error with insert task");
            e.printStackTrace();
        }
    }

    public int insertType(String type){
        try{
            PreparedStatement pStatement = this.connection.prepareStatement("insert into types (tasktype) values (?)", Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, type);
            pStatement.executeUpdate();
            if (pStatement.getGeneratedKeys().next())
            return pStatement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            System.out.println("Error with insert type");
            e.printStackTrace();
        }
        return 0;
    }

    public List<Type> get(){
        List<Type> types = new ArrayList<Type>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery("select * from types");
            while (result.next()){
                Type type = new Type();
                type.setId(result.getInt("id"));
                type.setTaskType(result.getString("tasktype"));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;   
    }

    public void update(){

    }

}
