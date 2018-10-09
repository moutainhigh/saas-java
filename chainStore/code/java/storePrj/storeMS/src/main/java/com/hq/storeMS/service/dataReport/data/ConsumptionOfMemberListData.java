package com.hq.storeMS.service.dataReport.data;

import java.util.List;

import com.zenmind.common.hotSwap.HotSwap;

public class ConsumptionOfMemberListData {

    public static ConsumptionOfMemberListData getInstance() {
        return HotSwap.getInstance().getSingleton(ConsumptionOfMemberListData.class);
    }

    private List<ConsumptionOfMemberData>consumptionOfMemberDataList;

    public void setConsumptionOfMemberDataList(List<ConsumptionOfMemberData> consumptionOfMemberDataList) {
        this.consumptionOfMemberDataList = consumptionOfMemberDataList;
    }

    public List<ConsumptionOfMemberData> getConsumptionOfMemberDataList() {
        return consumptionOfMemberDataList;
    }
}
