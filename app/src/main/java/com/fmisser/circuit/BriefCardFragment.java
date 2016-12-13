package com.fmisser.circuit;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fmisser.circuit.ui.common.LoopPagerAdapter;
import com.fmisser.circuit.ui.common.RecyclePagerAdapter;
import com.fmisser.circuit.ui.common.WrapContentHeightViewPager;

public class BriefCardFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private WrapContentHeightViewPager viewPager;

    public BriefCardFragment() {
        // Required empty public constructor
    }

    public static BriefCardFragment newInstance(String param1, String param2) {
        BriefCardFragment fragment = new BriefCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * DialogFragment 显示风格设置
     * 已通过样式配置实现
     */
    @Deprecated
    private void setStyle() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.dimAmount = 0.0f;
        layoutParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BriefCardFragmentStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brief_card, container, false);

        viewPager = (WrapContentHeightViewPager) view.findViewById(R.id.viewPager_brief_card);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new BriefCardAdapter(getActivity()));
        viewPager.setPageMargin(30);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class BriefCardAdapter extends LoopPagerAdapter {

        private Context context;
        private LayoutInflater inflater;

        private CardView cardView;

        private SparseArray<View> recycleViews = new SparseArray<>();

        public BriefCardAdapter(Context context) {
            this.context = context;
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
            return new BriefCardViewHolder(inflater.inflate(R.layout.layout_brief_card_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            if (viewHolder instanceof BriefCardViewHolder) {
                ((BriefCardViewHolder) viewHolder).setData(position);
            }
        }

        private class BriefCardViewHolder extends RecyclePagerAdapter.ViewHolder {

            private ImageView imageView;
            private TextView textViewName;
            private TextView textViewIntro;

            public BriefCardViewHolder(View itemView) {
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

    private class BriefCardPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
