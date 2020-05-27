package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.LicenseConstants;

/**
 * 生产环境授权文件
 * @author DougLei
 */
public class PRDLicenseFile extends LicenseFile {

	public PRDLicenseFile() {
		add(LicenseConstants.KEY_TYPE, "prd");
	}

	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		System.out.println("\n下面开始录入授权文件中的其他信息，如果想跳过某个配置项，请输入skip（不区分大小写）并回车确认");
		System.out.println("---------------------------------");
		
		System.out.println("请输入授权文件的有效日期（yyyy-MM-dd）：");
		String expired = scanner.next();
		if("skip".equalsIgnoreCase(expired))
			expired = getDefaultExpiredDate(Calendar.YEAR, 1);
		add(LicenseConstants.KEY_EXPIRED, expired);
		
		System.out.println("请输入服务器ip地址（多个用英文模式的逗号分隔开）：");
		String ip = scanner.next();
		if(!"skip".equalsIgnoreCase(ip)) 
			add(LicenseConstants.KEY_IP, ip);
		
		System.out.println("请输入服务器mac地址：");
		String mac = scanner.next();
		if(!"skip".equalsIgnoreCase(mac)) 
			add(LicenseConstants.KEY_MAC, mac);
	}
}
