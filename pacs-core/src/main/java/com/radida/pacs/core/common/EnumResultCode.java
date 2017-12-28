package com.radida.pacs.core.common;

public enum EnumResultCode {

	SUCCESS("200", "成功"), 
	SUCCESS_NODATA("201", "成功，无数据"), 
	
	ERROR_PARAM_EMPTY("301", "参数为空"), 
	ERROR_PARAM_VALUE("302", "参数值错误"), 
	ERROR_SERVICE("303", "服务异常"),
	ERROR_SESSION_INVALID("304", "用户信息过期或未登录"),
	ERROR_HEDER("305", "头信息错误"),
	ERROR_PARAM_JSON("310", "参数JSON解析错误"),
	ERROR_CODE_EXIST("311", "编码已存在"),
	ERROR_DATA_CONFIG("312", "数据配置错误"),
	ERROR_NO_RULE_EXIST("314", "编号规则已存在"),
	ERROR_DEFAULT_DATA_NODELETE("316", "默认数据不可删除"),
	ERROR_PAT_NO_DATA_EXIST("317", "患者编号已失效，请重新取号"),
	ERROR_STUDY_NO_DATA_EXIST("318", "检查编号已失效，请重新取号"),
	ERROR_PARAM_PWISEQ("319", "*新密码不能与旧密码一致，请重新输入"),
	ERROR_PARAM_DEFAULT("320", "默认角色不能编辑与删除"), 
	
	ERROR_QUEUE_EMPTY("321", "该医院不存在消息队列，请设置消息队列信息"),
	ERROR_C_USB_PARAM_EMPTY("322", "PARAMS参数为空"), 
	ERROR_STUDY_ISEMPTY("323", "无此检查，请检查后重试！"), 
	ERROR_PAT_ISEMPTY("324", "病人信息为空，请检查后重试！"), 
	ERROR_BIRTHDAY_VALUE("325", "(birthday)参数值格式不正确"), 
	ERROR_AGE_VALUE("326", "(age)参数值格式不正确"), 
	ERROR_STUDYTIME_VALUE("327", "(study_time)参数值格式不正确"), 
	ERROR_SERIES_NUM_VALUE("328", "(series_num)参数值格式不正确"), 
	ERROR_IMAGE_NUM_VALUE("329", "(image_num)参数值格式不正确"), 
	
	ERROR_USER_USER_NOTEXIST("402", "用户不存在"), 
	ERROR_USER_PASSWORD_WRONG("403", "用户密码错误"), 
	ERROR_USER_USER_EXIST("404", "用户已经存在"),
	ERROR_USER_NOTPERMISSION("405", "用户无此操作权限"), 
	ERROR_USER_USER_INVALID_DELETE("406", "用户无效或删除"), 
	ERROR_USER_LOGIN_FALSE("407", "登录失败"),
	ERROR_USER_NO_INS_GROUP_FALSE("409", "用户未关联医院或集团"),
	ERROR_USER_INS_CLOUD_FALSE("410", "所在医院未授权云PACS服务,请联系管理员"),
	ERROR_USER_ID_CLOUD_FALSE("411", "小锐号不存在,请查正！"),
	ERROR_BYUSER_ID_CLOUD_FALSE("412", "该小锐号医生已邀请"),
	ERROR_BYUSER_IS_REFER("425", "该医生已是合作医生"),
	ERROR_BYUSER_HAS_INS("426", "该医生已有所属医院"),
	ERROR_APPLY_FALSE("413", "您已申请过了"),
	IS_ALREADY_THE_INS_DOCTOR("414", "对不起，你已经是本医院医生，请核对小锐号"),
	ERROR_USER_INS_NOTEXIST("415", "登录用户没有所属医院"),
	ERROR_USER_check_INS_NOTEXIST("416", "登录用户没有选择医院"),
	ERROR_USER_GENDER_WRONG("417", "登录用户没有选择医院"),
	
	
	ERROR_USERNAME_FORMAT("418", "用户名格式错误"),
	ERROR_USER_USERNAME_EXIST("419", "用户名已存在"),
	ERROR_EMAIL_FORMAT("420", "邮箱格式错误"),
	ERROR_USER_EMAIL_EXIST("421", "邮箱已存在"),
	ERROR_PHONE_FORMAT("422", "手机号格式错误"),
	ERROR_USER_PHONE_EXIST("423", "手机号已存在"),
	ERROR_NAME_ALREADY("449","搜索名称已存在"),
	ERROR_USER_ROLES_WRONG("424", "您没有角色可以分配！"),
	
