package ru.svetozarov.models.entity;


import javax.persistence.*;
import java.util.Set;

/**
 * Created by Шмыга on 26.02.2017.
 */
@Entity
@Table(name = "status_order", schema = "taxi")
public class StatusOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column
    private String description;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "entytiStatus")
    private Set<OrderEntity> orderEntity;*/

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
