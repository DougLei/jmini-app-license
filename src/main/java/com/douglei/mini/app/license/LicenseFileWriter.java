package com.douglei.mini.app.license;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.douglei.mini.app.license.file.LicenseFile;

/**
 * 授权文件编写器
 * @author DougLei
 */
class LicenseFileWriter {
	private final int privateKey = 0xAB;
	private File file;
	
	public LicenseFileWriter() {
		file = new File(System.getProperty("user.home") + File.separatorChar + "License" + File.separatorChar + "license.lf");
	}
	
	/**
	 * 将授权文件信息写入到文件中, 并关闭流
	 * @param licenseFile
	 * @throws IOException
	 */
	public void writeAndClose(LicenseFile licenseFile) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(privateKey);
		
		for(String line: licenseFile.getValues()) {
			for (byte lb : line.getBytes()) {
				fos.write(lb^privateKey);
			}
			fos.write('\t'^privateKey);
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
