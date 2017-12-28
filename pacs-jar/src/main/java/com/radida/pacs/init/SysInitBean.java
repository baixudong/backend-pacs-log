package com.radida.pacs.init;

import org.springframework.beans.factory.InitializingBean;

import com.radida.pacs.log.thread.ThreadManager;
/**
 * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候会执行该方法。
 * @author baixd
 */
public class SysInitBean implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			ThreadManager.newInstance().start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


}
