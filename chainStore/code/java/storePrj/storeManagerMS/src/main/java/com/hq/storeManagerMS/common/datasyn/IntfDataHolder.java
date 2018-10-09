package com.hq.storeManagerMS.common.datasyn;

import com.hq.storeManagerMS.common.datasyn.info.DataSynItem;
import com.hq.storeManagerMS.common.datasyn.info.DataSynVer;

public interface IntfDataHolder {
	
	public DataSynItem getSynItem(DataSynVer clientSynVer);
}
