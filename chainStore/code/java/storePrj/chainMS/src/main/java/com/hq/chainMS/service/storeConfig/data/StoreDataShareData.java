package com.hq.chainMS.service.storeConfig.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * @Description 店铺是否共享数据返回类
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/9/21
 */
@SynClass
public class StoreDataShareData {

    private long storeId;

    /**
     * 共享类型
     * {@link com.hq.storeClient.service.storeConfig.data.chain.ShareEnum}
     */
    private int shareType;

    public static StoreDataShareData newInstance(long storeId, int shareType) {
        StoreDataShareData instance = new StoreDataShareData();
        instance.setStoreId(storeId);
        instance.setShareType(shareType);
        return instance;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getShareType() {
        return shareType;
    }

    public void setShareType(int shareType) {
        this.shareType = shareType;
    }
}
