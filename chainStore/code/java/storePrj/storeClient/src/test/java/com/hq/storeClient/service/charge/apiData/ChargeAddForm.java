package com.hq.storeClient.service.charge.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.charge.data.ChargePayItem;

public class ChargeAddForm {
	// 商户id
	private long buserId;
	// 商户手机号
	private long phone;
	// 商户姓名
	private String name;
	// 类型 ChargeTypeEnum 续费、升级、普通
	private int chargeType;
	// 等级时长 12月
	private int validPeriod;
	// 单位 对应 ValidPeriodUnitEnum
	private int validPeriodUnit;
	// 抵扣金额
	private long offsetAmount;
	// 优惠金额
	private long discountAmount;
	// 会员等级id
	private long vipLevelId;
	// 会员等级名称
	private String vipLevelName;
	// 到期时间
	private long expiredTime;
	// 代理区域
	private String agencyArea;
	// 代理姓名
	private String agencyName;
	// 代理电话
	private String agencyPhone;
	// 备注
	private String remark;
	// 来源 ChargeOriginEnum
	private int origin;
	// 支付明细
	private List<ChargePayItem> payItems = new ArrayList<ChargePayItem>();

	public static ChargeAddForm newInstance() {
		ChargeAddForm data = new ChargeAddForm();
		return data;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChargeType() {
		return chargeType;
	}

	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}

	public int getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(int validPeriod) {
		this.validPeriod = validPeriod;
	}

	public int getValidPeriodUnit() {
		return validPeriodUnit;
	}

	public void setValidPeriodUnit(int validPeriodUnit) {
		this.validPeriodUnit = validPeriodUnit;
	}

	public long getOffsetAmount() {
		return offsetAmount;
	}

	public void setOffsetAmount(long offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	public long getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(long discountAmount) {
		this.discountAmount = discountAmount;
	}

	public long getVipLevelId() {
		return vipLevelId;
	}

	public void setVipLevelId(long vipLevelId) {
		this.vipLevelId = vipLevelId;
	}

	public String getVipLevelName() {
		return vipLevelName;
	}

	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getAgencyArea() {
		return agencyArea;
	}

	public void setAgencyArea(String agencyArea) {
		this.agencyArea = agencyArea;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyPhone() {
		return agencyPhone;
	}

	public void setAgencyPhone(String agencyPhone) {
		this.agencyPhone = agencyPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public List<ChargePayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<ChargePayItem> payItems) {
		this.payItems = payItems;
	}
}
