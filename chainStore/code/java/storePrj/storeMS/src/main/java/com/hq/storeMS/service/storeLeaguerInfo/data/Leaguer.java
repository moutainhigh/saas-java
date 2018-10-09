package com.hq.storeMS.service.storeLeaguerInfo.data;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class Leaguer {
	/****************************简版信息**************************************/
	// 生成策略 storeId + cuserId每个店铺的会员都是C端的用户 所以storeId 与 cuserId必定构成唯一ID。
	private String id;
	// 客户姓名
	private String name;
	// 性别
	private int sex;
	// 手机号
	private long phone;
	//实体状态
	private int entityState;
	// 头像
	private String headImg;
	
	public static String genIdByStoreId(long storeId, long userId) {
		return StringFormatUtil.format(ServerConstants.ID_FORMAT, storeId, userId);
	}

	public static Leaguer newInstance() {
		return new Leaguer();
	}
	public static Leaguer newInstanceByDetail(LeaguerDetail detail) {
		Leaguer data = newInstance();
		data.setSex(detail.getSex());
		data.setName(detail.getName());
		data.setHeadImg(detail.getHeadImg());
		data.setPhone(detail.getPhone());
		data.setId(detail.getId());
		data.setEntityState(detail.getEntityState());
		return data;
	}
	
	@Override
	public String toString() {
		return "Leaguer [id=" + id + ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", entityState="
				+ entityState + ", headImg=" + headImg + "]";
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
