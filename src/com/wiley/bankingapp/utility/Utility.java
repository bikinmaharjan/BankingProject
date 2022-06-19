package com.wiley.bankingapp.utility;


import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Utility {
	@SuppressWarnings("unused")
	private boolean isValidDate(String date) {
		String[] d = date.split("/");
		int day = Integer.parseInt(d[0]);
		int month = Integer.parseInt(d[1]);
		int year = Integer.parseInt(d[2]);
	
		if(day < 32 && month <=12 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isValidName(String name){
		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		boolean b = m.find();
		return !b;
	}

	
	
}
