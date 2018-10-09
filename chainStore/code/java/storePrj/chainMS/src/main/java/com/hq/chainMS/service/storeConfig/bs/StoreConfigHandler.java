package com.hq.chainMS.service.storeConfig.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.chainMS.service.storeConfig.data.StoreDataShareData;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.hq.storeClient.service.storeConfig.data.chain.ShareDataConfig;
import com.hq.storeClient.service.storeConfig.data.chain.ShareEnum;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 店铺配置信息Handler
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/9/20
 */
public class StoreConfigHandler {

    public static StoreConfigHandler getInstance() {
        return HotSwap.getInstance().getSingleton(StoreConfigHandler.class);
    }


    public ReqResult<StoreConfig> getStoreConfigs(String storeIds) {
        ReqResult<StoreConfig> result = ReqResult.newInstance(false, StoreConfig.class);
        try {
            List<StoreConfig> storeConfigList = StoreConfigMgr.getInstance().getStoreConfigs(storeIds);
            result.setTargetList(storeConfigList);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String reason = LogHelper.getInstance().exceptionReason(storeIds);
            result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
            result.setTips("服务暂不可用，请稍后尝试");
            MainLog.error(LogModule.StoreConfig, "StoreConfigHandler[getStoreConfigs]", reason, e);
        }
        return result;

    }

    public ReqResult<StoreDataShareData> getDataShareConfigs(long chainId, String storeIds) {
        ReqResult<StoreDataShareData> result = ReqResult.newInstance(false, StoreDataShareData.class);
        try {
            List<StoreDataShareData> retList = getStoreDataShareConfig(chainId, storeIds);
            result.setTargetList(retList);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String reason = LogHelper.getInstance().exceptionReason(storeIds);
            result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
            result.setTips("服务暂不可用，请稍后尝试");
            MainLog.error(LogModule.StoreConfig, "StoreConfigHandler[getDataShareConfigs]", reason, e);
        }
        return result;

    }

    /**
     * 获取店铺数据共享配置
     * @param chainId
     * @param storeIds
     * @return
     */
    private List<StoreDataShareData> getStoreDataShareConfig(long chainId, String storeIds) {
        List<StoreConfig> storeConfigList = StoreConfigMgr.getInstance().getStoreConfigs(storeIds);
        List<StoreDataShareData> retList = new ArrayList<>();
        for (StoreConfig storeConfig : storeConfigList) {
            int sharedType = ShareEnum.NATIVE.ordinal();
            if (null != storeConfig.getChainConfig()) {
                ShareDataConfig shareDataConfig = storeConfig.getChainConfig().getShareDataConfigMap().get(chainId);
                if (null != shareDataConfig) {
                    sharedType = shareDataConfig.getShareFlag();
                }
            }
            StoreDataShareData storeDataShareData = StoreDataShareData.newInstance(storeConfig.getStoreId(), sharedType);
            retList.add(storeDataShareData);
        }
        return retList;
    }

}
