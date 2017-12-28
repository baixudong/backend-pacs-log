package com.radida.pacs.core.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileUtil {
	/**
	 * 下载zip文件 返回zip的文件路径
	 */
	public static String downLoadZip(String downloadURL, String visitZipUrl) {

		int bytesum = 0;
		int byteread = 0;
		Date date = new Date();

		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy/MM/dd");
		String dateFloder = sf1.format(date);

		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			URL url = new URL(visitZipUrl);
			URLConnection conn = url.openConnection();
			inStream = conn.getInputStream();
			fs = new FileOutputStream(downloadURL);
			byte[] buffer = new byte[4028];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			System.out.println("文件下载成功.....");
		} catch (Exception e) {
			System.out.println("下载异常" + e);
			return "false";
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				inStream = null;
			}
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				fs = null;
			}
		}
		return downloadURL;

	}

	/**
	 * 解压缩
	 * @param sZipPathFile 要解压的文件
	 * @param sDestPath 解压到某文件夹
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList Ectract(String sZipPathFile, String sDestPath) {
		ArrayList<String> allFileName = new ArrayList<String>();
		try {
			// 先指定压缩档的位置和档名，建立FileInputStream对象
			FileInputStream fins = new FileInputStream(sZipPathFile);
			// 将fins传入ZipInputStream中
			ZipInputStream zins = new ZipInputStream(fins);
			ZipEntry ze = null;
			byte[] ch = new byte[256];
			while ((ze = zins.getNextEntry()) != null) {
				File zfile = new File(sDestPath + File.separator+ze.getName());
				File fpath = new File(zfile.getParentFile().getPath());
				if (ze.isDirectory()) {
					if (!zfile.exists())
						zfile.mkdirs();
					zins.closeEntry();
				} else {
					if (!fpath.exists())
						fpath.mkdirs();
					FileOutputStream fouts = new FileOutputStream(zfile);
					int i;
					allFileName.add(zfile.getAbsolutePath());
					while ((i = zins.read(ch)) != -1)
						fouts.write(ch, 0, i);
					zins.closeEntry();
					fouts.close();
				}
			}
			fins.close();
			zins.close();
		} catch (Exception e) {
			System.err.println("Extract error:" + e.getMessage());
		}
		return allFileName;
	}  
	
	public  static void clearFiles(String workspaceRootPath) {
		File file = new File(workspaceRootPath);
		if (file.exists()) {
			deleteFile(file);
		}
	}

	public static void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		}
		file.delete();
	}
	
}
