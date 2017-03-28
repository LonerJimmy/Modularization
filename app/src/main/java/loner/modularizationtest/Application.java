package loner.modularizationtest;


import com.loner.Modularization;

/**
 * Created by loner on 2017/3/23.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Modularization.init();
//        demo2Modularization.register();
//        demo3Modularization.register();
//        demoModularization.register();
//        lonerModularization.register();
    }
}
