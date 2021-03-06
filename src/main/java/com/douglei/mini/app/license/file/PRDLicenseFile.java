package com.douglei.mini.app.license.file;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredDateProperty;
import com.douglei.mini.license.client.property.CustomProperty;
import com.douglei.mini.license.client.property.IpProperty;
import com.douglei.mini.license.client.property.MacProperty;
import com.douglei.tools.datatype.DateFormatUtil;
import com.douglei.mini.license.client.property.EffectiveDateProperty;

/**
 * 生产环境授权文件
 * @author DougLei
 */
public class PRDLicenseFile extends AbstractLicenseFile {

	protected PRDLicenseFile(String id) {
		super(id);
	}

	@Override
	protected String getType() {
		return "prd";
	}
	
	@Override
	public void setOtherLimitInfo(Scanner scanner) {
		System.out.println("\n下面开始录入授权文件中的其他信息, 如果想跳过某个配置项, 请输入skip（不区分大小写）并回车确认");
		System.out.println("---------------------------------");
		
		// 设置授权文件的起始日期
		Date current = new Date();
		effectiveDate = new EffectiveDateProperty(DateFormatUtil.format("yyyy-MM-dd", current));
		
		System.out.println("请输入授权文件的有效截止日期（yyyy-MM-dd）：");
		String expired_ = scanner.next();
		if("skip".equalsIgnoreCase(expired_))
			expired_ = getDefaultExpiredDate(current, Calendar.YEAR, 1);
		expiredDate = new ExpiredDateProperty(id.getValue(), expired_);
		
		System.out.println("请输入服务器ip地址（多个用英文模式的逗号分隔开）：");
		String ip_ = scanner.next();
		if(!"skip".equalsIgnoreCase(ip_)) 
			ip = new IpProperty(ip_);
		
		System.out.println("请输入服务器mac地址（多个用英文模式的逗号分隔开）：");
		String mac_ = scanner.next();
		if(!"skip".equalsIgnoreCase(mac_)) 
			mac = new MacProperty(mac_.toUpperCase());
		
		setCustomInfo(scanner);
	}
	
	/**
	 * 记录自定义授权信息
	 * @param scanner
	 */
	private void setCustomInfo(Scanner scanner) {
		System.out.println("\n下面开始录入自定义授权信息");
		System.out.println("---------------------------------");
		
		byte index = 1;
		String name;
		do {
			System.out.println("请输入第"+index+"个授权信息的name值（输入end（不区分大小写）并回车，可结束自定义授权信息的录入）: ");
			if("end".equalsIgnoreCase(name = scanner.next())) 
				break;
			System.out.println("请输入第"+index+"个授权信息的value值: ");
			if(customs == null)
				customs = new ArrayList<CustomProperty>();
			customs.add(new CustomProperty(name, scanner.next()));
			
			index++;
		}while(true);
	}
}
