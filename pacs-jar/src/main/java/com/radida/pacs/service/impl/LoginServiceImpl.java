package com.radida.pacs.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radida.pacs.core.common.EnumResultCode;
import com.radida.pacs.core.common.bean.ResultDto;
import com.radida.pacs.dao.ILoginDao;
import com.radida.pacs.pojo.UserPo;
import com.radida.pacs.service.ILoginService;


@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired ILoginDao loginDao;
	
	@Override
	public ResultDto<Boolean> query(String username, String password) {
		ResultDto<Boolean> ret = new ResultDto<Boolean>(EnumResultCode.SUCCESS.getCode());
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 参数校验
		if (StringUtils.isEmpty(username)) {
			ret.setResultcode(EnumResultCode.ERROR_PARAM_EMPTY.getCode());
			return ret;
		}
		if (StringUtils.isEmpty(password)) {
			ret.setResultcode(EnumResultCode.ERROR_PARAM_EMPTY.getCode());
			return ret;
		}
		params.put("username", username);
		params.put("password", password);
		
		UserPo userp = loginDao.query(params);
		if (userp == null) {
			ret.setResultcode(EnumResultCode.ERROR_USER_USER_NOTEXIST.getCode());
			return ret;
		}
		
		return ret;
	}


}
