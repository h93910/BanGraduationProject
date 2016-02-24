package com.ban.graduationproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ban.graduationproject.commbase.ComBase;
import com.ban.graduationproject.commbase.SimpleChange;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zxing.activity.CaptureActivity;

public class MainActivity extends MyBaseActivity {
	private SlidingMenu slidingMenu;

	private TextView mTitleView;
	private ImageView mMenuBtnView;
	private ImageView mSetBtnView;
	private LinearLayout layout;
	private FragmentManager fragmentManager;
	private Intent goToScan;

	private static int REQUESTCODE_LOCK = 0;// 放车请求码
	private static int REQUESTCODE_UNLOCK = 1;// 拿车请求码
	private static int REQUESTCODE_RECHARGE = 2;// 充值请求码

	private SimpleChange change;

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

		layout = (LinearLayout) findViewById(R.id.main_fragment);
		fragmentManager = getSupportFragmentManager();

		goToRecord();
	}

	@Override
	public void initView() {
		mMenuBtnView = (ImageView) findViewById(R.id.main_menu);
		mSetBtnView = (ImageView) findViewById(R.id.main_set);
		mTitleView = (TextView) findViewById(R.id.title_string);

		List<TextView> textViewSet = new ArrayList<TextView>();
		textViewSet.add((TextView) findViewById(R.id.main_recording_text));
		textViewSet.add((TextView) findViewById(R.id.main_recharge_text));
		textViewSet.add((TextView) findViewById(R.id.main_personal_text));

		List<ImageView> imageViewSet = new ArrayList<ImageView>();
		imageViewSet.add((ImageView) findViewById(R.id.main_recording_pic));
		imageViewSet.add((ImageView) findViewById(R.id.main_recharge_pic));
		imageViewSet.add((ImageView) findViewById(R.id.main_personal_pic));

		List<Integer> defaultPic = new ArrayList<Integer>();
		defaultPic.add(R.drawable.main_recording);
		defaultPic.add(R.drawable.main_recharge);
		defaultPic.add(R.drawable.main_personal);

		List<Integer> changePic = new ArrayList<Integer>();
		changePic.add(R.drawable.main_recording_fouse);
		changePic.add(R.drawable.main_recharge_focus);
		changePic.add(R.drawable.main_personal_focus);

		change = new SimpleChange(this, textViewSet, R.color.main_disable,
				R.color.main_color2, imageViewSet, defaultPic, changePic);
	}

	@Override
	public void initButton() {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				int id = v.getId();
				if (id == R.id.main_menu) {
					slidingMenu.showMenu(!slidingMenu.isMenuShowing());
				} else if (id == R.id.main_recording_layout) {
					goToRecord();
				} else if (id == R.id.main_recharge_layout) {
					goToRecharge();
				} else if (id == R.id.main_personal_layout) {
					goToPersonal();
				}
			}
		};

		mMenuBtnView.setOnClickListener(listener);
		findViewById(R.id.main_recording_layout).setOnClickListener(listener);
		findViewById(R.id.main_recharge_layout).setOnClickListener(listener);
		findViewById(R.id.main_personal_layout).setOnClickListener(listener);
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
	 * 充值的处理
	 * 
	 * @param v
	 */
	public void recharge(View v) {
		goToScan = new Intent(MainActivity.this, CaptureActivity.class);
		startActivityForResult(goToScan, REQUESTCODE_RECHARGE);
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
	 * 是否显示标题栏两边按钮
	 */
	private void showHeadBtn(boolean show) {
		int v = 0;
		if (show)
			v = View.VISIBLE;
		else
			v = View.GONE;
		mMenuBtnView.setVisibility(v);
		mSetBtnView.setVisibility(v);
		slidingMenu.setBehindWidth(show == true ? ComBase.dp2pix(150) : 0);
	}

	/**
	 * 跳转到拿放车记录
	 */
	public void goToRecord() {
		change.executeChange(0);
		mTitleView.setText(R.string.main_recording);
		showHeadBtn(true);

		fragmentManager.beginTransaction()
				.replace(layout.getId(), new RecordingFragment()).commit();
	}

	/**
	 * 跳转到充值记录
	 */
	public void goToRecharge() {
		change.executeChange(1);
		mTitleView.setText(R.string.main_recharge);
		showHeadBtn(true);

		fragmentManager.beginTransaction()
				.replace(layout.getId(), new RechargeFragment()).commit();

	}

	/**
	 * 跳转到用户管理
	 */
	public void goToPersonal() {
		change.executeChange(2);
		mTitleView.setText(R.string.main_personal);
		showHeadBtn(true);

		fragmentManager.beginTransaction()
				.replace(layout.getId(), new PersonalFragment()).commit();

	}

	/**
	 * 跳转到用户添加页面
	 */
	public void goToPersonalAdd() {
		mTitleView.setText(R.string.main_personal_add);
		showHeadBtn(false);

		fragmentManager.beginTransaction()
				.replace(layout.getId(), new PersonalAddFragment()).commit();

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
			} else if (arg0 == REQUESTCODE_RECHARGE) {
				Log.d("Ban", getString(R.string.menu_recharge));
			}

			Log.d("Ban", "车的ID：" + result);
		}

		super.onActivityResult(arg0, arg1, arg2);
	}
}
