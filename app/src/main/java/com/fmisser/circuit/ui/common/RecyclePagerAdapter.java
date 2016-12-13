package com.fmisser.circuit.ui.common;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Date: 2016/12/7.
 * Author: fmisser
 * Description: ViewPager 可复用适配器 + ViewHolder模式
 */

public abstract class RecyclePagerAdapter extends PagerAdapter {

    //每个item对应一个回收列表
    private SparseArray<ArrayList<ViewHolder>> recycleViews = new SparseArray<>();

    /**
     * 数据对应item的数量
     */
    public abstract int getItemCount();

    /**
     * 返回指定位置的UI类型
     */
    public abstract int getItemViewType(int position);

    /**
     * 创建指定UI类型的ViewHolder
     */
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 根据数据刷新UI组件
     */
    public abstract void onBindViewHolder(ViewHolder viewHolder, int position);


    @Override
    public int getCount() {
        return getItemCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ViewHolder viewHolder;
        int viewType = getItemViewType(position);

        //从对应item的回收列表中寻找对象,找不到则重新创建
        ArrayList<ViewHolder> viewHolderArrayList = recycleViews.get(viewType);
        if (viewHolderArrayList != null && !viewHolderArrayList.isEmpty()) {
            int index = viewHolderArrayList.size() - 1;
            viewHolder = viewHolderArrayList.remove(index);
        } else {
            viewHolder = onCreateViewHolder(container, viewType);
        }

        onBindViewHolder(viewHolder, position);

        container.addView(viewHolder.getItemView());
        return viewHolder.getItemView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View convertView = (View) object;
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        int viewType = getItemViewType(position);

        //回收item不用的对象
        ArrayList<ViewHolder> viewHolders = recycleViews.get(viewType);
        if (viewHolders == null) {
            viewHolders = new ArrayList<>();
            recycleViews.put(viewType, viewHolders);
        }
        viewHolders.add(viewHolder);

        container.removeView(convertView);
    }

    public abstract static class ViewHolder {

        private final View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            this.itemView.setTag(this);
        }

        public View getItemView() {
            return itemView;
        }
    }
}
