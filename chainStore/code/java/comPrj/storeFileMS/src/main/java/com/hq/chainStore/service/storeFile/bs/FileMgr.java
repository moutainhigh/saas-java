package com.hq.chainStore.service.storeFile.bs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hq.chainStore.service.storeFile.apiData.FileOriginForm;
import com.hq.chainStore.service.storeFile.apiData.FileUploadApiForm;
import com.hq.common.config.StoreFileMSCfgMgr;
import com.hq.common.constants.ServerConstants;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.image.ImageFileMgr;

public class FileMgr {
	
	public static FileMgr getInstance() {
		return HotSwap.getInstance().getSingleton(FileMgr.class);
	}
	
	public String saveFileWithOriginInfo(FileOriginForm originForm) throws IllegalStateException, IOException{
		MultipartFile multipartFile = originForm.getFile();
		String absPath = getAbsPath(originForm.getPath());
		File file = new File(absPath);
		if(file.exists()) {
			file.delete();
		}
		File parentFile = file.getParentFile();
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		multipartFile.transferTo(file);
		return originForm.getPath();
	}
	
	/**
	 * 
	 * @param FileUploadApiForm
	 * @param multipartFile
	 * @return relativeFilePath 文件路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public List<String> uploadFile(FileUploadApiForm uploadForm) throws IllegalStateException, IOException{
		List<String> imgPathList = new ArrayList<String>();
		
		MultipartFile multipartFile = uploadForm.getFile();
		/**获取文件的后缀**/
		String oriImgName = multipartFile.getOriginalFilename();  
		String suffix = StringUtils.substringAfterLast(oriImgName, ".");
		String sourceRelativeFilePath = getSourceRelativeFilePath(uploadForm, suffix);
		/**拼成完整的文件保存路径加文件**/    
		String absPath = getAbsPath(sourceRelativeFilePath);
		File file = new File(absPath);
		multipartFile.transferTo(file);
		
		String reduceImage = ImageFileMgr.getInstance().reduceImage(file);
		//String targetPath = StringUtils.substringAfterLast(reduceImage, getImgPath()+"/");
		String targetPath = StringUtils.substringAfterLast(reduceImage, getImgPath()+ File.separator);
		imgPathList.add(targetPath);
		return imgPathList;
	}
	
	/**
	 * 
	 * @param FileUploadApiForm
	 * @param multipartFile
	 * @return relativeFilePath 文件路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public List<String> uploadExcel(FileUploadApiForm uploadForm) throws IllegalStateException, IOException{
		List<String> imgPathList = new ArrayList<String>();
		
		MultipartFile multipartFile = uploadForm.getFile();
		/**获取文件的后缀**/
		String oriImgName = multipartFile.getOriginalFilename();  
		String suffix = StringUtils.substringAfterLast(oriImgName, ".");
		String sourceRelativeFilePath = getFilePath(uploadForm,oriImgName);
		/**拼成完整的文件保存路径加文件**/    
		String absPath = getAbsPath(sourceRelativeFilePath);
		File file = new File(absPath);
		multipartFile.transferTo(file);
		String targetPath = StringUtils.substringAfterLast(file.getAbsolutePath(), getImgPath()+ File.separator);
		imgPathList.add(targetPath);
		return imgPathList;
	}
	
	/**
	 * 上传商户证书文件
	 * @param uploadForm
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public List<String> uploadCertFile(FileUploadApiForm uploadForm) throws IllegalStateException, IOException{
		List<String> imgPathList = new ArrayList<String>();
		
		MultipartFile multipartFile = uploadForm.getFile();
		/**获取文件的后缀**/
		String oriImgName = multipartFile.getOriginalFilename(); 
		String sourceRelativeFilePath = getSourceRelativeFilePath4Cert(uploadForm, oriImgName);
		/**拼成完整的文件保存路径加文件**/    
		String absPath = getAbsPath(sourceRelativeFilePath);
		File file = new File(absPath);
		multipartFile.transferTo(file);
		
