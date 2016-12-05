package com.fragment.sms.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fragment.sms.fragment.MainFragment1;
import com.fragment.sms.fragment.MainFragment2;
import com.fragment.sms.fragment.MainFragment3;
import com.fragment.sms.fragment.MainFragment4;
import com.fragment.sms.R;


public class MainActivity extends Activity implements View.OnClickListener {


    private MainFragment1 mainFragment1;
    private MainFragment3 mainFragment3;
    private MainFragment2 mainFragment2;
    private MainFragment4 mainFragment4;

    private View rl_main_fragment1;
    private View rl_main_fragment2;
    private View rl_main_fragment3;
    private View rl_main_fragment4;

    private ImageView iv_main_image1;
    private ImageView iv_main_image2;
    private ImageView iv_main_image3;
    private ImageView iv_main_image4;

    private TextView tv_main_text1;
    private TextView tv_main_text2;
    private TextView tv_main_text3;
    private TextView tv_main_text4;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setTabSelection(0);//默认选中主页面
    }

    /**
     * 获取每个控件实例，并设置点击事件
     */
    private void initViews() {
        rl_main_fragment1 = findViewById(R.id.rl_main_fragment1);
        rl_main_fragment2 = findViewById(R.id.rl_main_fragment2);
        rl_main_fragment3 = findViewById(R.id.rl_main_fragment3);
        rl_main_fragment4 = findViewById(R.id.rl_main_fragment4);

        iv_main_image1 = (ImageView) findViewById(R.id.iv_main_image1);
        iv_main_image2 = (ImageView) findViewById(R.id.iv_main_image2);
        iv_main_image3 = (ImageView) findViewById(R.id.iv_main_image3);
        iv_main_image4 = (ImageView) findViewById(R.id.iv_main_image4);

        tv_main_text1 = (TextView) findViewById(R.id.tv_main_text1);
        tv_main_text2 = (TextView) findViewById(R.id.tv_main_text2);
        tv_main_text3 = (TextView) findViewById(R.id.tv_main_text3);
        tv_main_text4 = (TextView) findViewById(R.id.tv_main_text4);

        rl_main_fragment1.setOnClickListener(this);
        rl_main_fragment2.setOnClickListener(this);
        rl_main_fragment3.setOnClickListener(this);
        rl_main_fragment4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_main_fragment1:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.rl_main_fragment2:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.rl_main_fragment3:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.rl_main_fragment4:
                // 当点击了设置tab时，选中第4个tab
                setTabSelection(3);
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示主页，1表示应用，2表示分享，3表示我的。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                iv_main_image1.setImageResource(R.drawable.m_zhuye_yes);
                tv_main_text1.setTextColor(Color.BLUE);
                mainFragment1 = new MainFragment1();
                transaction.replace(R.id.main_viewArea, mainFragment1);
                break;
            case 1:
                iv_main_image2.setImageResource(R.drawable.m_yingyong_yes);
                tv_main_text2.setTextColor(Color.BLUE);
                mainFragment2 = new MainFragment2();
                transaction.replace(R.id.main_viewArea, mainFragment2);
                break;
            case 2:
                iv_main_image3.setImageResource(R.drawable.m_fenxiang_yes);
                tv_main_text3.setTextColor(Color.BLUE);
                mainFragment3 = new MainFragment3();
                transaction.replace(R.id.main_viewArea, mainFragment3);
                break;
            case 3:
                iv_main_image4.setImageResource(R.drawable.m_wode_yes);
                tv_main_text4.setTextColor(Color.BLUE);
                mainFragment4 = new MainFragment4();
                transaction.replace(R.id.main_viewArea, mainFragment4);
                break;
        }
        transaction.commit();
    }


    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        iv_main_image1.setImageResource(R.drawable.m_zhuye_no);
        tv_main_text1.setTextColor(Color.WHITE);
        iv_main_image2.setImageResource(R.drawable.m_yingyong_no);
        tv_main_text2.setTextColor(Color.WHITE);
        iv_main_image3.setImageResource(R.drawable.m_fenxiang_no);
        tv_main_text3.setTextColor(Color.WHITE);
        iv_main_image4.setImageResource(R.drawable.m_wode_no);
        tv_main_text4.setTextColor(Color.WHITE);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mainFragment1 != null) {
            transaction.hide(mainFragment1);
        }
        if (mainFragment2 != null) {
            transaction.hide(mainFragment2);
        }
        if (mainFragment3 != null) {
            transaction.hide(mainFragment3);
        }
        if (mainFragment4 != null) {
            transaction.hide(mainFragment4);
        }
    }

}
