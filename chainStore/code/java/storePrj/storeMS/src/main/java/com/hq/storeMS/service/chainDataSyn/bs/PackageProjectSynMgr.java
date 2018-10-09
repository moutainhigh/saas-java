package com.hq.storeMS.service.chainDataSyn.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProject;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataStatusEnum;
import com.hq.storeMS.service.chainDataSyn.data.PackageProjectSyn;
import com.hq.storeMS.service.chainPackageProject.bs.ChainPackageProjectDataHolder;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectDataHolder;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.zenmind.common.hotSwap.HotSwap;

public class PackageProjectSynMgr {

	public static PackageProjectSynMgr getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectSynMgr.class);
	}

	public PageResp<PackageProjectSyn> findChainPackageProject(ChainDataQueryForm queryForm) {
		List<PackageProjectSyn> list = findList(queryForm);
		List<PackageProjectSyn> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<PackageProjectSyn> findList(ChainDataQueryForm queryForm) {
		long chainId = queryForm.getChainId();
		long storeId = queryForm.getStoreId();
		List<PackageProjectSyn> result = new ArrayList<PackageProjectSyn>();
		ChainPackageProject chainData = ChainPackageProjectDataHolder.getInstance().get(chainId);
		StorePackageProject storeData = StorePackageProjectDataHolder.getInstance().get(storeId);

		Collection<PackageProject> array = chainData.getPackageProjectMap().values();
		for (PackageProject tmp : array) {
			// 过滤掉 已删除 或者 已下架的
//			if (tmp.getEntityState() == EntityState.Deleted.ordinal()
//					|| tmp.getState() == PackageStateEnum.Close.ordinal()) {
//				continue;
//			}
			// 分配到店
			if (!tmp.getApplyStoreIds().contains(storeId)) {
				continue;
			}
			PackageProjectSyn data = PackageProjectSyn.newInstanceByPackageProject(tmp, storeId, chainId);
			if (storeData.takePackageProjectById(tmp.getId()) == null) {
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else if (storeData.takePackageProjectById(tmp.getId()).getEntityState() == EntityState.Deleted.ordinal()){
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else {
				data.setSynStatus(ChainDataStatusEnum.HAVE.ordinal());
			}
			result.add(data);
		}
		return result;
	}

	private List<PackageProjectSyn> filterRecord(ChainDataQueryForm queryForm, List<PackageProjectSyn> list) {
		List<PackageProjectSyn> result = new ArrayList<PackageProjectSyn>();
		for (PackageProjectSyn record : list) {
			if (isRightRecord(queryForm, record)) {
				result.add(record);
			}
		}
		Collections.sort(result, new Comparator<PackageProjectSyn>() {
			@Override
			public int compare(PackageProjectSyn o1, PackageProjectSyn o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return result;
	}

	private boolean isRightRecord(ChainDataQueryForm queryForm, PackageProjectSyn record) {
		if (!checkNumberOrName(queryForm.getNumberOrName(), record)) {
			return false;
		}

		if (!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}
		
		if (!checkSynStatus(queryForm.getSynStatus(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkNumberOrName(String numberOrName, PackageProjectSyn record) {
		if (StringUtils.isBlank(numberOrName)) {
			return true;
		}
		if (record.getName() != null && record.getName().contains(numberOrName)) {
			return true;
		}
		if (record.getNumber() != null && record.getNumber().contains(numberOrName)) {
			return true;
		}
		return false;
	}

	private boolean checkTypeId(String typeId, PackageProjectSyn record) {
		if (StringUtils.isBlank(typeId)) {
			return true;
		}
		if (typeId.equals(record.getTypeId())) {
			return true;
		}
		return false;
	}
	
	private boolean checkSynStatus(int synStatus, PackageProjectSyn record) {
		if (synStatus == -1) {
			return true;
		}
		if (synStatus == record.getSynStatus()) {
			return true;
		}
		return false;
	}
}
