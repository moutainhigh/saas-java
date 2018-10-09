package com.hq.chainMS.service.chainProduct.bs.updateHandle;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateForm;

public interface IProductHandle {
	public OperateTips update(long chainId, ChainProductUpdateForm formInfo);
}
