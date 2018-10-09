package com.hq.payms.zenmind.zmWxpay.common;

import java.io.InputStream;

import com.hq.payms.common.config.PayMSCfgMgr;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;

import static com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCertUtil.*;

/**
 * 处理商户证书文件的帮助类 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmWXPayCertFileHelper {
	public static ZmWXPayCertFileHelper getInstance(){
		return HotSwap.getInstance().getSingleton(ZmWXPayCertFileHelper.class);
	}
	
	public ZmWXPayCertFileHelper() {
		init(10);
	}
	
	private AsynExecutor<FileBytes> asynExecutor;
	
	private final String certPathPrefix = PayMSCfgMgr.getProp().getZmWXPayCfg().getWxpayCertPathPrefix();
	private final String certPathNetPrefix = PayMSCfgMgr.getProp().getZmWXPayCfg().getWxpayCertPathNetPrefix();
	
	private void init(int nThreadsP){
		if(nThreadsP < 10) {
			nThreadsP = 10;
		}
		final int queueSize = 1024;
		IntfAsynTask<FileBytes> task = new IntfAsynTask<FileBytes>(){
			@Override
			public void doTask(FileBytes fileBytes) {
				writeFileTask(fileBytes);
			}
		};
		IntfErrorHandler<FileBytes> errorHandler = new IntfErrorHandler<FileBytes>() {
			@Override
			public void handle(FileBytes target, String msg, Throwable e) {
				e.printStackTrace();
			}
		};
		asynExecutor = AsynExecutor.newInstance(task, errorHandler, queueSize, nThreadsP);
		asynExecutor.init();
	}
	
	private void asyncWriteFile(FileBytes fileBytes) {
		asynExecutor.addData(fileBytes);
	}
	
	private void writeFileTask(FileBytes fileBytes) {
		writeFileToLocal(fileBytes.getFileName(), fileBytes.getBytes());
	}
	
	public static class FileBytes{
		
		public static FileBytes newInstance(String fileName, byte[] bytes) {
			FileBytes instance = new FileBytes();
			instance.fileName = fileName;
			instance.bytes = bytes;
			return instance;
		}
		
		private byte[] bytes;
		
		private String fileName;

		public byte[] getBytes() {
			return bytes;
		}

		public void setBytes(byte[] bytes) {
			this.bytes = bytes;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	}
	
	/**
	 * 根据本地绝对路径，或者本地路径前缀+文件名，或者远程路径前缀+文件名，来读取证书文件
	 * @param isLocalAbsPath 是否本地绝对路径
	 * @param certFileName 当isLocalAbsPath=true时，传入本地绝对路径；false时，传入文件名即可
	 * @return
	 */
	public InputStream getCertStreamByFileName(boolean isLocalAbsPath, String certFileName) {
		byte[] certData = null;
		if(isLocalAbsPath) {
			//本地绝对路径读取
			certData = readCertData(certFileName);
			return getCertStream(certData);
		}
		
		//本地路径前缀+文件名
		certData = readCertData(certPathPrefix + certFileName);
		if(certData != null) {
			return getCertStream(certData);
		}
		
		//远程路径前缀+文件名
		certData = readCertDataFromNet(certPathNetPrefix + certFileName);
		if(certData != null) {
			//异步写文件到本地
			asyncWriteFile(FileBytes.newInstance(certPathPrefix + certFileName, certData));
		}
		return getCertStream(certData);
	}
	
	public static void main(String[] args) {
		byte[] certData = readCertDataFromNet("https://images.cnblogs.com/cnblogs_com/mengdd/869539/o_qrcode_196.png");
		ZmWXPayCertFileHelper.getInstance().asyncWriteFile(FileBytes.newInstance("D:/o_qrcode_196.png", certData));
	}
	
}
