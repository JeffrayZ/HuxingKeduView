package com.example.kedudemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
public class HuxingKedu extends View {
    private Context context;
    private Paint paint;
    private Bitmap bitmap;

    public HuxingKedu(Context context) {
        this(context, null);
    }

    public HuxingKedu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HuxingKedu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HuxingKedu(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSize(400, widthMeasureSpec), resolveSize(400, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画刻度
        canvas.save();
        paint.reset();
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.parseColor("#3CB7EA"));
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(45);
        for (int i = 0; i < 31; i++) {
            canvas.drawLine(getWidth()/2 -60,0,getWidth()/2-50,0,paint);
            canvas.rotate(-9);
        }
        canvas.restore();

        // 画内圆弧
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(70,70,getWidth()-70,getHeight()-70,45,-270,false,paint);
        canvas.restore();

        // 绘制最小标题
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(55);
        paint.setTextSize(40f);
        canvas.drawText("15",0,getHeight()/2,paint);
        canvas.restore();

        // 绘制最大标题
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(-45);
        paint.setTextSize(40f);
        canvas.drawText("30",0,getHeight()/2,paint);
        canvas.restore();

        // 绘制带阴影的白色圆块
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(20,0,0,Color.GREEN);
        canvas.drawCircle(0,0,140,paint);
        canvas.restore();

        // 绘制动态变换的标题
        canvas.save();
        paint.setTextSize(80f);
        paint.setFakeBoldText(true);
        paint.setColor(Color.parseColor("#3CB7EA"));
        paint.clearShadowLayer();
        float stringWidth = paint.measureText(String.valueOf(currentTitle));
        float x = (getWidth() - stringWidth) / 2;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        canvas.drawText(String.valueOf(currentTitle),x,y,paint);
        canvas.restore();

        // 绘制指示器
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(currentAngle);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setShadowLayer(20,0,0,Color.GREEN);
        Path path = new Path();
        path.moveTo(-150,-20);
        path.lineTo(-190,0);
        path.lineTo(-150,20);
        path.close();
        canvas.drawPath(path,paint);
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
