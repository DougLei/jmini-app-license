package com.douglei.mini.app.license.file;

/**
 * 
 * @author DougLei
 */
public class LicenseFileFactory {
	
	/**
	 * 根据类型, 创建对应的授权文件实例
	 * @param type
	 * @return
	 */
	public static LicenseFile build(String type) {
		if("2".equals(type)) {
			return new DEVLicenseFile();
		}
		if("3".equals(type)) {
			return new PRDLicenseFile();
		}
		return new TEMPLicenseFile();
	}
}
