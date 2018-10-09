package com.hq.storeMS.service.homePage.bs.mgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.storeMS.service.homePage.apiData.QueryHomePageForm;
import com.hq.storeMS.service.homePage.data.HomePage;
import com.hq.storeMS.service.homePage.data.HomePageItemEnum;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.bs.WorkFlowTypeMgr;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.common.hotSwap.HotSwap;

public class HomePageMgrHelper {

	public static HomePageMgrHelper getInstance() {
		return HotSwap.getInstance().getSingleton(HomePageMgrHelper.class);
	}

	private Map<HomePageItemEnum, IHomePageMgr> handleMapper = new HashMap<HomePageItemEnum, IHomePageMgr>();

	public HomePageMgrHelper() {
		handleMapper.put(HomePageItemEnum.Card, new IHomePageMgr() {
			@Override
			public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
				StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(queryForm.getStoreId());
				homePage.setStoreCard(storeCard);
			}
		});
		handleMapper.put(HomePageItemEnum.Clerk, new IHomePageMgr() {
			@Override
			public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
				StoreClerkInfo storeClerk = StoreClerkInfoMgr.getInstance().getByStoreId(queryForm.getStoreId());
				homePage.setStoreClerk(storeClerk);
			}
		});
		handleMapper.put(HomePageItemEnum.Goods, new IHomePageMgr() {
			@Override
			public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
				StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(queryForm.getStoreId());
				homePage.setStoreGoods(storeGoods);
			}
		});
		handleMapper.put(HomePageItemEnum.Leaguer, new IHomePageMgr() {
			@Override
			public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
				StoreLeaguerInfo storeLeaguer = StoreLeaguerInfoMgr.getInstance().get(queryForm.getStoreId());
				homePage.setStoreLeaguer(storeLeaguer);
			}
		});
		handleMapper.put(HomePageItemEnum.Product, new IHomePageMgr() {
			@Override
			public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
				StoreProductInfo storeProduct = StoreProductInfoMgr.getInstance().getByStoreId(queryForm.getStoreId());
				homePage.setStoreProduct(storeProduct);
			}
		});
		handleMapper.put(HomePageItemEnum.StatisticsData, new IHomePageMgr() {
			@Override
			public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
				StatisticsDataMgr.getInstance().attachInfo(homePage, queryForm);
			}
		});
		handleMapper.put(HomePageItemEnum.WorkFlowType, new IHomePageMgr() {
			@Override
			public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
				QueryWorkFlowTypeForm params = QueryWorkFlowTypeForm.newInstance();
				List<WorkFlowType> workFlowTypes = WorkFlowTypeMgr.getInstance().findByCond(params);
				homePage.setWorkFlowTypes(workFlowTypes);
			}
		});
	}

	public void attachInfo(HomePage homePage, QueryHomePageForm queryForm) {
		Set<Integer> items = queryForm.getItems();
		for (Integer integer : items) {
			handleMapper.get(HomePageItemEnum.valueOf(integer)).attachInfo(homePage, queryForm);
		}
	}
}
