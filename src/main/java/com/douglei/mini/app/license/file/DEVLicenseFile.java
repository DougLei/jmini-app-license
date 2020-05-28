package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;

/**
 * 开发环境授权文件
 * @author DougLei
 */
public class DEVLicenseFile extends AbstractLicenseFile {
	
	@Override
	protected String getType() {
		return "dev";
	}
	
	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		expired = new ExpiredProperty(getDefaultExpiredDate(Calendar.MONTH, 3));
	}
}
