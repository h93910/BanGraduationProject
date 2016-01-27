package com.ban.graduationproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.ban.graduationproject.commbase.ComBase;

/**
 * SQLITE数据库类
 * 
 * @author Ban
 * 
 */
public class MyDateBase extends SQLiteOpenHelper {
	private MyDateBase myDateBase = null;
	public static SQLiteDatabase database;

	private MyDateBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	private MyDateBase(Context context) {
		super(context, ComBase.DATABASE_NAME, null, 1);
		database = getReadableDatabase();
	}

	public MyDateBase getInstance(Context context) {
		if (myDateBase == null) {
			myDateBase = new MyDateBase(context);
		}
		return myDateBase;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
