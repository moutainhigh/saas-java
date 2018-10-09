package com.hq.storeClient.service.pay.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PayResp {

	private String imgUrl;//被扫支付二维码图片
	private String outTradeNo;//单次支付唯一订单号
	
	public static PayResp newInstance(){
		return new PayResp();
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
}
