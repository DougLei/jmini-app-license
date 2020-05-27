package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.LicenseKeyConstants;

/**
 * 临时授权文件
 * @author DougLei
 */
public class TEMPLicenseFile extends LicenseFile {
	
	public TEMPLicenseFile() {
		add(LicenseKeyConstants.TYPE, "temp");
	}

	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		add(LicenseKeyConstants.EXPIRED, getDefaultExpiredDate(Calendar.MONTH, 1));
	}
}
