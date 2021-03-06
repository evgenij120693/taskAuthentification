package ru.svetozarov.models.entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by Шмыга on 01.03.2017.
 */
@Entity(name="OrderEntity")

@Table(name = "order", schema = "taxi", catalog = "taxi")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private ClientEntity entityClient;


    @Column(name = "date_registration")
    private String dateRegistration;
    @Column(name = "punkt_a")
    private String punktA;
    @Column(name = "punkt_b")
    private String punktB;
    @Column(name = "price")
    private int price;



    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "id_driver", referencedColumnName = "id", nullable=true)
    @NotFound(action = NotFoundAction.IGNORE)
    private DriverEntity entityDriver ;
    @Column(name = "start_date")
    private String dateStart;
    @Column(name = "end_date")
    private String dateEnd;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusOrderEntity entytiStatus;



    public OrderEntity() {
    }

    public int getId() {
        return id;
    }

    public ClientEntity getEntityClient() {
        return entityClient;
    }

    public void setEntityClient(ClientEntity entityClient) {
        this.entityClient = entityClient;
    }

    public DriverEntity getEntityDriver() {
        return entityDriver;
    }

    public void setEntityDriver(DriverEntity entityDriver) {
        this.entityDriver = entityDriver;
    }

    public StatusOrderEntity getEntytiStatus() {
        return entytiStatus;
    }

    public void setEntytiStatus(StatusOrderEntity entytiStatus) {
        this.entytiStatus = entytiStatus;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPunktA() {
        return punktA;
    }

    public void setPunktA(String punktA) {
        this.punktA = punktA;
    }

    public String getPunktB() {
        return punktB;
    }

    public void setPunktB(String punktB) {
        this.punktB = punktB;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }


}
