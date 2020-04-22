package com.example.maquetadoqr.Utils;

import java.util.ArrayList;

public class SCForm {
    private ArrayList<SCField> fields = new ArrayList<>();

    public ArrayList<SCField> getFields() {
        return fields;
    }

    public void addField(SCField field) {
        fields.add(field);
    }
}
