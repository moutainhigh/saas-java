package com.hq.storeMS.service.download.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.download.apiData.DownLoadDataForm;
import com.hq.storeMS.service.download.bs.DownLoadHandler;
import com.hq.storeMS.service.download.data.DownLoadDataResult;
import com.hq.storeMS.service.download.data.DownLoadTypeEnum;

@RestController
@RequestMapping(value = "/download")
public class DownLoadAPI {
	
	@RequestMapping(value = "/storeData", method = RequestMethod.GET)
	public void download(HttpServletResponse res) {
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			DownLoadDataForm formInfo = DownLoadDataForm.newInstance();
			formInfo.setDownLoadType(DownLoadTypeEnum.StoreLeaguer.ordinal());
			DownLoadDataResult downLoadDataResult = DownLoadHandler.getInstance().download(formInfo);
			
			String fileName = downLoadDataResult.getFileName();
			res.setHeader("content-type", "application/octet-stream");
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			os = res.getOutputStream();
			bis = new BufferedInputStream(downLoadDataResult.getInputStream());
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
