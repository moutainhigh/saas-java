package com.hq.storeMS.service.sellItem.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.sellItem.apiData.SellItemQueryForm;
import com.hq.storeMS.service.sellItem.bs.SellItemHandler;
import com.hq.storeMS.service.sellItem.data.SellItem;

/**
 * 根据Id获取项目、商品、次卡、套餐的信息 聚合以上四个域的查询
 * 
 * @author kevin
 *
 */
@RestController
@RequestMapping(value = "/sellItem")
public class SellItemAPI {
	@RequestMapping(value = "/getSellItemList", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<SellItem>> getSellItemList(@RequestBody SellItemQueryForm queryForm) {
		ReqResult<SellItem> result = SellItemHandler.getInstance().getSellItemList(queryForm);
		ResponseEntity<RestResp<SellItem>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/getSellItem", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<SellItem>> getSellItem(@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "sellItemType") int sellItemType, @RequestParam(value = "id") String id) {
		ReqResult<SellItem> result = SellItemHandler.getInstance().getSellItem(storeId, sellItemType, id);
		ResponseEntity<RestResp<SellItem>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
