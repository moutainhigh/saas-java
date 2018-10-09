package com.hq.storeMS.service.excel.bs;

import com.hq.storeMS.service.excel.data.ExcelDAO;
import com.hq.storeMS.service.excel.data.FileResp;
import com.zenmind.common.hotSwap.HotSwap;

/** 
 * @ClassName: ExcelDataHolder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年3月30日 下午3:48:54 
 *  
 */
public class ExcelDataHolder {

	public static ExcelDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ExcelDataHolder.class);
	}
	
	public FileResp saveExcel(Object postParam){
		return ExcelDAO.getInstance().saveExcel(postParam);
	}
}
