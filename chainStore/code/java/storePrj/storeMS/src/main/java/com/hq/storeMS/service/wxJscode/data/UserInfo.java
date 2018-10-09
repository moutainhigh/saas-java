package com.hq.storeMS.service.wxJscode.data;

import java.util.HashMap;
import java.util.Map;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 微信用户的加密信息，解密的结构实体 { "openId": "OPENID", "nickName": "NICKNAME", "gender":
 * GENDER, "city": "CITY", "province": "PROVINCE", "country": "COUNTRY",
 * "avatarUrl": "AVATARURL", "unionId": "UNIONID", "watermark": { "appid":"APPID", "timestamp":TIMESTAMP } }
 * 
 * @author kevin
 *
 */
@SynClass
public class UserInfo {
	private String openId;
	private String nickName;
	private int gender;
	private String city;
	private String province;
	private String country;
	private String avatarUrl;
	private String unionId;
	private Map<String, String> watermark = new HashMap<String, String>();

	public static UserInfo newInstance() {
		UserInfo data = new UserInfo();
		return data;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Map<String, String> getWatermark() {
		return watermark;
	}

	public void setWatermark(Map<String, String> watermark) {
		this.watermark = watermark;
	}

}
