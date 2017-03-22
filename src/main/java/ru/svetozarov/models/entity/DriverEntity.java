package ru.svetozarov.models.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Шмыга on 25.02.2017.
 */
@Entity
@Table(name = "driver", schema = "taxi")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "rating")
    private int rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_auto", referencedColumnName = "id")
    private AutoEntity entryAuto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusDriverEntity entryStatus;

    @OneToMany( mappedBy = "entityDriver")
    private Set<OrderEntity> orderEntities;


    public AutoEntity getEntryAuto() {
        return entryAuto;
    }

    public void setEntryAuto(AutoEntity entryAuto) {
        this.entryAuto = entryAuto;
    }

    public StatusDriverEntity getEntryStatus() {
        return entryStatus;
    }


    public void setEntryStatus(StatusDriverEntity entryStatus) {
        this.entryStatus = entryStatus;
    }

    public DriverEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

