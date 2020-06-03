package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;
import com.douglei.mini.license.client.property.StartProperty;

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
		Date current = new Date();
		start = new StartProperty(getStartDate(current));
		expired = new ExpiredProperty(getDefaultExpiredDate(current, Calendar.DAY_OF_YEAR, 90));
	}
}
