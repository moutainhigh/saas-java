package com.hq.chainMS.service.chainGoods.bs.updateHandle;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateForm;

public interface IGoodsHandle {
	public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo);
}
