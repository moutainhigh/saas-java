package com.hq.storeManagerMS.service.charge.bs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeManagerMS.common.util.AppUtils;
import com.hq.storeManagerMS.common.util.PageUtil;
import com.hq.storeManagerMS.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerMS.service.charge.data.Charge;
import com.hq.storeManagerMS.service.charge.data.ChargeOriginEnum;
import com.hq.storeManagerMS.service.charge.data.ChargePayItem;
import com.hq.storeManagerMS.service.charge.data.ChargeStatusEnum;
import com.hq.storeManagerMS.service.common.EntityState;
import com.hq.storeManagerMS.service.common.PageResp;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeMgr {

	public static ChargeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChargeMgr.class);
	}

	public void addAndReturnId(Charge target) {
		updateChargeStatusIfNeed(target);
		fillRemarkIfNeed(target);
		ChargeDataHolder.getInstance().addAndReturnId(target);
	}
	
	private void fillRemarkIfNeed(Charge target) {
		if(isFromStoreMS(target)) {
			StringBuilder remarkSB = new StringBuilder();
			remarkSB.append("会员等级：");
			remarkSB.append(target.getVipLevelName());
			remarkSB.append("；");
			
			remarkSB.append("到期时间：");
			String expiredTimeStr = AppUtils.timeStamp2Str(target.getExpiredTime(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			remarkSB.append(expiredTimeStr);
			
			target.setRemark(remarkSB.toString());
		}
	}

	private void updateChargeStatusIfNeed(Charge target) {
		if(isFromManagerMS(target)) {
			target.setStatus(ChargeStatusEnum.HAS_PAY.ordinal());
		}
	}
	
	private boolean isFromStoreMS(Charge target) {
		ChargeOriginEnum chargeOriginEnum = ChargeOriginEnum.valueOf(target.getOrigin());
		return chargeOriginEnum == ChargeOriginEnum.StoreMS;
	}
	
	private boolean isFromManagerMS(Charge target) {
		ChargeOriginEnum chargeOriginEnum = ChargeOriginEnum.valueOf(target.getOrigin());
		return chargeOriginEnum == ChargeOriginEnum.ManagerMS;
	}
	
	public void updateCharge(Charge target) {
		fillRemarkIfNeed(target);
		ChargeDataHolder.getInstance().update(target);
	}

	public void delete(Charge target) {
		target.setEntityState(EntityState.Deleted.ordinal());
		updateCharge(target);
	}

	public Charge get(long id) {
		return ChargeDataHolder.getInstance().get(id);
	}

	public PageResp<Charge> findChargePageInfo(ChargeQueryForm queryForm) {
		List<Charge> list = ChargeDataHolder.getInstance().findByCond(queryForm);
		List<Charge> result = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	/**
	 * 匹配正确的列表项
	 *
	 * @param queryForm
	 * @param list
	 * @return
	 */
	private List<Charge> filterRecord(ChargeQueryForm queryForm, List<Charge> list) {
		List<Charge> result = new ArrayList<Charge>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Charge record : list) {
				if (record == null)
					continue;
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<Charge>() {
			@Override
			public int compare(Charge o1, Charge o2) {
				return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
			}
		});
		return result;
	}

	// 谨记：内存筛选 一定要记得 不应该再操作数据库
	private boolean isRightRecord(ChargeQueryForm queryForm, Charge record) {
		boolean isRight = checkChargeChannel(queryForm.getChargeChannel(), record)
				&& checkOrigin(queryForm.getOrigin(), record)
				&& checkStatus(queryForm.getStatus(), record)
				&& checkMoney(queryForm.getMinMoney(), queryForm.getMaxMoney(), record)
				&& checkBUserId(queryForm.getBuserId(), record) && checkPhone(queryForm.getPhone(), record)
				&& checkName(queryForm.getName(), record);
		return isRight;
	}

	private boolean checkChargeChannel(int chargeChannel, Charge record) {
		if (chargeChannel == ChargeQueryForm.INVALID_VALUE)
			return true;
		Set<Integer> channels = new HashSet<Integer>();
		List<ChargePayItem> payItems = record.getPayItems();
		for (ChargePayItem chargePayItem : payItems) {
			channels.add(chargePayItem.getPayType());
		}
		return channels.contains(chargeChannel);
	}
	
	private boolean checkOrigin(int origin, Charge record) {
		if (origin == ChargeQueryForm.INVALID_VALUE)
			return true;
		return origin == record.getOrigin();
	}
	
	private boolean checkStatus(int status, Charge record) {
		if (status == ChargeQueryForm.INVALID_VALUE)
			return true;
		return status == record.getStatus();
	}

	private boolean checkMoney(long minMoney, long maxMoney, Charge record) {
		if(maxMoney == 0L) {
			maxMoney = Long.MAX_VALUE;
		}
		return record.getMoney() >= minMoney && record.getMoney() <= maxMoney;
	}
	
	private boolean checkBUserId(long buserId, Charge record) {
		if (buserId == 0)
			return true;
		return buserId == record.getBuserId();
	}

	private boolean checkPhone(long phone, Charge record) {
		if (phone == 0)
			return true;
		return phone == record.getPhone();
	}

	private boolean checkName(String name, Charge record) {
		if (StringUtils.isBlank(name))
			return true;
		return name.equals(record.getName());
	}

}
