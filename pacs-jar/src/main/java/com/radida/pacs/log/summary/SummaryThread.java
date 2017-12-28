package com.radida.pacs.log.summary;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.radida.pacs.core.common.DateFormat;
import com.radida.pacs.log.server.LogCache;
import com.radida.pacs.log.summary.service.ISummaryService;
import com.radida.pacs.log.thread.AbstractThread;

import cn.jpush.api.utils.StringUtils;

/**
 * @author baixd
 */
public class SummaryThread extends AbstractThread {

	public String IP = "192.168.1.77";
    
    /**
     * @param ip
     * @param list
     */
    public void run(String ip, LinkedList<String> list) {

        Map<String, String> mLog = new HashMap<String, String>();     //   根据关键字解析后的
        
        for (int i = 0, l = list.size(); i < l; i++) {
            String li = list.removeFirst();
            if (li == null) {
                continue;
            }
            try {
				ISummaryService summaryService = SummaryManager.getSummaryService(li);    //指定类
				String tabName = this.getTabName(ip);
				if (StringUtils.isNotEmpty(tabName)) {
					summaryService.summary(ip, tabName, li, mLog);
//					Map m = summaryService.summary(ip, tabName, li, mLog);
				}

				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        list.clear();
        list = null;
    
    }

    @Override
    public void init() {
    	
    }
    
    @Override
    public void execute() throws Exception {
		LinkedList<String> list = LogCache.read(IP);
        if (list == null || list.size() == 0) {
        	Thread.sleep(100);
        } else {
            int l = list.size();
            long l1 = System.currentTimeMillis();
            run(IP, list);
            long l2 = System.currentTimeMillis();
            System.out.println(this.getName() + " my size:" + l + " time :" + (l2 - l1));
		}
		
	}
    
    public String getTabName(String ip){
    	if (StringUtils.isEmpty(ip)) {
			return "";
		}
    	StringBuffer tabName = new StringBuffer();
    	tabName.append(ip);
		tabName.append("_");
		tabName.append(DateFormat.getDateStr(DateFormat.YMD_FORMAT));
		tabName.append("_");
		return tabName.toString().replace(".", "");
    }
    
}
