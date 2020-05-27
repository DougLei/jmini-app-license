package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.LicenseFileProperty;

/**
 * 开发环境授权文件
 * @author DougLei
 */
public class DEVLicenseFile extends LicenseFile {
	
	public DEVLicenseFile() {
		add(LicenseFileProperty.TYPE, "dev");
	}

	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		add(LicenseFileProperty.EXPIRED, getDefaultExpiredDate(Calendar.MONTH, 3));
	}
}
