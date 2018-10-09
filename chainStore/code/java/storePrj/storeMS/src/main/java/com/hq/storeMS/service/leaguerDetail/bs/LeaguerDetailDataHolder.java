package com.hq.storeMS.service.leaguerDetail.bs;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailCacheDAO;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailDAO;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerTypeEnum;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigDataHolder;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerAnalysisConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitLeaguerAnalysisEnum;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailDataHolder {
	
	public static LeaguerDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerDetailDataHolder.class);
	}
	
	public void addWithId(LeaguerDetail target) {
		LeaguerDetailDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
		StoreConfig storeConfig = StoreConfigDataHolder.getInstance().get(target.getStoreId());
		updateLeaguerDetailInfo(target,storeConfig);
		LeaguerDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(LeaguerDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		LeaguerDetailDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		LeaguerDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(LeaguerDetail target) {
		LeaguerDetailDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		LeaguerDetailCacheDAO.getInstance().delete(target);
	}
	
	public LeaguerDetail get(long storeId, String id) {
		LeaguerDetail target = LeaguerDetailCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = LeaguerDetailDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				LeaguerDetailCacheDAO.getInstance().save(target);
			}
		}
		
		StoreConfig storeConfig = StoreConfigDataHolder.getInstance().get(storeId);
		// RO 对象之后 填充内容  直接影响内存的对象[是否有bug有待研究]
		updateLeaguerDetailInfo(target,storeConfig);
		return target;
	}
	
	//获取原始的会员信息列表
	public List<LeaguerDetail> findSimpleDataList(LeaguerDetailQueryForm queryForm) {
		List<LeaguerDetail> list = LeaguerDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = LeaguerDetailDAO.getInstance().findLeaguerDetailList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				LeaguerDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}

	
	public List<LeaguerDetail> findLeaguerDetailList(LeaguerDetailQueryForm queryForm) {
		List<LeaguerDetail> list = LeaguerDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = LeaguerDetailDAO.getInstance().findLeaguerDetailList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				LeaguerDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		
		StoreConfig storeConfig = StoreConfigDataHolder.getInstance().get(queryForm.getStoreId());
		if(CollectionUtils.isNotEmpty(list)) {
			for (LeaguerDetail leaguerDetail : list) {
				updateLeaguerDetailInfo(leaguerDetail,storeConfig);
			}
		}
		return list;
	}
	
	private void updateLeaguerDetailInfo(LeaguerDetail leaguer, StoreConfig storeConfig) {
		if(leaguer == null) {
			return;
		}
		//客户的消费次数、消费总额、消费时间信息
		int consumeCount = leaguer.getConsumeCount() == 0 ? 1 : leaguer.getConsumeCount();
		float consumeAmount = leaguer.getConsumeAmount();
		long lastConsumeTime = leaguer.getLastConsumeTime();
		long firstConsumeTime = leaguer.getFirstConsumeTime();
		
		//客单价
		float avgPrice = BigDecimalUtil.round(consumeAmount / consumeCount, 2);
		leaguer.setAvgPrice(avgPrice);
		
		//月频率
		int number = AppUtils.diffTimeMonth(new Date(firstConsumeTime), new Date(lastConsumeTime))+1;
		int monthRate = consumeCount/number;
		leaguer.setMonthRate(monthRate);
		
//		if(monthRate >= CustomerTypeEnum.HIGH_GRADE_CUSTOMER.getRate()){
//			customerType = CustomerTypeEnum.HIGH_GRADE_CUSTOMER.ordinal();
//		}else if(monthRate >= CustomerTypeEnum.RISK_CUSTOMER.getRate()){
//			customerType = CustomerTypeEnum.RISK_CUSTOMER.ordinal();
//		}else{
//			customerType = CustomerTypeEnum.QUIESCENCE_CUSTOMER.ordinal();
//		}
		
		//客户类型
		int customerType = LeaguerTypeEnum.ALL.ordinal();
		long interval = System.currentTimeMillis() - lastConsumeTime;
		//根据storeConfig配置设置会员类型
		SysInitLeaguerAnalysisEnum highGradeCustomer = SysInitLeaguerAnalysisEnum.HIGH_GRADE_CUSTOMER;
		SysInitLeaguerAnalysisEnum riskCustomer = SysInitLeaguerAnalysisEnum.RISK_CUSTOMER;
		SysInitLeaguerAnalysisEnum quiescenceCustomer = SysInitLeaguerAnalysisEnum.QUIESCENCE_CUSTOMER;
		long highGradeThreshold = highGradeCustomer.getThreshold();
		long riskThreshold = riskCustomer.getThreshold();
		long quiescenceThreshold = quiescenceCustomer.getThreshold();
		
		if(null != storeConfig){
			Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap = storeConfig.getLeaguerConfig().getLeaguerAnalysisConfigMap();
			if(leaguerAnalysisConfigMap.containsKey(highGradeCustomer.ordinal()+1)){
				highGradeThreshold = leaguerAnalysisConfigMap.get(highGradeCustomer.ordinal()+1).getThreshold();
			}
			if(leaguerAnalysisConfigMap.containsKey(riskCustomer.ordinal()+1)){
				riskThreshold = leaguerAnalysisConfigMap.get(riskCustomer.ordinal()+1).getThreshold();
			}
			if(leaguerAnalysisConfigMap.containsKey(quiescenceCustomer.ordinal()+1)){
				quiescenceThreshold = leaguerAnalysisConfigMap.get(quiescenceCustomer.ordinal()+1).getThreshold();
			}
		}
		
		if(interval < highGradeThreshold * ServerConstants.ONE_DAY){
			customerType = LeaguerTypeEnum.HIGH_GRADE_CUSTOMER.ordinal();
		}else if((interval >= riskThreshold * ServerConstants.ONE_DAY) && (interval <= quiescenceThreshold * ServerConstants.ONE_DAY)){
			customerType = LeaguerTypeEnum.RISK_CUSTOMER.ordinal();
		}else if(interval > quiescenceThreshold * ServerConstants.ONE_DAY){
			customerType = LeaguerTypeEnum.QUIESCENCE_CUSTOMER.ordinal();
		}else{
			customerType = LeaguerTypeEnum.ALL.ordinal();
		}
		
		leaguer.setCustomerType(customerType);
		
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}
