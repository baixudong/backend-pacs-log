package com.radida.pacs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.radida.pacs.core.common.Base64Util;
import com.radida.pacs.core.common.EnumResultCode;
import com.radida.pacs.core.common.JsonUtils;
import com.radida.pacs.core.common.bean.ResultDto;
import com.radida.pacs.core.common.bean.ResultHelper;
import com.radida.pacs.service.ILoginService;

@Controller
public class LoginController {
	
	@Autowired
	public ILoginService loginService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("login")
	public @ResponseBody Object list(HttpServletRequest request, @RequestParam(value = "params") String params) {
		ResultDto<Boolean> ret = new ResultDto<Boolean>(EnumResultCode.SUCCESS.getCode());
		
		// 处理参数
		String params_decode = Base64Util.decodeStr(params);
		Map<String, Object> pMap;
		try {
			pMap = JsonUtils.readJson2Map(params_decode);
		} catch (Exception e) {
			ret.setResultcode(EnumResultCode.ERROR_PARAM_JSON.getCode());
			return ResultHelper.procResult(request, ret, null, null, 1, null, logger);
		}
		if (pMap == null) {
			ret.setResultcode(EnumResultCode.ERROR_PARAM_EMPTY.getCode());
			return ResultHelper.procResult(request, ret, null, null, 1, null, logger);
		}

		String username = (String) pMap.get("username");
		String password = (String) pMap.get("password");
		String callback = (String) pMap.get("callback");
		
		// 必传参数数组
		String[] requiredParamNames = { "username", "password" };
		Object checkParameterRet = ResultHelper.checkParamters(request, pMap, requiredParamNames, callback, logger);
		if (checkParameterRet != null) {
			return checkParameterRet;
		}
		ret = loginService.query(username, password);
		
		return ResultHelper.procResult(request, ret, null, callback, 1, null, logger);
	}

}
