package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;
import com.douglei.mini.license.client.property.TypeProperty;

/**
 * 临时授权文件
 * @author DougLei
 */
public class TEMPLicenseFile extends LicenseFile {
	
	public TEMPLicenseFile() {
		type = new TypeProperty("temp");
	}

	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		expired = new ExpiredProperty(getDefaultExpiredDate(Calendar.MONTH, 1));
	}
}
