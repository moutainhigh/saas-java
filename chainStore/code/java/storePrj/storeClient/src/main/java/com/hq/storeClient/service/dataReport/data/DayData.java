package com.hq.storeClient.service.dataReport.data;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dataSyn.annotation.SynClass;

import java.io.Serializable;

/**
 * 每一天的数据
 */
@SynClass
public class DayData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7223982157592510445L;

	private long timeMillis;//时间

    private double amount;//统计金额

    private int count;//统计次数

    private String timeStr;

    public DayData() {

    }

    public static DayData newInstance(long timeMillis) {
        DayData dayData = new DayData();
        dayData.setTimeStr(StringUtils4Client.timeStamp2Str(timeMillis));
        dayData.setTimeMillis(timeMillis);
        return dayData;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public void add(double amount) {
        this.amount += amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 增加消费金额
     *
     * @param amount
     */
    public void addAmount(double amount) {
        this.amount += amount;
    }

    /**
     * 增加消费次数
     *
     * @param count
     */
    public void addCount(double count) {
        this.count += count;
    }
}
