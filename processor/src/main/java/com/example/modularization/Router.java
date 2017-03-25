package com.example.modularization;

import java.util.HashMap;

/**
 * Created by loner on 2017/3/23.
 */

public class Router {

    private static Router sInstance = null;
    private static HashMap<String, Provider> mRegisterProvideMap;

    public static synchronized Router getInstance() {
        if (sInstance == null) {
            sInstance = new Router();
        }
        return sInstance;
    }

    private Router() {
        mRegisterProvideMap = new HashMap<>();
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

    public static HashMap<String, Provider> getmRegisterProvideMap() {
        return mRegisterProvideMap;
    }

    public Provider getProvider(String processName) {
        return mRegisterProvideMap.get(processName);
    }
}
