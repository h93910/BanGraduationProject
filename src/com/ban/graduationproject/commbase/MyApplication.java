package com.ban.graduationproject.commbase;

import com.ban.graduationproject.database.BaseDAO;
import com.ban.graduationproject.database.ManagerDAO;
import com.ban.graduationproject.database.MySampleDate;
import com.ban.graduationproject.entity.Manager;
import com.ban.graduationproject.entity.User;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {
	private Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();

		init();
	}

	private void init() {
		// 初始化键值对处理类
		MySampleDate.getInstance(context, "setting");

		// 设置分辨率
		ComBase.DPI = getResources().getDisplayMetrics().density;
		Log.d("系统分辨率", ComBase.DPI + "");

		// 初始化数据库
		ComBase.initDB(context);
		new ManagerDAO(context);
	}
}
