package com.lyzd.om.shared.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Thinker
 *
 */
public class DateUtil {
	
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static  String now() {
		//LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now() ,ZoneId.systemDefault());
		 LocalDateTime localDateTime = LocalDateTime.now();
		 
		return dtf.format(localDateTime);
	}
	

}