//		String targetPath = StringUtils.substringAfterLast(file.getAbsolutePath(), getImgPath()+ File.separator);
		//只回传文件名
		String targetPath = getFileNameWithSuffix(file.getAbsolutePath());
		imgPathList.add(targetPath);
		return imgPathList;
	}
	
	private String getSourceRelativeFilePath4Cert(FileUploadApiForm uploadForm, String oriImgName){
		String fileType = uploadForm.getFileType();
		String moduleType = uploadForm.getModuleType();
		String moduleId = uploadForm.getModuleId();
		createFileDirIfNotExist(fileType,moduleType,"");
		
		String prefix = StringUtils.substringBeforeLast(oriImgName, ".");
		String suffix = StringUtils.substringAfterLast(oriImgName, ".");
		
		// /{fileType}/{moduleType}/{prefix}_{moduleId}_{uuid}.{suffix}
		// file/payMS/cert/aaaaa_1_uuid.p12
		// moduleType传入payMS/cert; moduleId 传入storeId
		final String pattern = "{}/{}/{}_{}_{}.{}";
		
		String uuid = RandomStringUtils.random(4, ServerConstants.STR_RANDOM);
		return StringFormatUtil.format(pattern,fileType,moduleType,prefix,moduleId,uuid,suffix);
	}
	
	private String getFilePath(FileUploadApiForm uploadForm,String oriImgName){
		String fileType = uploadForm.getFileType();
		String moduleType = uploadForm.getModuleType();
		String moduleId = uploadForm.getModuleId();
		createFileDirIfNotExist(fileType,moduleType,moduleId);
		
		// /{fileType}/{moduleType}/{moduleId}/{date}/{oriImgName}
		final String pattern = "{}/{}/{}/{}_{}";
		
		//todo:抽取到时间类
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd_HH"); 
		String date = dateformat.format(new Date());   
//		String uuid = UUID.randomUUID().toString();
		
		return StringFormatUtil.format(pattern,fileType,moduleType,moduleId,date,oriImgName);
	}
	
	public boolean delete(String filePath){
		boolean success = false;
		String absPath = getAbsPath(filePath);
		File imgFile = new File(absPath);
		if(imgFile.exists()){
			imgFile.delete();
			success = true;
		}
		return success;
	}
	
	/**
	 * 原图片的相对路径
	 * @param uploadForm
	 * @param suffix
	 * @return
	 */
	private String getSourceRelativeFilePath(FileUploadApiForm uploadForm, String suffix){
		String fileType = uploadForm.getFileType();
		String moduleType = uploadForm.getModuleType();
		String moduleId = uploadForm.getModuleId();
		createFileDirIfNotExist(fileType,moduleType,moduleId);
		
		// /{fileType}/{moduleType}/{moduleId}/{date}_{uuid}.{suffix}
		final String pattern = "{}/{}/{}/{}_{}.{}";
		
		//todo:抽取到时间类
		SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmssSSS"); 
		String date = dateformat.format(new Date());   
		String uuid = RandomStringUtils.random(4, ServerConstants.STR_RANDOM);
		return StringFormatUtil.format(pattern,fileType,moduleType,moduleId,date,uuid,suffix);
	}
	
	private void createFileDirIfNotExist(String fileType,String moduleType,String moduleId) {
		// {fileType}/{moduleType}
		final String pattern = "{}/{}/{}/{}";
		String fileDir = StringFormatUtil.format(pattern, getImgPath(),fileType,moduleType,moduleId);
		File dir = new File(fileDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	
	public String getAbsPath(String relativePath){
		final String filePattern = "{}/{}";		
		return StringFormatUtil.format(filePattern,getImgPath(),relativePath);
	}
	
	private String getImgPath(){
		return StoreFileMSCfgMgr.getProp().getImgPath();
	}

	/**
	* 仅保留文件名不保留后缀
	*/
	public String getFileName(String pathandname) {
       
		int start = pathandname.lastIndexOf(File.separator);
		int end = pathandname.lastIndexOf(".");
		if (start != -1 && end != -1) {
			return pathandname.substring(start + 1, end);
		} else {
			return null;
		}		
	}
	
	/**
	 * 保留文件名及后缀
	 */
	public String getFileNameWithSuffix(String pathandname) {		
		int start = pathandname.lastIndexOf(File.separator);
		if (start != -1 ) {
			return pathandname.substring(start + 1);
		} else {
			return null;
		}		
	}
	
}
