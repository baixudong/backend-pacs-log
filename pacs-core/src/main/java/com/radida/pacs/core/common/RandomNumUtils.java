package com.radida.pacs.core.common;

import java.util.Random;

public class RandomNumUtils {

	/**
	 * 生成随机数字
	 * 
	 * @param num_len
	 *            生成的salt的总长度
	 * @return 随机数的字符串
	 */
	public static String genRandomNum(int num_len) {
		// 10个数字
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的salt的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer num = new StringBuffer("");
		Random r = new Random();
		while (count < num_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				num.append(str[i]);
				count++;
			}
		}

		return num.toString();
	}

	public static void main(String[] args) {
		System.out.println(RandomNumUtils.genRandomNum(6));
	}
}
