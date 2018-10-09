package com.hq.storeMS.service.orderDetail.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.DelimitCardItem;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.PreStoreCardItem;
import com.hq.orderRestClient.service.order.data.RechargeItem;
import com.hq.storeMS.service.bonusRecord.data.BonusBeanHelper;
import com.hq.storeMS.service.bonusRecord.data.BonusRecord;
import com.hq.storeMS.service.bonusRecord.data.OrderBonus;
import com.hq.storeMS.service.buser.data.SimpleBUser;
import com.hq.storeMS.service.buser.data.StoreBUser;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.orderNotes.data.ChargeBackItem;
import com.hq.storeMS.service.orderNotes.data.ChargeBackRecord;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.hq.storeMS.service.orderNotes.data.OrderPayRemark;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;
import com.hq.storeMS.service.productCardDetail.data.ProductCardItemEnum;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.GoodsType;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.PackageProjectType;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.hq.storeMS.service.workFlowData.data.PrdCardPayEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class OrderDetailBuilder {
	public static OrderDetailBuilder newInstance() {
		OrderDetailBuilder data = new OrderDetailBuilder();
		return data;
	}
	
	private Order order;
	private LeaguerDetail leaguerDetail;
	private StoreBUser storeBUserInfo;
	private StoreCardInfo storeCardInfo;
	private StoreGoods storeGoods;
	private StoreProductInfo storeProductInfo;
	private StorePackageProject storePackageProject;
	private OrderNotes orderNotes;
	private List<BonusRecord> bonusRecords;
	private OrderTrack orderTrack;
	
	public OrderDetail build() {
		OrderDetail result = OrderDetail.newInstance();
		result.setSimpleOrderInfo(buildSimpleOrderInfo());
		result.setSimpleLeaguerInfo(buildSimpleLeaguerInfo());
		
		result.setRechargeDetails(buildRechargeDetails());
		
		result.setDelimitCardDetails(buildDelimitCardDetails());
		result.setBuyDetails(buildBuyDetails());
		result.setDonateDetails(buildDonateDetails());
		result.setChargeBackDetails(buildChargeBackDetails());
		
		result.setOrderRemark(buildOrderRemark());
		result.setPayItems(order.getPayItems());
		result.setOrderBonusDetails(buildOrderBonusDetails());
		result.setOrderTrack(orderTrack);
		return result;
	}
	
	private SimpleOrderInfo buildSimpleOrderInfo() {
		String buserNameP = getBUserNameById(order.getCreatorId());
		return SimpleOrderInfo.newInstanceByOrder(order, buserNameP);
	}
	
	private SimpleLeaguerInfo buildSimpleLeaguerInfo() {
		LeaguerMemberCard leaguerMemberCard = leaguerDetail.getLeaguerMemberCard();
		MembershipCard membershipCard = null;
		if(leaguerMemberCard != null) {
			membershipCard = storeCardInfo.getMembershipCardMap().get(leaguerMemberCard.getCardId());
		}
		return SimpleLeaguerInfo.newInstanceByLeaguerDetailAndMemberCard(leaguerDetail, membershipCard);
	}
	
	private List<RechargeDetail> buildRechargeDetails() {
		List<RechargeDetail> result = new ArrayList<RechargeDetail>();
		List<RechargeItem> rechargeItems = order.getRechargeItems();
		for (RechargeItem item : rechargeItems) {
			result.add(RechargeDetail.newInstanceByRechargeItem(item));
		}
		return result;
	}
	
	private List<DelimitCardDetail> buildDelimitCardDetails() {
		List<DelimitCardDetail> result = new ArrayList<DelimitCardDetail>();
		
		List<DelimitCardItem> delimitCardItems = order.getDelimitCardItems();
		for (DelimitCardItem item : delimitCardItems) {
			DelimitCardDetail delimitCardItem = DelimitCardDetail.newInstanceByDelimitCardItem(item);
			ConsumeItem consumeItem = getConsumeItemByDelimtCard(item);
			delimitCardItem.setPgName(consumeItem.getPgName());
			delimitCardItem.setPrice(consumeItem.getPrice());
			delimitCardItem.setDefaultImg(consumeItem.getDefaultImg());
			delimitCardItem.setTypeName(consumeItem.getTypeName());
			delimitCardItem.setPrdCardName(getPrdCardNameByLeaguerPrdCardId(delimitCardItem.getLeaguerPrdCardId()));
			result.add(delimitCardItem);
		}
		
		List<PreStoreCardItem> storeCardItems = order.getPreStoreCardItems();
		for (PreStoreCardItem item : storeCardItems) {
			DelimitCardDetail detail = DelimitCardDetail.newInstanceByPreStoreCardItem(item);
			ConsumeItem consumeItem = getConsumeItemByDelimtCard(DelimitCardItem.newInstance(item.getItemType(), item.getPgId(), ""));
			detail.setPgName(consumeItem.getPgName());
			detail.setPrice(consumeItem.getPrice());
			detail.setDefaultImg(consumeItem.getDefaultImg());
			detail.setTypeName(consumeItem.getTypeName());
			result.add(detail);
		}
		
		return result;
	}
	
	private List<BuyDetail> buildBuyDetails() {
		List<BuyDetail> result = new ArrayList<BuyDetail>();
		List<BuyItem> buyItems = order.getBuyItems();
		for (BuyItem item : buyItems) {
			ConsumeItem consumeItem = getConsumeItemByBuyItem(item.getBuyType(), item.getPgId());
			result.add(BuyDetail.newInstanceByBuyItem(item, consumeItem));
		}
		return result;
	}
	
	private List<DonateDetail> buildDonateDetails() {
		List<DonateDetail> result = new ArrayList<DonateDetail>();
		List<DonationItem> donationItems = order.getDonationItems();
		for (DonationItem item : donationItems) {
			ConsumeItem consumeItem = getConsumeItemByBuyItem(item.getBuyType(), item.getPgId());
			DonateDetail donateDetail = DonateDetail.newInstanceByBuyItem(item, consumeItem);
			if(donateDetail.getPrice() == 0) {
				donateDetail.setPrice(consumeItem.getPrice());
			}
			result.add(donateDetail);
		}
		return result;
	}
	
	private List<ChargeBackDetail> buildChargeBackDetails() {
		List<ChargeBackDetail> result = new ArrayList<ChargeBackDetail>();
		if(orderNotes == null) {
			return result;
		}
		Collection<ChargeBackRecord> values = orderNotes.getChargeBackRecordMap().values();
		for (ChargeBackRecord chargeBackRecord : values) {
			ChargeBackDetail data = ChargeBackDetail.newInstanceByChargeBackRecord(chargeBackRecord);
			List<ChargeBackItem> chargeBackItems = chargeBackRecord.getChargeBackItems();
			for (ChargeBackItem chargeBackItem : chargeBackItems) {
				ConsumeItem buyItem = getConsumeItemByBuyItem(chargeBackItem.getBuyType(), chargeBackItem.getPgId());
				ChargeBackItemDetail detail = ChargeBackItemDetail.newInstance();
				FastBeanCopyer.getInstance().copy(chargeBackItem, detail);
				if(StringUtils.isNotBlank(chargeBackItem.getItemId())) {//退款 id为空
					detail.setPgName(buyItem.getPgName());
					detail.setDefaultImg(buyItem.getDefaultImg());
					detail.setPrice(buyItem.getPrice());
					detail.setTypeName(buyItem.getTypeName());
				}
				data.getChargeBackItems().add(detail);
			}
			result.add(data);
		}
		return result;
	}
	
	private OrderRemark buildOrderRemark() {
		if(orderNotes == null) {
			return OrderRemark.newInstance();
		}
		OrderPayRemark orderPayRemark = orderNotes.getOrderPayRemark();
		if(orderPayRemark == null){
			return OrderRemark.newInstance();
		}
		long creatorId = orderPayRemark.getCreatorId();
		String remark = orderPayRemark.getRemark();
		String buserNameP = orderPayRemark.getCreatorName();
		return OrderRemark.newInstance(creatorId, buserNameP, remark);
		
	}
	
	private List<OrderBonusDetail> buildOrderBonusDetails() {
		OrderBonus orderBonus = BonusBeanHelper.getInstance().bonusRecords2OrderBonus(bonusRecords);
		List<OrderBonusDetail> bonusDetails = new ArrayList<OrderBonusDetail>();
		if(MapUtils.isEmpty(orderBonus.getBonusInfoMap())) {
			return bonusDetails;
		}
		Collection<BonusInfo> values = orderBonus.getBonusInfoMap().values();
		for (BonusInfo item : values) {
			OrderBonusDetail detail = OrderBonusDetail.newInstance(item);
			detail.setPayType(item.getPrdCardPayType());
			ConsumeItem buyItem = getConsumeItemByBuyItem(item.getBuyType(), item.getPgId());
			detail.setPgName(buyItem.getPgName());
			detail.setPayName(getPayName(detail.getPayType(), detail.getLeaguerPrdCardId()));
			detail.setDefaultImg(buyItem.getDefaultImg());
			detail.setPrice(buyItem.getPrice());
			detail.setTypeName(buyItem.getTypeName());
			
			Collection<UserBonusDetail> buserBonus = detail.getUserBonusMap().values();
			for (UserBonusDetail userBonusDetail : buserBonus) {
				userBonusDetail.setBuserName(getBUserNameById(userBonusDetail.getBuserId()));
			}
			bonusDetails.add(detail);
		}
		return bonusDetails;
	}
	
	private ConsumeItem getConsumeItemByBuyItem(int buyType, String pgId) {
		String pgName="";
		float price=0.0f;
		String typeName="";
		String defaultImg="";
		BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(buyType);
		switch (buyTypeEnum) {
		case GOODS:
			Goods goods = storeGoods.getGoodsMap().get(pgId);
			if(goods != null) {
				pgName = goods.getName();
				price = goods.getPrice();
				defaultImg = goods.getDefaultImg();
				GoodsType goodsType = storeGoods.getGoodsTypeMap().get(goods.getTypeId());
				typeName = goodsType!=null ? goodsType.getName() : "";
			}
			break;
		case PACKAGE:
			PackageProject packageProject = storePackageProject.getPackageProjectMap().get(pgId);
			if(packageProject != null) {
				pgName = packageProject.getName();
				price = packageProject.getSellPrice();
				defaultImg = packageProject.getDefaultImg();
				PackageProjectType packageProjectType = storePackageProject.getPackageProjectTypeMap().get(packageProject.getTypeId());
				typeName = packageProjectType!=null ? packageProjectType.getName() : "";
			}
			break;
		case PRDCARD:
			ProductCard productCard = storeCardInfo.getProductCardMap().get(pgId);
			if(productCard != null) {
				pgName = productCard.getName();
				price = productCard.getSellPrice();
				defaultImg = productCard.getImgPath();
				PrdCardType prdCardType = storeCardInfo.getPrdCardTypeMap().get(productCard.getTypeId());
				typeName = prdCardType!=null ? prdCardType.getName() : "";
			}
			break;
		case PRODUCT:
			ProductInfo productInfo = storeProductInfo.getProductInfoMap().get(pgId);
			if(productInfo != null) {
				pgName = productInfo.getName();
				price = productInfo.getPrice();
				defaultImg = productInfo.getDefaultImg();
				ProductType productType = storeProductInfo.getProductTypeMap().get(productInfo.getTypeId());
				typeName = productType!=null ? productType.getName() : "";
			}
			break;
		case RECHARGE:
			pgName = "充值";
			break;
		default:
			pgName = "退款";
			break;
		}
		return ConsumeItem.newInstance().withDefaultImg(defaultImg).withPgId(pgId).withPgName(pgName).withPrice(price).withTypeName(typeName);
	}
	
	private ConsumeItem getConsumeItemByDelimtCard(DelimitCardItem item) {
		int itemType=item.getItemType(); 
		String pgId=item.getPgId();
		
		String pgName="";
		float price=0.0f;
		String typeName="";
		String defaultImg="";
		ProductCardItemEnum itemTypeEnum = ProductCardItemEnum.valueOf(itemType);
		switch (itemTypeEnum) {
		case GOODS:
			Goods goods = storeGoods.getGoodsMap().get(pgId);
			if(goods != null) {
				pgName = goods.getName();
				price = goods.getPrice();
				defaultImg = goods.getDefaultImg();
				GoodsType goodsType = storeGoods.getGoodsTypeMap().get(goods.getTypeId());
				typeName = goodsType!=null ? goodsType.getName() : "";
			}
			break;
		case PACKAGE:
			PackageProject packageProject = storePackageProject.getPackageProjectMap().get(pgId);
			if(packageProject != null) {
				pgName = packageProject.getName();
				price = packageProject.getSellPrice();
				defaultImg = packageProject.getDefaultImg();
				PackageProjectType packageProjectType = storePackageProject.getPackageProjectTypeMap().get(packageProject.getTypeId());
				typeName = packageProjectType!=null ? packageProjectType.getName() : "";
			}
			break;
		case PRODUCT:
			ProductInfo productInfo = storeProductInfo.getProductInfoMap().get(pgId);
			if(productInfo != null) {
				pgName = productInfo.getName();
				price = productInfo.getPrice();
				defaultImg = productInfo.getDefaultImg();
				ProductType productType = storeProductInfo.getProductTypeMap().get(productInfo.getTypeId());
				typeName = productType!=null ? productType.getName() : "";
			}
			break;
		default:
			break;
		}
		return ConsumeItem.newInstance().withDefaultImg(defaultImg).withPgId(pgId).withPgName(pgName).withPrice(price).withTypeName(typeName);
	}
	
	private String getPrdCardNameByLeaguerPrdCardId(String leaguerCardId) {
		LeaguerProductCard leaguerProductCard = leaguerDetail.getLeaguerProductCardMap().get(leaguerCardId);
		if(leaguerProductCard == null) {
			return null;
		}
		String cardId = leaguerProductCard.getCardId();
		ProductCard productCard = storeCardInfo.getProductCardMap().get(cardId);
		if(productCard == null) {
			return null;
		}
		return productCard.getName();
	}
	
	private String getBUserNameById(long buserId) {
		SimpleBUser buser = storeBUserInfo.getBuserInfoMap().get(buserId);
		String buserName = "";
		if(buser != null) {
			buserName = buser.getName();
		}
		return buserName;
	}
	
	private String getPayName(int payType, String leagerPrdCardId) {
		PrdCardPayEnum prdCardPayEnum = PrdCardPayEnum.valueOf(payType);
		String payName = prdCardPayEnum.getMark();
		if(prdCardPayEnum == PrdCardPayEnum.PrdCard && StringUtils.isNoneBlank(leagerPrdCardId)) {
			payName = getPrdCardNameByLeaguerPrdCardId(leagerPrdCardId);
		}
		return payName;
	}
	
	public OrderDetailBuilder withBonusRecords(List<BonusRecord> data) {
		this.bonusRecords = data;
		return this;
	}
	
	public OrderDetailBuilder withOrder(Order data) {
		this.order = data;
		return this;
	}
	
	public OrderDetailBuilder withLeaguerDetail(LeaguerDetail data) {
		this.leaguerDetail = data;
		return this;
	}
	
	public OrderDetailBuilder withStoreBUserInfo(StoreBUser data) {
		this.storeBUserInfo = data;
		return this;
	}
	
	public OrderDetailBuilder withStoreCardInfo(StoreCardInfo data) {
		this.storeCardInfo = data;
		return this;
	}
	
	public OrderDetailBuilder withStoreGoods(StoreGoods data) {
		this.storeGoods = data;
		return this;
	}
	
	public OrderDetailBuilder withStoreProductInfo(StoreProductInfo data) {
		this.storeProductInfo = data;
		return this;
	}
	
	public OrderDetailBuilder withStorePackageProject(StorePackageProject data) {
		this.storePackageProject = data;
		return this;
	}
	
	public OrderDetailBuilder withOrderNotes(OrderNotes data) {
		this.orderNotes = data;
		return this;
	}
	
	public OrderDetailBuilder withOrderTrack(OrderTrack data) {
		this.orderTrack = data;
		return this;
	}
}
