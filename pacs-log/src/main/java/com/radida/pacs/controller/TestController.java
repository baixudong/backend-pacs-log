package com.radida.pacs.controller;

import java.util.List;
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
import com.radida.pacs.core.common.PropertiesUtil;
import com.radida.pacs.core.common.bean.Page;
import com.radida.pacs.core.common.bean.ResultDto;
import com.radida.pacs.core.common.bean.ResultHelper;
import com.radida.pacs.pojo.TestPo;
import com.radida.pacs.service.ITestService;


@Controller
public class TestController {
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	public ITestService testService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/test")
	public @ResponseBody Object test(HttpServletRequest request, @RequestParam(value = "params") String params){
		List<TestPo> UserList = testService.queryList();
		System.out.println("" + UserList.size());
		return "---------";
	}
	
	@RequestMapping("list")
	public @ResponseBody Object list(HttpServletRequest request, @RequestParam(value = "params") String params) {
		ResultDto<Page<TestPo>> ret = new ResultDto<Page<TestPo>>(EnumResultCode.SUCCESS.getCode());
		
		// 处理参数
		String params_decode = Base64Util.decodeStr(params);
		Map<String, Object> pMap;
		try {
			pMap = JsonUtils.readJson2Map(params_decode);
		} catch (Exception e2) {
			ret.setResultcode(EnumResultCode.ERROR_PARAM_JSON.getCode());
			return ResultHelper.procResult(request, ret, null, null, 1, null, logger);
		}
		if (pMap == null) {
			ret.setResultcode(EnumResultCode.ERROR_PARAM_EMPTY.getCode());
			return ResultHelper.procResult(request, ret, null, null, 1, null, logger);
		}

		String pageIndex = (String) pMap.get("page");
		String pageSize = (String) pMap.get("count");
		String name = (String) pMap.get("name");
		String callback = (String) pMap.get("callback");
		
		// 必传参数数组
		String[] requiredParamNames = { "page", "count" };
		Object checkParameterRet = ResultHelper.checkParamters(request, pMap, requiredParamNames, callback, logger);
		if (checkParameterRet != null) {
			return checkParameterRet;
		}
		ret = testService.query(name, pageIndex, pageSize);
		
		String proxyJson = null;
		if (ResultHelper.checkRetSuccess(ret)) {
			proxyJson = propertiesUtil.getProperty("test_list");
		}
		return ResultHelper.procResult(request, ret, proxyJson, callback, 3, null, logger);
	}

}
