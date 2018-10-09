package com.hq.storeMS.service.orderDetail.data;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class OrderBonusDetail {
	// 提成ID prdCardPayType_buyType_pgId_leaguerPrdCardId
	private String bonusId;
	// 类型 BuyTypeEnum
	private int buyType;
	// 项目ID、次卡ID、商品ID、套餐ID
	private String pgId;
	// 名称[项目、商品、套餐、次卡]
	private String pgName;
	//分类名称
	private String typeName;
	// 默认图片
	private String defaultImg;
	//价格
	private float price;
	// 支付方式 PrdCardPayEnum 划卡、购买、赠送
	private int payType;
	// 现结、赠送、划卡[卡名称]
	private String payName;
	//客户次卡ID
	private String leaguerPrdCardId;
	// 医美师提成
	private Map<Long, UserBonusDetail> userBonusMap = new HashMap<Long, UserBonusDetail>();

	public static OrderBonusDetail newInstance() {
		OrderBonusDetail data = new OrderBonusDetail();
		return data;
	}
	
	public static OrderBonusDetail newInstance(BonusInfo bonusInfo) {
		return AppUtils.copyBeanBySerialize(bonusInfo, OrderBonusDetail.class);
	}

	public String getBonusId() {
		return bonusId;
	}

	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

	public Map<Long, UserBonusDetail> getUserBonusMap() {
		return userBonusMap;
	}

	public void setUserBonusMap(Map<Long, UserBonusDetail> userBonusMap) {
		this.userBonusMap = userBonusMap;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
