package com.hq.storeMS.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * @Description 会员消费记录统计 --会员消费排行榜
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/3
 */
@SynClass
public class MemberConsumptionData {

    //会员ID
    private String leagueId;

    //会员名字
    private String leagueName;

    private String leagueType;
    //总消费
    private double totalCost;

    public static MemberConsumptionData newInstance(String leagueId, String leagueName) {
        MemberConsumptionData target = new MemberConsumptionData();
        target.setLeagueId(leagueId);
        target.setLeagueName(leagueName);
        target.setLeagueType("普通会员");
        return target;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(String leagueType) {
        this.leagueType = leagueType;
    }

    /**
     * 增加
     *
     * @param cost
     */
    public void addTotalCost(double cost) {
        this.totalCost += cost;
    }

}
