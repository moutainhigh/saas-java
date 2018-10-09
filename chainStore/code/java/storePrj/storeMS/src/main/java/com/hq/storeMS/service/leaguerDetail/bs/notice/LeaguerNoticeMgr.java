package com.hq.storeMS.service.leaguerDetail.bs.notice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.hq.storeMS.common.message.trigger.TriggerTypeEnum;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.UseFlagEnum;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailDataHolder;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.message.apiData.BUserMessageAddForm;
import com.hq.storeMS.service.message.bs.BUserMessageMgr;
import com.hq.storeMS.service.store.bs.StoreDataHolder;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBirthdayConfig;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerNoticeMgr {
	public static LeaguerNoticeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerNoticeMgr.class);
	}
	
	private static final DateFormat SDF_DAY = new SimpleDateFormat("MM/dd");
	
	//遍历店铺 产生生日提醒消息   分页查询  防止店铺数量过大导致内存消耗殆尽
	public void batchStoreNotice() {
		int pageItemCount = 100;
		int count = (int)StoreDataHolder.getInstance().allStoreCount();
		int totalPage = PageUtil.getInstance().getTotalPage(count, pageItemCount);
		for (int i = 0; i < totalPage; i++) {
			List<Store> stores = StoreDataHolder.getInstance().findPage(pageItemCount, i+1);
			for (Store store : stores) {
				singleStoreNotice(store.getId());
			}
		}
	}
	
	//单店铺会员生日提醒消息
	public void singleStoreNotice(long storeId) {
		Map<String,Integer> mmddMap = getMMdds(storeId);
		if(MapUtils.isEmpty(mmddMap)) {
			return;
		}
		Set<String> mmdds = mmddMap.keySet();
		Map<String, List<LeaguerDetail>> map = getStoreLeaguerMap(storeId);
		for (String md : mmdds) {
			List<LeaguerDetail> list = map.get(md);
			if(CollectionUtils.isEmpty(list)) {
				continue;
			}
			for (LeaguerDetail data : list) {
				generateMessage(data, mmddMap.get(md));
			}
		}
	}
	
	private void generateMessage(LeaguerDetail detail, int count) {
		Set<Long> buserIds = detail.getBuserIds();
		if(CollectionUtils.isEmpty(buserIds)) {
			return ;
		}
		String number = count==0 ?"今天":count+"天后";
		String tips = TriggerTypeEnum.LEAGUER_BIRTHDAY_NOTICE.getTips();
		String messageBody = StringFormatUtil.format(tips, detail.getName(), number);
		for (Long buserId : buserIds) {
			BUserMessageAddForm addForm=BUserMessageAddForm.newInstance();
			addForm.setBuserId(buserId);
			addForm.setMessageObjId(detail.getId());
			addForm.setMessageType(TriggerTypeEnum.LEAGUER_BIRTHDAY_NOTICE.ordinal());
			addForm.setMessageBody(messageBody);
			addForm.setStoreId(detail.getStoreId());
			BUserMessageMgr.getInstance().addWithForm(addForm);
		}
	}
	
	// mmdd:count
	private Map<String,Integer> getMMdds(long storeId){
		Map<String,Integer> result = new HashMap<String,Integer>();
		Date nowDate = new Date();
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		List<LeaguerBirthdayConfig> birthdayConfigs = storeConfig.takeLeaguerBirthdayConfigs();
		for (LeaguerBirthdayConfig config : birthdayConfigs) {
			if(config.getUseFlag() == UseFlagEnum.Enable.ordinal()) {
				String mmdd = AppUtils.timeStamp2Str(DateUtils.addDays(nowDate, config.getCount()).getTime(), SDF_DAY);
				result.put(mmdd, config.getCount());
			}
		}
		return result;
	}
	
	//获取如下格式的数据       月/日:[客户列表]
	private Map<String, List<LeaguerDetail>> getStoreLeaguerMap(long storeId){
		Map<String, List<LeaguerDetail>> result = new HashMap<String, List<LeaguerDetail>>();
		LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		List<LeaguerDetail> list = LeaguerDetailDataHolder.getInstance().findSimpleDataList(queryForm);
		if(CollectionUtils.isEmpty(list)) {
			return result;
		}
		for (LeaguerDetail leaguerDetail : list) {
			if(leaguerDetail.getBirthday() == 0) {
				continue;
			}
			String mmdd = AppUtils.timeStamp2Str(leaguerDetail.getBirthday(), SDF_DAY);
			if(result.get(mmdd) == null) {
				result.put(mmdd, new ArrayList<LeaguerDetail>());
			}
			result.get(mmdd).add(leaguerDetail);
		}
		return result;
	}
	
}
