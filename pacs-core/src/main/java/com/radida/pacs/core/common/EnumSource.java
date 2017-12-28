package com.radida.pacs.core.common;

/**
 * 注册来源
 * @author zhengyuyang
 *
 */
public enum EnumSource {
	/**
	 * PACS(0)
	 */
	PACS(0),
	/**
	 * PACS-MOBILE-IOS(1)
	 */
	PACSMOBILEIOS(1),
	/**
	 * PACS-MOBILE-ANDROID(2)
	 */
	PACSMOBILEANDROID(2),
	/**
	 * PACS-ADMIN(3)
	 */
	PACSADMIN(3);

	private EnumSource(int num) {
		this.num = num;
	}

	private int num;

	public int value() {
		return num;
	}

	public static EnumSource getByEnumEntityType(int num) {
		EnumSource[] entityTypes = values();
		for (EnumSource type : entityTypes) {
			if (type.value() == num) {
				return type;
			}
		}
		return null;
	}
}