	ERROR_STUDY_PART_REPORT("511", "必须是同一份检查且不存在已提交报告的检查部位"),
	ERROR_STUDY_COLLECTED("512", "该病例已经收藏"),
	
	ERROR_OPENAPI_SIGN_WRONG("901","签名验证错误"),
	
	ERROR_REFER_USER_REFERRED("1001","已经转诊该用户，不要重复操做"),
	ERROR_REFER_STUDY_SUBMITTED("1002","检查已提交，无法进行转诊操作"),
	ERROR_REFER_STUDY_REFERRED("1003","检查已转诊，请先撤销之前转诊再进行转诊操作"),
	ERROR_REFER_PROCESSED("1004","转诊已处理，无法进行撤销操作"),
	ERROR_REFER_USER_DISUNION("1005","对不起！你不是该检查转诊者，无权进行撤销操作"),
	ERROR_REFER_CANCELED("1006","转诊已撤销"),
	ERROR_REFER_NOEXPERT("1007","没有可转诊专家"),
	ERROR_REFER_CANNOT("1008","云PACS不能转诊"),
	ERROR_REFER_USER_REF("1009","对不起，您无权对该病例进行转诊操作"),
	ERROR_REFER_USER_CANCEL_REF("1010","对不起，您无权对该病例进行撤销转诊操作"),
	
	ERROR_REFER_USER_LOCK_REF("1011","对不起，病例处于锁定状态，不能进行转诊/撤销转诊操作"),
	
	
	ERROR_GROUP_NAME_EXIST("1101","集团名称已存在"),
	ERROR_GROUP_INS_NOTEXIST("1102","所选医院不存在"),
	SHOW_ALL("446", "默认：全部显示"),
	ERROR_GROUP_INS_BELONGTOOTHERGROUP("1103","所选医院中可能已属于其他集团"),
	
	ERROR_CONTACT_PHONE_EMPTY("1201", "病人联系手机号码不能为空"),
	ERROR_VIDEO_REPORT_EMPTY("1202", "请专家先视频再写报告"),
	ERROR_VIDEO_EMPTY("1203", "请专家先视频"),
	
	ERROR_FILE_PICTUREFORMATERROR("602", "不支持的图片格式"),
	
	ERROR_MESSAGE_ADD_FAIL("1501", "添加消息列表失败"),
	
	ERROR_EXCHANGE_EXPERT_EXIST("1601", "该订单的专家正在更换中,待用户确认"),
	ERROR_ORDER_INVALID("1602", "订单已退款,无法再次退款"), 
	ERROR_ORDER_FINSH("1603", "订单已结果质检（完成）,无法退款"), 
	ERROR_EXCHANGE_EXPERT_ALLOW("1604", "该订单已跟换专家次数达到上线,不允许再次跟换专家"),
	ERROR_EXCHANGE_EXPERT("1605", "跟换专家消息发送失败"),

	ERROR_MESSAGE_TIMEERROR("1701", "请一分钟后再发送"),
	ERROR_MESSAGE_COUNTERROR("1702", "对不起,您今天已经发送了10次,请明天再试!!!"),
	ERROR_USER_MSGCODEE_TIMEOUT("1703", "验证码已过期,请重新获取验证码"),
	ERROR_USER_ERRORVERIFICATIONCODE("1704", "验证码不正确"),
	
	ERROR_DEED_NOTEXIST("1801", "卡券不存在"),
	ERROR_PAY_REFUND("1802", "退款失败"),
	
	ERROR_EXPERT_NAME_EXIST("1901", "专家团队名称已存在"),
	
	ERROR_GUEST_EXPERT_EXIST("2001","已邀请(转化)过,有未处理的消息,不能再次操作"),
	ERROR_GUEST_EXPERT_DELETE("2002","该数据不是拒绝状态数据,不能进行删除"),
	ERROR_GUEST_EXPERT_REF_EXIST("2003","该数据已转内部专家,不能再转"),
	ERROR_GUEST_EXPERT_IS_AUDIT("2004","该数据不是同意状态数据,不能赋予审核权限"),
	ERROR_GUEST_EXPERT_IS_ID("2005","该小锐号不存在");
	
	private String code;
	private String desc;

	private EnumResultCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static EnumResultCode getValueOf(String code) {
		if (null == code || "".equals(code)) {
			return null;
		}
		EnumResultCode[] codes = EnumResultCode.values();
		for (EnumResultCode type : codes) {
			if (type != null && type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}