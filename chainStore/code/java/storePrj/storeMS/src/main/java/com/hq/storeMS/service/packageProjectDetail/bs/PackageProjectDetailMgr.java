package com.hq.storeMS.service.packageProjectDetail.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectDetailMgr {

	public static PackageProjectDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailMgr.class);
	}
	
	private final DataVersionEnum dataVersionEnum = DataVersionEnum.PackageProjectDetail;
	
	public void addWithId(PackageProjectDetail target) {
		PackageProjectDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}

	public void update(PackageProjectDetail target) {
		PackageProjectDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public void delete(PackageProjectDetail target) {
		PackageProjectDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public PackageProjectDetail get(long storeId, String id) {
		PackageProjectDetail detail = PackageProjectDetailDataHolder.getInstance().get(storeId, id);
		if(detail != null) {
			return detail;
		}
		if(storeId == 0) {
			return null;
		}
		return PackageProjectDetailDataHolder.getInstance().get(storeId, AppUtils.joinByUnderline(storeId, id));
	}
	
	public List<PackageProjectDetail> getPackageProjectDetailListByStoreId(long storeId) {
		PackageProjectDetailQueryForm queryForm = PackageProjectDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		return PackageProjectDetailDataHolder.getInstance().findPackageProjectDetailList(queryForm);
	}
	
	public PageResp<PackageProjectDetail> getPackageProjectDetailPageInfo(PackageProjectDetailQueryForm queryForm) {
		List<PackageProjectDetail> list = PackageProjectDetailDataHolder.getInstance().findPackageProjectDetailList(queryForm);
		List<PackageProjectDetail> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<PackageProjectDetail> filterRecord(PackageProjectDetailQueryForm queryForm, List<PackageProjectDetail> list){
		List<PackageProjectDetail> result = new ArrayList<PackageProjectDetail>();
		if(CollectionUtils.isNotEmpty(list)){
			for (PackageProjectDetail record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<PackageProjectDetail>() {
			@Override
			public int compare(PackageProjectDetail o1, PackageProjectDetail o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(PackageProjectDetailQueryForm queryForm, PackageProjectDetail record){
		if(!checkCardNameOrNumber(queryForm.getNameOrNumber(), record)) {
			return false;
		}
		
		if(!checkStatus(queryForm.getStatusSet(), record)) {
			return false;
		}
		
		if(!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}
		return true;
	}
	
	private boolean checkCardNameOrNumber(String nameOrNumber, PackageProjectDetail record) {
		if(StringUtils.isBlank(nameOrNumber)) {
			return true;
		}
		if(record.getName()!=null && record.getName().contains(nameOrNumber)) {
			return true;
		}
		if(record.getNumber()!=null && record.getNumber().contains(nameOrNumber)) {
			return true;
		}
		return false;
	}
	
	private boolean checkStatus(Set<Integer> statusSet, PackageProjectDetail record) {
		if(CollectionUtils.isNotEmpty(statusSet) && !statusSet.contains(record.getState())) {
			return false;
		}
		return true;
	}
	
	private boolean checkTypeId(String typeId, PackageProjectDetail record) {
		if(StringUtils.isNoneBlank(typeId) && !typeId.equals(record.getTypeId())) {
			return false;
		}
		return true;
	}
}
