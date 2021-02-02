package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredDateProperty;
import com.douglei.tools.datatype.DateFormatUtil;
import com.douglei.mini.license.client.property.EffectiveDateProperty;

/**
 * 临时授权文件
 * @author DougLei
 */
public class TEMPLicenseFile extends AbstractLicenseFile {
	
	protected TEMPLicenseFile(String id) {
		super(id);
	}

	@Override
	protected String getType() {
		return "temp";
	}
	
	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		Date current = new Date();
		effectiveDate = new EffectiveDateProperty(DateFormatUtil.format("yyyy-MM-dd", current));
		expiredDate = new ExpiredDateProperty(id.getValue(), getDefaultExpiredDate(current, Calendar.DAY_OF_YEAR, 30));
	}
}
