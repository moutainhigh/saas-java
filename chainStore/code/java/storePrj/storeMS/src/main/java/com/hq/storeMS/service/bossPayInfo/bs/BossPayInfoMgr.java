package com.hq.storeMS.service.bossPayInfo.bs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.hq.payRestClient.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payRestClient.service.bossPayInfo.data.BossPayInfo;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeFileClient.service.file.data.UploadFileType;
import com.hq.storeMS.service.bossPayInfo.apiData.CertFileUpLoadForm;
import com.zenmind.common.hotSwap.HotSwap;

public class BossPayInfoMgr {

	public static BossPayInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BossPayInfoMgr.class);
	}
	

	public BossPayInfo add(BossPayInfoAddApiForm form){
		return BossPayInfoDataHolder.getInstance().add(form);
	}
	
	public BossPayInfo update(long id, BossPayInfoAddApiForm updateForm){
		return BossPayInfoDataHolder.getInstance().update(id, updateForm);
	}

	public BossPayInfo get(long id) {
		return BossPayInfoDataHolder.getInstance().get(id);
	}

	public BossPayInfo findByStoreId(long storeId){
		return BossPayInfoDataHolder.getInstance().findByStoreId(storeId);
	}
	
	public FileResp uploadCertFile(CertFileUpLoadForm upForm) throws IOException{
		
		MultipartFile file = upForm.getFile();
		ByteArrayResource resource = exchangeStream(file.getInputStream(), file.getOriginalFilename());
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();  
		param.add("file", resource); 
		param.add("fileType",UploadFileType.FILE.getType());
		param.add("moduleType",upForm.getModuleType());
		param.add("moduleId", upForm.getModuleId());
		
		return BossPayInfoDataHolder.getInstance().uploadCertFile(param);
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
