package com.example.maquetadoqr.POJOs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_form_field")
public class POJOField {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "field_id")
    private Integer fieldId;
    private Boolean visible;
    private Boolean readOnly;
    private Boolean required;
    private String name;
    private String label;
    private String type;
    private Integer formId;

    public POJOField(Boolean visible, Boolean readOnly, Boolean required, String name, String label, String type, Integer formId) {
        this.visible = visible;
        this.readOnly = readOnly;
        this.required = required;
        this.name = name;
        this.label = label;
        this.type = type;
        this.formId = formId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public Boolean getRequired() {
        return required;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }
}