package com.example.maquetadoqr.Utils;

import java.util.ArrayList;

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
