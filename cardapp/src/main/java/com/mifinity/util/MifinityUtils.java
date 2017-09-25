package com.mifinity.util;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Mifinity general utils
 * @author juliocesaradias
 */

public class MifinityUtils {
	
	public static Calendar formatDate(String dateToConvert) {
		Calendar cal = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("YY/MM");
			cal.setTime(sdf.parse(dateToConvert));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}
	
	public static boolean isNotEmptyOrNull(String value) {
		if(value != null && !value.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Calendar stringToCalendar(String date) {
	    SimpleDateFormat format = new SimpleDateFormat("YY/MM/DD", Locale.UK);
	    Date d;
	    Calendar c = Calendar.getInstance();
		try {
			d = format.parse(date);
			c.setTime(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	    return c;
	}
}
