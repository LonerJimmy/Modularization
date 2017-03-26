package loner.maindemo.demo;

import android.util.Log;

import com.loner.annotation.Action;
import com.loner.annotation.Provider;

/**
 * Created by loner on 2017/3/26.
 */

@Provider("demo3")
public class PUtil {

    @Action("isBlank3")
    public String isBlank3(String s) {
        Log.e("loner", "Demo util is or not blank");
        return s + " hello loner";
    }

}
