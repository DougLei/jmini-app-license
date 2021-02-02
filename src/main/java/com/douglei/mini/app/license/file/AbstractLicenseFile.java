package com.douglei.mini.app.license.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.douglei.mini.app.license.SignatureHandler;
import com.douglei.mini.license.client.LicenseFile;
import com.douglei.mini.license.client.property.IdProperty;
import com.douglei.mini.license.client.property.SignatureProperty;
import com.douglei.tools.datatype.DateFormatUtil;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractLicenseFile extends LicenseFile{
	private File file;
	
	protected AbstractLicenseFile(String id) {
		super.id = new IdProperty(id);
	}

	/**
	 * 获取授权文件类型
	 * @return
	 */
	protected abstract String getType();
	
	/**
	 * 获取授权文件默认的截止日期, 格式为yyyy-MM-dd, 不包括时分秒
	 * @param current
	 * @param field
	 * @param amount
	 * @return
	 */
	protected final String getDefaultExpiredDate(Date current, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.add(field, amount);
		return DateFormatUtil.format("yyyy-MM-dd", c.getTime());
	}
	
	/**
	 * 设置其他限制信息
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
		contents.add(id.getContent());
		contents.add(effectiveDate.getContent());
		contents.add(expiredDate.getContent());
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
	 * 获取授权文件
	 * @return
	 */
	public File getFile() {
		if(file == null)
			file = new File(System.getProperty("user.home") + File.separatorChar + ".license-app" + File.separatorChar + id.getValue() +'.'+ getType() + '.' + expiredDate.getValue() + suffix);
		return file;
	}
}
