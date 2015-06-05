package com.qian.util;

import java.util.Calendar;

public class Tools {
	
	/**
	 * 判断字符串是否符合格式：20140816
	 * @param input
	 * @return 格式正确(true)/格式不正确(false)
	 */
	public static boolean formatCheck(String input){
		String format = "^[1-9]\\d{7}$";
		if(input.matches(format)){
			return true;
		}
		return false;
	}
	
	public static boolean minIsFormat(String min){
		String format1 = "^[0-5]$";
		String format2 = "^[0-9]$";
		char[] minArray = min.toCharArray();
		if(new String(minArray[0]+"").matches(format1)){
			if(new String(minArray[1]+"").matches(format2)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断时分是否符合格式如1358
	 * @param time
	 * @return
	 */
	public static boolean timeIsFormat(String time){
		String format1 = "^[0-2]$";
		String format2 = "^[0-9]$";
		if(time != null && time.length() == 4){
			String hour = time.substring(0, 2);
			String min = time.substring(2);
			char[] hourArray = hour.toCharArray();
			
			if(new String(hourArray[0]+"").matches(format1)){
				if(new String(hourArray[1]+"").matches(format2)){
					if(Integer.parseInt(hourArray[0]+"") == 2 ){
						if(Integer.parseInt(hourArray[1]+"") <= 4 && Integer.parseInt(hourArray[1]+"") >= 0){
							if(minIsFormat(min)){
								System.out.println("match");
								return true;
							}
						}
						
					}else{
						if(new String(hourArray[1]+"").matches(format2)){
							if(minIsFormat(min)){
								return true;
							}
						}
					}
				}
				
			}	
		}
		return false;
	}
	
	/**
	 * 将1500的时间转换成 15:00
	 * @param time
	 * @return
	 */
	public static String timeFormatChange(String time){
		String start = time.substring(0, 2);
		String end = time.substring(2);
		
		return start+":"+end;
	}
	/**
	 * 得到当前日期
	 * @return current date like "20140819"
	 */
	public static String getCurrentDate(){
		StringBuffer sb = new StringBuffer();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		int day = ca.get(Calendar.DATE);
		if(month < 10){
			sb.append(year+"0"+(month+1)+day);
		}else{
			sb.append(year+""+(month+1)+day);
		}
		return sb.toString().trim();
	}
	
	/**
	 * 得到当前时分，记录每天每次交通使用情况
	 * @return time
	 */
	public static String getCurrentTime(){
		StringBuffer sb = new StringBuffer();
		Calendar ca = Calendar.getInstance();
		int time = ca.get(Calendar.HOUR);
		int min = ca.get(Calendar.MINUTE);
		sb.append(""+time+min);
		return sb.toString().trim();
	}
}
