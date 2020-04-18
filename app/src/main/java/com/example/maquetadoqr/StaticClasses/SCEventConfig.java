package com.example.maquetadoqr.StaticClasses;

import java.util.ArrayList;
import java.util.List;

public class SCEventConfig {
    private ArrayList<SCEvent> events = new ArrayList<>();

    private static SCEventConfig INSTANCE;

    public static SCEventConfig getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SCEventConfig();
        }
        return INSTANCE;
    }

    public void addEvent(SCEvent event) {
        events.add(event);
    }
}
