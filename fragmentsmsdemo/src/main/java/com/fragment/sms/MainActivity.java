package com.fragment.sms;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends Activity implements View.OnClickListener {


	private MainT1Fragment mainT1Fragment;
	private MainT3Fragment mainT3Fragment;
	private MainT2Fragment mainT2Fragment;
	private MainT4Fragment mainT4Fragment;

	private View mainT1Layout;
	private View mainT2Layout;
	private View mainT3Layout;
	private View mainT4Layout;

	private ImageView mainT1Image;
	private ImageView mainT2Image;
	private ImageView mainT3Image;
	private ImageView mainT4Image;

	private TextView mainT1Text;
	private TextView mainT2Text;
	private TextView mainT3Text;
	private TextView mainT4Text;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		setTabSelection(0);
	}

	/**
	 * 获取每个控件实例，并设置好点击事件
	 */
	private void initViews() {
		mainT1Layout = findViewById(R.id.main_t1_layout);
		mainT2Layout = findViewById(R.id.main_t2_layout);
		mainT3Layout = findViewById(R.id.main_t3_layout);
		mainT4Layout = findViewById(R.id.main_t4_layout);

		mainT1Image = (ImageView) findViewById(R.id.main_t1_image);
		mainT2Image = (ImageView) findViewById(R.id.main_t2_image);
		mainT3Image = (ImageView) findViewById(R.id.main_t3_image);
		mainT4Image = (ImageView) findViewById(R.id.main_t4_image);

		mainT1Text = (TextView) findViewById(R.id.main_t1_text);
		mainT2Text = (TextView) findViewById(R.id.main_t2_text);
		mainT3Text = (TextView) findViewById(R.id.main_t3_text);
		mainT4Text = (TextView) findViewById(R.id.main_t4_text);

		mainT1Layout.setOnClickListener(this);
		mainT2Layout.setOnClickListener(this);
		mainT3Layout.setOnClickListener(this);
		mainT4Layout.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.main_t1_layout:
				// 当点击了消息tab时，选中第1个tab
				setTabSelection(0);
				break;
			case R.id.main_t2_layout:
				// 当点击了联系人tab时，选中第2个tab
				setTabSelection(1);
				break;
			case R.id.main_t3_layout:
				// 当点击了动态tab时，选中第3个tab
				setTabSelection(2);
				break;
			case R.id.main_t4_layout:
				// 当点击了设置tab时，选中第4个tab
				setTabSelection(3);
				break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 *
	 * @param index
	 *            每个tab页对应的下标。0表示主页，1表示应用，2表示分享，3表示我的。
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
				mainT1Image.setImageResource(R.drawable.m_zhuye_yes);
				mainT1Text.setTextColor(Color.BLUE);
				mainT1Fragment = new MainT1Fragment();
				transaction.replace(R.id.main_viewArea, mainT1Fragment);
				break;
			case 1:
				mainT2Image.setImageResource(R.drawable.m_yingyong_yes);
				mainT2Text.setTextColor(Color.BLUE);
				mainT2Fragment = new MainT2Fragment();
				transaction.replace(R.id.main_viewArea, mainT2Fragment);
				break;
			case 2:
				mainT3Image.setImageResource(R.drawable.m_fenxiang_yes);
				mainT3Text.setTextColor(Color.BLUE);
				mainT3Fragment = new MainT3Fragment();
				transaction.replace(R.id.main_viewArea, mainT3Fragment);
				break;
			case 3:
				mainT4Image.setImageResource(R.drawable.m_wode_yes);
				mainT4Text.setTextColor(Color.BLUE);
				mainT4Fragment = new MainT4Fragment();
				transaction.replace(R.id.main_viewArea, mainT4Fragment);
				break;
		}
		transaction.commit();
	}


	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		mainT1Image.setImageResource(R.drawable.m_zhuye_no);
		mainT1Text.setTextColor(Color.WHITE);
		mainT2Image.setImageResource(R.drawable.m_yingyong_no);
		mainT2Text.setTextColor(Color.WHITE);
		mainT3Image.setImageResource(R.drawable.m_fenxiang_no);
		mainT3Text.setTextColor(Color.WHITE);
		mainT4Image.setImageResource(R.drawable.m_wode_no);
		mainT4Text.setTextColor(Color.WHITE);
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 *
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mainT1Fragment != null) {
			transaction.hide(mainT1Fragment);
		}
		if (mainT2Fragment != null) {
			transaction.hide(mainT2Fragment);
		}
		if (mainT3Fragment != null) {
			transaction.hide(mainT3Fragment);
		}
		if (mainT4Fragment != null) {
			transaction.hide(mainT4Fragment);
		}
	}

}
