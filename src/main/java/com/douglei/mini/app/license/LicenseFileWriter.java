package com.douglei.mini.app.license;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.douglei.mini.app.license.file.AbstractLicenseFile;

/**
 * 授权文件编写器
 * @author DougLei
 */
class LicenseFileWriter {
	private final int privateKey = 0xAb;
	private final int separator = '\n'^privateKey;
	private File file;
	
	/**
	 * 将授权文件信息写入到文件中, 并关闭流
	 * @param licenseFile
	 * @throws IOException
	 */
	public void writeAndClose(AbstractLicenseFile licenseFile) throws IOException{
		file = new File(System.getProperty("user.home") + File.separatorChar + ".license-app" + File.separatorChar + licenseFile.getFileName());
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(privateKey);
		fos.write(separator);
		
		for(String content: licenseFile.getContents()) {
			for (byte b : content.getBytes()) {
				fos.write(b^privateKey);
			}
			fos.write(separator);
		}
		fos.close();
	}
	
	/**
	 * 获取授权文件实例
	 * @return
	 */
	public File getLicenseFile() {
		return file;
	}
}
