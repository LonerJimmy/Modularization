package loner.maindemo.demo;

import android.util.Log;

import com.loner.annotation.Action;
import com.loner.annotation.Provider;

import loner.modularization.Modularization;

/**
 * Created by loner on 2017/3/26.
 */

@Provider("demo2")
public class DemoUtil {

    public DemoUtil() {
    }

    @Action("isBlank2")
    public String isBlank2(String s) {
        Log.e("loner", "Demo util is or not blank");
        return s + "pp";
    }

}
