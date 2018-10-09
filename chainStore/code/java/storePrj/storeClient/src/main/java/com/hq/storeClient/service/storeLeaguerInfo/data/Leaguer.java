package com.hq.storeClient.service.storeLeaguerInfo.data;

import com.hq.storeClient.common.constants.StoreClientConstants;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class Leaguer {
	// 生成策略 storeId + cuserId每个店铺的会员都是C端的用户 所以storeId 与 cuserId必定构成唯一ID。
	private String id;
	// 客户姓名
	private String name;
	// 性别
	private int sex;
	// 手机号
	private long phone;
	// 实体状态
	private int entityState;
	// 头像
	private String headImg;

	public static String genIdByStoreId(long storeId, long userId) {
		return StringFormatUtil.format(StoreClientConstants.ID_FORMAT, storeId, userId);
	}

	public static Leaguer newInstance() {
		Leaguer data = new Leaguer();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
