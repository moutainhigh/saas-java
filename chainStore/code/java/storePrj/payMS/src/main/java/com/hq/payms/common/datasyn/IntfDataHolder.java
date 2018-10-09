package com.hq.payms.common.datasyn;

import com.hq.payms.common.datasyn.info.DataSynItem;
import com.hq.payms.common.datasyn.info.DataSynVer;

public interface IntfDataHolder {
	
	public DataSynItem getSynItem(DataSynVer clientSynVer);
}
