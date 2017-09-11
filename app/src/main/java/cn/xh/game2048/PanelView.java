package cn.xh.game2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaohe
 * 17-08-28 028
 *
 * 自定义view
 * 用于显示2048的图片和文字等信息
 */
public class PanelView extends View{

    private static final int LINE = 4;
    private float margin;
    private float lineHeight;
    private float lineHeightHalf;
    private float itemHeight;
    private int[][] arr = null;
    private Paint imgPaint;
    private Paint textPaint;

    public PanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.rgb(132,132,132));
        initImgPaint();
        initTextPaint();
    }

    private void initTextPaint() {
        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.rgb(0,0,0));
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initImgPaint() {
        imgPaint = new Paint();
        imgPaint.setColor(Color.rgb(210,210,210));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize,heightSize);

        if(widthMode == MeasureSpec.UNSPECIFIED){
            width = heightSize;
        }else if(heightMode == MeasureSpec.UNSPECIFIED){
            width = widthSize;
        }
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        margin = w * 0.05f;
        lineHeight = w * 0.9f / LINE;
        lineHeightHalf = lineHeight/2;
        itemHeight = lineHeight * 0.9f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(arr == null) return;

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                int num = arr[i][j];
                imgPaint.setColor(ColorHelper.get(num));

                float x = i*lineHeight+margin;
                float y = j*lineHeight+margin;
                float rx = x + lineHeight * 0.05f;
                float ry = y + lineHeight * 0.05f;
//                canvas.drawRect(rx,ry,rx+itemHeight,ry+itemHeight,imgPaint);
                canvas.drawRoundRect(new RectF(rx,ry,rx+itemHeight,ry+itemHeight),8,8,imgPaint);

                if(num != 0){
//                    textPaint.setColor(num == 2 || num == 4 ? Color.BLACK : Color.GRAY);
//                    float tx = x - lineHeightHalf;
//                    float ty = y - lineHeightHalf;
//                    canvas.drawText(num+"",tx,ty,tx+lineHeight,ty+lineHeight,textPaint);
                    canvas.drawText(num+"",x+lineHeightHalf,y+lineHeightHalf * 1.2f,textPaint);

                }
            }
        }
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }
}
