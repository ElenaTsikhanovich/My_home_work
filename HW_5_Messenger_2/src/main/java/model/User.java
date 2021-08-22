package model;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String fio;
    private String birth;

    public User(){
    }

    public User(String login, String password, String fio, String birth) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.birth = birth;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "user info: " +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", birth='" + birth;
    }
}
