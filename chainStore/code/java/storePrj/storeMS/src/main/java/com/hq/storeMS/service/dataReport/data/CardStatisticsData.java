package com.hq.storeMS.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

import java.util.List;

@SynClass
public class CardStatisticsData {

    private String storeId;

    private String updataTime;

    private List<CardMapData> cardMapDataList;

    public CardStatisticsData() {
    }

    public CardStatisticsData(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setUpdataTime(String updataTime) {
        this.updataTime = updataTime;
    }

    public String getUpdataTime() {
        return updataTime;
    }

    public void setCardMapDataList(List<CardMapData> cardMapDataList) {
        this.cardMapDataList = cardMapDataList;
    }

    public List<CardMapData> getCardMapDataList() {
        return cardMapDataList;
    }

}
