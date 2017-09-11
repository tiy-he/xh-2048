package cn.xh.game2048;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class GameActivity extends Activity implements View.OnClickListener{
    private PanelView panelView;
    private Button initBtn,lookColorBtn;
    private TextView maxScoreTv,scoreTv;
    private ImageView backIv,resetIv;
    private GestureDetector myGestureDetector;
    private Game2048 game2048;
    private int maxScore,lv,score;

    private SharedPreferences sharedPreferences;

    /**
     * 保存数组信息到文件
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Editor edit = sharedPreferences.edit();

        JSONArray json = new JSONArray();
        int[][] arr = game2048.getArr();
        for (int j = 0; j < arr.length;j++){
            JSONArray jsonArr = new JSONArray();
            jsonArr.put(arr[0][j]);
            jsonArr.put(arr[1][j]);
            jsonArr.put(arr[2][j]);
            jsonArr.put(arr[3][j]);
            json.put(jsonArr);
        }
        edit.putString("arr",json.toString());

        edit.putInt("score",score);
        edit.putInt("remain",game2048.getRemain());

        if(score > maxScore){
            edit.putInt("maxScore",score);
        }

        edit.apply();
        L.d("保存完成");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        initView();

        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        game2048 = new Game2048();

        Intent intent = getIntent();
        lv = intent.getIntExtra("lv",-1);
        //-1表示继续游戏
        if(lv == -1){

            int[][] arr = null;
            try {
                arr = getArrInJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            initScore();
            gameInit(arr);
            int remain = sharedPreferences.getInt("remain",-1);
            if(remain != -1)
                game2048.setRemain(remain);
        }else{
            gameInit(lv);
        }

        initMaxScore();
    }

    private void initView(){
        panelView = (PanelView) findViewById(R.id.panelView);
        initBtn = (Button) findViewById(R.id.initBtn);
        initBtn.setOnClickListener(this);

        lookColorBtn = (Button) findViewById(R.id.lookColor);
        lookColorBtn.setOnClickListener(this);

        maxScoreTv = (TextView) findViewById(R.id.max_score);
        scoreTv = (TextView) findViewById(R.id.score);

        backIv = (ImageView) findViewById(R.id.back);
        backIv.setOnClickListener(this);

        resetIv = (ImageView) findViewById(R.id.reset);
        resetIv.setOnClickListener(this);

        //自己实现的处理手势结果的类
        myGestureDetector = new GestureDetector(this,new MyOnGestureListene());

        //给view设置OnTouchListener事件
        panelView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //转发MotionEvent到实例化时设置的OnGestureListener中
                myGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private void initMaxScore(){
        maxScore = sharedPreferences.getInt("maxScore",0);
        maxScoreTv.setText(maxScore+"");
    }

    private void initScore(){
        score = sharedPreferences.getInt("score",0);
        scoreTv.setText(score + "");
    }

    /**
     * 获取文件中的数组数据
     * @return 返回一个数组
     * @throws JSONException
     */
    private int[][] getArrInJson() throws JSONException {
        String json = sharedPreferences.getString("arr",null);
        L.d("json:"+json);
        if(json == null)
            return null;
        JSONArray jsonArray = new JSONArray(json);
        int[][] arr = null;
        for(int j=0,i,n=jsonArray.length();j<n;j++){
            JSONArray jsonArray2 = jsonArray.getJSONArray(j);
            int m=jsonArray2.length();
            if(arr == null)arr = new int[n][m];
            for(i=0;i<m;i++){
                arr[i][j] = jsonArray2.getInt(i);
            }
        }
        return  arr;
    }

    private void gameInit(int[][] arr){
        if(arr == null){
            game2048.init();
            panelView.setArr(game2048.getArr());
        }else{
            game2048.init(arr);
            game2048.setScore(score);
            panelView.setArr(arr);
        }
        panelView.invalidate();
    }

    private void gameInit(int lv){
        game2048.init(lv);
        panelView.setArr(game2048.getArr());
        panelView.invalidate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.initBtn:
                gameInit(null);
                break;
            case R.id.lookColor:
                game2048.lookColor();
                panelView.invalidate();
                break;
            case R.id.back:
                L.d("back");
                finish();
                break;
            case R.id.reset:
                L.d("reset");
                gameInit(lv);
                score = 0;
                game2048.setScore(0);
                scoreTv.setText("0");
                break;
            default:
                L.i("未知的按钮");
        }
    }

    private class MyOnGestureListene extends GestureDetector.SimpleOnGestureListener {

        /**
         * 当手势结果是滑动时，会调用些方法
         *
         * @param e1        手势按下时触发的事件
         * @param e2        手势抬起时触发的事件
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            float x1 = e1.getX();
            float x2 = e2.getX();
            float y1 = e1.getY();
            float y2 = e2.getY();

            int mini_width=120;
            int mini_speed=0;

            float distance_right=x2-x1;
            float distance_left=x1-x2;
            float distance_down=y2-y1;
            float distance_up=y1-y2;

            if(Math.abs(velocityX)>mini_speed){
                int[][] arr = null;
                game2048.outArr();
                L.d("---------------------");
                if(distance_right>mini_width){
                    arr = game2048.right();
                }
                else if(distance_left>mini_width){
                    arr = game2048.left();
                }
                else if(distance_down>mini_width){
                    arr = game2048.down();
                }
                else if(distance_up>mini_width){
                    arr = game2048.up();
                }
                if(arr != null){
                    game2048.outArr();
                    panelView.setArr(arr);
                    panelView.invalidate();

                    int newScore = game2048.getScore();
                    if(newScore > score){
                        score = newScore;
                        scoreTv.setText(score+"");

                        if(score > maxScore){
                            maxScoreTv.setText(score + "");
                        }
                    }
                }

            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
