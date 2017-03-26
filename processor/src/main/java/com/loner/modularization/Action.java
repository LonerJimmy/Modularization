package com.loner.modularization;

/**
 * Created by loner on 2017/3/23.
 */

public class Action {

    private String ActionKey;
    private String MethodName;
    private String packageClassName;

    public Action(String actionKey, String methodName,String packageName) {
        ActionKey = actionKey;
        MethodName = methodName;
        packageClassName=packageName;
    }

    public String getPackageClassName() {
        return packageClassName;
    }

    public String getMethodName() {
        return MethodName;
    }

    public String getActionKey() {
        return ActionKey;
    }

}
