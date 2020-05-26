package com.douglei.mini.app.license.file;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.douglei.mini.app.license.SignatureHandler;
import com.douglei.mini.license.client.LicenseKeyConstants;

/**
 * 
 * @author DougLei
 */
public abstract class LicenseFile {
	private StringBuilder content = new StringBuilder(160); // 记录授权文件中的内容, 签名用
	private List<String> values = new ArrayList<String>(); // 记录授权文件中, 每行数据的内容集合
	
	/**
	 * 设置签名信息
	 */
	public void setSign(SignatureHandler signatureHandler) {
		System.out.println(signatureHandler.sign(content.toString()));
		add(LicenseKeyConstants.KEY_SIGNATURE, signatureHandler.sign(content.toString()));
	}
	
	/**
	 * 记录其他限制信息
	 * @param scanner
	 */
	public abstract void setOtherLimitInfo(Scanner scanner);
	
	/**
	 * 添加授权文件中的信息键值对
	 * @param key
	 * @param value
	 */
	protected void add(String key, String value) {
		values.add(key + '=' + value);
		content.append(values.get(values.size()-1));
	}
	
	/**
	 * 获取授权文件默认的截止日期, 格式为yyyy-MM-dd, 不包括时分秒
	 * @param field
	 * @param amount
	 * @return
	 */
	protected String getDefaultExpiredDate(int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(field, amount);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getValues() {
		return values;
	}
}
