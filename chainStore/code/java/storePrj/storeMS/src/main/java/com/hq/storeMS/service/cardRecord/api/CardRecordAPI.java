package com.hq.storeMS.service.cardRecord.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.cardRecord.apiData.PlusCardRecordForm;
import com.hq.storeMS.service.cardRecord.apiData.QueryCardRecordForm;
import com.hq.storeMS.service.cardRecord.bs.CardRecordHandler;
import com.hq.storeMS.service.cardRecord.data.CardRecord;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/cardRecord")
public class CardRecordAPI {
	
	/**
	 * 通用的查询卡片使用情况接口   
	 * @param storeId
	 * @param maxTime
	 * @param minTime
	 * @param pageItemCount
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/findByCond" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<CardRecord>> findByCond(
    		@RequestParam(value="maxTime", defaultValue="0")Long maxTime,
    		@RequestParam(value="minTime", defaultValue="0")Long minTime,
    		@RequestParam(value="cardId", defaultValue="0")String cardId,
    		@RequestParam(value="status", defaultValue="-1")Integer status,
    		@RequestParam(value="storeId")Long storeId,
    		@RequestParam(value="pageItemCount", defaultValue="0")Integer pageItemCount,
    		@RequestParam(value="pageNo", defaultValue="1")Integer pageNo){
		QueryCardRecordForm queryForm = QueryCardRecordForm.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setPageItemCount(pageItemCount)
			.setPageNo(pageNo).setStoreId(storeId);
		ReqResult<CardRecord> result = CardRecordHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<CardRecord>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	/**
	 * 根据卡ID 查询卡对应的记录   用于卡详情展现里面
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value = "/findByCardId" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<CardRecord>> findByCardId(@RequestParam("cardId") String cardId){  
		ReqResult<CardRecord> result = CardRecordHandler.getInstance().findByCardId(cardId);
		ResponseEntity<RestResp<CardRecord>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	/**
	 * 根据客户ID和卡片ID 查看卡片的记录详情   用于客户查看卡记录页面   [使用场景？？   应该删除这个方法  使用findLeaguerUsefulCards替换]
	 * @param cardId   
	 * @param leaguerId
	 * @return
	 */
	@RequestMapping(value = "/findLeaguerCardById" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<CardRecord>> findLeaguerCardById(@RequestParam("cardId") String cardId,
			@RequestParam("leaguerId") String leaguerId){  
		ReqResult<CardRecord> result = CardRecordHandler.getInstance().findLeaguerCardById(cardId, leaguerId);
		ResponseEntity<RestResp<CardRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 更加客户ID 和 选卡的ID 查询有效的卡记录信息  用于收银 客户选择卡片支付的页面
	 * @param cardIds
	 * @param leaguerId
	 * @return
	 */
	@RequestMapping(value = "/findLeaguerUsefulCards" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<CardRecord>> findLeaguerUsefulCards(@RequestParam("cardIds") String cardIds,
			@RequestParam("leaguerId") String leaguerId){  
		ReqResult<CardRecord> result = CardRecordHandler.getInstance().findLeaguerUsefulCards(cardIds, leaguerId);
		ResponseEntity<RestResp<CardRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 增加卡使用的次数   用于消费后，卡记录数据更新
	 * @param plusForm
	 * @return
	 */
	@RequestMapping(value = "/plusCount" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<CardRecord>> plusCount(@RequestBody PlusCardRecordForm plusForm){  
		ReqResult<CardRecord> result = CardRecordHandler.getInstance().plusCount(plusForm);
		ResponseEntity<RestResp<CardRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
}
