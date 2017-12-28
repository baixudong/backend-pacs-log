package com.radida.pacs.core.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

	/**
	 * 字符串转换成int数组,分隔符为","
	 * 
	 * @param str
	 * @return
	 */
	public static int[] strToArray(String str) {
		if (null == str || "".equals(str.trim())) {
			return new int[0];
		}

		String[] stats = str.split(",");
		int[] sts = new int[stats.length];
		for (int i = 0; i < stats.length; i++) {
			sts[i] = Integer.valueOf(stats[i]);
		}

		return sts;
	}

	/**
	 * 整型数组转换为字符串，用","分割
	 * 
	 * @param arr
	 * @return
	 */
	public static String intArrayToStr(int[] arr) {
		if (arr != null && arr.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
				if (i < arr.length - 1) {
					sb.append(",");
				}
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 字符串数组转换为字符串，用","分割
	 * @param arr
	 * @return
	 */
	public static String stringArrayToStr(String[] arr) {
		if (arr != null && arr.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
				if (i < arr.length - 1) {
					sb.append(",");
				}
			}
			return sb.toString();
		}
		return "";
	}

	/**
	 * 判断字符串是否为空串(去首尾空格)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空串(去首尾空格)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyObj(Object obj) {
		if (null == obj || "".equals(obj.toString().trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 生成32位UUID 并去掉"-"
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 时间转成mapInteger
	 * @param startTime
	 * @param days
	 * @return
	 */
	public static Map<String, Integer> timeJumpMap(String startTime, int days) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(startTime.replaceAll("-", ""), 0);
		String startStr = DateFormat.getSpecifiedDayAfter(startTime);
		for (int i = 1; i <= days; i++) {
			map.put(startStr.replaceAll("-", ""), 0);
			startStr = DateFormat.getSpecifiedDayAfter(startStr);
		}
		return map;
	}
	
	/**
	 * 时间转成mapString
	 * @param startTime
	 * @param days
	 * @return
	 */
	public static Map<String, String> timeJumpObjectMap(String startTime, int days) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(startTime.replaceAll("-", ""), "");
		String startStr = DateFormat.getSpecifiedDayAfter(startTime);
		for (int i = 1; i <= days; i++) {
			map.put(startStr.replaceAll("-", ""), "");
			startStr = DateFormat.getSpecifiedDayAfter(startStr);
		}
		return map;
	}
	/**
	 * 集合转成字符串数组
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String listToString(List<String> list, char separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(separator);
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}
	
	
	/**
	 * 字符串转成字符串数组
	 * @param str
	 * @return
	 */
	public static String[] strToStrArray(String str) {
		if (null == str || "".equals(str.trim())) {
			return new String[0];
		}
		
		String[] stats = str.split(",");
		String[] sts = new String[stats.length];
		for (int i = 0; i < stats.length; i++) {
			sts[i] = stats[i];
		}
		return sts;
	}
}
