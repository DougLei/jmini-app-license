package com.douglei.mini.app.license;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import com.douglei.mini.license.client.Util;

/**
 * 签名处理器
 * @author DougLei
 */
public class SignatureHandler {
	private byte[] privateKey;
	
	public SignatureHandler(String privateKey) {
		this(Base64.getDecoder().decode(privateKey));
	}
	public SignatureHandler(byte[] privateKey) {
		this.privateKey = privateKey;
	}
	
	/**
	 * 使用私钥对授权文件进行签名
	 * @param content 授权文件的内容
	 * @return 签名内容
	 */
	public String sign(String content) {
		try {
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign((RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKey)));
			signature.update(Util.getDigest(content));
			
			return Base64.getEncoder().encodeToString(signature.sign());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("你的程序出问题了， 不要关闭该窗口， 并联系开发人员");
			LicenseApp.scanner.next();
			return null;
		} 
	}
}
