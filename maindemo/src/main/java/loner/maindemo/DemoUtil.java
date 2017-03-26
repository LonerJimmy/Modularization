package loner.maindemo;

import android.util.Log;

import com.loner.annotation.Action;
import com.loner.annotation.Provider;

/**
 * Created by loner on 2017/3/23.
 */

@Provider("demo")
public class DemoUtil {

    @Action("isBlank")
    public boolean isBlank(String s) {
        Log.e("loner", "Demo util is or not blank");
        return s == null || s.length() == 0;
    }

}
