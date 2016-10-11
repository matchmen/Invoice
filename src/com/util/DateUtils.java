package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.constant.Constants;
import com.exception.SystemException;

public class DateUtils {
	/**
	 * 字符串转换为Date
	 * @param str
	 * @return
	 * @throws SystemException
	 */
	public static Date StringParseToDate(String str) throws SystemException{
		
		SimpleDateFormat sf = new SimpleDateFormat(Constants.FORMAT_OF_DATE);
		
		try {
			return sf.parse(str);
		} catch (ParseException e) {
			throw new SystemException("系统异常，请联系管理员。");
		}
	}
	/**
	 * Date转换为字符串
	 */
	public static String dateParseToString(Date date){
		SimpleDateFormat sf = new SimpleDateFormat(Constants.FORMAT_OF_DATE);
		return sf.format(date);
	}
	/**
	 * util.Date转换为sql.Date
	 */
	public static java.sql.Date utilParseToSql(Date date){
		return new java.sql.Date(date.getTime());
	}
	/**
	 * sql.Date转换为util.Date
	 */
	public static java.sql.Date sqlParseToUtil(java.sql.Date date){
		
		return new java.sql.Date(date.getTime());
	}
}
