package com.radida.pacs.core.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

public class NetworkUtil {
	/**
	 * 监测端口是否通畅
	 * 
	 * @param host
	 * @param port
	 * @return
	 */
	public static boolean isHostConnectable(String host, int port) {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查url是否有效
	 * 
	 * @param urlStr
	 * @return
	 */
	public static boolean isAddressAvailable(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			try {
				InputStream in = url.openStream();
				in.close();
				return true;
			} catch (IOException e) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
//		System.out.println(isHostConnectable("115.28.17.195", 61616));
		System.out.println(isAddressAvailable("http://test.res.pacs.radida.com"));
	}
}
