package com.ban.graduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.ban.graduationproject.commbase.ComBase;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.zxing.activity.CaptureActivity;

public class MainActivity extends MyBaseActivity {
	private SlidingMenu slidingMenu;

	private Intent goToScan;

	private static int REQUESTCODE_LOCK = 0;// 放车请求码
	private static int REQUESTCODE_UNLOCK = 1;// 拿车请求码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);

		// 侧滑菜单设置
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setBehindWidth(ComBase.dp2pix(150));// 设置弹出多少
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.item_sliding_menu);
	}

	@Override
	public void initView() {

	}

	@Override
	public void initButton() {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				int id = v.getId();
				if (id == R.id.main_menu) {
					slidingMenu.showMenu(!slidingMenu.isMenuShowing());
				}
			}
		};

		findViewById(R.id.main_menu).setOnClickListener(listener);
	}

	@Override
	public void initListView() {

	}

	/**
	 * 放车的处理
	 * 
	 * @param v
	 */
	public void lockCar(View v) {
		goToScan = new Intent(MainActivity.this, CaptureActivity.class);
		startActivityForResult(goToScan, REQUESTCODE_LOCK);
	}

	/**
	 * 拿车的处理
	 * 
	 * @param v
	 */
	public void unlockCar(View v) {
		goToScan = new Intent(MainActivity.this, CaptureActivity.class);
		startActivityForResult(goToScan, REQUESTCODE_UNLOCK);
	}

	/**
	 * 跳转到设置
	 * 
	 * @param v
	 */
	public void goToSet(View v) {
		Intent temp = new Intent(this, SetAcitivity.class);
		startActivity(temp);
	}

	/**
	 * 处理扫描车牌之后的事
	 */
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// Log.i("Ban", "arg0" + arg0);
		// Log.i("Ban", "arg1" + arg1);

		if (arg0 == RESULT_OK) {
			String result = arg2.getStringExtra("carID");

			if (arg0 == REQUESTCODE_LOCK) {
				Log.d("Ban", getString(R.string.menu_lock));
			} else if (arg0 == REQUESTCODE_UNLOCK) {
				Log.d("Ban", getString(R.string.menu_unlock));
			}

			Log.d("Ban", "车的ID：" + result);
		}

		super.onActivityResult(arg0, arg1, arg2);
	}
}
