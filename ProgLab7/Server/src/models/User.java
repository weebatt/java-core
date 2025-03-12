package models;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Serial
    private static final long serialVersionUID = 9L;

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return "\"userName: " + userName +
                ", password: " + password + "\"";
    }
}
