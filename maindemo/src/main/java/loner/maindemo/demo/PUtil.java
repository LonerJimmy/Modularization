package loner.maindemo.demo;

import android.util.Log;

import com.loner.annotation.Action;
import com.loner.annotation.Provider;

import loner.modularization.Modularization;

/**
 * Created by loner on 2017/3/26.
 */

@Provider("demo3")
public class PUtil {

    public PUtil() {
    }

    @Action("isBlank3")
    public void isBlank3(String s) {
        Log.e("loner", "Demo util is or not blank");
    }

}
