package com.hq.chainStore.service.excel.bs;

import java.io.File;

import com.hq.chainStore.service.excel.apiData.ExcelUpLoadForm;
import com.hq.chainStore.service.excel.data.ExcelDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestResp;

/** 
 * @ClassName: ExcelMgr 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年3月30日 下午3:48:40 
 *  
 */
public class MyExcelMgr {

	public static MyExcelMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MyExcelMgr.class);
	}
	
	
	public RestResp resolveLeaguerExcel(ExcelUpLoadForm upForm,File file){
		return ExcelDAO.getInstance().resolveLeaguerExcel(upForm,file);
	}
	
	public RestResp resolveProductExcel(ExcelUpLoadForm upForm,File file){
		return ExcelDAO.getInstance().resolveProductExcel(upForm,file);
	}
	
	public RestResp resolveGoodsExcel(ExcelUpLoadForm upForm,File file){
		return ExcelDAO.getInstance().resolveGoodsExcel(upForm,file);
	}
	
	//ByteArrayResource是spring专有的类型  client不依赖spring框架
//	private ByteArrayResource exchangeStream(final InputStream is, final String fileName) throws IOException{
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024];  
//		while (is.read(buffer) > 0) {  
//			bos.write(buffer);  
//		}  
//		is.close();  
//		bos.close();  
//        ByteArrayResource resource = new ByteArrayResource(bos.toByteArray()){   
//        	@Override  
//        	public String getFilename() throws IllegalStateException {   
//        		return fileName;  
//        	}  
//
//        };
//		return resource;
//	}

	
}
