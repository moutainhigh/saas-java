package com.hq.storeMS.service.storeLeaguerInfo.bs.update;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.customerRestClient.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerRestClient.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.ImageUtil;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.cuser.bs.CUserMgr;
import com.hq.storeMS.service.img.apiData.FileUploadApiForm;
import com.hq.storeMS.service.img.apiData.ImgResp;
import com.hq.storeMS.service.img.bs.ImgMgr;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.store.apiData.JoinStoreForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerDelApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerUpdateInfoApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.SaveFollowUserForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfoBeanHelper;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerInfoMgr {

	public static LeaguerInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerInfoMgr.class);
	}
	
	public OperateTips saveFollowUserForm(long storeId, SaveFollowUserForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.SaveFollowUser.getMark()+"失败");
		String leaguerId = inputData.getLeaguerId();
		LeaguerDetail detail = LeaguerDetailMgr.getInstance().getSimple(storeId, leaguerId);
		detail.setBuserIds(inputData.getBuserIds());
		LeaguerDetailMgr.getInstance().updateSimple(detail);
		tips.setSuccess(true);
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Leaguer, StoreLeaguerInfoUpdateType.SaveFollowUser.getMark()));
		return tips;
	}

	public OperateTips delLeaguer(long storeId, LeaguerDelApiForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.DelLeaguer.getMark()+"失败");
		StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(storeId);
		if (StoreLeaguerInfoBeanHelper.getInstance().removeLeaguerInfo(storeLeaguerInfo, inputData.getId())) {
			StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeLeaguerInfo);
			//将详情信息也更新
			LeaguerDetail detail = LeaguerDetailMgr.getInstance().getSimple(storeId, inputData.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			LeaguerDetailMgr.getInstance().updateSimple(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Leaguer, StoreLeaguerInfoUpdateType.DelLeaguer.getMark()));
		}
		return tips;
	}

	public OperateTips updateLeaguerInfo(long storeId, LeaguerUpdateInfoApiForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.UpdateLeaguerInfo.getMark()+"失败");

		String leaguerId = inputData.getId();
		StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(storeId);
		
