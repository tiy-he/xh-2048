package cn.xh.game2048;

import android.graphics.Color;

/**
 * Created by xiaohe
 * 17-08-31 031
 *
 * 2048颜色提供
 */
public class ColorHelper {

    public static int get(int num){
        switch (num){
            case 0:
                return Color.rgb(167,167,167);
            case 2:
                return Color.rgb(210,210,210);
            case 4:
                return Color.rgb(210,210,210);
            case 8:
                return Color.rgb(50,50,255);
            case 16:
                return Color.rgb(100,255,150);
            case 32:
                return Color.rgb(255,0,255);
            case 64:
                return Color.rgb(255,125,0);
            case 128:
                return Color.rgb(200,255,100);
            case 256:
                return Color.rgb(100,255,255);
            case 512:
                return Color.rgb(255,255,0);
            case 1024:
                return Color.rgb(0,0,255);
            case 2048:
                return Color.rgb(61,235,61);
            case 4096:
                return Color.rgb(255,177,210);
            case 8192:
                return Color.rgb(255,70,70);

        }
        return -1;
    }

}
