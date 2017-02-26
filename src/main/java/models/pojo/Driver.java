package models.pojo;

/**
 * Created by Шмыга on 25.02.2017.
 */
public class Driver {
    private int id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String login;
    private String password;
    private int rating;
    private int auto;
    private int status;
    private Auto entryAuto;
    private Status entryStatus;

    public Driver(int id, String lastName, String firstName,
                  String phoneNumber, String login, String password, int rating, int auto, int status) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.rating = rating;
        this.auto = auto;
        this.status = status;
    }

    public Driver(int id, String lastName, String firstName, String phoneNumber, String login, String password, int rating, Auto entryAuto, Status entryStatus) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.rating = rating;
        this.entryAuto = entryAuto;
        this.entryStatus = entryStatus;
    }

    public Auto getEntryAuto() {
        return entryAuto;
    }

    public void setEntryAuto(Auto entryAuto) {
        this.entryAuto = entryAuto;
    }

    public Status getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Status entryStatus) {
        this.entryStatus = entryStatus;
    }

    public Driver() {
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

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
