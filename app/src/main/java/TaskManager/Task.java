package TaskManager;

import java.sql.Date;

public class Task {
    private int id;
    private Date date;
    private String task;
    private int typeId;
    private String taskType = "";
    private boolean complete = false;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getTask() {
        return task;
    }
    
    public void setTask(String task) {
        this.task = task;
    }
    
    public int getTypeId() {
        return typeId;
    }
    
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTaskType() {
        return taskType;
    }
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
    public boolean isComplete() {
        return complete;
    }
    
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
