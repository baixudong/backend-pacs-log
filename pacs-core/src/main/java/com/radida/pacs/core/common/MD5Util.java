package com.radida.pacs.core.common;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MD5Util {
	
	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
    
	/**
	 * 功能：生成签名结果
	 * @param sArray 要签名的数组Map
	 * @param key 安全校验码
	 * @return 签名结果字符串
	 */
	public static String BuildMysign(Map sArray, String key) {
		String prestr = CreateLinkString(sArray);  //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		prestr = prestr+key;                     //把拼接后的字符串再与安全校验码直接连接起来
		logger.info("加密原串:"+prestr);
		String mysign = MD5Encode(prestr);
		return mysign;
	}
	
	 public static String CreateLinkString(Map<String,Object> map){
	        ArrayList<String> list = new ArrayList<String>();
	        for(Map.Entry<String,Object> entry:map.entrySet()){
	            if(!isNullOrEmpty(entry.getValue())){
	                list.add(entry.getKey() + "=" + entry.getValue() + "&");
	            }
	        }
	        int size = list.size();
	        String [] arrayToSort = list.toArray(new String[size]);
	        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < size; i ++) {
	            sb.append(arrayToSort[i]);
	        }
	        String result = sb.toString();
	        return result;
	    }
	 
	  public static boolean isNullOrEmpty(Object obj) {  
	        if (obj == null)  
	            return true;  
	  
	        if (obj instanceof CharSequence)  
	            return ((CharSequence) obj).length() == 0;  
	  
	        if (obj instanceof Collection)  
	            return ((Collection) obj).isEmpty();  
	  
	        if (obj instanceof Map)  
	            return ((Map) obj).isEmpty();  
	  
	        if (obj instanceof Object[]) {  
	            Object[] object = (Object[]) obj;  
	            if (object.length == 0) {  
	                return true;  
	            }  
	            boolean empty = true;  
	            for (int i = 0; i < object.length; i++) {  
	                if (!isNullOrEmpty(object[i])) {  
	                    empty = false;  
	                    break;  
	                }  
	            }  
	            return empty;  
	        }  
	        return false;  
	    }  
}
