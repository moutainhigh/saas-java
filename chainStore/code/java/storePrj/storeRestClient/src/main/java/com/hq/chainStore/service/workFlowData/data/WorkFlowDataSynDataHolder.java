package com.hq.chainStore.service.workFlowData.data;

import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersion;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionSynDataHolder;
import com.hq.common.dataDetial.bs.AbsDetailDataHolder;
import com.hq.common.dataDetial.info.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class WorkFlowDataSynDataHolder extends AbsDetailDataHolder<WorkFlowData> {

	public static WorkFlowDataSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataSynDataHolder.class);
	}

	final private DataVersionEnum versionEnum = DataVersionEnum.WorkflowData;

	protected RestDao<WorkFlowData> getDao() {
		return WorkFlowDataDAO.getInstance();
	}
	
	@Override
	protected long getDataVersion(String ownerId, long storeId) {
		DetailDataVersion data = DetailDataVersionSynDataHolder.getInstance().getData(ownerId, String.valueOf(storeId));
		Long ver = data.getDetailDataVerMap().get(versionEnum.ordinal());
		if(ver != null) {
			return ver;
		}
		return 0;
	}

	@Override
	public DataVersionEnum getDataVersionEnum() {
		return versionEnum;
	}

}
