package com.hq.storeMS.service.activateCode.bs;

import java.util.List;

import com.hq.storeMS.service.activateCode.apiData.QueryActivateCodeForm;
import com.hq.storeMS.service.activateCode.data.ActivateCode;
import com.hq.storeMS.service.activateCode.data.ActivateCodeDAO;
import com.hq.storeMS.service.activateCode.data.ActivateCodeRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ActivateCodeDataHolder {
	
	public static ActivateCodeDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ActivateCodeDataHolder.class);
	}
	
	final private String groupName="activateCode";
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(ActivateCode target) {
		ActivateCodeDAO.getInstance().addAndReturnId(target);
		deleteRedisData(target, false);
	}

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(ActivateCode target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ActivateCodeDAO.getInstance().updpate(target);
		deleteRedisData(target, true);
	}
	
	public void delete(ActivateCode target) {
		ActivateCodeDAO.getInstance().delete(target.getId());
		deleteRedisData(target, true);
	}
	
	private void deleteRedisData(ActivateCode target, boolean myFlag){
		if(myFlag){
			ActivateCodeRedisDAO.getInstance().delete(target.getActivateCode());
		}
		ActivateCodeRedisDAO.getInstance().deleteList(groupName);
	}

	public ActivateCode get(long id) {
		ActivateCode target = ActivateCodeRedisDAO.getInstance().get(id);
		if(target == null){
			target = ActivateCodeDAO.getInstance().get(id);
			if(target != null){
				ActivateCodeRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public ActivateCode findOne(String activateCode) {
		return ActivateCodeDAO.getInstance().findOne(activateCode);
	}
	
	public List<ActivateCode> findByCond(QueryActivateCodeForm queryForm) {
		String listId = queryForm.getListId();
		
		List<ActivateCode> list = ActivateCodeRedisDAO.getInstance().getList(groupName, listId);
		if(list == null || list.isEmpty()){
			list = ActivateCodeDAO.getInstance().findByCond(queryForm);
			if(list != null && !list.isEmpty()){
				ActivateCodeRedisDAO.getInstance().saveList(list, groupName, listId);
			}
		}
		return list;
	}
	
}
