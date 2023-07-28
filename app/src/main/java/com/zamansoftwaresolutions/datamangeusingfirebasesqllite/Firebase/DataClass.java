package com.zamansoftwaresolutions.datamangeusingfirebasesqllite.Firebase;

public class DataClass {
    private String dataName;
    private String dataEmail;
    private String dataPhone;
    private String dataImage;
    private String dataAddress;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public DataClass() {
    }

    public DataClass(String dataName, String dataEmail, String dataPhone, String dataImage, String dataAddress) {
        this.dataName = dataName;
        this.dataEmail = dataEmail;
        this.dataPhone = dataPhone;
        this.dataImage = dataImage;
        this.dataAddress = dataAddress;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataEmail() {
        return dataEmail;
    }

    public void setDataEmail(String dataEmail) {
        this.dataEmail = dataEmail;
    }

    public String getDataPhone() {
        return dataPhone;
    }

    public void setDataPhone(String dataPhone) {
        this.dataPhone = dataPhone;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getDataAddress() {
        return dataAddress;
    }

    public void setDataAddress(String dataAddress) {
        this.dataAddress = dataAddress;
    }
}
