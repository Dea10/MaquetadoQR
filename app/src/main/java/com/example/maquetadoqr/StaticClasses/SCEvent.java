package com.example.maquetadoqr.StaticClasses;

import java.util.List;

public class SCEvent {
    private Integer featureId;
    private Integer order;
    private String featureKey;
    private String resourceName;
    private Boolean isAuthorized;
    private SCForm form;
    private SCChecklist checklist;
    private SCJourneyTravel journeyTravel;

    public SCEvent(Integer featureId, Integer order, String featureKey, String resourceName, Boolean isAuthorized, SCForm form, SCChecklist checklist, SCJourneyTravel journeyTravel) {
        this.featureId = featureId;
        this.order = order;
        this.featureKey = featureKey;
        this.resourceName = resourceName;
        this.isAuthorized = isAuthorized;
        this.form = form;
        this.checklist = checklist;
        this.journeyTravel = journeyTravel;
    }
}
