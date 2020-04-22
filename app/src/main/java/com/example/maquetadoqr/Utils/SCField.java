package com.example.maquetadoqr.Utils;

public class SCField {
    private Boolean visible;
    private Boolean readOnly;
    private Boolean required;
    private String name;
    private String label;
    private String type;

    public SCField(Boolean visible, Boolean readOnly, Boolean required, String name, String label, String type) {
        this.visible = visible;
        this.readOnly = readOnly;
        this.required = required;
        this.name = name;
        this.label = label;
        this.type = type;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
