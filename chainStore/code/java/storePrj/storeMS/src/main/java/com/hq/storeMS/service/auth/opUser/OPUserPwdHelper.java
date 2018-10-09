package com.hq.storeMS.service.auth.opUser;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hq.storeMS.service.opuser.data.OPUser;
import com.zenmind.common.hotSwap.HotSwap;

public class OPUserPwdHelper {
	
	public static OPUserPwdHelper getInstance(){
		return HotSwap.getInstance().getSingleton(OPUserPwdHelper.class);
	}

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";

	public void encryptUser(OPUser user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		
		String encryptPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt())).toHex();
		user.setPassword(encryptPassword);
	}
	
	public String getEncryptPassword(OPUser user,String password) {
		String encryptPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(user.getCredentialsSalt())).toHex();
		return encryptPassword;
	}

}
