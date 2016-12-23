package com.fmisser.circuit;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fmisser.circuit.ui.common.Utils;

public class ParkingFragment extends Fragment {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private TextView tvShowFragment;
    private BriefCardView briefCardView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ParkingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ParkingFragment newInstance(String param1, String param2) {
        ParkingFragment fragment = new ParkingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parking, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar_main);
        briefCardView = (BriefCardView) view.findViewById(R.id.brief_card);
//        drawerLayout = (DrawerLayout) view.findViewById(R.id.fragment_parking_drawerLayout);
        tvShowFragment = (TextView) view.findViewById(R.id.showFragment);
        tvShowFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (briefCardView.getVisibility() == View.VISIBLE) {
                    briefCardView.setVisibility(View.GONE);
                } else {
                    briefCardView.setVisibility(View.VISIBLE);
                }
            }
        });

        init();

        return view;
    }

    private void init() {

    }

    /**
     * 设置透明状态拦
     * 已经通过主题样式解决,当结合修改状态栏风格时,代码方式会有问题.
     */
    @Deprecated
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            int statusBarHeight = Utils.getStatusBarHeight(getContext());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
            layoutParams.setMargins(0, statusBarHeight, 0, 0);
            toolbar.setLayoutParams(layoutParams);
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
