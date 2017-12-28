package com.radida.pacs.service;

import com.radida.pacs.core.common.bean.ResultDto;


public interface ILoginService {

	public ResultDto<Boolean> query(String username, String password);

}
