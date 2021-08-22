package model;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String fio;
    private String birth;
    private String dateOfRegistration;

    public User(){
    }

    public User(String login, String password, String fio, String birth, String dateOfRegistration) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.birth = birth;
        this.dateOfRegistration = dateOfRegistration;
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

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String toString() {
        return "Информация о пользователе:{" +
                "логин='" + login + '\'' +
                ", пароль='" + password + '\'' +
                ", ФИО='" + fio + '\'' +
                ", Дата рождения='" + birth + '\'' +
                ", Дата регитсрации='" + dateOfRegistration + '\'' +
                '}';
    }
}
