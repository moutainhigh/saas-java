package com.hq.chainStore.pressTest.robot.workFlow;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.robot.AppointActor;
import com.hq.chainStore.service.appointment.data.Appointment;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddByAppoint;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.chainStore.service.workFlowData.bs.WorkFlowDataMgr;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowActor {

	public static WorkFlowActor getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowActor.class);
	}
	
	/**
	 * 随机获取工作流
	 * @param storeId
	 * @return
	 */
	public WorkFlowData getRandomWorkFlow(long storeId){
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setPageItemCount(10);
		queryForm.setPageNo(1);
		queryForm.setWorkFlowTypeId(1l);
		PageResp<WorkFlowData> pageResp = WorkFlowDataMgr.getInstance().findWorkFlowDataPageInfo(queryForm);
		List<WorkFlowData> list = pageResp.getList();
		if(list.size() > 0){
			return list.get(RandomUtils.nextInt(0, list.size()));
		}else{
			return null;
		}
	}
	
	/**
	 * 随机获取会员充值工作流
	 * @param storeId
	 * @return
	 */
	public WorkFlowData getRechargeRandomWorkFlow(long storeId){
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setPageItemCount(10);
		queryForm.setPageNo(1);
		queryForm.setWorkFlowTypeId(2l);
		PageResp<WorkFlowData> pageResp = WorkFlowDataMgr.getInstance().findWorkFlowDataPageInfo(queryForm);
		List<WorkFlowData> list = pageResp.getList();
		if(list.size() > 0){
			return list.get(RandomUtils.nextInt(0, list.size()));
		}else{
			return null;
		}
	}
	
	/**
	 * 查询列表
	 * @param storeId
	 * @return
	 */
	public List<WorkFlowData> getList(long storeId){
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setPageItemCount(10);
		queryForm.setPageNo(1);
		queryForm.setWorkFlowTypeId(1l);
		PageResp<WorkFlowData> pageResp = WorkFlowDataMgr.getInstance().findWorkFlowDataPageInfo(queryForm);
		return pageResp.getList();
	}
	
	/**
	 * 添加工作流
	 * @param storeId
	 * @param bossId
	 */
	public void addWorkFlow(long storeId,long bossId){
		WorkFlowDataAddForm addForm = WorkFlowDataAddForm.newInstance();
		addForm.setBuserId(bossId);
		addForm.setStoreId(storeId);
		addForm.setWorkFlowTypeId(RandomUtils.nextInt(1, 3));
		WorkFlowDataMgr.getInstance().addWorkFlowData(addForm);
	}
	
	/**
	 * 预约转购买消费
	 * @param storeId
	 * @param bossId
	 */
	public void addByAppoint(long storeId,long bossId){
		Appointment randomAppoint = AppointActor.getInstance().getRandomAppoint(storeId, bossId);
		if(randomAppoint != null){
			WorkFlowDataAddByAppoint addForm = WorkFlowDataAddByAppoint.newInstance();
			addForm.setAppointmentId(randomAppoint.getId());
			addForm.setCreatorId(bossId);
			WorkFlowDataMgr.getInstance().addByAppoint(addForm);
		}
	}
	
}
