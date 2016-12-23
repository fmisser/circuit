package com.fmisser.circuit.ui.common;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Date: 2016/12/14.
 * Author: fmisser
 * Description: ViewPager 页面切换动画
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.9f;
    private ViewPager viewPager;

    public ZoomOutPageTransformer(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void transformPage(View page, float position) {

        float pos = currentCenter(page);
        if (pos < -1.0f || pos > 1.0) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(pos));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }

    //考虑padding
    private float currentCenter(View page) {
        int paddingLeft = viewPager.getPaddingLeft();
        int paddingRight = viewPager.getPaddingRight();
        int pageWidth = viewPager.getMeasuredWidth() - paddingLeft - paddingRight;
        int position = page.getLeft() - viewPager.getScrollX() - paddingLeft;
        return (float) position / pageWidth;
    }
}
