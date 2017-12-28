package com.radida.pacs.core.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author shen
 *
 */
public class Base64ImageUtils {
	
	private Base64ImageUtils(){
		
	}
	
	// base64字符串转化成图片
	public static boolean generateImage(String imgStr, String imgFilePath) {
		if (imgStr == null || imgFilePath == null) {
			return false;
		}
		Base64 base64 = new Base64();
		try {
			byte[] b = base64.decode(imgStr.getBytes());
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			File file = new File(imgFilePath);

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 图片转化成base64字符串
	public static String getImageStr(String imgFilePath) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		Base64 base64 = new Base64();
		String imgStr = new String(base64.encode(data));
		return imgStr;
	}

}
