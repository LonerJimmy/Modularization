package loner.modularizationtest;


import com.loner.register.demo2Modularization;
import com.loner.register.demo3Modularization;
import com.loner.register.demoModularization;
import com.loner.register.lonerModularization;

/**
 * Created by loner on 2017/3/23.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        demo2Modularization.register();
        demo3Modularization.register();
        demoModularization.register();
        lonerModularization.register();
    }
}
