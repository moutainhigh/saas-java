package com.zenmind.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang3.StringUtils;

import com.hq.common.constants.ServerConstants;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.file.FileUtils;
import com.zenmind.common.hotSwap.HotSwap;

public class ImageFileMgr {
	public static ImageFileMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ImageFileMgr.class);
	}

	private final static String jpg = "jpg";
	private final static String jpeg = "jpeg";

	/**
	 * 压缩图片
	 * 
	 * @param input 原图片文件
	 * @return 压缩后的图片路径  格式如： 图层 1-png-reduce.jpg   原图片：图层 1.jpg  或者  图层 1.png
	 */
	public String reduceImage(File input) {
		try {
			if(input == null || !input.exists()){
				return "";
			}
			String absolutePath = input.getAbsolutePath();
			String suffix = StringUtils.substringAfterLast(absolutePath, ".");
			File output = null;
			if (jpg.equalsIgnoreCase(suffix) || jpeg.equalsIgnoreCase(suffix)) {
				output = input;
			} else {
				output = new File(getJpgPath(absolutePath, suffix));
				conversion(input, output);
				absolutePath = output.getAbsolutePath();
			}
			File targetFile = new File(getJpgReducePath(absolutePath));
			genReduceImage(output, targetFile);

			return targetFile.getAbsolutePath();
		} catch (Exception e) {
			throw (ImageFileException.newInstance("ImageFileMgr.reduceImage()", "", e));
		}
	}

	//压缩规则
	private void genReduceImage(File sourceFile, File targetFile) throws Exception {
		long fileSize = sourceFile.length();
		// 原图片小于100K 不做压缩
		if (fileSize <= ServerConstants.THUM_MAX_SIZE * 100) {
			FileUtils.copyFile(sourceFile, targetFile);
			// 100K 到 1000K
		} else if (fileSize <= ServerConstants.THUM_MAX_SIZE * 1000) {
			float quality = (ServerConstants.THUM_MAX_SIZE * 100.0f) / fileSize;
			Thumbnails.of(sourceFile).scale(1f).outputQuality(quality).toFile(targetFile);
			// 大于500K 都按0.1比例压缩
		} else {
			float quality = 0.1f;
			Thumbnails.of(sourceFile).scale(1f).outputQuality(quality).toFile(targetFile);
		}
	}

	// 获取压缩的图片地址
	private String getJpgReducePath(String absolutePath) {
		String path = StringUtils.substringBeforeLast(absolutePath, ".");
		return StringFormatUtil.format("{}-reduce.{}", path, jpg);
	}

	// 转换jpg格式
	private String getJpgPath(String absolutePath, String suffix) {
		String path = StringUtils.substringBeforeLast(absolutePath, ".");
		return StringFormatUtil.format("{}-{}.{}", path, suffix, jpg);
	}

	/**
	 * 图片格式转换
	 * 
	 * @param input 原图片文件
	 * @param output 转换后的图片文件
	 * @param outputFormat 如：jpg/png/jpeg/gif等图片格式
	 */
	public void conversion(File input, File output) {
		try {
			if (input.canRead()) {
				BufferedImage bi = ImageIO.read(input);
				BufferedImage newBufferedImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bi, 0, 0, Color.WHITE, null);
				ImageIO.write(newBufferedImage, jpg, output);
			}
		} catch (IOException e) {
			throw (ImageFileException.newInstance("ImageFileMgr.conversion()", "", e));
		}
	}

	public static void main(String[] args) {
		 String tt = "";
		 System.out.println(StringUtils.substringBeforeLast(tt, "img"));
		 System.out.println(StringUtils.substringAfterLast(tt, "/filedir/"));

//		File input=new File("F:/honkon/doc/体验店资料/医美师/tttt.png");
		// File output=new File("F:/honkon/doc/体验店资料/医美师/图层 1.jpg");
		// ImageFileMgr.getInstance().conversion(input, output);
		
//		ImageFileMgr.getInstance().reduceImage(input);
	}

}
