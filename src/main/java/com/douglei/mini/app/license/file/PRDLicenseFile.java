package com.douglei.mini.app.license.file;

/**
 * 生产环境授权文件
 * @author DougLei
 */
public class PRDLicenseFile extends LicenseFile {

	@Override
	public boolean existsLimit() {
		return true;
	}
}
