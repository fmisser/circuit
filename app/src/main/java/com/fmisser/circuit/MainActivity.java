package com.fmisser.circuit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fmisser.circuit.ui.common.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private TextView tvShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawerLayout);
        tvShowFragment = (TextView) findViewById(R.id.showFragment);
        tvShowFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BriefCardFragment fragment = BriefCardFragment.newInstance("param1", "param2");
                fragment.show(getFragmentManager(), "BriefCardFragment");
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.add(fragment, "BriefCardFragment");
//                fragmentTransaction.commit();
            }
        });

        init();
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
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            int statusBarHeight = Utils.getStatusBarHeight(this);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
            layoutParams.setMargins(0, statusBarHeight, 0, 0);
            toolbar.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置MIUI状态栏显示模式?
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkMode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkMode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置Meizu状态栏显示模式
     */
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
