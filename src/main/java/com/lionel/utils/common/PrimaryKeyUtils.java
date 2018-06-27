package com.lionel.utils.common;

import java.util.Date;

public class PrimaryKeyUtils {

	public static void main(String[] args) {
		Date date=new Date();
		System.out.println(getSecondTimestamp(date));

	}
	/** 
	 * 获取精确到秒的时间戳 
	 * @return 
	 */  
	public static int getSecondTimestamp(Date date){  
	    if (null == date) {  
	        return 0;  
	    }  
	    String timestamp = String.valueOf(date.getTime());  
	    int length = timestamp.length();  
	    if (length > 3) {  
	        return Integer.valueOf(timestamp.substring(0,length-3));  
	    } else {  
	        return 0;  
	    }  
	} 

}
