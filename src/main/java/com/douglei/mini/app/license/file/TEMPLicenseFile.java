package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredDateProperty;
import com.douglei.mini.license.client.property.EffectiveDateProperty;

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
		effectiveDate = new EffectiveDateProperty(getStartDate(current));
		expiredDate = new ExpiredDateProperty(getDefaultExpiredDate(current, Calendar.DAY_OF_YEAR, 30));
	}
}
