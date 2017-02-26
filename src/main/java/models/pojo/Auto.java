package models.pojo;

/**
 * Created by Шмыга on 26.02.2017.
 */
public class Auto {
    private int id;
    private String marka;
    private String model;
    private String regNumber;

    private String color;

    public Auto(int id, String marka, String model, String regNumber, String color) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.regNumber = regNumber;
        this.color = color;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public Auto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
