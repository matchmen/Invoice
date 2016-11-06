package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.constant.Constants;
import com.exception.SystemException;

public class DateUtils {
	/**
	 * �ַ�ת��ΪDate
	 * 
	 * @param str
	 * @return
	 * @throws SystemException
	 */
	public static Date StringParseToDate(String str) throws SystemException {

		if (null != str) {
			SimpleDateFormat sf = new SimpleDateFormat(Constants.FORMAT_OF_DATE);
			try {
				return sf.parse(str);
			} catch (ParseException e) {
				throw new SystemException("日期转换异常");
			}
		} else {
			return null;
		}
	}

	/**
	 * dateParseToString
	 */
	public static String dateParseToString(Date date) {
		if (null != date) {
			SimpleDateFormat sf = new SimpleDateFormat(Constants.FORMAT_OF_DATE);
			return sf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * utilParseToSql
	 */
	public static java.sql.Date utilParseToSql(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * sqlParseToUtil
	 */
	public static java.sql.Date sqlParseToUtil(java.sql.Date date) {

		return new java.sql.Date(date.getTime());
	}

	public static Date intToDate(Long i) {
		if (null != i) {
			return new Date(i);
		}
		return null;
	}
	
	public static String dateNullUtil(String str){
		if(null==str||StringUtils.isBlank(str)||StringUtils.isBlank(str))
			return null;
		else{
			return str;
		}
	}
	
	public static boolean validateDate(String str,Integer num){
		if(null!=str&&null!=num){
			SimpleDateFormat sf = new SimpleDateFormat(Constants.FORMAT_OF_DATE);
			try {
				Date date = sf.parse(str);
				Date nowDate = new Date();
				if(nowDate.getTime()-date.getTime()<=num*(1000 * 60 * 60 * 24)){
					return true;
				}
			} catch (ParseException e) {
				System.out.println("日期转换异常"+str);
			}
		}
		return false;
	}
}
