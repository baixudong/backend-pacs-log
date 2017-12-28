package com.radida.pacs.log.summary.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;

import com.radida.pacs.core.common.SpringTool;
import com.radida.pacs.core.common.UUIDGenerator;
import com.radida.pacs.dao.ILogDao;
import com.radida.pacs.log.summary.service.ISummaryService;

@Component
public class ErrorSummaryServiceImpl implements ISummaryService {
	
	@Autowired ILogDao logDao;
	
    @Override
    public Map<String, String> summary(String ip, String tabName, String log, Map<String, String> mLog) {

    	if (null == logDao) {
    		logDao = (ILogDao) SpringTool.getBean("ILogDao");
		}
    	Map<String, String> m = new HashMap<String, String>();
//    	System.out.println("ERROR_log== " + log);
    	Map<String, Object> params = new HashMap<String, Object>();
    	Date now = new Date();
    	params.put("tableName", tabName+"info");
    	params.put("gid", UUIDGenerator.getUUID());
    	params.put("msg", log);
    	params.put("createTime", now);
    	
    	try {
			logDao.create(params);
		} catch (BadSqlGrammarException e) {
			logDao.create(params);
		}
    	
    	return m;
		
    }


}
