package com.radida.pacs.dao;

import java.util.Map;

import com.radida.pacs.pojo.UserPo;

public interface ILoginDao {

	public UserPo query(Map<String, Object> params);

}
