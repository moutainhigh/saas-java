package com.hq.storeMS.service.workFlowData.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class BonusInfo {
	// 提成ID prdCardPayType_buyType_pgId_leaguerPrdCardId
	private String bonusId;
	// 是否划卡结算 对应 PrdCardPayEnum
	private int prdCardPayType;
	// 消费购买的类型 BuyTypeEnum 次卡、项目、商品、套餐
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	// 客户的次卡ID
	private String leaguerPrdCardId;
	// 医美师提成
	private Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();

	/****************************** 遗留字段 ******************************/
	// 次卡ID
	private String productCardId;

	public static BonusInfo newInstance() {
		return new BonusInfo();
	}

	// 根据赠送项 生成提成ID
	public static String genBonusIdByDonationItem(BuyTypeEnum buyTypeEnum, String pgId) {
		return genBonusId(PrdCardPayEnum.Donation.ordinal(), buyTypeEnum.ordinal(), pgId, "");
	}

	// 根据购买项 生成提成ID
	public static String genBonusIdByBuyItem(BuyTypeEnum buyTypeEnum, String pgId) {
		return genBonusId(PrdCardPayEnum.CashPay.ordinal(), buyTypeEnum.ordinal(), pgId, "");
	}

	// 根据划卡信息 生成提成ID
	public static String genBonusIdByDecreasePrdCard(String pgId, String leaguerPrdCardId) {
		return genBonusId(PrdCardPayEnum.PrdCard.ordinal(), BuyTypeEnum.PRODUCT.ordinal(), pgId, leaguerPrdCardId);
	}

	// 提成ID 也是购买项/赠送项的ID
	public static String genBonusId(int prdCardPayType, int buyType, String pgId, String leaguerPrdCardId) {
		if (StringUtils.isBlank(pgId)) {
			pgId = "";
		}
		if (StringUtils.isBlank(leaguerPrdCardId)) {
			leaguerPrdCardId = "";
		}
		// 支付方式_购买类型_购买项ID_次卡ID
		return StringFormatUtil.format("{}_{}_{}_{}", prdCardPayType, buyType, pgId, leaguerPrdCardId);
	}

	public BonusInfoAddForm toBonusInfoAddForm() {
		return AppUtils.copyBeanBySerialize(this, BonusInfoAddForm.class);
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

	public int getPrdCardPayType() {
		return prdCardPayType;
	}

	public void setPrdCardPayType(int prdCardPayType) {
		this.prdCardPayType = prdCardPayType;
	}

	public Map<Long, UserBonus> getUserBonusMap() {
		return userBonusMap;
	}

	public void setUserBonusMap(Map<Long, UserBonus> userBonusMap) {
		this.userBonusMap = userBonusMap;
	}

	public String getProductCardId() {
		return productCardId;
	}

	public void setProductCardId(String productCardId) {
		this.productCardId = productCardId;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

}
