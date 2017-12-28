package com.radida.pacs.core.common;

/**
 * 求诊状态
 * 
 * @author zhengyuyang
 *
 */
public enum EnumDiagRequestState {
	/**
	 * 已付款,病例待上传(0)
	 */
	PAY_UPLOAD(0),
	/**
	 * 1病例已上传（待质检）(1)
	 */
	UPLOAD_QUALITY(1),
	/**
	 * 病例质检（完成）(2)
	 */
	QUALITY_COMPLETE(2),
	/**
	 * 审核驳回病例(3)
	 */
	AUDIT_REJECT(3),
	/**
	 * 专家驳回病例(4)
	 */
	EXPERT_REJECT_CASE(4),
	/**
	 * 专家视频(5)
	 */
	EXPERT_VIDEO(5),
	/**
	 * 专家报告(6)
	 */
	EXPERT_REPORT(6),
	
	/**
	 * 结束求诊(7)
	 */
	END_DIAG_ASK(7),
	/**
	 * 结果质检（完成）(8)
	 */
	RESULT_CHECK(8),
	/**
	 * 结果质检驳回(9)
	 */
	RESULT_CHECK_REJECT(9)
	;

	private EnumDiagRequestState(int num) {
		this.num = num;
	}

	private int num;

	public int value() {
		return num;
	}
}
