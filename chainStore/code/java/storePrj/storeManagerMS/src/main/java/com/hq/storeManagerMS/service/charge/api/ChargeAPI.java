package com.hq.storeManagerMS.service.charge.api;

import com.hq.storeManagerMS.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerMS.service.charge.bs.ChargeHandler;
import com.hq.storeManagerMS.service.charge.data.Charge;
import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/charge")
public class ChargeAPI {

    /**
     * 根据条件查询 收费 列表
     *
     * @param chargeChannel
     * @param minCreateTime
     * @param maxCreateTime
     * @param minMoney
     * @param maxMoney
     * @param buserId
     * @param phone
     * @param name
     * @param pageItemCount
     * @param pageNo
     * @return
     */
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

    /**
     * 根据 id 获取 收费 Charge 对象
     *
     * @param chargeId
     * @return
     */
    @RequestMapping(value = "/{chargeId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<Charge>> get(
            @PathVariable("chargeId") long chargeId) {
        ReqResult<Charge> result = ChargeHandler.getInstance().getCharge(chargeId);
        ResponseEntity<RestResp<Charge>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 新增 Charge
     *
     * @param inputForm
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<RestResp<Charge>> addCharge(
            @RequestBody ChargeAddForm inputForm) {
        ReqResult<Charge> result = ChargeHandler.getInstance().addCharge(inputForm);
        ResponseEntity<RestResp<Charge>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 编辑 Charge
     *
     * @param inputForm
     * @return
     */
    @RequestMapping(value = "/{chargeId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<RestResp<Charge>> updateCharge(
            @PathVariable("chargeId") long chargeId,
            @RequestBody ChargeUpdateApiForm inputForm) {
        ReqResult<Charge> result = ChargeHandler.getInstance().updateCharge(chargeId, inputForm);
        ResponseEntity<RestResp<Charge>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 根据 id 删除 Charge
     *
     * @param chargeId
     * @return
     */
    @RequestMapping(value = "/{chargeId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<RestResp<Charge>> deleteCharge(
            @PathVariable("chargeId") long chargeId) {
        ReqResult<Charge> result = ChargeHandler.getInstance().deleteCharge(chargeId);
        ResponseEntity<RestResp<Charge>> respEntity = result.buildRespEntity();
        return respEntity;
    }
}
