package models.pojo;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class Admin {
    private int id;
    private String login;
    private String password;
    private String name;
    private String email;
    private int sendEmailFlag;

    public Admin(int id, String login, String password, String name, String email, int sendEmailFlag) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.sendEmailFlag = sendEmailFlag;
    }

    public Admin() {
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
