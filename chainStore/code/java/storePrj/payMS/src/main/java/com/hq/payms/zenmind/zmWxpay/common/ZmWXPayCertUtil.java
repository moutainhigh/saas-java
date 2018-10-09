package com.hq.payms.zenmind.zmWxpay.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

public class ZmWXPayCertUtil {
	
	/**
	 * 本地绝对路径读取
	 * @param certPath
	 * @return
	 */
	public static InputStream getCertStreamByAbsPath(String certPath) {
		byte[] certData = readCertData(certPath);
		if(certData != null) {
			return getCertStream(certData);
		}
		return null;
	}
	
	/**
	 * byte[] 转 InputStream
	 * @param certData
	 * @return
	 */
	public static InputStream getCertStream(byte[] certData) {
		if (certData == null) return null;
		return new ByteArrayInputStream(certData);
	}
	
	/**
	 * 读取本地证书文件
	 * @param certPath
	 * @return
	 */
	public static byte[] readCertData(String certPath){
		if(StringUtils.isBlank(certPath)) return null;
		InputStream certStream = null;
		try {
			File file = new File(certPath);
			certStream = new FileInputStream(file);
			byte[] byteData = new byte[(int) file.length()];
			certStream.read(byteData);
			return byteData;
		}catch (Exception ie) {
			ie.printStackTrace();
		} finally {
			if(certStream != null) {
				try {
					certStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 读取远程证书文件
	 * @param certPath
	 * @return
	 */
	public static byte[] readCertDataFromNet(String netPath) {
		if(StringUtils.isBlank(netPath)) return null;
		InputStream inStream = null;
		ByteArrayOutputStream swapStream = null;
		try {
			URL url = new URL(netPath);
			inStream = url.openStream();
			swapStream = new ByteArrayOutputStream(); 
			byte[] buff = new byte[1024]; //buff用于存放循环读取的临时数据 
			int readLen = 0; 
			while ((readLen = inStream.read(buff)) != -1) { 
				swapStream.write(buff, 0, readLen); 
			} 
			return swapStream.toByteArray();
		} catch (Exception ie) {
			ie.printStackTrace();
		} finally {
			try {
				if (swapStream != null) {
					swapStream.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 写文件到本地
	 * @param localPath
	 * @param fileBytes
	 */
	public static void writeFileToLocal(String localPath, byte[] fileBytes) {
		if(StringUtils.isBlank(localPath) || fileBytes == null) return;
        File file = new File(localPath);  
        //创建输出流  
        FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(file);
			 //写入数据  
	        outStream.write(fileBytes);  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}

}
