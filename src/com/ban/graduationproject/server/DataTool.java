package com.ban.graduationproject.server;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTool {
	public static String getSimpleDate(Date date, SimpleDateFormat formatter) {
		String dateString = formatter.format(date);
		return dateString;
	}
}
