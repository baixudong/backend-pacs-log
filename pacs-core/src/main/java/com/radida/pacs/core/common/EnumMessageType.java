package com.radida.pacs.core.common;

public enum EnumMessageType {
	UNACCEPT("您好!您的求诊未受理!", 1), 
	ACCEPT("您好!您的求诊已受理,病历已上传", 2), 
	ASSESS("评价成功", 3),
	APPROVE("您的认证信息已提交，请耐心等待认证结果", 4),
	UNDIAG("您有新的未处理协诊请求，请及时处理", 5),
	DIAGATTACH("附件信息提交成功，请及时前往PACS端上传病例信息", 6),
	ADDORDER("订单支付成功，请等待协诊医生上传病例", 7),
	FIRSTLOGIN("欢迎您登录小锐医生",8),
	REFUSEREQUEST("您提交的求诊订单已被驳回",9),
	DIAGCHECKSUCC("病例已通过质检",10),
	DIACHECKGFAIL("病例未通过质检,已驳回",11),
	RESCHECKSUCC2DOC("报告结果通过质检",12),
	RESCHECKSUCC2USER("您的求诊报告已生成，请及时查看",13),
	RESCHECKFAIL2DOC("报告结果未通过质检,已驳回",14),
	ORDERSUCCESS("订单已完成",15),
	
	ADDMONEY("补款通知",51),
	RETURNMONEY("退款通知",51),
	ZORONMONEY("更换专家",52),
	
	REFUNDSUCCESS("您的订单已退款成功", 61);
	
	// 成员变量
	private String name;
	private int code;

	// 构造方法
	private EnumMessageType(String name, int code) {
		this.name = name;
		this.code = code;
	}

	// 普通方法
	public static String getValueOf(Integer code) {
		if (null == code || "".equals(code)) {
			return null;
		}
		EnumMessageType[] contactRelations = EnumMessageType.values();
		for (EnumMessageType contactRelation : contactRelations) {
			if (contactRelation != null && contactRelation.getCode() == code) {
				return contactRelation.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
