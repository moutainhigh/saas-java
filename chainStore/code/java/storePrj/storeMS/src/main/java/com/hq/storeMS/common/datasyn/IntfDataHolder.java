package com.hq.storeMS.common.datasyn;

import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynVer;

public interface IntfDataHolder {
	
	public DataSynItem getSynItem(DataSynVer clientSynVer);
}
