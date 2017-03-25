package com.example.modularization;

/**
 * Created by loner on 2017/3/23.
 */

public class Action {

    private String ActionKey;
    private String MethodName;

    public Action(String actionKey, String methodName) {
        ActionKey = actionKey;
        MethodName = methodName;
    }

    public String getMethodName() {
        return MethodName;
    }

    public String getActionKey() {
        return ActionKey;
    }

    public void setActionKey(String actionKey) {
        ActionKey = actionKey;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }
}
