package com.example.maquetadoqr.Utils;

public class SCDriver {
    private String picture;
    private String driverName;
    private String fiscalCode;
    private String companyName;
    private String phoneNumber;
    private Integer disctum;
    private Integer driverId;
    private Integer translineId;
    private Boolean usrMovil;

    private static SCDriver INSTANCE;

    public static SCDriver getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SCDriver();
        }
        return INSTANCE;
    }

    public String getPicture() {
        return picture;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getDisctum() {
        return disctum;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public Integer getTranslineId() {
        return translineId;
    }

    public Boolean getUsrMovil() {
        return usrMovil;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDisctum(Integer disctum) {
        this.disctum = disctum;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public void setTranslineId(Integer translineId) {
        this.translineId = translineId;
    }

    public void setUsrMovil(Boolean usrMovil) {
        this.usrMovil = usrMovil;
    }
}
