package com.loner.modularization;

import java.util.HashMap;

/**
 * Created by loner on 2017/3/22.
 */

public class Provider {

    private String providerName;
    private HashMap<String, Action> actions;

    public Provider() {
        actions = new HashMap<>();
    }

    public HashMap<String, Action> getActions() {
        return actions;
    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }

    public void registerAction(Action action) {
        actions.put(action.getActionKey(), action);
    }

    public void registerActions(Action... actions) {
        for (int i = 0; i < actions.length; i++) {
            this.actions.put(actions[i].getActionKey(), actions[i]);
        }
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderName() {
        return providerName;
    }

}
