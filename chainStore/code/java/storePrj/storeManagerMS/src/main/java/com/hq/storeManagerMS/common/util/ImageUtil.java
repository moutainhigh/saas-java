package com.hq.storeManagerMS.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.zenmind.common.hotSwap.HotSwap;

/**
 * 创建文字图片
 * 
 * @author
 *
 */
public class ImageUtil {
	public static ImageUtil getInstance() {
		return HotSwap.getInstance().getSingleton(ImageUtil.class);
	}

	// 默认格式
	private final String FORMAT_NAME = "JPG";
	// 默认 宽度
	private final int WIDTH = 100;
	// 默认 高度
	private final int HEIGHT = 100;

	/**
	 * 创建图片
	 * 
	 * @param content 内容
	 * @param font 字体
	 * @param width 宽
	 * @param height 高
	 * @return
	 */
	private BufferedImage createImage(String content, Font font, Integer width,
			Integer height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) bi.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		//整个画布填充白色
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		//画一个圆形 填充蓝色
		g2d.setColor(new Color(15,136,235));
        g2d.fillOval(0, 0, width, height);
        // 设置字体样式
        g2d.setFont(font);
        //设置内容颜色
        g2d.setColor(Color.WHITE);
        //计算绘制内容的坐标点
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //填写内容  
        g2d.drawString(content, (int)x, (int)baseY);
        
        g2d.dispose();
		return bi;
	}

	/**
	 * 获取 图片
	 * 
	 * @param content 内容
	 * @param font 字体
	 * @param width 宽
	 * @param height 高
	 * @return
	 */
	public BufferedImage getImage(String content, Font font, Integer width, Integer height) {
		width = width == null ? WIDTH : width;
		height = height == null ? HEIGHT : height;
		if (null == font)
			font = new Font("宋体", Font.BOLD, (int)(width * 0.3));
		return createImage(content, font, width, height);
	}

	/**
	 * 获取 图片
	 * 
	 * @param content 内容
	 * @param width 宽
	 * @param height 高
	 * @return
	 */
	public BufferedImage getImage(String content, Integer width, Integer height) {
		return getImage(content, null, width, height);
	}

	/**
	 * 获取图片
	 * 
	 * @param content 内容
	 * @return
	 */
	public BufferedImage getImage(String content) {
		return getImage(content, null, null);
	}

	/**
	 * 获取图片
	 * 
	 * @param content 内容
	 * @param font 字体
	 * @param width 宽
	 * @param height 高
	 * @param destPath 输出路径
	 * @throws IOException
	 */
	public void getImage(String content, Font font, Integer width, Integer height, String destPath) throws IOException {
		mkdirs(destPath);
		String file = UUID.randomUUID().toString() + ".jpg";
		ImageIO.write(getImage(content, font, width, height), FORMAT_NAME, new File(destPath + "/" + file));
	}

	/**
	 * 获取图片
	 * 
	 * @param content 内容
	 * @param font 字体
	 * @param width 宽
	 * @param height 高
	 * @param output 输出流
	 * @throws IOException
	 */
	public void getImage(String content, Font font, Integer width, Integer height, OutputStream output) throws IOException {
		ImageIO.write(getImage(content, font, width, height), FORMAT_NAME, output);
	}

	/**
	 * 获取图片
	 * 
	 * @param content  内容
	 * @param width 宽
	 * @param height 高
	 * @param destPath 输出路径
	 * @throws IOException
	 */
	public void getImage(String content, Integer width, Integer height, String destPath) throws IOException {
		getImage(content, null, width, height, destPath);
	}

	/**
	 * 获取图片
	 * 
	 * @param content 内容
	 * @param width 宽
	 * @param height 高
	 * @param output 输出流
	 * @throws IOException
	 */
	public void getImage(String content, Integer width, Integer height, OutputStream output) throws IOException {
		getImage(content, null, width, height, output);
	}
	
	private void mkdirs(String destPath) {
		File file = new File(destPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	public static void main(String[] args) throws Exception {
		ImageUtil.getInstance().getImage("张三", 100, 100, "f:/");
	}
}
