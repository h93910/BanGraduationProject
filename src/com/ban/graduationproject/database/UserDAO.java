package com.ban.graduationproject.database;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.ban.graduationproject.commbase.ComBase;
import com.ban.graduationproject.entity.Car;
import com.ban.graduationproject.entity.User;
import com.ban.graduationproject.server.DataTool;

public class UserDAO extends BaseDAO<User> {
	private String[] allValueName = { "name", "sex", "tel", "created",
			"deadline", "car_id", "station", "weixin_openid" };
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(ComBase.DATE_FORMAT);

	public UserDAO(Context context) {
		super(context, "user");
	}

	/**
	 * 查询
	 */
	public User findByNameAndCarId(User example) {
		String selection = allValueName[0] + "=? and " + allValueName[5] + "=?";
		String[] selectionArgs = { example.getName(),
				String.valueOf(example.getCarId()) };
		Cursor cursor = database.query(tableName, null, selection,
				selectionArgs, null, null, null);
		if (cursor != null && cursor.moveToNext()) {
			return getFromCursor(cursor);
		}
		return null;
	};

	public boolean insertNew(User user) {
		String[] value = { user.getName(), String.valueOf(user.getSex()),
				String.valueOf(user.getTel()),
				DataTool.getSimpleDate(user.getCreated(), format),
				DataTool.getSimpleDate(user.getDeadline(), format),
				String.valueOf(user.getCarId()),
				String.valueOf(user.getStation()), user.getWeixinOpenId() };
		User exist = findByNameAndCarId(user);
		if (exist == null) {
			return insertNew(allValueName, value);
		} else {
			return update(allValueName, value, "id=?",
					new String[] { String.valueOf(exist.getId()) });
		}
	}

	@Override
	public User getFromCursor(Cursor cursor) {
		User u = new User();
		u.setId(cursor.getLong(cursor.getColumnIndex("id")));
		u.setName(cursor.getString(cursor.getColumnIndex(allValueName[0])));
		u.setSex(cursor.getInt(cursor.getColumnIndex(allValueName[1])) == 1 ? true
				: false);
		u.setTel(cursor.getLong(cursor.getColumnIndex(allValueName[2])));
		String created = cursor.getString(cursor
				.getColumnIndex(allValueName[3]));
		System.out.println(created);
		String deadline = cursor.getString(cursor
				.getColumnIndex(allValueName[4]));
		System.out.println(deadline);
		u.setCarId(cursor.getLong(cursor.getColumnIndex(allValueName[5])));
		u.setStation(cursor.getInt(cursor.getColumnIndex(allValueName[6])));
		u.setWeixinOpenId(cursor.getString(cursor
				.getColumnIndex(allValueName[7])));

		return u;
	}
}
