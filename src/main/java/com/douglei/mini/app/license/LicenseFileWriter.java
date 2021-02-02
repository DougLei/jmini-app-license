package com.douglei.mini.app.license;

import java.io.FileOutputStream;
import java.io.IOException;

import com.douglei.mini.app.license.file.AbstractLicenseFile;

/**
 * 授权文件编写器
 * @author DougLei
 */
class LicenseFileWriter implements AutoCloseable{
	private final int privateKey = 0xAb;
	private final int separator = '\n'^privateKey;
	private FileOutputStream fos;
	
	/**
	 * 将授权文件信息写入到文件
	 * @param licenseFile
	 * @throws IOException
	 */
	public void write(AbstractLicenseFile licenseFile) throws IOException{
		fos = new FileOutputStream(licenseFile.getFile());
		fos.write(privateKey);
		fos.write(separator);
		
		for(String content: licenseFile.getContents()) {
			for (byte b : content.getBytes()) 
				fos.write(b^privateKey);
			fos.write(separator);
		}
	}
	
	@Override
	public void close() throws Exception {
		if(fos != null) {
			fos.close();
			fos = null;
		}
	}
}
