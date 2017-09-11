package cn.xh.game2048;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiaohe
 * 17-09-03 003
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button goOn,newGame,about;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        goOn = (Button) findViewById(R.id.goOn);
        newGame = (Button) findViewById(R.id.newGame);
        about = (Button) findViewById(R.id.about);

        goOn.setOnClickListener(this);
        newGame.setOnClickListener(this);
        about.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goOn:
                Intent intent = new Intent(this,GameActivity.class);
                intent.putExtra("lv",-1);
                startActivity(intent);
                break;
            case R.id.newGame:
                showNewGameSelect();
                break;
            case R.id.about:
                showAbout();
                break;
            default:
                L.e("未知的按钮");
        }
    }

    private void showAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layout = layoutInflater.inflate(R.layout.about,null);
        builder.setView(layout);
        builder.setTitle("关于");
        builder.show();
    }

    private void showNewGameSelect() {
        String[] items = new String[]{"2048","4096","8192"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra("lv",which);
                startActivity(intent);
            }
        });
        builder.setTitle("请选择你要挑战的");
        builder.show();
    }
}
