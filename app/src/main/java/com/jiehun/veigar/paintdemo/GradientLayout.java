package com.jiehun.veigar.paintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @description:
 * @author: houwj
 * @date: 2019/3/25
 */
public class GradientLayout extends View {
    private Paint mPaint;
    public GradientLayout(Context context) {
        super(context);
    }

    public GradientLayout(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GradientLayout(Context context,@Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setARGB(255,255,255,0);
        mPaint.setAlpha(200);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);//描边效果      FILL充满  STROKE边框 FILL_AND_STROKE
        mPaint.setStrokeWidth(4);//描边边距
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆角效果
        mPaint.setStrokeJoin(Paint.Join.MITER);//拐角风格

    }
}
