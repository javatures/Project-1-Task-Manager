package TaskManager;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public User(String user, String password) {
        this.username = user;
        this.password = password;
    }

    public User(String user, String password, String firstName, String lastName, String email) {
        this.username = user;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}