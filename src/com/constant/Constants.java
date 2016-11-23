package com.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.util.StringUtils;

public class Constants {
	
	@Autowired
	private static Constants constants;
	
	/**
	 * yyyy/MM/dd
	 */
	public static String FORMAT_OF_DATE = "yyyy/MM/dd";
	/**
	 * 发票状态
	 */
	public static Map<String,String> invoiceStatusMap = new HashMap<String,String>();
	/**
	 * 01 已开具
	 */
	public static String invoiceStatus_01 = "01";
	/**
	 * 02 已检视
	 */
	public static String invoiceStatus_02 = "02";
	/**
	 * 03 已寄出
	 */
	public static String invoiceStatus_03 = "03";
	/**
	 * 04 已收到
	 */
	public static String invoiceStatus_04 = "04";
	/**
	 * 05 已入账
	 */
	public static String invoiceStatus_05 = "05";
	/**
	 * 06 已付款
	 */
	public static String invoiceStatus_06 = "06";
	/**
	 * 07 已确认开错
	 */
	public static String invoiceStatus_07 = "07";
	/**
	 * 08 已寄出
	 */
	public static String invoiceStatus_08 = "08";
	/**
	 * 09 已收到
	 */
	public static String invoiceStatus_09 = "09";
	/**
	 * 10 已作废
	 */
	public static String invoiceStatus_10 = "10";
	/**
	 * 11 已红冲
	 */
	public static String invoiceStatus_11 = "11";
	/**
	 * 12 已重开
	 */
	public static String invoiceStatus_12 = "12";
	/**
	 * 01 已开具
	 */
	public static String invoiceStatus_str_01 = "已开具";
	/**
	 * 02 已检视
	 */
	public static String invoiceStatus_str_02 = "已检视";
	/**
	 * 03 已寄出
	 */
	public static String invoiceStatus_str_03 = "已寄出";
	/**
	 * 04 已收到
	 */
	public static String invoiceStatus_str_04 = "已收到";
	/**
	 * 05 已入账
	 */
	public static String invoiceStatus_str_05 = "已入账";
	/**
	 * 06 已付款
	 */
	public static String invoiceStatus_str_06 = "已付款";
	/**
	 * 07 已确认开错
	 */
	public static String invoiceStatus_str_07 = "已确认开错";
	/**
	 * 08 已寄出
	 */
	public static String invoiceStatus_str_08 = "已寄出(错)";
	/**
	 * 09 已收到
	 */
	public static String invoiceStatus_str_09 = "已收到(错)";
	/**
	 * 10 已作废
	 */
	public static String invoiceStatus_str_10 = "已作废";
	/**
	 * 11 已红冲
	 */
	public static String invoiceStatus_str_11 = "已红冲";
	/**
	 * 12 已重开
	 */
	public static String invoiceStatus_str_12 = "已重开";
	/**
	 * 数字转为中文
	 */
	public static String numParseStr(String num){
		if(null!=num||!StringUtils.isBlank(num)||StringUtils.isEmpty(num)){
			invoiceStatusMap.put(invoiceStatus_01, invoiceStatus_str_01);
			invoiceStatusMap.put(invoiceStatus_02, invoiceStatus_str_02);
			invoiceStatusMap.put(invoiceStatus_03, invoiceStatus_str_03);
			invoiceStatusMap.put(invoiceStatus_04, invoiceStatus_str_04);
			invoiceStatusMap.put(invoiceStatus_05, invoiceStatus_str_05);
			invoiceStatusMap.put(invoiceStatus_06, invoiceStatus_str_06);
			invoiceStatusMap.put(invoiceStatus_07, invoiceStatus_str_07);
			invoiceStatusMap.put(invoiceStatus_08, invoiceStatus_str_08);
			invoiceStatusMap.put(invoiceStatus_09, invoiceStatus_str_09);
			invoiceStatusMap.put(invoiceStatus_10, invoiceStatus_str_10);
			invoiceStatusMap.put(invoiceStatus_11, invoiceStatus_str_11);
			invoiceStatusMap.put(invoiceStatus_12, invoiceStatus_str_12);
			return invoiceStatusMap.get(num);
		}
		return invoiceStatus_str_01;
	}
	/**
	 * 中文转为数字
	 */
	public static String strParseNum(String str){
		if(null!=str||!StringUtils.isBlank(str)||StringUtils.isEmpty(str)){
			invoiceStatusMap.put(invoiceStatus_str_01, invoiceStatus_01);
			invoiceStatusMap.put(invoiceStatus_str_02, invoiceStatus_02);
			invoiceStatusMap.put(invoiceStatus_str_03, invoiceStatus_03);
			invoiceStatusMap.put(invoiceStatus_str_04, invoiceStatus_04);
			invoiceStatusMap.put(invoiceStatus_str_05, invoiceStatus_05);
			invoiceStatusMap.put(invoiceStatus_str_06, invoiceStatus_06);
			invoiceStatusMap.put(invoiceStatus_str_07, invoiceStatus_07);
			invoiceStatusMap.put(invoiceStatus_str_08, invoiceStatus_08);
			invoiceStatusMap.put(invoiceStatus_str_09, invoiceStatus_09);
			invoiceStatusMap.put(invoiceStatus_str_10, invoiceStatus_10);
			invoiceStatusMap.put(invoiceStatus_str_11, invoiceStatus_11);
			invoiceStatusMap.put(invoiceStatus_str_12, invoiceStatus_12);
			return invoiceStatusMap.get(str);
		}
		return invoiceStatus_01;
	}
	/**
	 * 下载合同模板名称
	 */
	public static String EXCEL_FILENAME = "合同信息表.xlsx";
	/**
	 * 下载发票模板名称
	 */
	public static String EXCEL_FILENAME_INVOICE = "发票信息表.xlsx";
	/**
	 * 发票类型_专用发票
	 */
	public static String SPECIAL_INVOICE_STR = "专用发票";
	/**
	 * 发票类型_普通发票
	 */
	public static String PLAIN_INVOICE_STR = "普通发票";
	/**
	 * 发票类型_专用发票
	 */
	public static String SPECIAL_INVOICE_NUM = "1";
	/**
	 * 发票类型_普通发票
	 */
	public static String PLAIN_INVOICE_NUM = "2";
	/**
	 * 发票类型number→String
	 * @param num
	 * @return
	 */
	public static String parseToStr(String num){
		
		if(StringUtils.isSpace(num)){
			return "";
		}
		
		Map<String,String> invoiceType = new HashMap<String,String>(); 
		
		invoiceType.put(SPECIAL_INVOICE_NUM, SPECIAL_INVOICE_STR);
		invoiceType.put(PLAIN_INVOICE_NUM, PLAIN_INVOICE_STR);
		
		return invoiceType.get(num);
	}
	/**
	 * 发票类型String→number
	 * @param num
	 * @return
	 */
	public static String parseToNum(String str){
		
		if(StringUtils.isSpace(str)){
			return "";
		}
		
		Map<String,String> invoiceType = new HashMap<String,String>(); 
		
		invoiceType.put(SPECIAL_INVOICE_STR,SPECIAL_INVOICE_NUM);
		invoiceType.put(PLAIN_INVOICE_STR,PLAIN_INVOICE_NUM);
		
		return invoiceType.get(str);
	}
}
