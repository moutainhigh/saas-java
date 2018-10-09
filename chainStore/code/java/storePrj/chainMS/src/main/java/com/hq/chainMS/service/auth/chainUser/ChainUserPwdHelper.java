package com.hq.chainMS.service.auth.chainUser;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserPwdHelper {
	
	public static ChainUserPwdHelper getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserPwdHelper.class);
	}

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";

	public void encryptUser(ChainUser user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String encryptPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt())).toHex();
		user.setPassword(encryptPassword);
	}
	
	public String getEncryptPassword(ChainUser user, String password) {
		String encryptPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(user.getCredentialsSalt())).toHex();
		return encryptPassword;
	}
	
	public String getEncryptPassword(String password){
		return new SimpleHash(algorithmName, password).toHex();
	}

}
