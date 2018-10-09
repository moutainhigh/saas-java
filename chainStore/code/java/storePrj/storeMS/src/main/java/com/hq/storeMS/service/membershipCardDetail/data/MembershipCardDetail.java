package com.hq.storeMS.service.membershipCardDetail.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "membershipCardDetail")
public class MembershipCardDetail {
	// _mem_storeId_index
	@Id
	private String id;
	@IndexField
	private long storeId;
	// 会员卡编号
	private String number;
	// 名称
	private String name;
	// 赠送金额
	private float freeMoney;
	// 项目折扣
	private float prodDiscount=10.0f;
	// 商品折扣
	private float goodsDiscount=10.0f;
	// 次卡折扣
	private float prdCardDiscount=10.0f;
	// 套餐折扣
	private float packagePrjDiscount=10.0f;
	// 卡片状态 对应 CardStatusEnum
	private int status;
	private int entityState;
	// 描述
	private String descript;
	// 图片
	private String imgPath;
	// 来源 对应 DataOriginEnum
	private int origin;

	@IndexField
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static MembershipCardDetail newInstance() {
		MembershipCardDetail data = new MembershipCardDetail();
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}

	public static MembershipCardDetail newInstanceByMembershipCard(MembershipCard data, long storeId) {
		MembershipCardDetail result = AppUtils.copyBeanBySerialize(data, MembershipCardDetail.class);
		result.storeId = storeId;
		return result;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getFreeMoney() {
		return freeMoney;
	}

	public void setFreeMoney(float freeMoney) {
		this.freeMoney = freeMoney;
	}

	public float getProdDiscount() {
		return prodDiscount;
	}

	public void setProdDiscount(float prodDiscount) {
		this.prodDiscount = prodDiscount;
	}

	public float getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(float goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	public float getPrdCardDiscount() {
		return prdCardDiscount;
	}

	public void setPrdCardDiscount(float prdCardDiscount) {
		this.prdCardDiscount = prdCardDiscount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public float getPackagePrjDiscount() {
		return packagePrjDiscount;
	}

	public void setPackagePrjDiscount(float packagePrjDiscount) {
		this.packagePrjDiscount = packagePrjDiscount;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

}
