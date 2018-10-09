package com.hq.chainClient.testClass.robot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.CollectionUtils;

import com.hq.chainClient.service.chain.bs.ChainClientMgr;
import com.hq.chainClient.service.chain.data.Chain;
import com.hq.chainClient.service.chain.data.ChainStore;
import com.hq.chainClient.service.chainCard.bs.ChainCardClientMgr;
import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.MembershipCard;
import com.hq.chainClient.service.chainCard.data.PrdCardType;
import com.hq.chainClient.service.chainCard.data.ProductCard;
import com.hq.chainClient.service.chainGoods.bs.ChainGoodsClientMgr;
import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.Goods;
import com.hq.chainClient.service.chainGoods.data.GoodsType;
import com.hq.chainClient.service.chainPackageProject.bs.ChainPackageProjectClientMgr;
import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectType;
import com.hq.chainClient.service.chainProduct.bs.ChainProductClientMgr;
import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.Product;
import com.hq.chainClient.service.chainProduct.data.ProductType;
import com.hq.chainClient.service.chainUser.apiData.ChainUserLoginForm;
import com.hq.chainClient.service.chainUser.apiData.LoginResp;
import com.hq.chainClient.service.chainUser.bs.ChainUserClientMgr;
import com.hq.chainClient.service.chainUser.data.ChainUser;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class Boss {
	private List<Long> chainIds = new ArrayList<Long>();
	private long chainId;
	private ChainUser user;

	private long phone;
	private String password;

	public static Boss newBoss(long phoneP, String passwordP) {
		Boss boss = new Boss();
		boss.phone = phoneP;
		boss.password = passwordP;
		return boss;
	}

	public boolean login() {
		ChainUserLoginForm loginForm = ChainUserLoginForm.newInstance();
		loginForm.setPhone(phone);
		loginForm.setPassword(password);
		RestResp restResp = ChainUserClientMgr.getInstance().login(loginForm);

		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		user = loginResp.getChainUser();
		if(!CollectionUtils.isEmpty(user.takeChainIds())) {
			chainIds.addAll(user.takeChainIds());
			chainId = chainIds.get(0);
		}
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(user.getId(), token);
		return true;
	}
	
	public ChainStore getRandomChainStore(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		Chain chain = ChainClientMgr.getInstance().getChain(chainId);
		List<ChainStore> list = new ArrayList<>(chain.getChainStoreMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public Goods getRandomGoods(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainGoods chainData = ChainGoodsClientMgr.getInstance().get(chainId);
		List<Goods> list = new ArrayList<Goods>(chainData.getGoodsMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public GoodsType getRandomGoodsType(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainGoods chainData = ChainGoodsClientMgr.getInstance().get(chainId);
		List<GoodsType> list = new ArrayList<GoodsType>(chainData.getGoodsTypeMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(1, list.size()));
	}
	
	public Product getRandomProduct(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainProduct chainData = ChainProductClientMgr.getInstance().get(chainId);
		List<Product> list = new ArrayList<Product>(chainData.getProductInfoMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductType getRandomProductType(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainProduct chainData = ChainProductClientMgr.getInstance().get(chainId);
		List<ProductType> list = new ArrayList<ProductType>(chainData.getProductTypeMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(1, list.size()));
	}
	
	public PackageProject getRandomPackageProject(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainPackageProject chainData = ChainPackageProjectClientMgr.getInstance().get(chainId);
		List<PackageProject> list = new ArrayList<PackageProject>(chainData.getPackageProjectMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public PackageProjectType getRandomPackageProjectType(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainPackageProject chainData = ChainPackageProjectClientMgr.getInstance().get(chainId);
		List<PackageProjectType> list = new ArrayList<PackageProjectType>(chainData.getPackageProjectTypeMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(1, list.size()));
	}
	
	public MembershipCard getRandomMembershipCard(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainCard chainData = ChainCardClientMgr.getInstance().get(chainId);
		List<MembershipCard> list = new ArrayList<MembershipCard>(chainData.getMembershipCardMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductCard getRandomProductCard(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainCard chainData = ChainCardClientMgr.getInstance().get(chainId);
		List<ProductCard> list = new ArrayList<ProductCard>(chainData.getProductCardMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public PrdCardType getRandomProductCardType(long chainId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		ChainCard chainData = ChainCardClientMgr.getInstance().get(chainId);
		List<PrdCardType> list = new ArrayList<PrdCardType>(chainData.getPrdCardTypeMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(1, list.size()));
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public long getId() {
		return user.getId();
	}

}
