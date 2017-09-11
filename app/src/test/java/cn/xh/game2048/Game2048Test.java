package cn.xh.game2048;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaohe
 * 17-08-30 030
 */
public class Game2048Test {

    private Game2048 game2048 = new Game2048();

    @Test
    public void countTest() {
//        List<int[]> arrList = new ArrayList<>();

        int[] numArr = new int[]{0,2,4};
        int x = 3;
        int times = 0;
        for(int i=0;i<x;i++){
            for(int j=0;j<x;j++){
                for(int n=0;n<x;n++){
                    for(int m=0;m<x;m++){
                        String str = String.valueOf(numArr[i]) + "," +
                                numArr[j] + "," +
                                numArr[n] + "," +
                                numArr[m];
                        System.out.println(str);
                        times++;
                    }
                }
            }
        }
        System.out.println(times);

        /*int[] arr, resultArr;
        arr = resultArr = new int[]{2, 0, 0, 0};
        game2048.count(arr);
        assertArrayEquals(arr, resultArr);

        arr = new int[]{};*/
    }

    @Test
    public void countTest2(){
        int[] arr = new int[]{0,0,0,0};
        int[] result = new int[]{0,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,0,0,2};
        result = new int[]{2,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,0,2,0};
        result = new int[]{2,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,0,2,2};
        result = new int[]{4,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,0,2,4};
        result = new int[]{2,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,0,2};
        result = new int[]{4,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,0,4};
        result = new int[]{2,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,2,0};
        result = new int[]{4,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,2,2};
        result = new int[]{4,2,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,2,4};
        result = new int[]{4,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,4,0};
        result = new int[]{2,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,4,2};
        result = new int[]{2,4,2,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,2,4,4};
        result = new int[]{2,8,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,4,2,2};
        result = new int[]{4,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{0,4,4,2};
        result = new int[]{8,2,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,0,0,0};
        result = new int[]{2,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,0,0,2};
        result = new int[]{4,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,0,0,4};
        result = new int[]{2,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,0,2,0};
        result = new int[]{4,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,0,2,2};
        result = new int[]{4,2,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,0,2,4};
        result = new int[]{4,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,0,4,4};
        result = new int[]{2,8,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,2,0,0};
        result = new int[]{4,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,2,2,0};
        result = new int[]{4,2,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,2,2,2};
        result = new int[]{4,4,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{2,4,2,4};
        result = new int[]{2,4,2,4};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{16,16,2,0};
        result = new int[]{32,2,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{64,0,0,0};
        result = new int[]{64,0,0,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);

        arr = new int[]{4,8,0,4};
        result = new int[]{4,8,4,0};
        game2048.count(arr);
        Assert.assertArrayEquals(arr,result);
    }
}

