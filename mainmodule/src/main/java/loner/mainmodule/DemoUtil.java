package loner.mainmodule;

import android.util.Log;

import com.loner.annotation.Action;
import com.loner.annotation.Provider;

/**
 * Created by loner on 2017/3/23.
 */

@Provider("loner")
public class DemoUtil {

    @Action("isBlank")
    public String isBlank(String s) {
        Log.e("loner", "Demo util is or not blank");
        return s+"loner maindemo hello";
    }

}
