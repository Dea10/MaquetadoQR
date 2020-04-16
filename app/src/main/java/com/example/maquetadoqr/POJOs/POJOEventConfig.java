package com.example.maquetadoqr.POJOs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event")
public class POJOEventConfig {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    private Integer featureId;
    private Integer order;
    private String featureKey;
    private String resourceName;
    private Boolean isAuthorized;

    public POJOEventConfig(Integer featureId, Integer order, String featureKey, String resourceName, Boolean isAuthorized) {
        this.featureId = featureId;
        this.order = order;
        this.featureKey = featureKey;
        this.resourceName = resourceName;
        this.isAuthorized = isAuthorized;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public Integer getOrder() {
        return order;
    }

    public String getFeatureKey() {
        return featureKey;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Boolean getAuthorized() {
        return isAuthorized;
    }
}

