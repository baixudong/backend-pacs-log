package com.radida.pacs.core.common;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	/**
	 * 使用Base64加密算法加密字符串
	 */
	public static String encodeStr(String plainText) {
		byte[] b = plainText.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	/**
	 * 使用Base64加密算法解密字符串
	 */
	public static String decodeStr(String encodeStr) {
		//处理url传输过程中base64接收乱码问题
		encodeStr=encodeStr.replaceAll(" ", "+").replaceAll("\n", "");
		
		byte[] b = encodeStr.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = "";
		try {
			s = new String(b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		String params = "窦强";
		String encodeStr = Base64Util.encodeStr(params);
		//String encodeStr = "eyJ1c2VybmFtZSI6IiV1N0FBNiV1NUYzQSIsInBhc3N3ZCI6ImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiYXBwaWQiOiJ3ZWJzaXRlIiwidWEiOiJNb3ppbGxhLzUuMCAoV2luZG93cyBOVCA2LjE7IFdPVzY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvMzUuMC4xOTE2LjExNCBTYWZhcmkvNTM3LjM2IiwiY2FsbGJhY2siOiJjYWxsYmFjayJ9";
		String decodeStr = Base64Util.decodeStr(encodeStr);
//		try {
//			JSONObject json =new JSONObject(decodeStr);
//			String userName = json.getString("username");
//			
//			System.out.println(decodeUserName);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		System.out.println("encodeStr:\t" + encodeStr + "\n" + "decodeStr:\t" + decodeStr);
	}
}
