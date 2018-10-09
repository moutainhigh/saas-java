package com.hq.storeClient.service.dataReport.apiData;

import com.hq.storeClient.common.utils.StringUtils4Client;

public class ChainReportQueryForm {
    private String storeIds;

    private long maxTime;
    private long minTime;

    public static ChainReportQueryForm newInstance() {
        ChainReportQueryForm data = new ChainReportQueryForm();
        data.storeIds = null;
        data.minTime = 0L;
        data.maxTime = 0L;
        return data;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public String getListId() {
        return StringUtils4Client.joinByUnderline(minTime, maxTime, storeIds);
    }

}
