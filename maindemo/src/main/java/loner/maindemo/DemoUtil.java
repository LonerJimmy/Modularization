package loner.maindemo;

import android.util.Log;

import com.loner.annotation.Action;
import com.loner.annotation.Provider;

import loner.modularization.Modularization;

/**
 * Created by loner on 2017/3/23.
 */

@Provider("demo")
public class DemoUtil {

    public DemoUtil() {
    }

    @Action("isBlank")
    public boolean isBlankOrNot(String s) {
        Log.e("loner", "Demo util is or not blank");
        return s == null || s.length() == 0;
    }

}
