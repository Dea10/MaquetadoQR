package com.example.maquetadoqr.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity(tableName = "event_configuration")
public class EventConfiguration {

    @PrimaryKey
    @ColumnInfo(name="feature_id")
    private long id;

    @ColumnInfo(name="feature_key")
    private String featureKey;

    @ColumnInfo(name = "order")
    private int order;

    @ColumnInfo(name = "event_name")
    private String name;

    @ColumnInfo(name = "is_authorized")
    private boolean isAuthorized;

    @ColumnInfo(name = "configuration")
    private String featureConfiguration;

    public EventConfiguration(long id, String featureKey,int order, String name, boolean isAuthorized, String featureConfiguration) {
        this.id = id;
        this.featureKey =featureKey;
        this.order = order;
        this.name = name;
        this.isAuthorized = isAuthorized;
        this.featureConfiguration = featureConfiguration;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFeatureKey() {
        return featureKey;
    }

    public void setFeatureKey(String featureKey) {
        this.featureKey = featureKey;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public String getFeatureConfiguration() {
        return featureConfiguration;
    }

    public void setFeatureConfiguration(String featureConfiguration) {
        this.featureConfiguration = featureConfiguration;
    }
}
