package com.hq.experience.data.orderComment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.order.apiData.OrderQueryForm;
import com.hq.chainStore.service.order.bs.OrderMgr;
import com.hq.chainStore.service.order.data.Order;
import com.hq.chainStore.service.orderComment.apiData.SaveBeauticianCommentForm;
import com.hq.chainStore.service.orderComment.bs.OrderCommentMgr;
import com.hq.experienceData.TOrderComment;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

@Deprecated
public class GenerateOrderCommentData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623958L;
		String service = "http://192.168.10.170:9114/ws/v1";
		String reportService = "http://192.168.10.170:9117/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateOrderCommentData().genData(phone);
	}
	
	public GenerateOrderCommentData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genOrderCommentData();
			}
			System.out.println("Generate OrderComment Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genOrderCommentData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setPageItemCount(10);
		params.setPageNo(1);
		params.setStoreId(storeId);
		List<Order> orders = OrderMgr.getInstance().findOrderList(params);
		Order order = orders.get(RandomUtils.nextInt(0, orders.size()));
		
		List<TOrderComment> list = TOrderComment.buildTOrderCommentList();
		TOrderComment tOrderComment = list.get(RandomUtils.nextInt(0, list.size()));
		
		FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
		apiForm.setFileType("img");
		apiForm.setModuleType("orderComment");
		apiForm.setModuleId(order.getId()+"");
		String uploadImg = uploadImg(apiForm, tOrderComment.getImgPath());
		
		//医美师添加订单评论  暂时不能生成C端的评论数据
		SaveBeauticianCommentForm saveForm = SaveBeauticianCommentForm.newInstance();
		saveForm.setContent(tOrderComment.getContent());
		List<String> imgPaths = new ArrayList<>();
		imgPaths.add(uploadImg);
		saveForm.setImgPaths(imgPaths);
		OrderCommentMgr.getInstance().saveBeauticianComment(order.getId(), storeId, saveForm);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
