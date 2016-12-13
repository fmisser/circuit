package com.fmisser.circuit.ui.common;

import android.view.ViewGroup;

/**
 * Date: 2016/12/13.
 * Author: fmisser
 * Description: 循环的ViewPager, 和 {@link LoopViewPager} 配合使用
 */


public abstract class LoopPagerAdapter extends RecyclePagerAdapter {

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
