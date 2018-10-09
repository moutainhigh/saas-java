package com.hq.storeMS.service.wxJscode.data;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class WxUserInfoAESUtil {
	public static WxUserInfoAESUtil getInstance() {
		return HotSwap.getInstance().getSingleton(WxUserInfoAESUtil.class);
	}

	public UserInfo getUserInfo(String sessionKey, String encryptedData, String iv) {
		try {
			String result = decryptWXAppletInfo(sessionKey, encryptedData, iv);
			return JsonUtil.getInstance().fromJson(result, UserInfo.class);
		} catch (Exception e) {
			MainLog.error(LogModule.WeChat, "WxUserInfoAESUtil[getUserInfo]", "", e);
		}
		return null;
	}

	private String decryptWXAppletInfo(String sessionKey, String encryptedData, String iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String result = null;
		byte[] encrypData = Base64.decodeBase64(encryptedData);
		byte[] ivData = Base64.decodeBase64(iv);
		byte[] sessionKeyB = Base64.decodeBase64(sessionKey);

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(sessionKeyB, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		byte[] doFinal = cipher.doFinal(encrypData);
		result = new String(doFinal);
		return result;
	}

	public static void main(String[] args) {
		String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
		String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
		String iv = "r7BXXKkLb8qrSNn05n0qiA==";
		
		UserInfo userInfo = WxUserInfoAESUtil.getInstance().getUserInfo(sessionKey, encryptedData, iv);
		System.out.println(userInfo.getUnionId());
	}
}