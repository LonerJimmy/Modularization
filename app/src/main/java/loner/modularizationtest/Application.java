package loner.modularizationtest;

import com.loner.reflect.InitModularization;

/**
 * Created by loner on 2017/3/23.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InitModularization.init();
    }
}
