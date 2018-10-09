package com.hq.customerMS.common.datasyn;

import com.hq.customerMS.common.datasyn.info.DataSynItem;
import com.hq.customerMS.common.datasyn.info.DataSynVer;

public interface IntfDataHolder {
	
	public DataSynItem getSynItem(DataSynVer clientSynVer);
}
