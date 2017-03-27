package loner.modularization;

import android.app.Activity;

import com.loner.modularization.Action;
import com.loner.modularization.Router;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by loner on 2017/3/26.
 */

public class Modularization {

    public static void init(Activity activity) {
        init(activity.getClass());
    }

    public static void init(Class c) {
        init(c.getSimpleName());
    }

    public static void init(String provider) {
        try {
            Class injectorClazz = Class.forName("com.loner.register." + provider + "Modularization");
            Method method = injectorClazz.getDeclaredMethod("register");
            method.invoke(injectorClazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
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
