package com.fmisser.circuit;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fmisser.circuit.ui.common.LoopPagerAdapter;
import com.fmisser.circuit.ui.common.RecyclePagerAdapter;
import com.fmisser.circuit.ui.common.Utils;
import com.fmisser.circuit.ui.common.WrapContentHeightViewPager;
import com.fmisser.circuit.ui.common.ZoomOutPageTransformer;

/**
 * Date: 2016/12/19.
 * Author: fmisser
 * Description:
 */

public class BriefCardView extends LinearLayout {

    private WrapContentHeightViewPager viewPager;

    public BriefCardView(Context context) {
        super(context);
        init(context);
    }

    public BriefCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BriefCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_brief_card, this);
        viewPager = (WrapContentHeightViewPager) findViewById(R.id.viewPager_brief_card);

        initViewPager();
    }

    //初始化ViewPager
    protected void initViewPager() {
        viewPager.setFocusable(true);
        viewPager.setAdapter(new BriefCardAdapter(getContext()));

        int screenWidth = Utils.getScreenWidth(getContext());
        int unit = screenWidth / 20;
//        viewPager.setPageMargin(unit / 3);
        viewPager.setPadding(unit * 2, 0, unit * 2, 0);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer(viewPager));
    }

    private class BriefCardAdapter extends LoopPagerAdapter {

        private LayoutInflater inflater;

        BriefCardAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BriefCardAdapter.BriefCardViewHolder(inflater.inflate(R.layout.layout_brief_card_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            if (viewHolder instanceof BriefCardAdapter.BriefCardViewHolder) {
                ((BriefCardAdapter.BriefCardViewHolder) viewHolder).setData(position);
            }
        }

        private class BriefCardViewHolder extends RecyclePagerAdapter.ViewHolder {

            private ImageView imageView;
            private TextView textViewName;
            private TextView textViewIntro;

            BriefCardViewHolder(View itemView) {
                super(itemView);
                this.imageView = (ImageView) itemView.findViewById(R.id.cardView_image);
                this.textViewName = (TextView) itemView.findViewById(R.id.cardView_name);
                this.textViewIntro = (TextView) itemView.findViewById(R.id.cardView_intro);
            }

            public void setData(int position) {
                this.textViewIntro.setText(String.format("%s", position));
            }
        }
    }

}
