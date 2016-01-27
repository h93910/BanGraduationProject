package com.ban.graduationproject.commbase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ComBase {
	public static int mode = 0;// 0：测试模式 1：发布模式

	public static String DATABASE_NAME = "Ban.db";// 数据库名称

	public static float DPI = 0;

	private static ExecutorService executorService = Executors
			.newFixedThreadPool(10);// 线程池


	/**
	 * dp单位转px
	 * 
	 * @param dp
	 * @return px
	 */
	public static int dp2pix(float dp) {
		return (int) (DPI * dp);
	}

	/**
	 * 开一个线程运行事物
	 * 
	 * @param runnable
	 */
	public static void runInThead(Runnable runnable) {
		executorService.execute(runnable);
	}

	/**
	 * 判断网络是否打开
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConn(Context context) {
		boolean bisConnFlag = false;
		ConnectivityManager conManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = conManager.getActiveNetworkInfo();
		if (network != null) {
			bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
		}
		return bisConnFlag;
	}

	/**
	 * 判断wifi网络是否打开
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断移动网络是否打开
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
