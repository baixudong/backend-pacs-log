package com.radida.pacs.core.common;

import java.util.UUID;

/**
 * 
 * @author shen
 *
 */
public class UUIDGenerator {

	public UUIDGenerator() {

	}

	/**
	 * get UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// remove '-'
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
