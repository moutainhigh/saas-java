package com.hq.customerMS.service.auth.cuser;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hq.customerMS.service.cuser.data.CUser;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserPwdHelper {

	public static CUserPwdHelper getInstance() {
		return HotSwap.getInstance().getSingleton(CUserPwdHelper.class);
	}

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";

	public void encryptUser(CUser cuser) {
		cuser.setSalt(randomNumberGenerator.nextBytes().toHex());

		String encryptPassword = new SimpleHash(algorithmName, cuser.getPassword(),
				ByteSource.Util.bytes(cuser.getCredentialsSalt())).toHex();
		cuser.setPassword(encryptPassword);
	}

	public String getEncryptPassword(CUser cuser, String password) {
		String encryptPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(cuser.getCredentialsSalt())).toHex();
		return encryptPassword;
	}
}
