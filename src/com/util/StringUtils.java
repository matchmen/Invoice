package com.util;

public class StringUtils {
	
	/**
	 * ¿Õ×Ö·û´®ÅĞ¶Ï
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		return str.length()==0;
	}
	/**
	 * ¿Õ°××Ö·û´®ÅĞ¶Ï
	 */
	public static boolean isBlank(String str){
		return str.trim().length()==0;
	}
	/**
	 * Êı×ÖÅĞ¶Ï
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		return null!=str && str.matches("[\\d]+(\\.[\\d]+)?");
	}
	
	/**
	 * ×ÖÄ¸ÅĞ¶Ï
	 * @param args
	 */
	public static boolean isLetter(String str){
		return null!=str && str.matches("[a-zA-Z]+");
	}
	
	/**
	 * ÓÊÏä¸ñÊ½ÅĞ¶Ï
	 * @param args
	 */
	public static boolean isEmail(String str){
		return null!=str && str.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}
	/**
	 * ÈÕÆÚ¸ñÊ½ÅĞ¶Ï
	 */
	public static boolean isDate(String str){
		return null!=str && str.matches("[\\d]{4}\\/(([0][1-9])|([1][0-2]))\\/(([0][1-9])|([1|2][0-9])|[3][0|1])");
	}
}
