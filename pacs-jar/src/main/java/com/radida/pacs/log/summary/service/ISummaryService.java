package com.radida.pacs.log.summary.service;

import java.util.Map;

public interface ISummaryService {

    public Map<String, String> summary(String ip, String tabName, String log, Map<String, String> mLog);

    
    
}
