package com.radida.pacs.core.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImgUtils {
	/**
	 * 等比例缩放
	 * 
	 * @param inputFile
	 * @param outputPicName
	 * @param max
	 *            最大像素
	 * @param type
	 *            type=1 表示 以宽度为基础缩放 type=2以高度为基础缩放 0表示已宽高比较大的为基础缩放
	 */
	public static void zoomPicture(File inputFile, String outputPicName, double max, int type) {
		double maxLimit = max;
		double ratio = 1.0;
		try {
			BufferedImage Bi = ImageIO.read(inputFile);

			switch (type) {
			case 0:
				if (Bi.getHeight() > Bi.getWidth())
					ratio = maxLimit / Bi.getHeight();
				else
					ratio = maxLimit / Bi.getWidth();
				break;
			case 1:
				if (Bi.getWidth() > maxLimit) {
					ratio = maxLimit / Bi.getWidth();
				}
				break;
			case 2:
				if (Bi.getHeight() > maxLimit) {
					ratio = maxLimit / Bi.getHeight();
				}
				break;
			default:
				break;
			}

			if (ratio == 1.0) {
				copyImg(inputFile, outputPicName);
				return;
			}

			int widthdist = (int) Math.floor(Bi.getWidth() * ratio),
					heightdist = (int) Math.floor(Bi.getHeight() * ratio);

			BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(Bi.getScaledInstance(widthdist, heightdist, BufferedImage.SCALE_SMOOTH), 0, 0,
					null);

			tag.getGraphics().dispose();

			File littleFile = new File(outputPicName);
			if (!littleFile.getParentFile().exists()) {
				littleFile.getParentFile().mkdirs();
			}
			ImageIO.write(tag, "JPEG", littleFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取缩略图
	 * 
	 * @param img
	 * @param max
	 *            最大像素
	 * @param type
	 *            type type=1 表示 以宽度为基础缩放 type=2以高度为基础缩放 0表示已宽高比较大的为基础缩放
	 */
	public static InputStream getThumbnail(MultipartFile img, double max, int type) {
		double maxLimit = max;
		double ratio = 1.0;
		InputStream is = null;
		try {
			BufferedImage Bi = ImageIO.read(img.getInputStream());

			switch (type) {
			case 0:
				if (Bi.getHeight() > Bi.getWidth())
					ratio = maxLimit / Bi.getHeight();
				else
					ratio = maxLimit / Bi.getWidth();
				break;
			case 1:
				if (Bi.getWidth() > maxLimit) {
					ratio = maxLimit / Bi.getWidth();
				}
				break;
			case 2:
				if (Bi.getHeight() > maxLimit) {
					ratio = maxLimit / Bi.getHeight();
				}
				break;
			default:
				break;
			}

			int widthdist = (int) Math.floor(Bi.getWidth() * ratio);
			int heightdist = (int) Math.floor(Bi.getHeight() * ratio);

			BufferedImage tagImage = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

			tagImage.getGraphics().drawImage(Bi.getScaledInstance(widthdist, heightdist, BufferedImage.SCALE_SMOOTH), 0,
					0, null);

			tagImage.getGraphics().dispose();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(bos);
			ImageIO.write(tagImage, StringUtils.substringAfterLast(img.getOriginalFilename(), "."), imageOutputStream);
			is = new ByteArrayInputStream(bos.toByteArray());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return is;
	}

	/**
	 * 流式获取缩略图
	 * 
	 * @param img
	 * @param max
	 *            最大像素
	 * @param type
	 *            type type=1 表示 以宽度为基础缩放 type=2以高度为基础缩放 0表示已宽高比较大的为基础缩放
	 */
	public static InputStream getThumbnailByStream(InputStream inputStream, String imgExtension, double max, int type) {
		double maxLimit = max;
		double ratio = 1.0;
		InputStream is = null;
		try {
			BufferedImage Bi = ImageIO.read(inputStream);

			switch (type) {
			case 0:
				if (Bi.getHeight() > Bi.getWidth())
					ratio = maxLimit / Bi.getHeight();
				else
					ratio = maxLimit / Bi.getWidth();
				break;
			case 1:
				if (Bi.getWidth() > maxLimit) {
					ratio = maxLimit / Bi.getWidth();
				}
				break;
			case 2:
				if (Bi.getHeight() > maxLimit) {
					ratio = maxLimit / Bi.getHeight();
				}
				break;
			default:
				break;
			}

			int widthdist = (int) Math.floor(Bi.getWidth() * ratio);
			int heightdist = (int) Math.floor(Bi.getHeight() * ratio);

			BufferedImage tagImage = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

			tagImage.getGraphics().drawImage(Bi.getScaledInstance(widthdist, heightdist, BufferedImage.SCALE_SMOOTH), 0,
					0, null);

			tagImage.getGraphics().dispose();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(bos);
			ImageIO.write(tagImage, imgExtension, imageOutputStream);
			is = new ByteArrayInputStream(bos.toByteArray());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return is;
	}

	// 复制图片
	public static void copyImg(File src, String target) {
		try {
			if (src == null || !src.exists()) {
				return;
			}
			int length = (int) getImgSize(src.getAbsolutePath());
			FileInputStream in = new FileInputStream(src);

			File tarFile = new File(target);
			if (!tarFile.getParentFile().exists()) {
				tarFile.getParentFile().mkdirs();
			}

			FileOutputStream out = new FileOutputStream(tarFile);
			byte[] buffer = new byte[length * 2];
			while (true) {
				int ins = in.read(buffer);
				if (ins == -1) {
					in.close();
					out.flush();
					out.close();
					break;
				} else
					out.write(buffer, 0, ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getImgSize(String url) {
		File f = new File(url);
		if (f.exists()) {

		} else {
			// LogUtils.logger.info("文件不存在:{}", f);
		}

		return f.length();
	}
	// 获取缩略图的KEY
	public static String getThumbnailImgKey(String imgKey) {
		String thumbImgKey = null;
		if(null != imgKey){
			if(imgKey.lastIndexOf(".")>0){
				thumbImgKey = imgKey.substring(0,imgKey.lastIndexOf("."))+".thumb"+imgKey.substring(imgKey.lastIndexOf("."),imgKey.length());
			}
		}
		return thumbImgKey;
	}

	
	public static void main(String[] args) {
		int type = 0;
		double max = 200;
		File inputFile = new File("/Users/shawn/nginx/data/a.jpg");
		String outputPicName = "/Users/shawn/nginx/data/thumb2.jpg";
		zoomPicture(inputFile, outputPicName, max, type);
	}
	
	/**
	 * 根据内容判断是否是图片类型,ico,dcm类型图片无法判断
	 * 
	 * @param inputStream
	 * @return
	 * @author: zhangyafei
	 */
	public static boolean isPicture2InputStream(InputStream inputStream) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (bi == null) {
			return false;
		} else {
			return true;
		}
	}
}
