package com.hq.storeMS.service.goodsDetail.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailMgr {

	public static GoodsDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailMgr.class);
	}
	
	final private DataVersionEnum dataVersionEnum = DataVersionEnum.GoodsDetail;
	
	public void addWithId(GoodsDetail target) {
		addImgDefaultPaths(target);
		GoodsDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	//填充默认图片   在入口做检验  查询出口不需要再次检查
	private void addImgDefaultPaths(GoodsDetail target){
		try {
			if(CollectionUtils.isEmpty(target.getImgPaths())){
				target.getImgPaths().add(ServerConstants.GOODS_DEFAULT_PATH);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.GoodsDetail, "GoodsDetailMgr[addImgDefaultPaths]", "", e);
		}
	}

	public void update(GoodsDetail target) {
		GoodsDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public void delete(GoodsDetail target) {
		GoodsDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public GoodsDetail get(long storeId, String goodsId) {
		GoodsDetail detail = GoodsDetailDataHolder.getInstance().get(storeId, goodsId);
		if(detail != null) {
			return detail;
		}
		if(storeId == 0) {
			return null;
		}
		return GoodsDetailDataHolder.getInstance().get(storeId, AppUtils.joinByUnderline(storeId, goodsId));
	}
	
	public PageResp<GoodsDetail> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		List<GoodsDetail> list = GoodsDetailDataHolder.getInstance().findGoodsDetailList(queryForm);
		List<GoodsDetail> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<GoodsDetail> filterRecord(GoodsDetailQueryForm queryForm, List<GoodsDetail> list){
		List<GoodsDetail> result = new ArrayList<GoodsDetail>();
		if(CollectionUtils.isNotEmpty(list)){
			for (GoodsDetail record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<GoodsDetail>() {
			@Override
			public int compare(GoodsDetail o1, GoodsDetail o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(GoodsDetailQueryForm queryForm, GoodsDetail record){
		if(!checkState(queryForm.getState(), record)) {
			return false;
		}
		
		if(!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}
		
		if(!checkNumberOrName(queryForm.getNumberOrName(), record)) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkState(int state, GoodsDetail record){
		if(state == -1) {
			return true;
		}
		
		if(record.getState() == state) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkTypeId(String typeId, GoodsDetail record){
		if(StringUtils.isBlank(typeId)) {
			return true;
		}
		
		if(typeId.equals(record.getTypeId())) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkNumberOrName(String numberOrName, GoodsDetail record){
		if(StringUtils.isBlank(numberOrName)) {
			return true;
		}
		
		String name = record.getName();
		if(name !=null && name.contains(numberOrName)) {
			return true;
		}
		
		String number = record.getNumber();
		if(number!=null && number.contains(numberOrName)) {
			return true;
		}
		
		return false;
	}
	
	
}
