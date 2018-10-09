package com.hq.storeMS.service.clerkSalary.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.clerkSalary.data.ClerkSalary;
import com.hq.storeMS.service.clerkSalary.data.ClerkSalaryDAO;
import com.hq.storeMS.service.clerkSalary.data.ClerkSalaryRedisDAO;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ClerkSalaryDataHolder implements IntfDataHolder{
	
	public static ClerkSalaryDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ClerkSalaryDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ClerkSalary;
	final private String sufffix = "clerkSalary";
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addWithId(ClerkSalary target) {
		ClerkSalaryDAO.getInstance().addWithId(target);
		String groupName=getGroupName(target.getStoreId());
		ClerkSalaryRedisDAO.getInstance().deleteList(groupName);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(ClerkSalary target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ClerkSalaryDAO.getInstance().updpate(target);

		String groupName=getGroupName(target.getStoreId());
		ClerkSalaryRedisDAO.getInstance().deleteList(groupName);
		ClerkSalaryRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(String id) {
		ClerkSalary target = get(id);
		if(target != null){
			ClerkSalaryDAO.getInstance().delete(id);
			
			String groupName=getGroupName(target.getStoreId());
			ClerkSalaryRedisDAO.getInstance().deleteList(groupName);
			ClerkSalaryRedisDAO.getInstance().delete(id);
			
		}
	}

	public ClerkSalary get(String id) {
		ClerkSalary target = ClerkSalaryRedisDAO.getInstance().get(id);
		if(target == null){
			target = ClerkSalaryDAO.getInstance().get(id);
			if(target != null){
				ClerkSalaryRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<ClerkSalary> findByStoreId(long storeId, int pageItemCount,int pageNo) {
		String listId = StringFormatUtil.format("{}_{}_{}", storeId, pageItemCount, pageNo);
		
		String groupName=getGroupName(storeId);
		List<ClerkSalary> list = ClerkSalaryRedisDAO.getInstance().getList(groupName, listId);
		if(CollectionUtils.isEmpty(list)){
			list = ClerkSalaryDAO.getInstance().findByStoreId(storeId, pageItemCount, pageNo);
			if(CollectionUtils.isNotEmpty(list)){
				ClerkSalaryRedisDAO.getInstance().saveList(list, groupName, listId);
			}
		}
		return list;
	}
	
	private String getGroupName(Object id){
		return sufffix+id.toString();
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		ClerkSalary target = this.get(id);			
		if(target != null){
			long newVer = target.getVer();
			if(clientSynVer.getVer() < newVer){
				String data = DataSynMgr.getInstance().toClientData(target);
				item = DataSynItem.newInstance(clientSynVer, newVer, data );
			}
		}else{
			MainLog.info(LogModule.ClerkSalary, "ClerkSalaryDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
		}
		return item;
	}
	
}
