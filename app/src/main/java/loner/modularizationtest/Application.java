package loner.modularizationtest;

/**
 * Created by loner on 2017/3/23.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        com.example.modularization.Provider provider1 = new com.example.modularization.Provider();
        provider1.setPackageName("loner");
        provider1.setProviderName("zjm");

        provider1.registerAction(new com.example.modularization.Action("pp","getName"));
        provider1.registerAction(new com.example.modularization.Action("qq","getQQ"));
        provider1.registerAction(new com.example.modularization.Action("qq","getQQ"));

        com.example.modularization.Router.getInstance().registerProvider(provider1);

        com.example.modularization.Provider provider2 = new com.example.modularization.Provider();
        provider1.setPackageName("loner");
        provider1.setProviderName("zjm");

        provider1.registerAction(new com.example.modularization.Action("qq","getQQ"));
        provider1.registerAction(new com.example.modularization.Action("qq","getQQ"));
        provider1.registerAction(new com.example.modularization.Action("qq","getQQ"));

        com.example.modularization.Router.getInstance().registerProvider(provider2);


    }
}
