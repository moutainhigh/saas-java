package com.hq.testClass.robot.buser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.CollectionUtils;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.common.ClientConstants;
import com.hq.chainStore.service.store.apiData.StoreAddApiForm;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.chainStore.service.storeCardInfo.data.PrdCardType;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfoSynDataHolder;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreAdminRoleInfo4Add;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfoSynDataHolder;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.chainStore.service.storeGoods.data.GoodsType;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.hq.chainStore.service.storeGoods.data.StoreGoodsSynDataHolder;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfoSynDataHolder;
import com.hq.chainStore.service.storePackageProject.data.PackageProject;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProject;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProjectSynDataHolder;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.ProductType;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfoSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.hq.testClass.robot.opUser.OPSuper;

public class Boss {
	
	private long storeId;
	
	private BRobot robot;
	
	public static Boss newBoss(BRobot robot){
		Boss boss = new Boss();
		boss.robot = robot;
		return boss;
	}
	
	/***************************获取店铺随机的基础数据 start***************************/
	public ClerkInfo getRandomClerk(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		String targetId = ClientConstants.STORE_CLERKINFO_ID_SUFFFIX+storeId;
		StoreClerkInfo storeData = StoreClerkInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), targetId);
		List<ClerkInfo> list = new ArrayList<ClerkInfo>(storeData.getClerkInfoMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public Leaguer getRandomLeaguer(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreLeaguerInfo storeData = StoreLeaguerInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<Leaguer> list = new ArrayList<Leaguer>(storeData.getLeaguerInfoMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public Leaguer getLeaguer(long storeId, String leaguerId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreLeaguerInfo storeData = StoreLeaguerInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeData.getLeaguerInfoMap().get(leaguerId);
	}
	
	public ProductInfo getRandomProductInfo(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreProductInfo storeData = StoreProductInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<ProductInfo> list = new ArrayList<ProductInfo>(storeData.getProductInfoMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductType getRandomProductType(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreProductInfo storeData = StoreProductInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<ProductType> list = new ArrayList<ProductType>(storeData.getProductTypeMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductInfo getProductInfo(long storeId, String productId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreProductInfo storeData = StoreProductInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeData.getProductInfoMap().get(productId);
	}
	
	public Goods getRandomGoods(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreGoods storeData = StoreGoodsSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<Goods> list = new ArrayList<Goods>(storeData.getGoodsMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public GoodsType getRandomGoodsType(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreGoods storeData = StoreGoodsSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<GoodsType> list = new ArrayList<GoodsType>(storeData.getGoodsTypeMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public Goods getGoods(long storeId, String goodsId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreGoods storeData = StoreGoodsSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeData.getGoodsMap().get(goodsId);
	}
	
	public ProductCard getRandomProductCard(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreCardInfo storeData = StoreCardInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<ProductCard> list = new ArrayList<ProductCard>(storeData.getProductCardMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public PrdCardType getRandomPrdCardType(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreCardInfo storeData = StoreCardInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<PrdCardType> list = new ArrayList<PrdCardType>(storeData.getPrdCardTypeMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductCard getProductCard(long storeId, String prdCardId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreCardInfo storeData = StoreCardInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeData.getProductCardMap().get(prdCardId);
	}
	
	public MembershipCard getRandomMembershipCard(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreCardInfo storeData = StoreCardInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<MembershipCard> list = new ArrayList<MembershipCard>(storeData.getMembershipCardMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public MembershipCard getMembershipCard(long storeId, String memberId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreCardInfo storeData = StoreCardInfoSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeData.getMembershipCardMap().get(memberId);
	}
	
	public PackageProject getRandomPackageProject(long storeId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StorePackageProject storeData = StorePackageProjectSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		List<PackageProject> list = new ArrayList<PackageProject>(storeData.getPackageProjectMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public PackageProject getPackageProject(long storeId, String packageId) {
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StorePackageProject storeData = StorePackageProjectSynDataHolder.getInstance().getData(String.valueOf(getId()), String.valueOf(storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeData.getPackageProjectMap().get(packageId);
	}
	
	/***************************获取店铺随机的基础数据 end***************************/

	public BRobot getRobot() {
		return robot;
	}

	public void setRobot(BRobot robot) {
		this.robot = robot;
	}

	public boolean reg(){
		return robot.reg();
	}
	
	public boolean regWithParam(long phone){
		return robot.regWithParam(phone);
	}
	
	public boolean login(){
		return robot.login();
	}
	
	public boolean loginWithParam(long phone,long buserId){
		return robot.loginWithParam(phone,buserId);
	}

	public BUser getBuser(Long buserId) {
		return robot.getBuser(buserId);
	}
	
	public void openSimpleStoreAndApprovedByOPSuper(int mark){
		Store store = openSimpleStore(mark);
		System.out.println("======openSimpleStore==========");
		long storeIdTmp = store.getId();
		submit4Check(storeIdTmp);
		System.out.println("======submit4Check==========");
		OPSuper.getInstance().approveStore(storeIdTmp);
		System.out.println("======approveStore==========");
	}
	
	public Store openSimpleStore(int mark){
		StoreAddApiForm formInfo = StoreAddApiForm.newInstance();
		formInfo.setName(getStoreName(mark)).setBossId(robot.getId());
		return openStore(formInfo);
	}
	
	
	
	public Store openStore(StoreAddApiForm formInfo){
		
		BRobotData data = robot.getData();
		
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		Store store = StoreMgr.getInstance().openStore(formInfo);

		this.storeId = store.getId();
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return store;
	}
	
	public void submit4Check(long storeIdP){
		BRobotData data = robot.getData();
		
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		StoreMgr.getInstance().submit4Check(storeIdP);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void genJoinStoreQrCode(long storeId){
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		StoreMgr.getInstance().genJoinStoreQrCode(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public Store getStore(){
		
		AccessTokenMgr.getInstance().setOpIdTL(this.robot.getId());
		Store store = StoreMgr.getInstance().get(this.storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		return store;
		
	}
	
	public StoreClerkInfo getStoreClerkInfo(){
		AccessTokenMgr.getInstance().setOpIdTL(this.robot.getId());
		Store store = StoreMgr.getInstance().get(this.storeId);
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().get(store.getClerkInfoId());
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeClerkInfo;
		
	}
	
	public StoreClerkInfo getStoreClerkInfoByStoreId(String storeClerkInfoId){
		AccessTokenMgr.getInstance().setOpIdTL(this.robot.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().get(storeClerkInfoId);
		AccessTokenMgr.getInstance().removeOpIdTL();
		return storeClerkInfo;
		
	}
	
	public void handleApplyClerk(Long storeId,String storeClerkInfoId, long bUserId, boolean approved){
		BRobotData data = robot.getData();
		
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());

		StoreClerkInfoMgr.getInstance().handleApplyClerkInfo(storeId, storeClerkInfoId, bUserId, approved);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void addClerk(long bUserId){
		BRobotData data = robot.getData();
		
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		Store store = StoreMgr.getInstance().get(this.storeId);
		
		StoreClerkInfoMgr.getInstance().addClerk(store.getId(), bUserId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void addClerkWithParam(long bUserId,long storeId,String storeClerkInfoId){
		BRobotData data = robot.getData();
		
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		StoreClerkInfoMgr.getInstance().addClerk(storeId, bUserId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	/**
	 * 
	 * addStoreAdminRole:(新增角色). <br/>   
	 *  
	 * @author kevin
	 * @return  返回角色ID
	 * @since JDK 1.6
	 */
	public int addStoreAdminRole(String roleName, StoreAdminPermEnum adminPerm) {
		StoreClerkInfo storeClerkInfo = getStoreClerkInfo();
		int roleIdIndex = storeClerkInfo.getRoleIdIndex()+1;
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		StoreAdminRoleInfo4Add addStoreAdminRoleInfo = StoreAdminRoleInfo4Add.newInstance()
			.setStoreId(storeClerkInfo.getStoreId())
			.setName(roleName)
			.setRoleIdIndex(roleIdIndex)
			.setStoreClerkInfoId(storeClerkInfo.getId())
			.addPermSet(adminPerm)
			.setDescript(adminPerm.getDescript())
			;
		StoreClerkInfoMgr.getInstance().addStoreAdminRole(addStoreAdminRoleInfo);
		AccessTokenMgr.getInstance().removeOpIdTL();
		return roleIdIndex;
	}
	
	/**
	 * 
	 * addRole2Clerk:(将角色赋给员工). <br/>   
	 *  
	 * @author kevin
	 * @param clerkInfo
	 * @param roleId  
	 * @since JDK 1.6
	 */
	public void addRole2Clerk(long buserId, int roleId) {
		StoreClerkInfo storeClerkInfo = getStoreClerkInfo();
		
		AccessTokenMgr.getInstance().setOpIdTL(getId());
		Set<Integer> roleIdSet = new HashSet<Integer>();
		roleIdSet.add(roleId);
		StoreClerkInfoMgr.getInstance().addRoleSet2Clerk(storeClerkInfo.getStoreId(), storeClerkInfo.getId(), buserId, roleIdSet);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	

	public long getStoreId() {
		return storeId;
	}


	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}


	private String getStoreName(int mark) {
		return "体验店_"+mark;
	}


	public long getId() {
		return this.robot.getId();
	}
	
	public long getRobotStoreId() {
		List<Long> list = this.robot.getData().getStoreIds();
		Collections.sort(list);
		this.storeId = list.get(0);
		return storeId;
	}
	
	public void changeVipLevel(){
		AccessTokenMgr.getInstance().setOpIdTL(robot.getData().getUserId());
		robot.changeVipLevel();
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
}
