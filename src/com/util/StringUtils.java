package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.exception.SystemException;

public class StringUtils {
	
	/**
	 * 为空判断
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		return str.length()==0;
	}
	/**
	 * 为空串判断
	 */
	public static boolean isBlank(String str){
		return null!=str && str.trim().length()==0;
	}
	/**
	 * 数字判断
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		return null!=str && str.matches("[\\d]+(\\.[\\d]+)?");
	}
	
	/**
	 * 字母判断
	 * @param args
	 */
	public static boolean isLetter(String str){
		return null!=str && str.matches("[a-zA-Z]+");
	}
	
	/**
	 * 邮箱格式判断
	 * @param args
	 */
	public static boolean isEmail(String str){
		return null!=str && str.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}
	/**
	 * 日期格式判断
	 */
	public static boolean isDate(String str){
		return null!=str && str.matches("[\\d]{4}\\/(([0][1-9])|([1][0-2]))\\/(([0][1-9])|([1|2][0-9])|[3][0|1])");
	}
	/**
	 * MD5加密用户口令
	 * @throws SystemException 
	 */
	public static String md5Password(String str) throws SystemException{
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(str.getBytes());
			byte [] tmp=messageDigest.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : tmp) {
				sb.append(Integer.toHexString(b&0xff));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("errorPage","用户口令加密异常");
		}
	}
	
	/**
	 * 空串，null，空判断
	 */
	public static Boolean isSpace(String str){
		if(null==str||isBlank(str)||isEmpty(str)){
			return true;
		}else{
			return false;
		}
	}
}
