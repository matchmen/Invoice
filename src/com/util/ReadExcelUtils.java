package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import com.exception.SystemException;
import com.model.Contract;

public class ReadExcelUtils {
	
	/**
	 * 读取excel生成Contract
	 * @param url
	 * @return
	 */
	public Contract getContract(String url){
		
		
		
		return null;
	}
	
	public static InputStream  getExcelByte() throws SystemException{
		
		try {
			return new BufferedInputStream(new FileInputStream(new File("WebContent/download/合同信息表.xlsx")));
		} catch (FileNotFoundException e) {
			throw new SystemException("error", "文件不存在!");
		}
	}
}
