package com.fragment.sms.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView tv_main_title;

    private float rawX1;
    private float rawY1;
    private float rawX2;
    private float rawY2;
    private float x;
    private float y;

    private static int index1=0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         /*
         沉浸式状态栏设置
         透明状态栏、透明导航栏
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

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

        tv_main_title = (TextView) findViewById(R.id.tv_main_title);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                rawX1 = event.getRawX();
                rawY1 = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                rawX2 = event.getRawX();
                rawY2 = event.getRawY();
                slideChange(index1);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void slideChange(int i) {
        x = Math.abs(rawX1-rawX2);
        y = Math.abs(rawY1 - rawY2);

        /*
        获取像素密度
         */
        float density=getResources().getDisplayMetrics().density;
        float px = density * 30;

        if(x > y){
           if(Math.abs(rawX2)-Math.abs(rawX1)>px){
               /*
               右滑
                */
               if(i==0){
                   setTabSelection(0);
                   index1=0;
               }else{
                   setTabSelection(i-1);
                   index1-=1;
               }
           }else if(Math.abs(rawX1)-Math.abs(rawX2)>px){
               /*
               左滑
                */
               if(i==3){
                   setTabSelection(3);
                   index1=3;
               }else{
                   setTabSelection(i+1);
                   index1+=1;
               }
           }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_main_fragment1:
                // 当点击了消息tab时，选中第1个tab
                index1=0;
                setTabSelection(0);
                break;
            case R.id.rl_main_fragment2:
                // 当点击了联系人tab时，选中第2个tab
                index1=1;
                setTabSelection(1);
                break;
            case R.id.rl_main_fragment3:
                // 当点击了动态tab时，选中第3个tab
                index1=2;
                setTabSelection(2);
                break;
            case R.id.rl_main_fragment4:
                // 当点击了设置tab时，选中第4个tab
                index1=3;
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
                tv_main_title.setText("新年祝福");
                iv_main_image1.setImageResource(R.drawable.image_newyear_yes);
                tv_main_text1.setTextColor(getResources().getColor(R.color.colorTabSelect));
                mainFragment1 = new MainFragment1();
                transaction.replace(R.id.main_viewArea, mainFragment1);
                break;
            case 1:
                tv_main_title.setText("圣诞祝福");
                iv_main_image2.setImageResource(R.drawable.image_shengdan_yes);
                tv_main_text2.setTextColor(getResources().getColor(R.color.colorTabSelect));
                mainFragment2 = new MainFragment2();
                transaction.replace(R.id.main_viewArea, mainFragment2);
                break;
            case 2:
                tv_main_title.setText("元旦祝福");
                iv_main_image3.setImageResource(R.drawable.image_yuandan_yes);
                tv_main_text3.setTextColor(getResources().getColor(R.color.colorTabSelect));
                mainFragment3 = new MainFragment3();
                transaction.replace(R.id.main_viewArea, mainFragment3);
                break;
            case 3:
                tv_main_title.setText("生日祝福");
                iv_main_image4.setImageResource(R.drawable.image_birthday_yes);
                tv_main_text4.setTextColor(getResources().getColor(R.color.colorTabSelect));
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
        iv_main_image1.setImageResource(R.drawable.image_newyear_no);
        tv_main_text1.setTextColor(getResources().getColor(R.color.colorTab));
        iv_main_image2.setImageResource(R.drawable.image_shengdan_no);
        tv_main_text2.setTextColor(getResources().getColor(R.color.colorTab));
        iv_main_image3.setImageResource(R.drawable.image_yuandan_no);
        tv_main_text3.setTextColor(getResources().getColor(R.color.colorTab));
        iv_main_image4.setImageResource(R.drawable.image_birthday_no);
        tv_main_text4.setTextColor(getResources().getColor(R.color.colorTab));
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


    /**
     * 两次返回键退出程序
     */
    private long exitTime=0;
    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis()-exitTime>2000){
            Toast.makeText(MainActivity.this,"再按一次退出应用",Toast.LENGTH_SHORT).show();
            exitTime=System.currentTimeMillis();
        }else{
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());//彻底关闭进程，执行此方法的activity不会执行onDestroy方法
        }
    }

}
