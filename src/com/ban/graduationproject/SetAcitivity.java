package com.ban.graduationproject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class SetAcitivity extends MyBaseActivity {
	private DatePickerDialog datePickerDialog;
	private TimePickerDialog timePickerDialog;

	private Calendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_set);
	}

	@Override
	public void initView() {
		calendar = Calendar.getInstance();

		datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				System.out.println("??????");
				try {
					setDate(year, monthOfYear, dayOfMonth);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				timePickerDialog.show();
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));

		timePickerDialog = new TimePickerDialog(this, new OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				try {
					setTime(hourOfDay, minute);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
				true);
	}

	@Override
	public void initButton() {

	}

	@Override
	public void initListView() {

	}

	public void setDate(View v) {
		datePickerDialog.show();
	}

	public void setDate(int year, int month, int day) throws IOException,
			InterruptedException {

		requestPermission();

		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		long when = c.getTimeInMillis();

		if (when / 1000 < Integer.MAX_VALUE) {
			SystemClock.setCurrentTimeMillis(when);
		}

		long now = Calendar.getInstance().getTimeInMillis();
		// Log.d(TAG, "set tm="+when + ", now tm="+now);

		if (now - when > 1000)
			throw new IOException("failed to set Date.");
	}

	public void setTime(int hourOfDay, int minute) throws IOException,
			InterruptedException {

		requestPermission();

		Calendar c = Calendar.getInstance();

		c.set(Calendar.HOUR_OF_DAY, hourOfDay);
		c.set(Calendar.MINUTE, minute);
		long when = c.getTimeInMillis();

		if (when / 1000 < Integer.MAX_VALUE) {
			SystemClock.setCurrentTimeMillis(when);
		}

		long now = Calendar.getInstance().getTimeInMillis();
		// Log.d(TAG, "set tm="+when + ", now tm="+now);

		if (now - when > 1000)
			throw new IOException("failed to set Date.");
	}

	static void requestPermission() throws InterruptedException, IOException {
		createSuProcess("chmod 666 /dev/alarm").waitFor();
	}

	static Process createSuProcess() throws IOException {
		File rootUser = new File("/system/xbin/ru");
		if (rootUser.exists()) {
			return Runtime.getRuntime().exec(rootUser.getAbsolutePath());
		} else {
			return Runtime.getRuntime().exec("su");
		}
	}

	static Process createSuProcess(String cmd) throws IOException {

		DataOutputStream os = null;
		Process process = createSuProcess();

		try {
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit $?\n");
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}

		return process;
	}
}
