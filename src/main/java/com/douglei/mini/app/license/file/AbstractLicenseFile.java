package com.douglei.mini.app.license.file;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.douglei.mini.app.license.SignatureHandler;
import com.douglei.mini.license.client.LicenseFile;
import com.douglei.mini.license.client.property.SignatureProperty;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractLicenseFile extends LicenseFile{
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取授权文件类型
	 * @return
	 */
	protected abstract String getType();
	
	/**
	 * 获取授权文件默认的起始日期, 格式为yyyy-MM-dd, 不包括时分秒
	 * @param current
	 * @return
	 */
	protected String getStartDate(Date current) {
		return sdf.format(current);
	}
	
	/**
	 * 获取授权文件默认的截止日期, 格式为yyyy-MM-dd, 不包括时分秒
	 * @param current
	 * @param field
	 * @param amount
	 * @return
	 */
	protected String getDefaultExpiredDate(Date current, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(field, amount);
		return sdf.format(c.getTime());
	}
	
	/**
	 * 记录其他限制信息
	 * @param scanner
	 */
	public abstract void setOtherLimitInfo(Scanner scanner);
	
	/**
	 * 设置签名信息
	 */
	public void setSign(SignatureHandler signatureHandler) {
		signature = new SignatureProperty(signatureHandler.sign(getContentDigest()));
	}
	
	/**
	 * 获取授权文件的内容集合, 用于写入到授权文件中
	 * @return
	 */
	public List<String> getContents(){
		List<String> contents = new ArrayList<String>();
		contents.add(start.getContent());
		contents.add(expired.getContent());
		if(ip != null)
			contents.add(ip.getContent());
		if(mac != null)
			contents.add(mac.getContent());
		if(customs != null)
			customs.forEach(custom -> contents.add(custom.getContent()));
		contents.add(signature.getContent());
		return contents;
	}
	
	/**
	 * 获取授权文件名
	 * @return
	 */
	public String getFileName() {
		return getType() + '.' + expired.getValue() + suffix;
	}
}
