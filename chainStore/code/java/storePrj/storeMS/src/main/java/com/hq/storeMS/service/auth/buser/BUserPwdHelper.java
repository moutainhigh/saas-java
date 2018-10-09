package com.hq.storeMS.service.auth.buser;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hq.storeMS.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserPwdHelper {
	
	public static BUserPwdHelper getInstance(){
		return HotSwap.getInstance().getSingleton(BUserPwdHelper.class);
	}

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";

	public void encryptUser(BUser user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		
		String encryptPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt())).toHex();
		user.setPassword(encryptPassword);
	}
	
	public String getEncryptPassword(BUser user,String password) {
		String encryptPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(user.getCredentialsSalt())).toHex();
		return encryptPassword;
	}
	
	/**
	 * 
	 * getEncryptPassword:(对字符串密码进行加密). <br/>   
	 *  
	 * @author kevin
	 * @param password
	 * @return  
	 * @since JDK 1.6
	 */
	public String getEncryptPassword(String password){
		return new SimpleHash(algorithmName, password).toHex();
	}

}
