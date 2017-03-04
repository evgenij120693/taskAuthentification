package ru.svetozarov.models.pojo;

/**
 * Created by Шмыга on 01.03.2017.
 */
public class Order {
    private int id;
    private int idClient;
    private Client entityClient;
    private String dateRegistration;
    private String punktA;
    private String punktB;
    private int price;
    private int idDriver;
    private Driver entityDriver;
    private String dateStart;
    private String dateEnd;
    private int idStatus;
    private Status entytiStatus;

    public Order(int id, int idClient, String dateRegistration, String punktA, String punktB,
                 int price, int idDriver, String dateStart, String dateEnd, int idStatus) {
        this.id = id;
        this.idClient = idClient;
        this.dateRegistration = dateRegistration;
        this.punktA = punktA;
        this.punktB = punktB;
        this.price = price;
        this.idDriver = idDriver;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.idStatus = idStatus;
    }

    public Order(int id, Client entityClient, String dateRegistration, String punktA, String punktB,
                 int price, Driver entityDriver, String dateStart, String dateEnd, Status entytiStatus) {
        this.id = id;
        this.entityClient = entityClient;
        this.dateRegistration = dateRegistration;
        this.punktA = punktA;
        this.punktB = punktB;
        this.price = price;
        this.entityDriver = entityDriver;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.entytiStatus = entytiStatus;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public Client getEntityClient() {
        return entityClient;
    }

    public void setEntityClient(Client entityClient) {
        this.entityClient = entityClient;
    }

    public Driver getEntityDriver() {
        return entityDriver;
    }

    public void setEntityDriver(Driver entityDriver) {
        this.entityDriver = entityDriver;
    }

    public Status getEntytiStatus() {
        return entytiStatus;
    }

    public void setEntytiStatus(Status entytiStatus) {
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
