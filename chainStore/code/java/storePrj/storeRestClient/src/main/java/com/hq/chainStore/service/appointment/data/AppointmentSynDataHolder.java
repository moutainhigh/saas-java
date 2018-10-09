package com.hq.chainStore.service.appointment.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.buser.data.BUserSynDataHolder;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersion;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionSynDataHolder;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfoSynDataHolder;
import com.hq.common.dataDetial.bs.AbsDetailDataHolder;
import com.hq.common.dataDetial.info.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class AppointmentSynDataHolder extends AbsDetailDataHolder<Appointment> {

	public static AppointmentSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentSynDataHolder.class);
	}

	final private DataVersionEnum versionEnum = DataVersionEnum.LeaguerDetail;

	protected RestDao<Appointment> getDao() {
		return AppointmentDAO.getInstance();
	}
	
	@Override
	protected long getDataVersion(String ownerId, long storeId) {
		DetailDataVersion data = DetailDataVersionSynDataHolder.getInstance().getData(ownerId, String.valueOf(storeId));
		Long ver = data.getDetailDataVerMap().get(versionEnum.ordinal());
		if(ver != null) {
			return ver;
		}
		return 0;
	}

	@Override
	public DataVersionEnum getDataVersionEnum() {
		return versionEnum;
	}
	
	/**
	 * 将预约信息加强 附加医美师 项目等信息
	 * @param appointment 预约原始信息
	 * @param ownerId 当前登录者ID
	 * @return
	 */
	public AppointmentDetails appointment2AppointmentDetails(String ownerId, Appointment appointment){
		Long storeId = appointment.getStoreId();
		
		AppointmentDetails data = AppointmentDetails.newInstance();
		data.setAppointment(appointment);
		
		//附加项目详情
		StoreProductInfo storeProductInfo = StoreProductInfoSynDataHolder.getInstance().getData(ownerId, String.valueOf(storeId));
		if(appointment.getProductId() > 0){
			data.setProductInfo(storeProductInfo.getProductInfoMap().get(String.valueOf(appointment.getProductId())));
		}else{
//			data.setPackedProductInfo(storeProductInfo.getPackedProductInfoMap().get(appointment.getProductId()));
		}
		
		//附加医美师详情
		BUser buser = BUserSynDataHolder.getInstance().getData(ownerId, String.valueOf(appointment.getBeauticianId()));
		data.setBeauticianInfo(buser);
		return data;
	}
	
	/**
	 * 将预约信息加强 附加医美师 项目等信息
	 * @param appointments 预约原始信息列表
	 * @param ownerId 当前登录者ID
	 * @return
	 */
	public List<AppointmentDetails> appointmentList2AppointmentDetailsList(String ownerId, Long storeId, List<Appointment> appointments){
		List<AppointmentDetails> datas = new ArrayList<AppointmentDetails>();
		//店铺项目
		Map<String, ProductInfo> productInfoMap = StoreProductInfoSynDataHolder.getInstance().getData(ownerId, String.valueOf(storeId)).getProductInfoMap();
		Set<Long> ids = new HashSet<Long>();
		for (Appointment appointment : appointments) {
			ids.add(appointment.getBeauticianId());
		}
		//所有医美师
		Map<Long, BUser> buserMap = BUserSynDataHolder.getInstance().findBUserMap(ids);
		for (Appointment appointment : appointments) {
			AppointmentDetails data = AppointmentDetails.newInstance();
			data.setAppointment(appointment);
			data.setProductInfo(productInfoMap.get(String.valueOf(appointment.getProductId())));
			data.setBeauticianInfo(buserMap.get(appointment.getBeauticianId()));
			datas.add(data);
		}
		return datas;
	}

}
