package com.fmisser.circuit;

import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.fmisser.circuit.ui.common.Utils;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final long EXIT_MILLIS = 2000L;
    private long lastTime = 0L;

    private CoordinatorLayout coordinatorLayout;
    private BottomBar bottomBar;

    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_home);

        Utils.setMIUIStatusBarDarkMode(this, true);

        init();
    }

    private void init() {

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.content_news);
                        if (newsFragment == null) {
                            newsFragment = NewsFragment.newInstance(1);
                        }
                        switchToFragment(R.id.content_news, newsFragment);
                        break;
                    case R.id.tab_parking:
                        ParkingFragment parkingFragment = (ParkingFragment) getSupportFragmentManager().findFragmentById(R.id.content_fragment);
                        if (parkingFragment == null) {
                            parkingFragment = ParkingFragment.newInstance("", "");
                        }
                        switchToFragment(R.id.content_fragment, parkingFragment);
                        break;
                    case R.id.tab_explore:
                        break;
                    case R.id.tab_account:
                        break;
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.content_news);
                        if (newsFragment != null) {
                            newsFragment.reselect();
                        }
                        break;
                }
            }
        });
    }

    public void switchToFragment(@IdRes int containerViewId, Fragment to) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null &&
                !fragmentList.isEmpty()) {
            for (Fragment fragment : fragmentList) {
                if (fragment.isVisible()) {
                    transaction.hide(fragment);
                }
            }
        }

        if (!to.isAdded()) {
            transaction.add(containerViewId, to)
                    .commit();
        } else {
            transaction.show(to)
                    .commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackKeyDown();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void onBackKeyDown() {
        long currMillis = System.currentTimeMillis();
        if (currMillis - lastTime > EXIT_MILLIS) {
            lastTime = currMillis;
            Snackbar.make(coordinatorLayout, "再按一次返回键退出", (int) EXIT_MILLIS)
                    .show();
        } else {
            //退到后台,不结束应用
            moveTaskToBack(false);
        }
    }
}
