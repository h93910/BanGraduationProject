package com.ban.graduationproject.database;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.ban.graduationproject.commbase.ComBase;
import com.ban.graduationproject.entity.Car;

public class CarDAO extends BaseDAO<Car> {
	private String[] allValueName = { "type", "car_number" };
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(ComBase.DATE_FORMAT);

	public CarDAO(Context context) {
		super(context, "car");
	}

	public Car findByExample(Car example) {
		String selection = allValueName[0] + "=? and " + allValueName[1] + "=?";
		String[] selectionArgs = { String.valueOf(example.getType()),
				example.getCarNumber() };
		Cursor cursor = database.query(tableName, null, selection,
				selectionArgs, null, null, null);
		if (cursor != null && cursor.moveToNext()) {
			return getFromCursor(cursor);
		}
		return null;
	}

	public boolean insertNew(Car car) {
		if (findByExample(car) != null) {// 先前已加入过车辆信息就不用再加了，直接返回true;
			return true;
		}

		String[] value = { String.valueOf(car.getType()), car.getCarNumber() };
		return insertNew(allValueName, value);
	}

	@Override
	public Car getFromCursor(Cursor cursor) {
		Car car = new Car();
		car.setId(cursor.getInt(cursor.getColumnIndex("car_id")));
		car.setType(cursor.getInt(cursor.getColumnIndex(allValueName[0])));
		car.setCarNumber(cursor.getString(cursor
				.getColumnIndex(allValueName[1])));
		return car;
	}
}
