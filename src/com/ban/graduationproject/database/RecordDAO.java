package com.ban.graduationproject.database;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.ban.graduationproject.commbase.ComBase;
import com.ban.graduationproject.entity.Record;
import com.ban.graduationproject.server.DataTool;

public class RecordDAO extends BaseDAO<Record> {
	private String[] allValueName = { "car_id", "time", "action" };
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(ComBase.DATE_FORMAT);

	public RecordDAO(Context context) {
		super(context, "record");
	}

	public Record findByExample(Record example) {
		return null;
	}

	public boolean insertNew(Record record) {
		String[] value = { String.valueOf(record.getCarId()),
				DataTool.getSimpleDate(record.getTime(), format),
				String.valueOf(record.getAction()) };
		return insertNew(allValueName, value);
	}

	@Override
	public Record getFromCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}
}
