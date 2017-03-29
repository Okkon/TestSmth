package com.company.fxapp.events;

import com.company.fxapp.core.AbstractEvent;
import com.company.fxapp.core.GAction;
import com.company.fxapp.core.GameCore;

public class ActionSelectionEvent extends AbstractEvent {

    private GAction action;

    public ActionSelectionEvent(GAction action) {
        this.action = action;
    }

    @Override
    protected void perform() {
        GameCore.getInstance().setSelectedAction(action);
        action.onSelect();
    }

    public GAction getAction() {
        return action;
    }
}
