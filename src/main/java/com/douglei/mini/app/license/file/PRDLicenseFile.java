package com.douglei.mini.app.license.file;

import java.util.Calendar;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;
import com.douglei.mini.license.client.property.IpProperty;
import com.douglei.mini.license.client.property.MacProperty;

/**
 * 生产环境授权文件
 * @author DougLei
 */
public class PRDLicenseFile extends AbstractLicenseFile {

	@Override
	protected String getType() {
		return "prd";
	}
	
	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		System.out.println("\n下面开始录入授权文件中的其他信息，如果想跳过某个配置项，请输入skip（不区分大小写）并回车确认");
		System.out.println("---------------------------------");
		
		System.out.println("请输入授权文件的有效日期（yyyy-MM-dd）：");
		String expired_ = scanner.next();
		if("skip".equalsIgnoreCase(expired_))
			expired_ = getDefaultExpiredDate(Calendar.YEAR, 1);
		expired = new ExpiredProperty(expired_);
		
		System.out.println("请输入服务器ip地址（多个用英文模式的逗号分隔开）：");
		String ip_ = scanner.next();
		if(!"skip".equalsIgnoreCase(ip_)) 
			ip = new IpProperty(ip_);
		
		System.out.println("请输入服务器mac地址（多个用英文模式的逗号分隔开）：");
		String mac_ = scanner.next();
		if(!"skip".equalsIgnoreCase(mac_)) 
			mac = new MacProperty(mac_);
	}
}
