package com.example.kedudemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: KeDuDemo
 * @Package: com.example.kedudemo
 * @ClassName: HuxingKedu
 * @Description: 弧形刻度Demo
 * @Author: Jeffray
 * @CreateDate: 2020/5/19 15:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 15:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
// @see(https://github.com/superSp/RulerView)
public class ShuipingKedu extends View {
    private Context context;
    private Paint paint = new Paint();;
    private Path path = new Path();

    public ShuipingKedu(Context context) {
        this(context, null);
    }

    public ShuipingKedu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShuipingKedu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ShuipingKedu(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSize(400, widthMeasureSpec), resolveSize(200, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画尺子
        canvas.save();
        paint.reset();
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvas.restore();
    }

    private float currentAngle = -45;
    private float currentTitle = 15;

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
        this.currentTitle = (currentAngle + 45) / 18 + 15;
        invalidate();

    }
}
