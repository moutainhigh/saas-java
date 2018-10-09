package com.hq.storeMS.service.excel.bs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.hq.storeFileClient.service.file.data.UploadFileType;
import com.hq.storeMS.service.excel.apiData.ExcelUpLoadForm;
import com.hq.storeMS.service.excel.data.FileResp;
import com.zenmind.common.hotSwap.HotSwap;

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
	
	public FileResp saveExcel(ExcelUpLoadForm upForm) throws IllegalStateException, IOException{
		MultipartFile file = upForm.getExcel();
		ByteArrayResource resource = exchangeStream(file.getInputStream(), file.getOriginalFilename());
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();  
		param.add("file", resource); 
		param.add("fileType",UploadFileType.FILE.getType());
		param.add("moduleType",upForm.getModuleType());
		param.add("moduleId", upForm.getModuleId());
		return ExcelDataHolder.getInstance().saveExcel(param);
	}
	
	private ByteArrayResource exchangeStream(final InputStream is, final String fileName) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];  
		while (is.read(buffer) > 0) {  
			bos.write(buffer);  
		}  
		is.close();  
		bos.close();  
        ByteArrayResource resource = new ByteArrayResource(bos.toByteArray()){   
        	@Override  
        	public String getFilename() throws IllegalStateException {   
        		return fileName;  
        	}  

        };
		return resource;
	}

	
}
