package com.hq.chainStore.service.storeCardInfo.data;


/**
 *新建次卡 所选项目实体
 */

public class PrdInCard {
	
	private long prdId;
	private int type;//使用类型  对应枚举PrdInCardEnum
	private int count;//次数
	
	
	public static PrdInCard newInstance() {
		PrdInCard data = new PrdInCard();
		return data;
	}


	public long getPrdId() {
		return prdId;
	}


	public void setPrdId(long prdId) {
		this.prdId = prdId;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
