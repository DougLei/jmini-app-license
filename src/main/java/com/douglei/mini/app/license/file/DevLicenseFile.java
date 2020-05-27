package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.LicenseKeyConstants;

/**
 * 开发环境授权文件
 * @author DougLei
 */
public class DevLicenseFile extends LicenseFile {
	
	public DevLicenseFile() {
		add(LicenseKeyConstants.TYPE, "dev");
	}

	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		add(LicenseKeyConstants.EXPIRED, getDefaultExpiredDate(Calendar.MONTH, 3));
	}
}
