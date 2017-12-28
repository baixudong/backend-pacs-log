package com.radida.pacs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radida.pacs.core.common.EnumResultCode;
import com.radida.pacs.core.common.bean.Page;
import com.radida.pacs.core.common.bean.PageHelper;
import com.radida.pacs.core.common.bean.ResultDto;
import com.radida.pacs.dao.ITestDao;
import com.radida.pacs.pojo.TestPo;
import com.radida.pacs.service.ITestService;


@Service
public class TestServiceImpl implements ITestService {
	
	@Autowired ITestDao testDao;
	
	@Override
	public List<TestPo> queryList() {
		return testDao.queryList();
	}

	@Override
	public ResultDto<Page<TestPo>> query(String name, String pageIndex, String pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 参数校验
		if (StringUtils.isNotEmpty(name)) {
			params.put("name", name);
		}
		params.put("pageindex", PageHelper.getStartPosition(Integer.parseInt(pageIndex), Integer.parseInt(pageSize)));
		params.put("pagesize", Integer.parseInt(pageSize));
		
		ResultDto<Page<TestPo>> ret = new ResultDto<Page<TestPo>>(EnumResultCode.SUCCESS.getCode());
		Integer total = testDao.queryCount(params);
		if ( total<1 ) {
			ret.setResultcode(EnumResultCode.SUCCESS_NODATA.getCode());
			return ret;
		}
		List<TestPo> tList = testDao.query(params);
		Page<TestPo> page = new Page<TestPo>(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		page.setData(tList);
		page.setTotalResults(total);
		ret.setData(page);
		return ret;
	}


}
