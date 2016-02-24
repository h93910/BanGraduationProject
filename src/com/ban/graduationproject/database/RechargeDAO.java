package com.ban.graduationproject.database;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.database.Cursor;

import com.ban.graduationproject.commbase.ComBase;
import com.ban.graduationproject.entity.Recharge;
import com.ban.graduationproject.server.DataTool;

public class RechargeDAO extends BaseDAO<Recharge> {
	private String[] allValueName = { "who", "money", "receipt","time" };
	private SimpleDateFormat format = new SimpleDateFormat(
			ComBase.DATE_FORMAT);

	public RechargeDAO(Context context) {
		super(context,"recharge");
	}

	public Recharge findByExample(Recharge example) {
		return null;
	}

	public boolean insertNew(Recharge recharge) {
		String[] value = { String.valueOf(recharge.getWho()),
				String.valueOf(recharge.getMoney()), recharge.getReceipt(),
				DataTool.getSimpleDate(recharge.getTime(), format) };
		return insertNew(allValueName, value);
	}

	@Override
	public Recharge getFromCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

}
