package TaskManager;

public class Type {
    private int id;
    private String taskType;

    public Type(String type){
        this.taskType = type;
    }
    
    public Type(int id, String type){
        this.id = id;
        this.taskType = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

}
