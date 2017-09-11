package cn.xh.game2048;

import java.util.Random;

/**
 * Created by xiaohe
 * 17-08-29 029
 *
 * 2048核心
 *
 * 2048的所有运算都依靠此类
 */
public class Game2048 {
    private static final int LINE = 4;

    private int[][] gameArr;
    private int remain;//剩余空间
    private Random random;
    private int score;

    public void init(){
        initDefault();
        addItem();
    }

    public void init(int lv){
        initDefault();
        int num = 0;
        switch (lv){
            case 0:
                num = 0;
                break;
            case 1:
                num = 2048;
                break;
            case 2:
                num = 4096;
                break;
        }
        gameArr[LINE-1][LINE-1] = num;
        remain -= 1;
        addItem();
    }

    public void init(int[][] gameArr){
        initDefault();
        this.gameArr = gameArr;
    }

    private void initDefault(){
        gameArr = new int[LINE][LINE];
        remain = LINE * LINE;
        random = new Random();
    }

    public void lookColor(){
        gameArr[0][1] = 2;
        gameArr[0][2] = 4;
        gameArr[0][3] = 8;
        gameArr[1][0] = 16;
        gameArr[1][1] = 32;
        gameArr[1][2] = 64;
        gameArr[1][3] = 128;
        gameArr[2][0] = 256;
        gameArr[2][1] = 512;
        gameArr[2][2] = 1024;
        gameArr[2][3] = 2048;
        gameArr[3][0] = 4096;
        gameArr[3][1] = 8192;
        gameArr[3][2] = 0;
        gameArr[3][3] = 0;
    }

    public void outArr(){
        for(int j=0;j<LINE;j++){
            String str = gameArr[0][j] + "," +
                    gameArr[1][j] + "," +
                    gameArr[2][j] + "," +
                    gameArr[3][j];
            L.d(str);
        }
    }

    private int getNum(){
        return (random.nextInt(2)+1) * 2;
    }

    private int getPosition(int remain){
        return random.nextInt(remain);
    }

    private void addItem(){
        if(remain == 0){
            L.d("没有空间");
            return;
        }
        int pos = getPosition(remain);
        L.d("item position is "+pos);
        for(int j=0,i;j<LINE;j++){
            for(i=0;i<LINE;i++){
                if(gameArr[i][j] == 0 && pos-- == 0){
                    int num = getNum();
                    gameArr[i][j] = num;
                    remain--;
                    L.d("num is "+num+",remain is "+remain);
                    return;
                }
            }
        }
        L.e("未知");
    }

    /**
     * 传入一个一维数组，将其2048运算，将更改后的值放入原数组。
     * 返回数组元素是否被改变，返回true表示被改变
     *
     * @param arr 待运算的一维数组
     * @return 数组是否被更改，返回true表示更改
     */
    public boolean count(int[] arr){
        int i,j;
        int index = 0;//该值表示当前准备被赋值的下标
        boolean isChange = false;
        for(i=0;i<LINE;i++){
            //若元素为0，则跳出
            if(arr[i] == 0)continue;

            //当不为0元素为最后一个时，不需要进行第2层
            if(i == LINE-1){
                //下标和i相同时，不需要交换数据
                if(index != i){
                    arr[index] = arr[i];
                    arr[i] = 0;
                    isChange = true;
                }
                break;
            }
            for(j=i+1;j<LINE;j++){
                if(arr[j] == 0){
                    if(j == LINE-1){
                        if(index != i){
                            arr[index] = arr[i];
                            arr[i] = 0;
                            isChange = true;
                        }
                        break;
                    }
                    continue;
                }
                if(arr[i] == arr[j]){
                    arr[index] = arr[i] * 2;
                    score += arr[index];//计分
                    if(index++ != i){
                        arr[i] = 0;
                    }
                    arr[j] = 0;
                    remain++;
                    isChange = true;
                }else{
                    if(index != i){
                        arr[index] = arr[i];
                        arr[i] = 0;
                        i = j-1;
                        isChange = true;
                    }
                    index++;
                }
                break;
            }
        }
        return isChange;
    }

    public int[][] up(){
        L.d("up");
        int[] arr;
        int change = 0;
        for(int i=0;i<LINE;i++){
            arr = gameArr[i];
            if(count(arr))change++;
            gameArr[i] = arr;
        }
        if (change != 0)addItem();
        return gameArr;
    }
    public int[][] down(){
        L.d("down");
        int[] arr = new int[LINE];
        int change = 0;
        for(int i=0;i<LINE;i++){
            arr[3] = gameArr[i][0];
            arr[2] = gameArr[i][1];
            arr[1] = gameArr[i][2];
            arr[0] = gameArr[i][3];
            if(count(arr))change++;
            gameArr[i][0] = arr[3];
            gameArr[i][1] = arr[2];
            gameArr[i][2] = arr[1];
            gameArr[i][3] = arr[0];
        }
        if (change != 0)addItem();
        return gameArr;
    }
    public int[][] left(){
        L.d("left");
        int[] arr = new int[LINE];
        int change = 0;
        for(int j=0;j<LINE;j++){
            arr[0] = gameArr[0][j];
            arr[1] = gameArr[1][j];
            arr[2] = gameArr[2][j];
            arr[3] = gameArr[3][j];
            if(count(arr))change++;
            gameArr[0][j] = arr[0];
            gameArr[1][j] = arr[1];
            gameArr[2][j] = arr[2];
            gameArr[3][j] = arr[3];
        }
        if (change != 0)addItem();
        return gameArr;
    }
    public int[][] right(){
        L.d("right");
        int[] arr = new int[LINE];
        int change = 0;
        for(int j=0;j<LINE;j++){
            arr[0] = gameArr[3][j];
            arr[1] = gameArr[2][j];
            arr[2] = gameArr[1][j];
            arr[3] = gameArr[0][j];
            if(count(arr))change++;
            gameArr[0][j] = arr[3];
            gameArr[1][j] = arr[2];
            gameArr[2][j] = arr[1];
            gameArr[3][j] = arr[0];
            L.d(arr[3]+","+arr[2]+","+arr[1]+","+arr[0]);
        }
        if (change != 0)addItem();
        return gameArr;
    }

    public int[][] getArr() {
        return gameArr;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}
