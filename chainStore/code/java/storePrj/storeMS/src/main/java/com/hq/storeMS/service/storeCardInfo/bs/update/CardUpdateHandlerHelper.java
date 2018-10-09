package com.hq.storeMS.service.storeCardInfo.bs.update;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateApiForm;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class CardUpdateHandlerHelper {

	public static CardUpdateHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(CardUpdateHandlerHelper.class);
	}

	private Map<StoreCardInfoUpdateType, ICardUpdateHandler> handleMapper = new HashMap<StoreCardInfoUpdateType, ICardUpdateHandler>();

	public CardUpdateHandlerHelper() {
		handleMapper.put(StoreCardInfoUpdateType.AddPrdCardTop, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return ProductCardTopMgr.getInstance().addToTop(storeId, updateForm.getAddPrdCardTop());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.CancelPrdCardTop, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return ProductCardTopMgr.getInstance().cancelTop(storeId, updateForm.getCancelPrdCardTop());
			}
		});
		
		handleMapper.put(StoreCardInfoUpdateType.AddPrdCardType, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return PrdCardTypeMgr.getInstance().addPrdCardType(storeId, updateForm.getPrdCardTypeAddForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.DelPrdCardType, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return PrdCardTypeMgr.getInstance().delPrdCardType(storeId, updateForm.getPrdCardTypeRemoveForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.UpdPrdCardType, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return PrdCardTypeMgr.getInstance().updPrdCardType(storeId, updateForm.getPrdCardTypeUpdateForm());
			}
		});
		
		handleMapper.put(StoreCardInfoUpdateType.AddMembershipCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return MembershipCardMgr.getInstance().addMembershipCard(storeId, updateForm.getAddMembershipCard());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.AddMembershipCardList, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return BatchCardMgr.getInstance().addMembershipCardList(storeId, updateForm.getAddMembershipCardList());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.DelMembershipCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return MembershipCardMgr.getInstance().delMembershipCard(storeId, updateForm.getDelMembershipCard());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.UpdMembershipCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return MembershipCardMgr.getInstance().updMembershipCard(storeId, updateForm.getUpdMembershipCard());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.UpdMemberCardState, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return MembershipCardMgr.getInstance().updMemberCardState(storeId, updateForm.getUpdateMemberCardStateData());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.BatchUpdMemberCardState, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return BatchCardMgr.getInstance().batchUpdMemberCardState(storeId, updateForm.getBatchUpdateMemberCardStateData());
			}
		});
		
		handleMapper.put(StoreCardInfoUpdateType.AddProductCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return ProductCardMgr.getInstance().addProductCard(storeId, updateForm.getAddProductCard());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.DelProductCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return ProductCardMgr.getInstance().delProductCard(storeId, updateForm.getDelProductCard());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.UpdProductCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return ProductCardMgr.getInstance().updProductCard(storeId, updateForm.getUpdProductCard());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.UpdProductCardState, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return ProductCardMgr.getInstance().updProductCardState(storeId, updateForm.getUpdateProductCardStateData());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.BatchUpdProductCardState, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.CARD_ADMIN);
				return BatchCardMgr.getInstance().batchUpdProductCardState(storeId, updateForm.getBatchUpdateProductCardStateData());
			}
		});
		
		handleMapper.put(StoreCardInfoUpdateType.AddDiscountCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.DelDiscountCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.UpdDiscountCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				return OperateTips.newInstance(true);
			}
		});
		
		handleMapper.put(StoreCardInfoUpdateType.BatchCancelChainCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainProductCardMgr.getInstance().batchCancelChainCard(storeId, updateForm.getCardBatchCancelForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.BatchPullCardFromChain, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainProductCardMgr.getInstance().batchAddChainCard(storeId, updateForm.getCardBatchPullForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.CancelChainCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainProductCardMgr.getInstance().cancelChainCard(storeId, updateForm.getCardCancelForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.PullCardFromChain, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainProductCardMgr.getInstance().addChainCard(storeId, updateForm.getCardPullForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.BatchCancelChainMemberCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainMemberCardMgr.getInstance().batchCancelChainMemberCard(storeId, updateForm.getMemberCardBatchCancelForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.BatchPullMemberCardFromChain, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainMemberCardMgr.getInstance().batchAddChainMemberCard(storeId, updateForm.getMemberCardBatchPullForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.CancelChainMemberCard, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainMemberCardMgr.getInstance().cancelChainMemberCard(storeId, updateForm.getMemberCardCancelForm());
			}
		});
		handleMapper.put(StoreCardInfoUpdateType.PullMemberCardFromChain, new ICardUpdateHandler(){
			@Override
			public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChainMemberCardMgr.getInstance().addChainMemberCard(storeId, updateForm.getMemberCardPullForm());
			}
		});
	}

	public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm) {
		StoreCardInfoUpdateType updateType = StoreCardInfoUpdateType.valueOf(updateForm.getUpdateType());
		ICardUpdateHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(storeId, updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
