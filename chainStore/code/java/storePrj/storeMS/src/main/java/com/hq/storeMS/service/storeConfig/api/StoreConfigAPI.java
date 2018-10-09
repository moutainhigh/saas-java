package com.hq.storeMS.service.storeConfig.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateForm;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigHandler;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;

@RestController
@RequestMapping(value = "/storeConfig")
public class StoreConfigAPI {
    @RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<StoreConfig>> getStoreConfig(
            @PathVariable("storeId") long storeId) {
        ReqResult<StoreConfig> result = StoreConfigHandler.getInstance().getByStoreId(storeId);
        ResponseEntity<RestResp<StoreConfig>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    @RequestMapping(value = "/{storeId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<RestResp<StoreConfig>> updateStoreConfig(
            @PathVariable("storeId") long storeId,
            @RequestBody StoreConfigUpdateForm updateForm) {
        ReqResult<StoreConfig> result = StoreConfigHandler.getInstance().update(storeId, updateForm);
        ResponseEntity<RestResp<StoreConfig>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    @RequestMapping(value = "/getStoreConfigs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<StoreConfig>> getStoreConfigs(@RequestParam("storeIds") String storeIds) {
        ReqResult<StoreConfig> result = StoreConfigHandler.getInstance().getListByStoreIds(storeIds);
        ResponseEntity<RestResp<StoreConfig>> respEntity = result.buildRespEntity();
        return respEntity;
    }


}
