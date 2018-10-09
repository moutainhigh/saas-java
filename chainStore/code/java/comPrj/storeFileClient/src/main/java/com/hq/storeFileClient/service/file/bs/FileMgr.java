package com.hq.storeFileClient.service.file.bs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.hq.storeFileClient.service.file.apiData.FileApiForm;
import com.hq.storeFileClient.service.file.apiData.FileOriginForm;
import com.hq.storeFileClient.service.file.apiData.InputStreamApiForm;
import com.hq.storeFileClient.service.file.apiData.InputStreamOriginForm;
import com.hq.storeFileClient.service.file.apiData.MultipartFileApiForm;
import com.hq.storeFileClient.service.file.apiData.MultipartFileOriginForm;
import com.hq.storeFileClient.service.file.data.BaseUploadForm;
import com.hq.storeFileClient.service.file.data.FileDAO;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.zenmind.common.hotSwap.HotSwap;

public class FileMgr {

	public static FileMgr getInstance() {
		return HotSwap.getInstance().getSingleton(FileMgr.class);
	}
	
	public void deleteFile(String...filePaths) {
		for (String filePath : filePaths) {
			FileDAO.getInstance().deleteFile(filePath);
		}
	}

	public FileResp saveFileWithOriginInfo(FileOriginForm apiForm) throws Exception {
		return saveFileWithOriginInfo(apiForm.getPath(), exchangeStream(apiForm.getFile()));
	}
	
	public FileResp saveFileWithOriginInfo(InputStreamOriginForm apiForm) throws Exception {
		return saveFileWithOriginInfo(apiForm.getPath(), exchangeStream(apiForm.getFile(), apiForm.getFileName()));
	}
	
	public FileResp saveFileWithOriginInfo(MultipartFileOriginForm apiForm) throws Exception {
		return saveFileWithOriginInfo(apiForm.getPath(), exchangeStream(apiForm.getFile()));
	}
	
	private FileResp saveFileWithOriginInfo(String path, ByteArrayResource resource) {
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.add("file", resource);
		param.add("path", path);
		return FileDAO.getInstance().saveFileWithOriginInfo(param);
	}
	
	public FileResp saveFile(InputStreamApiForm apiForm) throws Exception {
		FileResp fileResp = FileResp.newInstance();
		List<InputStream> files = apiForm.getFiles();
		for (int i = 0; i < files.size(); i++) {
			ByteArrayResource resource = exchangeStream(files.get(i), apiForm.getFileNames().get(i));
			FileResp resp = postFile(apiForm.toBaseUploadForm(), resource);
			if(resp!=null) {
				fileResp.addAllPath(resp.getImgPathList());
			}
		}
		return fileResp;
	}
	
	public FileResp saveFile(FileApiForm apiForm) throws Exception {
		FileResp fileResp = FileResp.newInstance();
		List<File> files = apiForm.getFiles();
		for (File file : files) {
			ByteArrayResource resource = exchangeStream(file);
			FileResp resp = postFile(apiForm.toBaseUploadForm(), resource);
			if(resp!=null) {
				fileResp.addAllPath(resp.getImgPathList());
			}
		}
		return fileResp;
	}

	public FileResp saveFile(MultipartFileApiForm apiForm) throws Exception {
		FileResp fileResp = FileResp.newInstance();
		List<MultipartFile> files = apiForm.getFiles();
		for (MultipartFile file : files) {
			ByteArrayResource resource = exchangeStream(file);
			FileResp resp = postFile(apiForm.toBaseUploadForm(), resource);
			if(resp!=null) {
				fileResp.addAllPath(resp.getImgPathList());
			}
		}
		return fileResp;
	}
	
	private FileResp postFile(BaseUploadForm baseUploadForm, ByteArrayResource resource) {
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.add("file", resource);
		param.add("fileType", baseUploadForm.getFileType());
		param.add("moduleType", baseUploadForm.getModuleType());
		param.add("moduleId", baseUploadForm.getModuleId());
		return FileDAO.getInstance().postFile(param);
	}

	private ByteArrayResource exchangeStream(final File file) {
		ByteArrayResource resource = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			resource = exchangeStream(is, file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
	}

	private ByteArrayResource exchangeStream(MultipartFile multipartFile) throws IOException {
		return exchangeStream(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
	}

	private ByteArrayResource exchangeStream(final InputStream is, final String fileName) throws IOException {
		ByteArrayResource resource = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			while (is.read(buffer) > 0) {
				bos.write(buffer);
			}
			resource = new ByteArrayResource(bos.toByteArray()) {
				@Override
				public String getFilename() throws IllegalStateException {
					return fileName;
				}
			};
		} finally {
			if(is!=null) {
				is.close();
			}
			if(bos!=null) {
				bos.close();
			}
		}
		return resource;
	}
}
