package com.radida.pacs.service;

import java.util.List;

import com.radida.pacs.core.common.bean.Page;
import com.radida.pacs.core.common.bean.ResultDto;
import com.radida.pacs.pojo.TestPo;


public interface ITestService {

	public List<TestPo> queryList();

	public ResultDto<Page<TestPo>> query(String name, String pageIndex, String pageSize);

}
