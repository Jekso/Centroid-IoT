package Classes;

/**
 * Created by ahmedezz on 08/07/2016.
 */
public class SystemApiClass {
    private String system_id,ac_mode,ac1,ac2,ac3,ac4,door_state,current_temp,user_temp,system_hash,created_at,updated_at;
private int error;

    public SystemApiClass(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public SystemApiClass(String system_id, String ac_mode, String ac1, String ac2, String ac3, String ac4, String door_state, String current_temp, String user_temp, String system_hash, String created_at, String updated_at) {
        this.system_id = system_id;
        this.ac_mode = ac_mode;
        this.ac1 = ac1;
        this.ac2 = ac2;
        this.ac3 = ac3;
        this.ac4 = ac4;
        this.door_state = door_state;
        this.current_temp = current_temp;
        this.user_temp = user_temp;
        this.system_hash = system_hash;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public String getAc_mode() {
        return ac_mode;
    }

    public void setAc_mode(String ac_mode) {
        this.ac_mode = ac_mode;
    }

    public String getAc1() {
        return ac1;
    }

    public void setAc1(String ac1) {
        this.ac1 = ac1;
    }

    public String getAc2() {
        return ac2;
    }

    public void setAc2(String ac2) {
        this.ac2 = ac2;
    }

    public String getAc3() {
        return ac3;
    }

    public void setAc3(String ac3) {
        this.ac3 = ac3;
    }

    public String getAc4() {
        return ac4;
    }

    public void setAc4(String ac4) {
        this.ac4 = ac4;
    }

    public String getDoor_state() {
        return door_state;
    }

    public void setDoor_state(String door_state) {
        this.door_state = door_state;
    }

    public String getCurrent_temp() {
        return current_temp;
    }

    public void setCurrent_temp(String current_temp) {
        this.current_temp = current_temp;
    }

    public String getUser_temp() {
        return user_temp;
    }

    public void setUser_temp(String user_temp) {
        this.user_temp = user_temp;
    }

    public String getSystem_hash() {
        return system_hash;
    }

    public void setSystem_hash(String system_hash) {
        this.system_hash = system_hash;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
