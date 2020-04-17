package com.example.maquetadoqr.POJOs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_form")
public class POJOForm {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "form_id")
    private Integer formId;

    @ColumnInfo(name = "event_config_id")
    private Integer eventConfigId;

    public POJOForm(Integer eventConfigId) {
        this.formId = formId;
        this.eventConfigId = eventConfigId;
    }

    public Integer getFormId() {
        return formId;
    }

    public Integer getEventConfigId() {
        return eventConfigId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public void setEventConfigId(Integer eventConfigId) {
        this.eventConfigId = eventConfigId;
    }
}