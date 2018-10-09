package com.hq.storeMS.service.excel.data;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * @ClassName: FileResp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年4月2日 下午3:06:06
 * 
 */
@SynClass
public class FileResp {

	public static FileResp newInstance() {
		return new FileResp();
	}

	private List<String> imgPathList = new ArrayList<String>();

	public FileResp addPath(String path) {
		imgPathList.add(path);
		return this;
	}

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}

}
