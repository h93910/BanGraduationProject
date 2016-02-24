package com.ban.graduationproject.database;

import android.content.Context;
import android.database.Cursor;

import com.ban.graduationproject.entity.Manager;

public class ManagerDAO extends BaseDAO<Manager> {
	private String[] allValueName = { "username", "password", "competence" };

	public ManagerDAO(Context context) {
		super(context, "manager");
	}

	public Manager findByExample(Manager example) {
		Manager manager=null;
		String selection = allValueName[0] + "=? and " + allValueName[1] + "=?";
		String[] selectionArgs = { example.getUsername(), example.getPassword() };
		Cursor cursor = database.query(tableName, null, selection,
				selectionArgs, null, null, null);
		if (cursor != null && cursor.moveToNext()) {
			manager = new Manager();
			manager.setUsername(cursor.getString(cursor
					.getColumnIndex(allValueName[0])));
			manager.setPassword(cursor.getString(cursor
					.getColumnIndex(allValueName[1])));
			manager.setCompetence(cursor.getInt(cursor
					.getColumnIndex(allValueName[2])));
		}
		cursor.close();
		return manager;
	}

	@Override
	public Manager getFromCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	};
}
