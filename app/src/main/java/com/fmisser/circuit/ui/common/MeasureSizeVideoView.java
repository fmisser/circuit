package com.fmisser.circuit.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Date: 2016/12/23.
 * Author: fmisser
 * Description:
 */

public class MeasureSizeVideoView extends VideoView {

    public MeasureSizeVideoView(Context context) {
        super(context);
    }

    public MeasureSizeVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureSizeVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
