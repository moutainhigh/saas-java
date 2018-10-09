package com.hq.storeMS.service.activateCode.bs;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.service.activateCode.apiData.QueryActivateCodeForm;
import com.hq.storeMS.service.activateCode.data.ActivateCode;
import com.hq.storeMS.service.activateCode.data.ActivateCodeStatusEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class ActivateCodeMgr {

	public static ActivateCodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ActivateCodeMgr.class);
	}
	
	public OperateTips checkActiveCode(String activateCode) {
		OperateTips tips = OperateTips.newInstance(false,"激活码错误");
		
		if(!StoreMSCfgMgr.getProp().isActivateCodeOpen()){
			tips.setSuccess(true);
			return tips;
		}
		
		if(StringUtils.isBlank(activateCode)){
			tips.setTips("激活码为空,请重新填写");
			return tips;
		}
		
		ActivateCode code = findOne(activateCode);
		if(code == null){
			tips.setTips("激活码不存在");
			return tips;
		}
		
		if(code.getStatus() == ActivateCodeStatusEnum.VALID.ordinal()){
			tips.setSuccess(true);
		}else{
			tips.setTips("激活码已失效");
		}
		return tips;
	}
	
	public void addAndReturnId(ActivateCode target) {
		ActivateCodeDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void updateActivateCode(ActivateCode target) {
		ActivateCodeDataHolder.getInstance().update(target);
	}
	
	public ActivateCode findOne(String activateCode){
		return ActivateCodeDataHolder.getInstance().findOne(activateCode);
	}
	
	public ActivateCode get(long id){
		return ActivateCodeDataHolder.getInstance().get(id);
	}
	
	public List<ActivateCode> findByCond(QueryActivateCodeForm queryForm) {
		return ActivateCodeDataHolder.getInstance().findByCond(queryForm);
	}

}
