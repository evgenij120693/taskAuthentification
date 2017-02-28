package models.pojo;

/**
 * Created by Шмыга on 01.03.2017.
 */
public class Order {
    private int id;
    private int idClient;
    private String punktA;
    private String punktB;
    private int price;
    private String dateRegistration;
    private String dateStart;
    private String dateEnd;
    private int idDriver;
    private int idStatus;

    public Order(int id, int idClient, String punktA, String punktB,
                 int price, String dateRegistration,
                 String dateStart, String dateEnd, int idDriver, int idStatus) {
        this.id = id;
        this.idClient = idClient;
        this.punktA = punktA;
        this.punktB = punktB;
        this.price = price;
        this.dateRegistration = dateRegistration;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.idDriver = idDriver;
        this.idStatus = idStatus;
    }

    public Order() {
    }

    public int getId() {
        return id;
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
