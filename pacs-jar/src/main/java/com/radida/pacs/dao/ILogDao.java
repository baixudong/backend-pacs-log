package com.radida.pacs.dao;

import java.util.List;
import java.util.Map;

import com.radida.pacs.pojo.LogPo;

public interface ILogDao {

	public void create(Map<String, Object> params);
	
	public List<LogPo> query(Map<String, Object> params);

}
