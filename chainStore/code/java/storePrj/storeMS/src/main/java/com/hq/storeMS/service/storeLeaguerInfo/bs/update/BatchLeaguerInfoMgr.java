package com.hq.storeMS.service.storeLeaguerInfo.bs.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.buser.bs.StoreBUserMgr;
import com.hq.storeMS.service.buser.data.SimpleBUser;
import com.hq.storeMS.service.common.GenderEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.excel.data.ExcelLeaguer;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.data.DateTypeEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 批量客户导入的操作
 * @author kevin
 *
 */
public class BatchLeaguerInfoMgr {

	public static BatchLeaguerInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BatchLeaguerInfoMgr.class);
	}

	public OperateTips addListFromExcel(long storeId, List<ExcelLeaguer> sourceList) {
		List<LeaguerAddApiForm> addList = new ArrayList<LeaguerAddApiForm>();
		for (ExcelLeaguer excelLeaguer : sourceList) {
			addList.add(toAddLeaguerData(excelLeaguer, storeId));
		}
		return importListData(storeId, addList);
	}

	public OperateTips addListFromStore(StoreLeaguerInfoUpdateApiForm updateForm) {
		long storeId = updateForm.getStoreId();
		List<Leaguer> sourceList = updateForm.getAddListFromStore();
		List<LeaguerAddApiForm> addList = new ArrayList<LeaguerAddApiForm>();
		for (Leaguer leaguer : sourceList) {
			LeaguerAddApiForm addForm = LeaguerAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(leaguer, addForm);
			addList.add(addForm);
		}
		return importListData(storeId, addList);
	}
	
	public OperateTips addListOfLeaguerIds(long storeId, String leaguerIds) {
		String[] ids = leaguerIds.split(",");
		List<LeaguerDetail> sourceList = new ArrayList<LeaguerDetail>();
		for (String id : ids) {
			LeaguerDetail detail = LeaguerDetailMgr.getInstance().get(storeId, id);
			sourceList.add(detail);
		}
		List<LeaguerAddApiForm> addList = new ArrayList<LeaguerAddApiForm>();
		for (LeaguerDetail leaguerDetail : sourceList) {
			LeaguerAddApiForm addForm = LeaguerAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(leaguerDetail, addForm);
			addList.add(addForm);
		}
		return importListData(storeId, addList);
	}
	
	private LeaguerAddApiForm toAddLeaguerData(ExcelLeaguer source, long storeId) {
		LeaguerAddApiForm target = LeaguerAddApiForm.newInstance();
		BeanUtils.copyProperties(source, target, "sex", "buserIds", "dateType");

		// 需特殊处理的字段
		String sex = source.getSex() == null ? "": source.getSex().trim();
		String buserIds = source.getBuserIds() == null ? "": source.getBuserIds().trim();
		String dateType = source.getDateType() == null ? "": source.getDateType().trim();

		if (GenderEnum.MALE.getGender().equals(sex)) {
			target.setSex(GenderEnum.MALE.ordinal());
		} else if(GenderEnum.FEMALE.getGender().equals(sex)){
			target.setSex(GenderEnum.FEMALE.ordinal());
		} else {
			target.setSex(GenderEnum.EMPTY.ordinal());
		}
		
		if (DateTypeEnum.LUNARDATE.getMark().equals(dateType)) {
			target.setDateType(DateTypeEnum.LUNARDATE.ordinal());
		} else {
			target.setDateType(DateTypeEnum.SOLARDATE.ordinal());
		}
		
		
		if(StringUtils.isNotBlank(buserIds)){
			Collection<SimpleBUser> busers = StoreBUserMgr.getInstance().get(storeId).getBuserInfoMap().values();
			Map<String, SimpleBUser> buserMap = new HashMap<String, SimpleBUser>();
			for (SimpleBUser buser : busers) {
				if(buserMap.get(buser.getName()) == null) {
					buserMap.put(buser.getName(), buser);
				}
			}
			
			String[] buserNames = buserIds.split("\\+");
			Set<Long> ids = new HashSet<Long>();
			for (String name : buserNames) {
				SimpleBUser buser = buserMap.get(name);
				if (buser != null) {
					ids.add(buser.getId());
				}
			}
			target.setBuserIds(ids);
		}
		return target;
	}
	
	public OperateTips importListData(long storeId, List<LeaguerAddApiForm> addList) {
		OperateTips tips = OperateTips.newInstance(false, "批量导入客户失败");
		List<Long> phones = new ArrayList<Long>();
		boolean flag = false;
		for (LeaguerAddApiForm leaguerAddApiForm : addList) {
			OperateTips optips = OperateTips.newInstance(false);
			try {
				optips = LeaguerInfoMgr.getInstance().addLeaguerInfo(storeId, leaguerAddApiForm);
			} catch (Exception e) {
				MainLog.error(LogModule.Leaguer, "BatchLeaguerInfoHandle[update]", "importListData failure", e);
			}
			if (!optips.isSuccess()) {
				phones.add(leaguerAddApiForm.getPhone());
				flag = true;
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下手机号导入失败：[" + StringUtils.join(phones, ",") + "]");
		}else {
			tips.setTips("批量导入成功");
		}
		return tips;
	}
}
