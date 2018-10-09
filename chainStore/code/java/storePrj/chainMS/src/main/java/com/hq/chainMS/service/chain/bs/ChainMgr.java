package com.hq.chainMS.service.chain.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.service.chain.apiData.ChainQueryForm;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkMgr;
import com.hq.chainMS.service.common.PageResp;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainMgr {

	public static ChainMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainMgr.class);
	}

	public void addAndReturnId(Chain target) {
		ChainDataHolder.getInstance().addAndReturnId(target);
		ChainClerkMgr.getInstance().initChainClerk(target.getId());
		
		String number = StringUtils.leftPad(target.getId()+"", 6, "0");
		target.setNumber(number);
		ChainDataHolder.getInstance().update(target);
	}

	public void updateChain(Chain target) {
		if(StringUtils.isBlank(target.getNumber())) {
			String number = StringUtils.leftPad(target.getId()+"", 6, "0");
			target.setNumber(number);
		}
		ChainDataHolder.getInstance().update(target);
	}
	
	public Chain get(long chainId) {
		return ChainDataHolder.getInstance().get(chainId);
	}
	
	public List<Chain> findByIdList(Collection<Long> chainIds) {
		List<Chain> result = new ArrayList<Chain>();
		for (Long chainId : chainIds) {
			Chain chain = ChainDataHolder.getInstance().get(chainId);
			if(chain != null) {
				result.add(chain);
			}
		}
		return result;
	}

	public Chain findByNumber(String number) {
		return ChainDataHolder.getInstance().findByNumber(number);
	}

	public PageResp<Chain> findChainByCond(ChainQueryForm queryForm) {
		List<Chain> list = ChainDataHolder.getInstance().findChainByCond(queryForm);
		List<Chain> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<Chain> filterRecord(ChainQueryForm queryForm, List<Chain> list) {
		List<Chain> result = new ArrayList<Chain>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Chain record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<Chain>() {
			@Override
			public int compare(Chain o1, Chain o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return result;
	}

	private boolean isRightRecord(ChainQueryForm queryForm, Chain record) {
		return true;
	}

}
