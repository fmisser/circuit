package com.fmisser.circuit.ui.common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Date: 2016/12/13.
 * Author: fmisser
 * Description: 支持循环的ViewPager, 和 {@link LoopPagerAdapter} 配合使用
 */


public class LoopViewPager extends ViewPager {

    public LoopViewPager(Context context) {
        super(context);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {

        //当使用LoopViewPager时,将数据item对应position转化成无限循环的position
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter instanceof LoopPagerAdapter) {
            int position = super.getCurrentItem() - super.getCurrentItem() % ((LoopPagerAdapter) pagerAdapter).getItemCount() + item;
            super.setCurrentItem(position);
        } else {
            super.setCurrentItem(item);
        }
    }

    @Override
    public int getCurrentItem() {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter instanceof LoopPagerAdapter) {
            return super.getCurrentItem() % ((LoopPagerAdapter) pagerAdapter).getItemCount();
        } else {
            return super.getCurrentItem();
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        //当使用LoopViewPager时,初始化无限循环的position为中间
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter instanceof LoopPagerAdapter) {
            int midd = pagerAdapter.getCount() / 2;
            super.setCurrentItem(midd - midd % ((LoopPagerAdapter) pagerAdapter).getItemCount());
        }
    }
}
