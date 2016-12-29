package com.fmisser.circuit.ui.common;

import android.view.ViewGroup;

/**
 * Date: 2016/12/13.
 * Author: fmisser
 * Description: 循环的ViewPager, 和 {@link LoopViewPager} 配合使用
 */


public abstract class LoopPagerAdapter extends RecyclePagerAdapter {

    private static final int MAX_LOOP_SIZE = 1000;

    /**
     * 返回模拟数据的数量
     * 不要返回太大的数,否则调用 {@link LoopViewPager#setCurrentItem(int)} 会引起ANR.
     */
    @Override
    public int getCount() {
        if (getItemCount() == 0) {
            return 0;
        } else {
//            return Integer.MAX_VALUE;
            return MAX_LOOP_SIZE;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //转化成数据item的position
        int itemPosition = position % getItemCount();
        return super.instantiateItem(container, itemPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int itemPosition = position % getItemCount();
        super.destroyItem(container, itemPosition, object);
    }
}
