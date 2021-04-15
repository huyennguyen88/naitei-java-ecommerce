package com.triplet.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	private final String FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd hh:mm:ss";

	public String convertDateTime(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
		try {
			return formatter.format(date);
		} catch (Exception e) {
			return null;
		}
	}
}
