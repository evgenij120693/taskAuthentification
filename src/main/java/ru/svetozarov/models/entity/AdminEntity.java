package ru.svetozarov.models.entity;

import javax.persistence.*;

/**
 * Created by Шмыга on 24.02.2017.
 */
@Entity
@Table(name = "status_driver", schema = "taxi")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "")
    private String login;
    private String password;
    private String name;
    private String email;
    private int sendEmailFlag;

    public AdminEntity() {
    }

    public int getSendEmailFlag() {
        return sendEmailFlag;
    }

    public void setSendEmailFlag(int sendEmailFlag) {
        this.sendEmailFlag = sendEmailFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
