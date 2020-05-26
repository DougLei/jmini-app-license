package com.douglei.mini.app.license;

import java.io.IOException;
import java.util.Scanner;

import com.douglei.mini.app.license.file.LicenseFile;
import com.douglei.mini.app.license.file.LicenseFileFactory;

/**
 * 授权小程序
 * @author DougLei
 */
public class LicenseApp {
	static final Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("---------------------------------");
		System.out.println("欢迎进入授权小程序");
		System.out.println("---------------------------------\n");
		
		System.out.println("你是否持有密钥， 请输入yes/no（不区分大小写）：");
		SignatureHandler signatureHandler = null;
		String input;
		do {
			input = scanner.next();
			if("yes".equalsIgnoreCase(input)) {
				System.out.println("请输入你的密钥：");
				signatureHandler = new SignatureHandler(scanner.next());
				break;
			}
			if("no".equalsIgnoreCase(input)) {
				System.out.println("请输入种子信息，系统将会根据你的种子信息，为你生成公/私钥：");
				signatureHandler = new SignatureHandler(new KeyPairGenerator().genKeyPair2File(scanner.next()));
				break;
			}
			System.out.println("请输入正确的值");
		}while(true);
		
		System.out.println("\n---------------------------------");
		System.out.println("请选择授权文件的类型（默认值为1）：");
		System.out.println("1. 临时授权文件，有效期固定一个月，没有其他限制");
		System.out.println("2. 开发环境的授权文件，有效期固定三个月，没有其他限制");
		System.out.println("3. 生产环境的授权文件，有效期默认一年（可调整），同时可以追加其他限制");
		System.out.println("---------------------------------");
		LicenseFile licenseFile = LicenseFileFactory.build(scanner.next());
		licenseFile.setOtherLimitInfo(scanner);
		licenseFile.setSign(signatureHandler);
		
		try {
			LicenseFileWriter writer = new LicenseFileWriter();
			writer.writeAndClose(licenseFile);
			System.out.println("授权文件生成结束， 请按照以下路径去获取你的授权文件 ========> " + writer.getLicenseFile());
			System.out.println("确认后， 直接关闭该窗口来结束程序");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("你的程序出问题了， 不要关闭该窗口， 并联系开发人员");
			scanner.next();
		}
		scanner.next();
	}
}
