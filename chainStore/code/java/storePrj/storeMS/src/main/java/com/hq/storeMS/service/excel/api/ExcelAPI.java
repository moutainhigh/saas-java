package com.hq.storeMS.service.excel.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.excel.apiData.ExcelUpLoadForm;
import com.hq.storeMS.service.excel.bs.ExcelHandler;
import com.hq.storeMS.service.excel.data.ExcelGoods;
import com.hq.storeMS.service.excel.data.ExcelLeaguer;
import com.hq.storeMS.service.excel.data.ExcelProduct;

/**
 * @ClassName: ExcelAPI
 * @Description: excel相关接口
 * @author helen
 * @date 2018年3月30日 上午9:04:07
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelAPI {
	
	/**上传并解析excel*/
	@RequestMapping(value = "/leaguer", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ExcelLeaguer>> resolveLeaguerExcel(
			@ModelAttribute ExcelUpLoadForm upForm) {
		ReqResult<ExcelLeaguer> result = ExcelHandler.getInstance().resolveLeaguerExcel(upForm);
		ResponseEntity<RestResp<ExcelLeaguer>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ExcelProduct>> resolveProductExcel(
			@ModelAttribute ExcelUpLoadForm upForm) {
		ReqResult<ExcelProduct> result = ExcelHandler.getInstance().resolveProductExcel(upForm);
		ResponseEntity<RestResp<ExcelProduct>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/goods", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ExcelGoods>> resolveGoodsExcel(
			@ModelAttribute ExcelUpLoadForm upForm) {
		ReqResult<ExcelGoods> result = ExcelHandler.getInstance().resolveGoodsExcel(upForm);
		ResponseEntity<RestResp<ExcelGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
