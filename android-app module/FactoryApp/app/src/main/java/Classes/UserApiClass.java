package Classes;

/**
 * Created by ahmedezz on 08/07/2016.
 */
public class UserApiClass {
    private String username,password,token;
    private int id,system_id,role,error;

    public UserApiClass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserApiClass(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public UserApiClass(String username, String password, String token, int id, int system_id, int role){
        this.username = username;
        this.password = password;
        this.token = token;
        this.id = id;
        this.system_id = system_id;
        this.role = role;
        //this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSystem_id() {
        return system_id;
    }

    public void setSystem_id(int system_id) {
        this.system_id = system_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

   /* public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }*/
}
