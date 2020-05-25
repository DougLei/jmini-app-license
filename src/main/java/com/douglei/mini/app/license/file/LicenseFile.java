package com.douglei.mini.app.license.file;

import java.util.List;

/**
 * 
 * @author DougLei
 */
public abstract class LicenseFile {
	public static final String KEY_TYPE = "type";  
	public static final String KEY_EXPIRE = "expire";  
	public static final String KEY_IP = "ip";  
	public static final String KEY_MAC = "mac";
	
	protected List<String> values;

	/**
	 * 是否存在限制
	 * @return
	 */
	public boolean existsLimit() {
		return false;
	}
}
