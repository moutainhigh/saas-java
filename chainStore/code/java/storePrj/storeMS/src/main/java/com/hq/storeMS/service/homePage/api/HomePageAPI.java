package com.hq.storeMS.service.homePage.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.homePage.apiData.QueryHomePageForm;
import com.hq.storeMS.service.homePage.bs.HomePageHandler;
import com.hq.storeMS.service.homePage.data.HomePage;

@RestController
@RequestMapping(value = "/homePage")
public class HomePageAPI {
	
	@RequestMapping(value = "/getHomePageData" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<HomePage>> getHomePageData(
    		@RequestParam(value="items", defaultValue="") String items,
    		@RequestParam(value="storeId") long storeId,
    		@RequestParam(value="buserId") long buserId){
		QueryHomePageForm queryForm = QueryHomePageForm.newInstance();
		queryForm.setBuserId(buserId);
		queryForm.setStoreId(storeId);
		ReqResult<HomePage> result = HomePageHandler.getInstance().getHomePageData(queryForm, items);
		ResponseEntity<RestResp<HomePage>> respEntity = result.buildJsonRespEntity();
		return respEntity;
    }
}
