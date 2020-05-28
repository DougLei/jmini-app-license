package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;
import com.douglei.mini.license.client.property.TypeProperty;

/**
 * 开发环境授权文件
 * @author DougLei
 */
public class DEVLicenseFile extends LicenseFile {
	
	public DEVLicenseFile() {
		type = new TypeProperty("dev");
	}

	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		expired = new ExpiredProperty(getDefaultExpiredDate(Calendar.MONTH, 3));
	}
}
