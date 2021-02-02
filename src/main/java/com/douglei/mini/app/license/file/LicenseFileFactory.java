package com.douglei.mini.app.license.file;

/**
 * 
 * @author DougLei
 */
public class LicenseFileFactory {
	
	/**
	 * 根据类型, 创建对应的授权文件实例
	 * @param id
	 * @param type
	 * @return
	 */
	public static AbstractLicenseFile build(String id, String type) {
		if("2".equals(type)) {
			return new DEVLicenseFile(id);
		}
		if("3".equals(type)) {
			return new PRDLicenseFile(id);
		}
		return new TEMPLicenseFile(id);
	}
}
