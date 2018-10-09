package com.hq.chainMS.service.storeConfig.api;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.storeConfig.bs.StoreConfigHandler;
import com.hq.chainMS.service.storeConfig.data.StoreDataShareData;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/9/20
 */
@RestController
@RequestMapping(value = "/storeConfig")
public class StoreConfigAPI {

    /**
     * 根据ID列表获取对应店铺StoreConfig
     *
     * @param storeIds
     * @return
     */
    @RequestMapping(value = "/getStoreConfigs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<StoreConfig>> getStoreConfigs(@RequestParam(value = "storeIds") String storeIds
    ) {
        ReqResult<StoreConfig> result = StoreConfigHandler.getInstance().getStoreConfigs(storeIds);
        ResponseEntity<RestResp<StoreConfig>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 根据ID列表获取对应店铺数据共享设置
     *
     * @param storeIds
     * @return
     */
    @RequestMapping(value = "/getDataShareConfigs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<StoreDataShareData>> getDataShareConfigs(@RequestParam(value = "storeIds") String storeIds,
                                                                            @RequestParam(value = "chainId") long chainId
    ) {
        ReqResult<StoreDataShareData> result = StoreConfigHandler.getInstance().getDataShareConfigs(chainId, storeIds);
        ResponseEntity<RestResp<StoreDataShareData>> respEntity = result.buildRespEntity();
        return respEntity;
    }

}
