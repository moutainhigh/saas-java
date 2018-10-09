package com.hq.storeMS.service.leaguerDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MemberCardExist {
	private boolean exist;
	private String tips;
	
	public static MemberCardExist newInstance(boolean existP) {
		MemberCardExist data = new MemberCardExist();
		data.exist = existP;
		if(!existP) {
			data.tips = "会员卡号不存在";
		}
		return data;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
}
