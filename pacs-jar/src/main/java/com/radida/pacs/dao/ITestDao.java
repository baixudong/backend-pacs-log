package com.radida.pacs.dao;

import java.util.List;
import java.util.Map;

import com.radida.pacs.pojo.TestPo;

public interface ITestDao {

	public List<TestPo> queryList();

	/**
	 * 测试类查询接口
	 * @param params
	 * @return 
	 */
	public List<TestPo> query(Map<String, Object> params);

	public Integer queryCount(Map<String, Object> params);

}
