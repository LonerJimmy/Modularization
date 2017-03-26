package com.loner.modularization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loner on 2017/3/23.
 */

public class Router {

    private static Router sInstance = null;
    private static List<String> aptPackages;
    private static HashMap<String, Provider> mRegisterProvideMap;

    public static synchronized Router getInstance() {
        if (sInstance == null) {
            sInstance = new Router();
        }
        return sInstance;
    }

    private Router() {
        mRegisterProvideMap = new HashMap<>();
        aptPackages = new ArrayList<>();
    }

    /**
     * 注册provider
     *
     * @param provider
     */
    public void registerProvider(Provider provider) {
        if (mRegisterProvideMap == null) {
            mRegisterProvideMap = new HashMap<>();
        }

        Provider tmpProvider = mRegisterProvideMap.get(provider.getProviderName());

        if (tmpProvider != null) {
            throw new RuntimeException(provider.getProviderName() + " has registered.");
        }

        mRegisterProvideMap.put(provider.getProviderName(), provider);
    }

    public void addPackageName(String i) {
        aptPackages.add(i);
    }

    public void clearPackageName() {
        aptPackages.clear();
    }

    public List<String> getAptPackages() {
        return aptPackages;
    }

    public HashMap<String, Provider> getmRegisterProvideMap() {
        return mRegisterProvideMap;
    }

    public Provider getProvider(String processName) {
        return mRegisterProvideMap.get(processName);
    }
}
