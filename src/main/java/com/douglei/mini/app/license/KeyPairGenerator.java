package com.douglei.mini.app.license;

import java.io.File;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.util.Base64;

import com.douglei.tools.instances.file.writer.FileBufferedWriter;

/**
 * 公/私钥生成器
 * @author DougLei
 */
class KeyPairGenerator {
	
	/**
	 * 生成公/私钥到文件中
	 * @param seed
	 * @return 返回私钥数据
	 */
	public byte[] genKeyPair2File(String seed) {
		try {
			java.security.KeyPairGenerator keyPairGenerator = java.security.KeyPairGenerator.getInstance("RSA");
			SecureRandom secureRandom = new SecureRandom(seed.getBytes());
			keyPairGenerator.initialize(1024, secureRandom); // 初始化密码对生成器, 密钥大小的范围为:96~1024
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			
			File publicKeyFile = new File(System.getProperty("user.home") + File.separatorChar + ".license-app" + File.separatorChar + "public.key（公钥-公开）.txt");
			FileBufferedWriter publicKeyFileWriter = new FileBufferedWriter(publicKeyFile);
			publicKeyFileWriter.write(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
			publicKeyFileWriter.close();
			
			
			File privateKeyFile = new File(System.getProperty("user.home") + File.separatorChar + ".license-app" + File.separatorChar + "private.key（私钥-绝对保密）.txt");
			byte[] privateKey = keyPair.getPrivate().getEncoded();
			FileBufferedWriter privateKeyFileWriter = new FileBufferedWriter(privateKeyFile);
			privateKeyFileWriter.write(Base64.getEncoder().encodeToString(privateKey));
			privateKeyFileWriter.close();
			
			System.out.println("公钥生成结束， 请按照以下路径去获取你的公钥 ========> " + publicKeyFile);
			System.out.println("私钥生成结束， 请按照以下路径去获取你的私钥 ========> " + privateKeyFile);
			System.out.println("注意： 请剪切走私钥文件， 一定要防止私钥文件被其他人获取到");
			
			return privateKey;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("你的程序出问题了， 不要关闭该窗口， 并联系开发人员");
			LicenseApp.scanner.next();
			return null;
		} 
	}
}
