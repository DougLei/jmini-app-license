package com.douglei.mini.app.license;

import java.io.File;
import java.io.IOException;

import com.douglei.tools.instances.file.writer.FileBufferedWriter;

/**
 * 授权文件编写器
 * @author DougLei
 */
class LicenseFileWriter {
	private File licenseFile;
	private FileBufferedWriter licenseFileWriter;
	private StringBuilder content;
	
	public LicenseFileWriter() {
		licenseFile = new File(System.getProperty("user.home") + File.separatorChar + "License" + File.separatorChar + "license.lf");
		licenseFileWriter = new FileBufferedWriter(licenseFile);
		content = new StringBuilder(100);
	}

	/**
	 * 写入key和value
	 * @param key
	 * @param value
	 * @throws IOException 
	 */
	public void write(String key, String value) throws IOException {
		licenseFileWriter.write(key + '=' + value);
		content.append(key).append('=').append(value);
	}
	
	/**
	 * 写入签名
	 * @param key
	 * @param signatureHandler 签名处理器
	 * @throws IOException 
	 */
	public void writeSign(String key, SignatureHandler signatureHandler) throws IOException {
		licenseFileWriter.writeln(key + '=' + signatureHandler.sign(content.toString()));
	}

	/**
	 * 关闭编写器
	 */
	public void close() {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * 获取授权文件实例
	 * @return
	 */
	public File getLicenseFile() {
		return licenseFile;
	}
}
