package com.hq.storeMS.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * @Description 会员来源统计对象
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/3
 */
@SynClass
public class LeaguerOriginData {

    private int originId;//来源ID
    private String originName;//来源名字
    private int count;//来源统计的人数

    public static LeaguerOriginData newInstance(int originId, String originName) {
        LeaguerOriginData leaguerOriginData = new LeaguerOriginData();
        leaguerOriginData.originId = originId;
        leaguerOriginData.originName = originName;
        return leaguerOriginData;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 增加该来源的客户人数
     * @param count
     */
    public void addCount(int count) {
        this.count += count;
    }
}
