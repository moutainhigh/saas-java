package com.hq.storeMS.service.excel.bs;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.excel.apiData.ExcelUpLoadForm;
import com.hq.storeMS.service.excel.data.ExcelGoods;
import com.hq.storeMS.service.excel.data.ExcelLeaguer;
import com.hq.storeMS.service.excel.data.ExcelProduct;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * @ClassName: ExcelHandle
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年3月30日 下午3:48:01
 * 
 */
public class ExcelHandler {

	public static ExcelHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ExcelHandler.class);
	}

	public ReqResult<ExcelLeaguer> resolveLeaguerExcel(ExcelUpLoadForm uploadForm) {
		ReqResult<ExcelLeaguer> result = ReqResult.newInstance(false, ExcelLeaguer.class);
		MultipartFile file = uploadForm.getExcel();
		try {
			
			
			boolean success = ExcelHelper.getInstance().checkModule(file,"leaguer");
			if(success){
				MyExcelMgr.getInstance().saveExcel(uploadForm);
				List<ExcelLeaguer> targetList = ExcelHelper.getInstance().getExcelData(file,ExcelLeaguer.class);
							
				result.setTargetList(targetList);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setTips("模板有误，请重新上传文件");
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Excel, "ExcelHandler[resolveLeaguerExcel]", "", e);
		}
		return result;
	}
	
	public ReqResult<ExcelProduct> resolveProductExcel(ExcelUpLoadForm uploadForm) {
		ReqResult<ExcelProduct> result = ReqResult.newInstance(false, ExcelProduct.class);
		MultipartFile file = uploadForm.getExcel();
		try {
			boolean success = ExcelHelper.getInstance().checkModule(file,"product");
			if(success){
				MyExcelMgr.getInstance().saveExcel(uploadForm);
				List<ExcelProduct> targetList = ExcelHelper.getInstance().getExcelData(file,ExcelProduct.class);
							
				result.setTargetList(targetList);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setTips("模板有误，请重新上传文件");
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
			
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Excel, "ExcelHandler[resolveProductExcel]", "", e);
		}
		return result;
	}
	
	public ReqResult<ExcelGoods> resolveGoodsExcel(ExcelUpLoadForm uploadForm) {
		ReqResult<ExcelGoods> result = ReqResult.newInstance(false, ExcelGoods.class);
		MultipartFile file = uploadForm.getExcel();
		try {
			
			boolean success = ExcelHelper.getInstance().checkModule(file,"goods");
			if(success){
				MyExcelMgr.getInstance().saveExcel(uploadForm);
				List<ExcelGoods> targetList = ExcelHelper.getInstance().getExcelData(file,ExcelGoods.class);
							
				result.setTargetList(targetList);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setTips("模板有误，请核对模板后重新上传");
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Excel, "ExcelHandler[resolveProductExcel]", "", e);
		}
		return result;
	}
}
