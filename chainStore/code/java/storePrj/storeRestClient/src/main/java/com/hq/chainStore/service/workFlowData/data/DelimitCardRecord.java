package com.hq.chainStore.service.workFlowData.data;

public class DelimitCardRecord {
	// 划卡ID leaguerPrdCardId_pgId_itemType
	private String delimitCardId;
	// 客户次卡ID
	private String leaguerPrdCardId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目、商品、套餐ID
	private String pgId;
	// 抵消次数
	private int count;
	// 创建时间
	private long createTime;

	public static DelimitCardRecord newInstance() {
		DelimitCardRecord prodRecord = new DelimitCardRecord();
		prodRecord.createTime = System.currentTimeMillis();
		return prodRecord;
	}

	public String getDelimitCardId() {
		return delimitCardId;
	}

	public void setDelimitCardId(String delimitCardId) {
		this.delimitCardId = delimitCardId;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
