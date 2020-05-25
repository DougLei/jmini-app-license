package com.douglei.mini.app.license.file;

import java.util.List;

/**
 * 
 * @author DougLei
 */
public abstract class LicenseFile {
	protected List<String> values;

	/**
	 * 是否存在限制
	 * @return
	 */
	public boolean existsLimit() {
		return false;
	}
}
