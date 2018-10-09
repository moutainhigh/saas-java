package com.hq.orderMS.common.datasyn;

import com.hq.orderMS.common.datasyn.info.DataSynItem;
import com.hq.orderMS.common.datasyn.info.DataSynVer;

public interface IntfDataHolder {
	
	public DataSynItem getSynItem(DataSynVer clientSynVer);
}
