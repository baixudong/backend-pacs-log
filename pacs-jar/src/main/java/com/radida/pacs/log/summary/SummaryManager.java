package com.radida.pacs.log.summary;

import com.radida.pacs.log.summary.service.ISummaryService;
import com.radida.pacs.log.summary.service.impl.ErrorSummaryServiceImpl;
import com.radida.pacs.log.summary.service.impl.InfoSummaryServiceImpl;

public class SummaryManager {
	
	private final static String LevelInfo = "level:info";

	private static InfoSummaryServiceImpl info = new InfoSummaryServiceImpl();
	private static ErrorSummaryServiceImpl error = new ErrorSummaryServiceImpl();
	
	public static SummaryManager sm = new SummaryManager();
	public static SummaryManager newInstance(){
		return sm;
	}

	/**
	 * 根据日志匹配指定到类
	 * @param logs
	 * @return
	 */
	public static ISummaryService getSummaryService(String logs) {
		
		if (logs.indexOf(LevelInfo) == 0) {
			return info;
		}
		
		return error;
	}
	
	
}
