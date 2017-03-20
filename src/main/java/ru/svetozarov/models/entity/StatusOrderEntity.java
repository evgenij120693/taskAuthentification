package ru.svetozarov.models.entity;


import javax.persistence.*;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Entity
@Table(name = "status_driver", schema = "taxi")
public class StatusOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column
    private String description;

    public StatusOrderEntity() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
