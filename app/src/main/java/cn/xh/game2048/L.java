package cn.xh.game2048;

import android.util.Log;

/**
 * Created by xiaohe
 * 17-08-16 016
 */
public class L {
    private static final String TAG = "xiaohe";
    private static final boolean DEBUG = false;

    public static void d(String msg){
        if(DEBUG)
            Log.d(TAG,msg);
    }

    public static void d(int msg){
        if(DEBUG)
            Log.d(TAG,msg+"");
    }

    public static void d(double msg){
        if(DEBUG)
            Log.d(TAG,msg+"");
    }

    public static void i(String msg){
        Log.i(TAG,msg+"");
    }

    public static void e(String msg){
        Log.e(TAG,msg);
    }

}
