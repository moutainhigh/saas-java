package com.hq.experience.data.leaguerAffair;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.leaguerAffair.apiData.AddAlarmClock;
import com.hq.chainStore.service.leaguerAffair.apiData.AddArchives;
import com.hq.chainStore.service.leaguerAffair.apiData.AddLeaguerDiscountCard;
import com.hq.chainStore.service.leaguerAffair.apiData.AddLeaguerMembershipCard;
import com.hq.chainStore.service.leaguerAffair.apiData.AddLeaguerProductCard;
import com.hq.chainStore.service.leaguerAffair.bs.LeaguerAffairMgr;
import com.hq.chainStore.service.leaguerAffair.data.LeaguerAffair;
import com.hq.chainStore.service.storeBeauticianInfo.bs.StoreBeauticianInfoMgr;
import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianInfo;
import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.DiscountCard;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.experienceData.TLeaguerAffair;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

@Deprecated
public class GenerateLeaguerAffairData extends BaseGenerate{
	
	public static void main(String[] args) throws Exception {
		long phone = 13660623958L;
		String service = "http://192.168.10.170:9114/ws/v1";
		String reportService = "http://192.168.10.170:9117/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateLeaguerAffairData().genData(phone);
	}
	
	public GenerateLeaguerAffairData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genLeaguerAffairData();
			}
			System.out.println("Generate LeaguerAffair Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genLeaguerAffairData() {
		//原始店铺数据
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<Leaguer> leaguers = StoreLeaguerInfoMgr.getInstance().getLeaguerList(storeId);
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getStoreCardInfo(storeId);
		List<BeauticianInfo> beauticianInfos = new ArrayList<>(StoreBeauticianInfoMgr.getInstance().get(storeId).getBeauticianInfoMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();

		//随机提取对象数据
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String leaguerAffairId = leaguers.get(RandomUtils.nextInt(0, leaguers.size())).getId();
		List<DiscountCard> discountCards = new ArrayList<DiscountCard>(storeCardInfo.getDiscountCardMap().values());
		List<MembershipCard> membershipCards = new ArrayList<MembershipCard>(storeCardInfo.getMembershipCardMap().values());
		List<ProductCard> productCards = new ArrayList<ProductCard>(storeCardInfo.getProductCardMap().values());
		DiscountCard discountCard = discountCards.get(RandomUtils.nextInt(0, discountCards.size()));
		MembershipCard membershipCard = membershipCards.get(RandomUtils.nextInt(0, membershipCards.size()));
		ProductCard productCard = productCards.get(RandomUtils.nextInt(0, productCards.size()));
		BeauticianInfo beauticianInfo = beauticianInfos.get(RandomUtils.nextInt(0, beauticianInfos.size()));
		
		LeaguerAffair data = LeaguerAffairMgr.getInstance().getLeaguerAffair(leaguerAffairId);
		
		//绑定优惠券
		AddLeaguerDiscountCard addDiscountCard = AddLeaguerDiscountCard.newInstance();
		addDiscountCard.setDiscountCardId(discountCard.getId());
		LeaguerAffairMgr.getInstance().addDiscountCard(storeId, leaguerAffairId, addDiscountCard);
		
		//绑定会员卡
		AddLeaguerMembershipCard addMembershipCard = AddLeaguerMembershipCard.newInstance();
		addMembershipCard.setMembershipCardId(membershipCard.getId());
		LeaguerAffairMgr.getInstance().addMembershipCard(storeId, leaguerAffairId, addMembershipCard);
		
		//绑定耗卡
		AddLeaguerProductCard addProductCard = AddLeaguerProductCard.newInstance();
		addProductCard.setProductCardId(productCard.getId());
		LeaguerAffairMgr.getInstance().addProductCard(storeId, leaguerAffairId, addProductCard);
		
		//添加客户闹钟
		List<TLeaguerAffair> alarmClocks = TLeaguerAffair.buildAddAlarmClockList();
		AddAlarmClock addAlarmClock = AddAlarmClock.newInstance();
		FastBeanCopyer.getInstance().copy(alarmClocks.get(RandomUtils.nextInt(0, alarmClocks.size())), addAlarmClock);
		addAlarmClock.setId(data.getAlarmClockIndex() + 1);
		addAlarmClock.setBeauticianId(beauticianInfo.getBuserId());
		LeaguerAffairMgr.getInstance().addAlarmClock(storeId, leaguerAffairId, addAlarmClock);
		
		//添加客户档案
		List<TLeaguerAffair> archives = TLeaguerAffair.buildAddArchivesList();
		AddArchives addArchives = AddArchives.newInstance();
		FastBeanCopyer.getInstance().copy(archives.get(RandomUtils.nextInt(0, archives.size())), addArchives);
		addArchives.setId(data.getArchivesIndex() + 1);
		LeaguerAffairMgr.getInstance().addArchives(storeId, leaguerAffairId, addArchives);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
	
