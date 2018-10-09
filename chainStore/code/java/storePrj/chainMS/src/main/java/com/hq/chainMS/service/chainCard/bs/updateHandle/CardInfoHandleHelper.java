package com.hq.chainMS.service.chainCard.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateApiForm;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class CardInfoHandleHelper {

	public static CardInfoHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(CardInfoHandleHelper.class);
	}

	private Map<ChainCardUpdateType, ICardInfoHandle> handleMapper = new HashMap<ChainCardUpdateType, ICardInfoHandle>();

	public CardInfoHandleHelper() {
		handleMapper.put(ChainCardUpdateType.AddPrdCardType, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return PrdCardTypeHandle.getInstance().addPrdCardType(chainId, updateForm.getPrdCardTypeAddForm());
			}
		});
		handleMapper.put(ChainCardUpdateType.DelPrdCardType, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return PrdCardTypeHandle.getInstance().delPrdCardType(chainId, updateForm.getPrdCardTypeRemoveForm());
			}
		});
		handleMapper.put(ChainCardUpdateType.UpdPrdCardType, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return PrdCardTypeHandle.getInstance().updPrdCardType(chainId, updateForm.getPrdCardTypeUpdateForm());
			}
		});
		
		handleMapper.put(ChainCardUpdateType.AddMembershipCard, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return MembershipCardHandle.getInstance().addMembershipCard(chainId, updateForm.getAddMembershipCard());
			}
		});
		handleMapper.put(ChainCardUpdateType.DelMembershipCard, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return MembershipCardHandle.getInstance().delMembershipCard(chainId, updateForm.getDelMembershipCard());
			}
		});
		handleMapper.put(ChainCardUpdateType.UpdMembershipCard, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return MembershipCardHandle.getInstance().updMembershipCard(chainId, updateForm.getUpdMembershipCard());
			}
		});
		handleMapper.put(ChainCardUpdateType.UpdMemberCardState, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return MembershipCardHandle.getInstance().updMemberCardState(chainId, updateForm.getUpdateMemberCardStateData());
			}
		});
		handleMapper.put(ChainCardUpdateType.BatchUpdMemberCardState, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return BatchCardHandle.getInstance().batchUpdMemberCardState(chainId, updateForm.getBatchUpdateMemberCardStateData());
			}
		});
		
		handleMapper.put(ChainCardUpdateType.AddProductCard, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return ProductCardHandle.getInstance().addProductCard(chainId, updateForm.getAddProductCard());
			}
		});
		handleMapper.put(ChainCardUpdateType.DelProductCard, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return ProductCardHandle.getInstance().delProductCard(chainId, updateForm.getDelProductCard());
			}
		});
		handleMapper.put(ChainCardUpdateType.UpdProductCard, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return ProductCardHandle.getInstance().updProductCard(chainId, updateForm.getUpdProductCard());
			}
		});
		handleMapper.put(ChainCardUpdateType.UpdProductCardState, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return ProductCardHandle.getInstance().updProductCardState(chainId, updateForm.getUpdateProductCardStateData());
			}
		});
		handleMapper.put(ChainCardUpdateType.BatchUpdProductCardState, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return BatchCardHandle.getInstance().batchUpdProductCardState(chainId, updateForm.getBatchUpdateProductCardStateData());
			}
		});
		
		handleMapper.put(ChainCardUpdateType.MemberCardAllot, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return MembershipCardHandle.getInstance().allotMemberCard(chainId, updateForm.getMemberCardAllotForm());
			}
		});
		handleMapper.put(ChainCardUpdateType.MemberCardBatchAllot, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return BatchCardHandle.getInstance().memberCardBatchAllot(chainId, updateForm.getMemberCardBatchAllotForm());
			}
		});
		handleMapper.put(ChainCardUpdateType.ProductCardAllot, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return ProductCardHandle.getInstance().allotProductCard(chainId, updateForm.getProductCardAllotForm());
			}
		});
		handleMapper.put(ChainCardUpdateType.ProductCardBatchAllot, new ICardInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm){
				return BatchCardHandle.getInstance().productCardBatchAllot(chainId, updateForm.getProductCardBatchAllotForm());
			}
		});
	}

	public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm) {
		ChainCardUpdateType updateType = updateForm.getChainCardUpdateType();
		ICardInfoHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(chainId, updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
