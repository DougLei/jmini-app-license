package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;

/**
 * 临时授权文件
 * @author DougLei
 */
public class TEMPLicenseFile extends AbstractLicenseFile {
	
	@Override
	protected String getType() {
		return "temp";
	}
	
	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		expired = new ExpiredProperty(getDefaultExpiredDate(Calendar.MONTH, 1));
	}
}
