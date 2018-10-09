package com.hq.storeMS.service.incomePay.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.incomePay.apiData.IncomeAddPayForm;
import com.hq.storeMS.service.incomePay.apiData.IncomePayQueryForm;
import com.hq.storeMS.service.incomePay.apiData.IncomePayUpdateApiForm;
import com.hq.storeMS.service.incomePay.bs.IncomePayHandler;
import com.hq.storeMS.service.incomePay.data.IncomePay;

@RestController
@RequestMapping(value = "/incomePay")
public class IncomePayAPI {

    /**
     * 根据条件查询 收支 列表
     *
     * @param storeId
     * @param category
     * @param minIncomePayTime
     * @param maxIncomePayTime
     * @param minMoney
     * @param maxMoney
     * @param buserId
     * @param typeId
     * @param pageItemCount
     * @param pageNo
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findIncomePayPageInfo", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<PageResp>> findIncomePayPageInfo(
            @RequestParam(value = "storeId") long storeId,
            @RequestParam(value = "category", defaultValue = "-1") int category,
            @RequestParam(value = "minIncomePayTime", defaultValue = "0") long minIncomePayTime,
            @RequestParam(value = "maxIncomePayTime", defaultValue = "0") long maxIncomePayTime,
            @RequestParam(value = "minMoney", defaultValue ="0") long minMoney,
            @RequestParam(value = "maxMoney", defaultValue = "0") long maxMoney,
            @RequestParam(value = "buserId", defaultValue = "0") long buserId,
            @RequestParam(value = "typeId", defaultValue = "") String typeId,
            @RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        IncomePayQueryForm queryForm = IncomePayQueryForm.newInstance();
        queryForm.setStoreId(storeId).setCategory(category).setMinIncomePayTime(minIncomePayTime)
                .setMaxIncomePayTime(maxIncomePayTime).setMinMoney(minMoney).setMaxMoney(maxMoney).setBuserId(buserId)
                .setTypeId(typeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
        ReqResult<PageResp> result = IncomePayHandler.getInstance().findIncomePayPageInfo(queryForm);
        ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
        return respEntity;
    }

    /**
     * 根据 id 获取 收支 IncomePay 对象
     *
     * @param storeId
     * @param incomePayId
     * @return
     */
    @RequestMapping(value = "/{storeId}/{incomePayId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<IncomePay>> get(
            @PathVariable("storeId") long storeId,
            @PathVariable("incomePayId") long incomePayId) {
        ReqResult<IncomePay> result = IncomePayHandler.getInstance().getIncomePay(storeId, incomePayId);
        ResponseEntity<RestResp<IncomePay>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 新增 IncomePay
     *
     * @param inputForm
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<RestResp<IncomePay>> addIncomePay(
            @RequestBody IncomeAddPayForm inputForm) {
        ReqResult<IncomePay> result = IncomePayHandler.getInstance().addIncomePay(inputForm);
        ResponseEntity<RestResp<IncomePay>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 编辑 IncomePay
     *
     * @param inputForm
     * @return
     */
    @RequestMapping(value = "/{storeId}/{incomePayId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<RestResp<IncomePay>> updateIncomePay(
    		@PathVariable("storeId") long storeId,
    		@PathVariable("incomePayId") long incomePayId,
            @RequestBody IncomePayUpdateApiForm inputForm) {
        ReqResult<IncomePay> result = IncomePayHandler.getInstance().updateIncomePay(storeId, incomePayId, inputForm);
        ResponseEntity<RestResp<IncomePay>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 根据 id 删除 IncomePay
     *
     * @param storeId
     * @param incomePayId
     * @return
     */
    @RequestMapping(value = "/{storeId}/{incomePayId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<RestResp<IncomePay>> deleteIncomePay(
            @PathVariable("storeId") long storeId,
            @PathVariable("incomePayId") long incomePayId) {
        ReqResult<IncomePay> result = IncomePayHandler.getInstance().deleteIncomePay(storeId, incomePayId);
        ResponseEntity<RestResp<IncomePay>> respEntity = result.buildRespEntity();
        return respEntity;
    }
}
