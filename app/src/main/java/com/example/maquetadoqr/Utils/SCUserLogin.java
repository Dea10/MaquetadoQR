package com.example.maquetadoqr.Utils;

public class SCUserLogin {
    private static SCUserLogin INSTANCE;

    private String token = "token";
    private Integer userId = 0;
    private String roleFlow = "roleFlow";
    private String userName = "userName";
    private String companyName = "companyName";

    public static SCUserLogin getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SCUserLogin();
        }
        return INSTANCE;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleFlow() {
        return roleFlow;
    }

    public void setRoleFlow(String roleFlow) {
        this.roleFlow = roleFlow;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}