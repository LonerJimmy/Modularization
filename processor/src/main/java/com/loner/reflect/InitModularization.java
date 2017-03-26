package com.loner.reflect;

import com.loner.modularization.Action;
import com.loner.modularization.Router;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loner on 2017/3/26.
 */

public class InitModularization {

    public static void init() {

        try {
            Class<?> threadClazz = Class.forName("com.loner.Modularization");
            Method[] methods = threadClazz.getDeclaredMethods();
            List<String> names = new ArrayList<>();
            for (int i = 0; i < methods.length; i++) {
                names.add(methods[i].getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object excute(String provider, String action, Class... var2) {

        try {
            Action action1 = Router.getInstance().getmRegisterProvideMap().get(provider).getAction(action);

            Class<?> threadClazz = Class.forName(action1.getPackageClassName());
            Method method = threadClazz.getDeclaredMethod(action1.getMethodName(), var2);
            Object b = (Object) method.invoke(threadClazz.newInstance(), "abewr");
            return b;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
