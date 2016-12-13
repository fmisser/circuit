package com.fmisser.circuit.ui.common;

import android.content.Context;
import android.util.TypedValue;

/**
 * Date: 2016/11/28.
 * Author: fmisser
 */

public class Utils {


    /**
     * 通过资源获取状态拦高度
     */
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * transform dp to pixels
     */
    public static float dp2px(Context context, float dpVal)
    {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * transform sp to pixels
     */
    public static float sp2px(Context context, float spVal)
    {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());

    }

}