//		if (checkPhoneExists4Update(inputData.getPhone(), storeLeaguerInfo, inputData.getId())) {
//			tips.setTips("客户手机号已存在，请换一个手机号码");
//			return tips;
//		}
		
		if (StoreLeaguerInfoBeanHelper.getInstance().updateLeaguerInfo(storeLeaguerInfo, inputData)) {
			StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeLeaguerInfo);
		}
		
		//将详情信息也更新
		LeaguerDetail detail = LeaguerDetailMgr.getInstance().getSimple(storeId, leaguerId);
		inputData.updateLeaguerDetail(detail);
		LeaguerDetailMgr.getInstance().updateSimple(detail);
		tips.setSuccess(true);
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Leaguer, StoreLeaguerInfoUpdateType.UpdateLeaguerInfo.getMark()));
		return tips;
	}

	//B端添加客户 只用phone判断cuser是否存在 不存在则添加cuser
	public OperateTips addLeaguerInfo(long storeId, LeaguerAddApiForm inputData){
		OperateTips tips = OperateTips.newInstance(false,"添加新客户失败");
		if(StoreVipMgr.getInstance().isLeaguerLimited(storeId)){
			tips.setTips("当前店铺客户数量已达上限");
			return tips;
		}
		
		StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(storeId);
		if(checkPhoneExists4Add(inputData.getPhone(), storeLeaguerInfo)){
			tips.setTips("客户手机号已存在，请换一个手机号码");
			return tips;
		}
		
		CUser cuser = checkCUser(inputData);
		
		LeaguerDetail leaguerInfo = genLeaguerInfo(cuser, inputData, storeId);
		if(addLeaguer(storeLeaguerInfo, leaguerInfo, cuser, storeId)){
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, leaguerInfo.getName(), OpLogTypeEnum.Leaguer, "新增会员"));
			tips.setSuccess(true);
		}
		
		return tips;
	}
	
	//C端用户加入店铺 只用cuserId判断cuser是否存在已经加入 不使用phone作为判断条件
	public OperateTips addLeaguer4CuserJoin(JoinStoreForm joinStoreForm){
		OperateTips tips = OperateTips.newInstance(false,"加入店铺失败");
		
		long cuserId = joinStoreForm.getCuserId();
		long storeId = joinStoreForm.getStoreId();
		LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().get(storeId, Leaguer.genIdByStoreId(storeId, cuserId));
		if(leaguerDetail!=null) {
			tips.setTips("用户已经加入该店铺");
			return tips;
		}
		
		CUser cuser = CUserMgr.getInstance().get(cuserId);
		LeaguerAddApiForm formInfo = LeaguerAddApiForm.newInstance();
		formInfo.setName(cuser.getName());
		formInfo.setPhone(cuser.getPhone());
		formInfo.setSex(cuser.getGender());
		formInfo.setHeadImg(cuser.getHeadImg());
		StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(storeId);
		leaguerDetail = genLeaguerInfo(cuser, formInfo, storeId);
		
		if(addLeaguer(storeLeaguerInfo, leaguerDetail, cuser, storeId)) {
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkPhoneExists4Add(long phone, StoreLeaguerInfo storeLeaguerInfo){
		return checkPhoneExists(phone, storeLeaguerInfo, "");
	}
	
//	private boolean checkPhoneExists4Update(long phone, StoreLeaguerInfo storeLeaguerInfo, String leaguerId){
//		return checkPhoneExists(phone, storeLeaguerInfo, leaguerId);
//	}
	
	private boolean checkPhoneExists(long phone, StoreLeaguerInfo storeLeaguerInfo, String leaguerId){
		if(phone == 0L) {
			return false;
		}
		Map<String, Leaguer> leaguerMap = storeLeaguerInfo.getLeaguerInfoMap();
		Collection<Leaguer> values = leaguerMap.values();
		for (Leaguer leaguer : values) {
			if(leaguer.getEntityState() != EntityState.Deleted.ordinal() && leaguer.getPhone()==phone) {
				return true;
			}
		}
		return false;
	}
	
	//检查CUser信息，并获取
	private CUser checkCUser(LeaguerAddApiForm formInfo){
		CUser cuser = CUserMgr.getInstance().findByPhone(formInfo.getPhone());
		if(cuser == null){//C端用户是否存在？ 不存在则新增
			CuserAdd4Ms addForm = CuserAdd4Ms.newInstance();
			addForm.setPhone(formInfo.getPhone());
			cuser = CUserMgr.getInstance().addFromMs(addForm);
		}
		return cuser;
	}
	
	//初始化LeaguerDetail对象
	private LeaguerDetail genLeaguerInfo(CUser cuser, LeaguerAddApiForm formInfo, long storeId){
		LeaguerDetail leaguer = formInfo.toLeaguerDetail();
		leaguer.setId(Leaguer.genIdByStoreId(storeId, cuser.getId()));
		leaguer.setCuserId(cuser.getId());
		leaguer.setStoreId(storeId);

		try {
			if(StringUtils.isBlank(leaguer.getHeadImg())){
				String content = leaguer.getName();
				if(StringUtils.isBlank(content)){
					String phone = String.valueOf(leaguer.getPhone());
					content = StringUtils.substring(String.valueOf(phone), -2);
				}else if(content.length() > 1){
					content = new String(content.getBytes(),"UTF-8");
					content = StringUtils.substring(content, -2);
				}
				OutputStream os = new ByteArrayOutputStream();
				ImageUtil.getInstance().getImage(content, 100, 100, os);
				String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".jpg";
				FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
				apiForm.setFileType("img");
				apiForm.setModuleType("storeLeaguerInfo");
				apiForm.setModuleId(storeId+"");
				ImgResp imgResp = ImgMgr.getInstance().saveImg(parse(os), fileName, apiForm);
				leaguer.setHeadImg(imgResp.getImgPathList().get(0));
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Leaguer, "LeaguerInfoHandle[genLeaguerInfo]", "gen head img has Exceptions", e);
		}
		return leaguer;
	}
	
	private ByteArrayInputStream parse(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}
	
	//添加客户
	private boolean addLeaguer(StoreLeaguerInfo storeLeaguerInfo, LeaguerDetail leaguer, CUser cuser, long storeId){
		Leaguer simpleLeaguer = Leaguer.newInstanceByDetail(leaguer);
		if(StoreLeaguerInfoBeanHelper.getInstance().addLeaguerInfo(storeLeaguerInfo, simpleLeaguer)){
			StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeLeaguerInfo);
			//回写客户信息到C端用户信息表
			cuser.getLeaguerIdSet().add(leaguer.getId());
			cuser.getStoreIdSet().add(storeId);
			CuserUpdate4Ms inputForm = CuserUpdate4Ms.newInstance();
			FastBeanCopyer.getInstance().copy(cuser, inputForm);
			CUserMgr.getInstance().updateFromMs(inputForm);
		}
		
		LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().getSimple(storeId, leaguer.getId());
		if(leaguerDetail!=null && leaguerDetail.getEntityState() == EntityState.Deleted.ordinal()) {
			//客户、员工删除后新增按恢复处理（改变状态，不做新增）  --add by kevin 2018-6-15
			leaguerDetail.setEntityState(EntityState.Normal.ordinal());
			LeaguerDetailMgr.getInstance().updateSimple(leaguerDetail);
		}else {
			LeaguerDetailMgr.getInstance().addWithId(leaguer);
		}
		
		return true;
	}
}
