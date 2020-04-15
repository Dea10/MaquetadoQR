package com.example.maquetadoqr.POJOs;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_login_table")
public class POJOUserLogin {

    @PrimaryKey
    @NonNull
    private String token;
    private Integer userId;
    private String roleFlow;
    private String userName;
    private String companyName;

    public POJOUserLogin(String token, Integer userId, String roleFlow, String userName, String companyName) {
        this.token = token;
        this.userId = userId;
        this.roleFlow = roleFlow;
        this.userName = userName;
        this.companyName = companyName;
    }

    public String getToken() {
        return token;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getRoleFlow() {
        return roleFlow;
    }

    public String getUserName() {
        return userName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
