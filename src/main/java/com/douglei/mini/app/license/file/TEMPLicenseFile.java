package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;
import com.douglei.mini.license.client.property.StartProperty;

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
		Date current = new Date();
		start = new StartProperty(getStartDate(current));
		expired = new ExpiredProperty(getDefaultExpiredDate(current, Calendar.MONTH, 1));
	}
}
