package com.douglei.mini.app.license.file;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.douglei.mini.license.client.property.ExpiredProperty;
import com.douglei.mini.license.client.property.ExtProperty;
import com.douglei.mini.license.client.property.IpProperty;
import com.douglei.mini.license.client.property.MacProperty;
import com.douglei.mini.license.client.property.StartProperty;
import com.douglei.mini.license.client.property.ValidateMode;

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
		
		// 设置授权文件的起始日期
		Date current = new Date();
		start = new StartProperty(getStartDate(current));
		
		System.out.println("请输入授权文件的有效日期（yyyy-MM-dd）：");
		String expired_ = scanner.next();
		if("skip".equalsIgnoreCase(expired_))
			expired_ = getDefaultExpiredDate(current, Calendar.YEAR, 1);
		expired = new ExpiredProperty(expired_);
		
		System.out.println("请输入服务器ip地址（多个用英文模式的逗号分隔开）：");
		String ip_ = scanner.next();
		if(!"skip".equalsIgnoreCase(ip_)) 
			ip = new IpProperty(ip_);
		
		System.out.println("请输入服务器mac地址（多个用英文模式的逗号分隔开）：");
		String mac_ = scanner.next();
		if(!"skip".equalsIgnoreCase(mac_)) 
			mac = new MacProperty(mac_.toUpperCase());
		
		setExtLimitInfo(scanner);
	}
	
	/**
	 * 记录其他扩展信息
	 * @param scanner
	 */
	private void setExtLimitInfo(Scanner scanner) {
		System.out.println("\n下面开始录入扩展信息");
		System.out.println("---------------------------------");
		
		byte index = 1;
		String key, value;
		do {
			System.out.println("请输入第"+index+"个扩展信息的key值（输入skip（不区分大小写）并回车，可结束扩展信息的录入）: ");
			if("skip".equalsIgnoreCase(key = scanner.next())) 
				break;
			System.out.println("请输入第"+index+"个扩展信息的value值: ");
			value = scanner.next();
			System.out.println("请输入第"+index+"个扩展信息的验证模式: 1.启动验证，2.定时验证，3.均需验证");
			addExts(key, value, toVM(scanner.nextInt()));
			index++;
		}while(true);
	}
	
	/**
	 * 获取验证模式
	 * @param vmNum
	 * @return
	 */
	private ValidateMode toVM(int vmNum) {
		if(vmNum == 1)
			return ValidateMode.START;
		if(vmNum == 2)
			return ValidateMode.TIMER;
		return ValidateMode.ALL;
	}
	
	/**
	 * 添加扩展信息
	 * @param key
	 * @param value
	 * @param vm
	 */
	private void addExts(String key, String value, ValidateMode vm) {
		if(exts == null)
			exts = new ArrayList<ExtProperty>();
		exts.add(new ExtProperty(key, value));
		exts.add(new ExtProperty(key+'.'+ValidateMode.KEY_SUFFIX, vm.name()));
	}
}
