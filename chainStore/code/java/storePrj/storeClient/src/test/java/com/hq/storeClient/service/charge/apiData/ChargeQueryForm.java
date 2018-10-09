package com.hq.storeClient.service.charge.apiData;

import com.zenmind.dao.rest.ReqMap;

public class ChargeQueryForm {
	public static final int INVALID_VALUE = -1;//无效值

    /**
     * 收费渠道枚举 {@link com.hq.storeManagerMS.service.charge.data.ChargeChannelEnum}
     */
    private int chargeChannel = INVALID_VALUE;
    
	//来源 ChargeOriginEnum
	private int origin = INVALID_VALUE;
	//状态 ChargeStatusEnum
	private int status = INVALID_VALUE;

    private long minCreateTime;

    private long maxCreateTime;

    private long minMoney;

    private long maxMoney;

    private long buserId;//商户id
    
    private long phone;//商户手机号

    private String name;//商户姓名

    private int pageItemCount;

    private int pageNo;

    public static ChargeQueryForm newInstance() {
        ChargeQueryForm instance = new ChargeQueryForm();
        return instance;
    }

    public ReqMap toReqMap() {
        ReqMap reqMap = ReqMap.newInstance();
        reqMap.add("origin", origin).add("status", status).add("chargeChannel", chargeChannel)
        		.add("minCreateTime", minCreateTime).add("maxCreateTime", maxCreateTime)
                .add("minMoney", minMoney).add("maxMoney", maxMoney).add("buserId", buserId)
                .add("phone", phone).add("name", name);
        return reqMap;
    }

    public int getChargeChannel() {
        return chargeChannel;
    }

    public ChargeQueryForm setChargeChannel(int chargeChannel) {
        this.chargeChannel = chargeChannel;
        return this;
    }

    public long getMinCreateTime() {
        return minCreateTime;
    }

    public ChargeQueryForm setMinCreateTime(long minCreateTime) {
        this.minCreateTime = minCreateTime;
        return this;
    }

    public long getMaxCreateTime() {
        return maxCreateTime;
    }

    public ChargeQueryForm setMaxCreateTime(long maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
        return this;
    }

    public long getMinMoney() {
        return minMoney;
    }

    public ChargeQueryForm setMinMoney(long minMoney) {
        this.minMoney = minMoney;
        return this;
    }

    public long getMaxMoney() {
        return maxMoney;
    }

    public ChargeQueryForm setMaxMoney(long maxMoney) {
        this.maxMoney = maxMoney;
        return this;
    }

    public long getBuserId() {
        return buserId;
    }

    public ChargeQueryForm setBuserId(long buserId) {
        this.buserId = buserId;
        return this;
    }

    public long getPhone() {
        return phone;
    }

    public ChargeQueryForm setPhone(long phone) {
        this.phone = phone;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChargeQueryForm setName(String name) {
        this.name = name;
        return this;
    }

    public int getPageItemCount() {
        return pageItemCount;
    }

    public ChargeQueryForm setPageItemCount(int pageItemCount) {
        this.pageItemCount = pageItemCount;
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public ChargeQueryForm setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

	public int getOrigin() {
		return origin;
	}

	public ChargeQueryForm setOrigin(int origin) {
		this.origin = origin;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public ChargeQueryForm setStatus(int status) {
		this.status = status;
		return this;
	}
}
