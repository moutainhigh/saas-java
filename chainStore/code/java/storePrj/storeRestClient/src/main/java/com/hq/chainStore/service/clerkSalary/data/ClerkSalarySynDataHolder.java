package com.hq.chainStore.service.clerkSalary.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.common.StringUtils4Client;
import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ClerkSalarySynDataHolder extends AbsDataSynDataHolder<ClerkSalary> {

	public static ClerkSalarySynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ClerkSalarySynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ClerkSalary;

	protected Class<ClerkSalary> getClazz() {
		return ClerkSalary.class;
	}

	protected RestDao<ClerkSalary> getDao() {
		return ClerkSalaryDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	/**
	 * 根据店铺Id和员工ID获取工资信息
	 * @param ownerId 当前登录者
	 * @param storeId 店铺ID
	 * @param clerkId 员工ID
	 * @return
	 */
	public ClerkSalary getClerkSalary(String ownerId, Long storeId, Long clerkId){
		String targetId = StringUtils4Client.format("{}_{}", storeId, clerkId);
		return super.getData(ownerId, targetId);
	}

	/**
	 * 根据店铺Id和员工ID获取工资记录列表
	 * @param ownerId 当前登录者
	 * @param storeId 店铺ID
	 * @param clerkId 员工ID
	 * @return
	 */
	public List<ClerkSalaryRecords> getClerkSalaryRecords(String ownerId, Long storeId, Long clerkId){
		ClerkSalary clerkSalary = getClerkSalary(ownerId, storeId, clerkId);
		return new ArrayList<ClerkSalaryRecords>(clerkSalary.getClerkSalaryRecordsMap().values());
	}
	
	/**
	 * 根据店铺Id和员工ID获取工资管理列表
	 * @param ownerId 当前登录者
	 * @param storeId 店铺ID
	 * @param clerkId 员工ID
	 * @return
	 */
	public List<ClerkPayroll> getClerkPayrolls(String ownerId, Long storeId, Long clerkId){
		ClerkSalary clerkSalary = getClerkSalary(ownerId, storeId, clerkId);
		return new ArrayList<ClerkPayroll>(clerkSalary.getClerkPayrollMap().values());
	}
	
	/**
	 * 获取工资记录详情
	 * @param ownerId 当前登录者
	 * @param storeId 店铺ID
	 * @param clerkId 员工ID
	 * @param month 月份 如:2017-08
	 * @return
	 */
	public ClerkSalaryRecords getClerkSalaryRecord(String ownerId, Long storeId, Long clerkId, String month){
		return getClerkSalary(ownerId, storeId, clerkId).getClerkSalaryRecordsMap().get(month);
	}
	
	/**
	 * 获取工资管理详情
	 * @param ownerId 当前登录者
	 * @param storeId 店铺ID
	 * @param clerkId 员工ID
	 * @param month 月份 如:2017-08
	 * @return
	 */
	public ClerkPayroll getClerkPayroll(String ownerId, Long storeId, Long clerkId, String month){
		return getClerkSalary(ownerId, storeId, clerkId).getClerkPayrollMap().get(month);
	}
}
