package com.hq.storeManagerMS.service.auth.muser;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hq.storeManagerMS.service.muser.data.MUser;
import com.hq.storeManagerMS.service.muser.data.MUserRO;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserPwdHelper {
	
	public static MUserPwdHelper getInstance(){
		return HotSwap.getInstance().getSingleton(MUserPwdHelper.class);
	}

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";

	public void encryptUser(MUser muser) {
		muser.setSalt(randomNumberGenerator.nextBytes().toHex());
		String encryptPassword = new SimpleHash(algorithmName, muser.getPassword(),
				ByteSource.Util.bytes(muser.getCredentialsSalt())).toHex();
		muser.setPassword(encryptPassword);
	}
	
	public String getEncryptPassword(MUserRO muser,String password) {
		String encryptPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(muser.getCredentialsSalt())).toHex();
		return encryptPassword;
	}
}
