package com.fmisser.circuit.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2016/12/13.
 * Author: fmisser
 * Description: 能计算出实际高度的ViewPager
 */

public class WrapContentHeightViewPager extends LoopViewPager {

    public WrapContentHeightViewPager(Context context) {
        super(context);
    }

    public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i= 0; i< getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }
        int heightMeasureSpec1 = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec1);
    }
}
