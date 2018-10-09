package com.hq.storeMS.service.charge.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.charge.bs.ChargeHandler;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerRestClient.service.charge.data.Charge;

@RestController
@RequestMapping(value = "/charge")
public class ChargeAPI {
	
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/findChargePageInfo", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<PageResp>> findChargePageInfo(
    		@RequestParam(value = "origin", defaultValue = "-1") int origin,
            @RequestParam(value = "chargeChannel", defaultValue = "-1") int chargeChannel,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "minCreateTime", defaultValue = "0") long minCreateTime,
            @RequestParam(value = "maxCreateTime", defaultValue = "0") long maxCreateTime,
            @RequestParam(value = "minMoney", defaultValue = "0") long minMoney,
            @RequestParam(value = "maxMoney", defaultValue = "0") long maxMoney,
            @RequestParam(value = "buserId", defaultValue = "0") long buserId,
            @RequestParam(value = "phone", defaultValue = "0") long phone,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        ChargeQueryForm queryForm = ChargeQueryForm.newInstance();
        queryForm.setOrigin(origin).setStatus(status).setChargeChannel(chargeChannel).setMinCreateTime(minCreateTime)
                .setMaxCreateTime(maxCreateTime).setMinMoney(minMoney).setMaxMoney(maxMoney).setBuserId(buserId)
                .setPhone(phone).setName(name).setPageItemCount(pageItemCount).setPageNo(pageNo);
        ReqResult<PageResp> result = ChargeHandler.getInstance().findChargePageInfo(queryForm);
        ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
        return respEntity;
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Charge>> getCharge(@PathVariable("id") long id) {
		ReqResult<Charge> result = ChargeHandler.getInstance().getCharge(id);
		ResponseEntity<RestResp<Charge>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chargeId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Charge>> updateCharge(@PathVariable("chargeId") long chargeId,
			@RequestBody ChargeUpdateApiForm updateForm) {
		ReqResult<Charge> result = ChargeHandler.getInstance().updateCharge(chargeId, updateForm);
		ResponseEntity<RestResp<Charge>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Charge>> addCharge(@RequestBody ChargeAddForm formInfo) {
		ReqResult<Charge> result = ChargeHandler.getInstance().addCharge(formInfo);
		ResponseEntity<RestResp<Charge>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
