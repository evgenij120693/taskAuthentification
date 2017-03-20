package ru.svetozarov.models.entity;

/**
 * Created by Шмыга on 01.03.2017.
 */
public class OrderEntity {
    private int id;
    private int idClient;
    private ClientEntity entityClient;
    private String dateRegistration;
    private String punktA;
    private String punktB;
    private int price;
    private int idDriver;
    private DriverEntity entityDriver;
    private String dateStart;
    private String dateEnd;
    private int idStatus;
    private StatusDriverEntity entytiStatus;



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

    public StatusDriverEntity getEntytiStatus() {
        return entytiStatus;
    }

    public void setEntytiStatus(StatusDriverEntity entytiStatus) {
        this.entytiStatus = entytiStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}
