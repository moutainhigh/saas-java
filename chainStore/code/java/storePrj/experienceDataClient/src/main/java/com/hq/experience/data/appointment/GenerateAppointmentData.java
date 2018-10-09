package com.hq.experience.data.appointment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.CollectionUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentUpdateStatusApiForm;
import com.hq.chainStore.service.appointment.bs.AppointmentMgr;
import com.hq.chainStore.service.appointment.data.AppointProduct;
import com.hq.chainStore.service.appointment.data.Appointment;
import com.hq.chainStore.service.appointment.data.AppointmentQueryParams;
import com.hq.chainStore.service.appointment.data.AppointmentStatusEnum;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

public class GenerateAppointmentData extends BaseGenerate{
	private long appointmentTime;
	private List<ProductInfo> productInfos;
	private List<Leaguer> leaguers;
	private List<BUser> busers;
	
	public static void main(String[] args) throws Exception {
		long phone = 13660623953L;
		
		String storeService = "http://192.168.40.220/storems/ws/v1";
		String orderService = "http://192.168.40.220/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		new GenerateAppointmentData().genData(phone);
	}
	
	public GenerateAppointmentData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
//			storeIds.clear();
//			storeIds.add(21L);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = df.parse(df.format(new Date()));
			for (int i = 0; i < 3; i++) {
				for (Long id : storeIds) {
					this.storeId = id;
					this.appointmentTime = now.getTime() + i * 24L * 3600 * 1000;
					if(!hasAppointment(this.appointmentTime)){
						initAppointmentData();
						addNewAppointment();
						addReceiveAppointment();
						addCancelAppointment();
						addSuccessAppointment();
					}
				}
			}
			System.out.println("Generate Appointment Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initAppointmentData(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		productInfos = new ArrayList<ProductInfo>(StoreProductInfoMgr.getInstance().get(storeId).getProductInfoMap().values());
		leaguers = StoreLeaguerInfoMgr.getInstance().getStoreLeaguerInfo(storeId).getLeaguerInfoList();
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		Collection<ClerkInfo> clerks = storeClerkInfo.getClerkInfoMap().values();
		Set<Long> ids = new TreeSet<Long>();
		for (ClerkInfo clerkInfo : clerks) {
			ids.add(clerkInfo.getBuserId());
		}
		busers = BUserMgr.getInstance().findByMultitId(ids);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public boolean hasAppointment(long time){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppointmentQueryParams params = AppointmentQueryParams.newInstance();
		params.setStoreId(storeId);
		params.setMaxTime(time + 24L * 3600 * 1000);
		params.setMinTime(time);
		params.setPageNo(1);
		params.setPageItemCount(20);
		List<Appointment> list = AppointmentMgr.getInstance().findAppointmentList(params);
		AccessTokenMgr.getInstance().removeOpIdTL();
		return !CollectionUtils.isEmpty(list);
	}
	
	public void addNewAppointment() throws Exception {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Appointment appointment = addAppointment(appointmentTime + 18L * 3600 * 1000);
		AppointmentUpdateStatusApiForm updateStatusForm = AppointmentUpdateStatusApiForm.newInstance();
		updateStatusForm.setStatus(AppointmentStatusEnum.NEW.ordinal());
		AppointmentMgr.getInstance().updateAppointmentStatus(appointment.getId(), storeId, updateStatusForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void addReceiveAppointment() throws Exception {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		addAppointment(appointmentTime + 19L * 3600 * 1000);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void addSuccessAppointment() throws Exception {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Appointment appointment = addAppointment(appointmentTime + 20L * 3600 * 1000);
		AppointmentUpdateStatusApiForm updateStatusForm = AppointmentUpdateStatusApiForm.newInstance();
		updateStatusForm.setStatus(AppointmentStatusEnum.SUCCESS.ordinal());
		AppointmentMgr.getInstance().updateAppointmentStatus(appointment.getId(), storeId, updateStatusForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void addCancelAppointment() throws Exception {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Appointment appointment = addAppointment(appointmentTime + 21L * 3600 * 1000);
		AppointmentUpdateStatusApiForm updateStatusForm = AppointmentUpdateStatusApiForm.newInstance();
		updateStatusForm.setStatus(AppointmentStatusEnum.CANCEL.ordinal());
		AppointmentMgr.getInstance().updateAppointmentStatus(appointment.getId(), storeId, updateStatusForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private Appointment addAppointment(long appTime){
		ProductInfo info = productInfos.get(RandomUtils.nextInt(0, productInfos.size()));
		Leaguer leaguer = leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		BUser buser = busers.get(RandomUtils.nextInt(0, busers.size()));
		
		AppointmentAddApiForm apiForm = AppointmentAddApiForm.newInstance();
		
		apiForm.setStoreId(storeId);
		apiForm.setLeaguerId(leaguer.getId());
		apiForm.setAppointTime(appTime);
		apiForm.setCreatorId(boss.getId());
		apiForm.setCreatorName(boss.getName());
		
		List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();
		AppointProduct prd = AppointProduct.newInstance();
		Set<Long> buserIds = new TreeSet<Long>();
		buserIds.add(buser.getId());
		prd.setBuserIds(buserIds);
		prd.setProductCardId("");
		prd.setProductId(info.getId());
		prd.setProductCount(RandomUtils.nextLong(1L, 3L));
		appointProducts.add(prd);
		
		apiForm.setAppointProducts(appointProducts);
		Appointment appointment = AppointmentMgr.getInstance().addAppointment(apiForm);
		return appointment;
	}
}
	
