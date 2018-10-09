package com.hq.chainStore.service.euser.apiData;


/**
 * @ClassName: EUserUpdateCountData
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年1月19日 下午2:43:28
 * 
 */
public class EUserUpdateCountData {

	private long euserId;
	private int count;

	public static EUserUpdateCountData newInstance() {

		return new EUserUpdateCountData();
	}

	public long getEuserId() {
		return euserId;
	}

	public void setEuserId(long euserId) {
		this.euserId = euserId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
