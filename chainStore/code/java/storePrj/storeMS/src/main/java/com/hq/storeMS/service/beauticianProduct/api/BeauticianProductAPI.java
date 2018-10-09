package com.hq.storeMS.service.beauticianProduct.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.beauticianProduct.apiData.BeauticianProductUpdateApiForm;
import com.hq.storeMS.service.beauticianProduct.bs.BeauticianProductHandler;
import com.hq.storeMS.service.beauticianProduct.data.BeauticianProduct;

@RestController
@RequestMapping(value = "/beauticianProduct")
public class BeauticianProductAPI {
	
	@RequestMapping(value = "/{beauticianProductId}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<BeauticianProduct>> getBeauticianProduct(@PathVariable("beauticianProductId") String beauticianProductId){  
		ReqResult<BeauticianProduct> result = BeauticianProductHandler.getInstance().getBeauticianProduct(beauticianProductId);
		ResponseEntity<RestResp<BeauticianProduct>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{beauticianProductId}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<BeauticianProduct>> updateBeauticianProduct(@PathVariable("beauticianProductId") String beauticianProductId,
    		@RequestBody BeauticianProductUpdateApiForm updateForm){  
		ReqResult<BeauticianProduct> result = BeauticianProductHandler.getInstance().updateBeauticianProduct(beauticianProductId, updateForm);
		ResponseEntity<RestResp<BeauticianProduct>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
}
