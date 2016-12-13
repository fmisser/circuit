package com.fmisser.circuit.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.fmisser.circuit.R;

/**
 * Date: 2016/11/28.
 * Author: fmisser
 * Description: 带有小红点的TextView
 */

public class RedDotTextView extends TextView {

    public static final int RED_DOT_VISIBLE = 0;
    public static final int RED_DOT_INVISIBLE = 1;
    public static final int DEFAULT_RED_DOT_RADIUS = 3;

    //默认为显示
    private int redDotVisibility;
    //默认大小(px)
    private float redDotRadius;

    public RedDotTextView(Context context) {
        super(context);
        initAttrs(null);
    }

    public RedDotTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public RedDotTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    protected void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RedDotTextView);
            redDotVisibility = typedArray.getInt(R.styleable.RedDotTextView_redDotVisibility, RED_DOT_VISIBLE);
            redDotRadius = typedArray.getDimension(R.styleable.RedDotTextView_redDotRadius, Utils.dp2px(getContext(), DEFAULT_RED_DOT_RADIUS));
            typedArray.recycle();
        } else {
            redDotVisibility = RED_DOT_VISIBLE;
            redDotRadius = Utils.dp2px(getContext(), DEFAULT_RED_DOT_RADIUS);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (redDotVisibility == RED_DOT_VISIBLE) {
            int width = getWidth();
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(width - redDotRadius, redDotRadius, redDotRadius, paint);
        }
    }

    public int getRedDotVisibility() {
        return redDotVisibility;
    }

    public void setRedDotVisibility(int redDotVisibility) {
        this.redDotVisibility = redDotVisibility;
        invalidate();
    }

    /**
     * @return 红点半径(px)
     */
    public float getRedDotRadius() {
        return redDotRadius;
    }

    /**
     * 设置红点大小
     * @param redDotRadius 红点半径(px)
     */
    public void setRedDotRadius(float redDotRadius) {
        this.redDotRadius = redDotRadius;
        invalidate();
    }

    /**
     * 设置红点大小
     * @param redDotRadiusWithDp 红点半径(dp)
     */
    public void setRedDotRadiusWithDp(float redDotRadiusWithDp) {
        this.redDotRadius = Utils.dp2px(getContext(), DEFAULT_RED_DOT_RADIUS);
        invalidate();
    }
}
