package com.hq.storeMS.service.dataReport.bs;

import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailDataHolder;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.ArrayList;
import java.util.List;

public class FindLeaguerDeteilList {
    public static FindLeaguerDeteilList getInstance() {
        return HotSwap.getInstance().getSingleton(FindLeaguerDeteilList.class);
    }

    public List<LeaguerDetail> findLeaguerDeteilList (String stratTime,String endTime,String storeId){

        List<LeaguerDetail> leaguerDetailList = new ArrayList<>();
        LeaguerDetailQueryForm queryForm = new LeaguerDetailQueryForm();
        queryForm.setStoreId(Long.valueOf(storeId));
        queryForm.setMinTime(Long.valueOf(stratTime));
        queryForm.setMaxTime(Long.valueOf(endTime));
        leaguerDetailList = LeaguerDetailDataHolder.getInstance().findLeaguerDetailList(queryForm);

        return leaguerDetailList;

    }

    public List<LeaguerDetail> getLeaguerDetailListByStoreId(String storeId){
        List<LeaguerDetail> leaguerDetailList = new ArrayList<>();
        LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance();
        queryForm.setStoreId(Long.valueOf(storeId));
        leaguerDetailList = LeaguerDetailDataHolder.getInstance().findLeaguerDetailList(queryForm);
        return leaguerDetailList;
    }


}
