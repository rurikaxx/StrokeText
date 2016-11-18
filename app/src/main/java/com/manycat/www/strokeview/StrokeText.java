package com.manycat.www.strokeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by luodavid on 2016/11/17.
 */
public class StrokeText extends TextView {

    private TextPaint mPaint;

    private int strokeWidth = 0;

    private int strokeColor = 0;

    public StrokeText(Context context) {
        super(context);
        setStrokeDefaultStyle();
        init();
    }

    public StrokeText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStrokeStyle( context, attrs);
        init();
    }

    public StrokeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setStrokeStyle( context, attrs);
        init();
    }

    private void setStrokeStyle( Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes( attrs, R.styleable.stroketext);

        String colorString = typedArray.getString(R.styleable.stroketext_strokeColor);

        if( colorString == null ) {
            setStrokeDefaultStyle();
        }
        else {
            strokeColor = Color.parseColor(colorString);
        }
    }

    private void setStrokeDefaultStyle() {
        strokeColor = Color.parseColor("#000000");
    }

    private void init() {
        mPaint = new TextPaint();
        mPaint.setColor(strokeColor);
        mPaint.setTextSize(getTextSize());
        mPaint.setStyle( Paint.Style.STROKE);
        strokeWidth = (int)( getTextSize() / 10);
        mPaint.setStrokeWidth( strokeWidth );
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawText( getText().toString(), 0, - getPaint().getFontMetrics().top, mPaint);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth() + strokeWidth, getMeasuredHeight());
    }

}
