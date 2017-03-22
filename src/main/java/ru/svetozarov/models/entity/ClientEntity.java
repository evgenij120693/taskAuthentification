package ru.svetozarov.models.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Шмыга on 25.02.2017.
 */

@Entity
@Table(name = "client", schema = "taxi")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String sex;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String login;
    @Column
    private String password;

    @OneToMany( mappedBy = "entityClient")
    private Set<OrderEntity> orderEntities;



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

    public ClientEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
