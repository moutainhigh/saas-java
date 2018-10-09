package com.hq.chainStore.service.storeLeaguerInfo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hq.common.StringUtils4Client;
import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreLeaguerInfoSynDataHolder extends AbsDataSynDataHolder<StoreLeaguerInfo> {

	public static StoreLeaguerInfoSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreLeaguerInfo;

	protected Class<StoreLeaguerInfo> getClazz() {
		return StoreLeaguerInfo.class;
	}

	protected RestDao<StoreLeaguerInfo> getDao() {
		return StoreLeaguerInfoDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	/**
	 * 获取店铺客户列表
	 * @param ownerId
	 * @param storeId
	 * @return
	 */
	public List<Leaguer> getLeaguers(String ownerId, Long storeId){
		StoreLeaguerInfo storeLeaguerInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<Leaguer>(storeLeaguerInfo.getLeaguerInfoMap().values());
	}
	
	/**
	 * 根据条件，模糊匹配客户   
	 * @param ownerId
	 * @param storeId
	 * @param cond  可以是： cuserId phone name
	 * @return
	 */
	public List<Leaguer> getLeaguersByCond(String ownerId, Long storeId, String cond){
		StoreLeaguerInfo storeLeaguerInfo = super.getData(ownerId, String.valueOf(storeId));
		List<Leaguer> result = new ArrayList<Leaguer>();
		List<Leaguer> leaguers = new ArrayList<Leaguer>(storeLeaguerInfo.getLeaguerInfoMap().values());
		if(StringUtils4Client.isBlank(cond)){
			result = leaguers;
		}else{
			cond = cond.trim();
			for (Leaguer leaguer : leaguers) {
				long cuserId = leaguer.getCuserId();
				long phone = leaguer.getPhone();
				String name = leaguer.getName();
				if(cuserId > 0 && String.valueOf(cuserId).contains(cond)){
					result.add(leaguer);
				}else if(phone > 0 && String.valueOf(leaguer.getPhone()).contains(cond)){
					result.add(leaguer);
				}else if(StringUtils4Client.isNotBlank(name) && leaguer.getName().contains(cond)){
					result.add(leaguer);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取店铺客户的MAP集合
	 * @param ownerId
	 * @param storeId
	 * @return
	 */
	public Map<String, Leaguer> getLeaguerInfoMap(String ownerId, Long storeId){
		StoreLeaguerInfo storeLeaguerInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeLeaguerInfo.getLeaguerInfoMap();
	}
	
	/**
	 * 获取客户详情
	 * @param ownerId
	 * @param storeId
	 * @param leaguerId
	 * @return
	 */
	public Leaguer getLeaguerInfo(String ownerId, Long storeId, String leaguerId){
		return getLeaguerInfoMap(ownerId, storeId).get(leaguerId);
	}
	
	/**
	 * 根据CUserId获取客户详情
	 * @param ownerId
	 * @param storeId
	 * @param leaguerId
	 * @return
	 */
	public Leaguer getLeaguerInfoByCUserId(String ownerId, long storeId, long cuserId){
		return getLeaguerInfo(ownerId, storeId, StringUtils4Client.format("{}_{}", storeId, cuserId));
	}

}
