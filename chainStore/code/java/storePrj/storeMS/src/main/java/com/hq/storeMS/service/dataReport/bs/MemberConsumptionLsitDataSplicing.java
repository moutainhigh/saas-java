package com.hq.storeMS.service.dataReport.bs;

import com.hq.storeMS.service.dataReport.data.ConsumptionOfMemberData;
import com.hq.storeMS.service.dataReport.data.ConsumptionOfMemberListData;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.ArrayList;
import java.util.List;

public class MemberConsumptionLsitDataSplicing {

    public static MemberConsumptionLsitDataSplicing getInstance() {
        return HotSwap.getInstance().getSingleton(MemberConsumptionLsitDataSplicing.class);
    }

    public ConsumptionOfMemberListData getConsumptionOfMemberListData(String starttime,String endTime,String storeId){

        List<LeaguerDetail> leaguerDetailList = HelperClass.getInstance().leaguerDetailsSort(FindLeaguerDeteilList.getInstance().findLeaguerDeteilList(starttime,endTime,storeId));
        List<ConsumptionOfMemberData> consumptionOfMemberDataList = new ArrayList<>();
        for (LeaguerDetail leaguerDetail:leaguerDetailList){

            ConsumptionOfMemberData consumptionOfMemberData = new ConsumptionOfMemberData();
            consumptionOfMemberData.setUserName(leaguerDetail.getName());
            consumptionOfMemberData.setMemberType("普通会员");
            consumptionOfMemberData.setPrice(String.valueOf(leaguerDetail.getConsumeAmount()));
            consumptionOfMemberDataList.add(consumptionOfMemberData);

        }

        ConsumptionOfMemberListData consumptionOfMemberListData = new ConsumptionOfMemberListData();
        consumptionOfMemberListData.setConsumptionOfMemberDataList(consumptionOfMemberDataList);

        return consumptionOfMemberListData;

    }



}
