package ru.svetozarov.models.pojo;

/**
 * Created by Шмыга on 27.02.2017.
 */
public class Setting {
    private int sendMessageFlag;

    public Setting(int sendMessageFlag) {
        this.sendMessageFlag = sendMessageFlag;
    }

    public int getSendMessageFlag() {
        return sendMessageFlag;
    }

    public void setSendMessageFlag(int sendMessageFlag) {
        this.sendMessageFlag = sendMessageFlag;
    }
}
