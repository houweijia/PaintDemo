package com.jiehun.veigar.paintdemo.tucenghunhe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jiehun.veigar.paintdemo.R;

/**
 * @description:
 * @author: houwj
 * @date: 2019/3/27
 */
public class XfermodeEraserView extends View {
    private Paint  mPaint;
    private Bitmap mDstBmp, mScrBmp, mTxtBmp;
    private Path mPath;

    public XfermodeEraserView(Context context) {
        super(context);
        init();
    }

    public XfermodeEraserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeEraserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public XfermodeEraserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(80);

        //禁止硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //被遮盖层 底层
        mTxtBmp = BitmapFactory.decodeResource(getResources(), R.drawable.aiqing);


        //遮盖层
        mScrBmp = BitmapFactory.decodeResource(getResources(), R.drawable.img_love_plaza);


        mDstBmp = Bitmap.createBitmap(mScrBmp.getWidth(), mScrBmp.getHeight(), Bitmap.Config.ARGB_8888);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制刮奖结果
        canvas.drawBitmap(mTxtBmp, 0, 0, mPaint);

        //使用离屏绘制

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        //先将路径绘制到bitmap
        Canvas dstCanvas = new Canvas(mDstBmp);
        dstCanvas.drawPath(mPath, mPaint);

        //绘制目标图像
        canvas.drawBitmap(mDstBmp, 0, 0, mPaint);

        //设置 模式为SRC_OUT 擦橡皮区域为交集区域 需要显示
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        //绘制源图像
        canvas.drawBitmap(mScrBmp, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

    private float mEventX, mEventY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mEventX = event.getX();
                mEventY = event.getY();
                mPath.moveTo(mEventX,mEventY);
                break;
            case MotionEvent.ACTION_MOVE:
                //画二阶贝塞尔曲线
                float endX = (event.getX() -mEventX)/2+mEventX;
                float endY = (event.getY() -mEventY)/2+mEventY;
                mPath.quadTo(mEventX,mEventY,endX,endY);
                mEventX = event.getX();
                mEventY = event.getY();
                break;
        }
        invalidate();
        return true;
    }
}
