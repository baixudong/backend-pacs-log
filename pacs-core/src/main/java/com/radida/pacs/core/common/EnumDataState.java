package com.radida.pacs.core.common;

public enum EnumDataState {
	/**
	 * 无效(0)
	 */
	INVALID(0),
	/**
	 * 有效(1)
	 */
	VALID(1),
	/**
	 * 删除(-1)
	 */
	DELETE(-1);

	private EnumDataState(int num) {
		this.num = num;
	}

	private int num;

	public int value() {
		return num;
	}
}
