package com.hq.storeMS.service.materialRecords.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.service.materialRecords.data.MaterialRecords;
import com.hq.storeMS.service.materialRecords.data.MaterialRecordsDAO;
import com.hq.storeMS.service.materialRecords.data.MaterialRecordsRedisDAO;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class MaterialRecordsDataHolder implements IntfDataHolder{
	
	public static MaterialRecordsDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MaterialRecordsDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.MaterialRecords;
	final private String sufffix = "materialRecords";
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(MaterialRecords target) {
		MaterialRecordsDAO.getInstance().addAndReturnId(target);
		
		String groupName=getGroupName(target.getStoreId());
		MaterialRecordsRedisDAO.getInstance().deleteList(groupName);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(MaterialRecords target) {
		MaterialRecordsDAO.getInstance().updpate(target);
		
		String groupName=getGroupName(target.getStoreId());
		MaterialRecordsRedisDAO.getInstance().deleteList(groupName);
		MaterialRecordsRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(long id) {
		MaterialRecords records = get(id);
		if(records != null){
			MaterialRecordsDAO.getInstance().delete(id);
			
			String groupName=getGroupName(records.getStoreId());
			MaterialRecordsRedisDAO.getInstance().deleteList(groupName);
			MaterialRecordsRedisDAO.getInstance().delete(id);
		}
	}

	public MaterialRecords get(long id) {
		MaterialRecords target = MaterialRecordsRedisDAO.getInstance().get(id);
		if(target == null){
			target = MaterialRecordsDAO.getInstance().get(id);
			if(target != null){
				MaterialRecordsRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<MaterialRecords> findByStoreId(long storeId,long maxTime,long minTime,int pageItemCount, int pageNo) {
		String format = "{}_{}_{}_{}_{}";
		String listId = StringFormatUtil.format(format, storeId, maxTime, minTime, pageItemCount, pageNo);
		
		String groupName=getGroupName(storeId);
		List<MaterialRecords> list = MaterialRecordsRedisDAO.getInstance().getList(groupName, listId);
		if(CollectionUtils.isEmpty(list)){
			list = MaterialRecordsDAO.getInstance().findByStoreId(storeId,maxTime,minTime,pageItemCount,pageNo);
			if(CollectionUtils.isNotEmpty(list)){
				MaterialRecordsRedisDAO.getInstance().saveList(list, groupName, listId);
			}
		}
		return list;
	}
	
	public List<MaterialRecords> findByMaterialId(String materialId, int pageItemCount, int pageNo) {
		String format = "{}_{}_{}";
		String listId = StringFormatUtil.format(format, materialId, pageItemCount, pageNo);
		
		String groupName=getGroupName(materialId.split("_")[0]);
		List<MaterialRecords> list = MaterialRecordsRedisDAO.getInstance().getList(groupName, listId);
		if(CollectionUtils.isEmpty(list)){
			list = MaterialRecordsDAO.getInstance().findByMaterialId(materialId, pageItemCount, pageNo);
			if(CollectionUtils.isNotEmpty(list)){
				MaterialRecordsRedisDAO.getInstance().saveList(list, groupName, listId);
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
		
		if(NumberUtils.isNumber(id)){
			long idL = Long.valueOf(id);
			MaterialRecords target = this.get(idL);			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.StoreMaterialInfo, "MaterialDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.StoreMaterialInfo, "MaterialDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
}
