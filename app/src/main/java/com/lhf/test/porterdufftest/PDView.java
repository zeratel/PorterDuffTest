package com.lhf.test.porterdufftest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * com.lhf.test.porterdufftest
 * Created by zeratel3000
 * on 2016 01 16/1/7 19 04
 * description
 */
public class PDView extends View {

    Paint mPaint = new Paint();
    private int width;
    private int height;

    public PDView(Context context) {
        super(context);
    }

    public PDView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PDView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {


            super.onDraw(canvas);
            canvas.drawColor(Color.TRANSPARENT);//设画布绿色
/*
* 离屏缓存
* Layer层的宽和高要设定好，不然会出现有些部位不再层里面，你的操作是不对这些部位起作用的
* 不设置最后不能还原画布，导致最后不是透明的
*/
            int sc = canvas.saveLayer(0,0,width,height, null, Canvas.ALL_SAVE_FLAG);
// 先绘制dis目标图
            mPaint.setColor(0xcccccccc);
            canvas.drawRect(0,0,width,height,mPaint);
// 设置混合模式 （只在源图像和目标图像相交的地方绘制目标图像）
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
// 再绘制src源图
            canvas.drawRect(300,300,800,800,mPaint);
// 还原混合模式
            mPaint.setXfermode(null);
// 还原画布
            canvas.restoreToCount(sc);

    }

}
