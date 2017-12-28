package com.radida.pacs.log.thread;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.radida.pacs.log.receive.udp.UdpServer;
import com.radida.pacs.log.receive.udp.UdpThread;
import com.radida.pacs.log.summary.SummaryThread;

/**
 * @author baixd
 */
public class ThreadManager {

	private boolean runing = true;

	private static final Map<String, ThreadPool> THREADS = new HashMap<String, ThreadPool>();

	private final static ThreadManager tm = new ThreadManager();

	public static ThreadManager newInstance() {
		return tm;
	}

	public void start() throws ClassNotFoundException {
		
		UdpServer server = UdpServer.newInstance();
		server.init();
		
		startThread("udp", UdpThread.class, 1, 1, 1);
		startThread("summary", SummaryThread.class, 1, 1, 1);
//		startThread("udp", UdpThread.class, 5, 10, 5);
//		startThread("summary", SummaryThread.class, 5, 10, 5);
		
		// 守护线程启动
		new Thread() {
            @Override
            public void run() {
                while (runing) {
        			try {
	    				Thread.sleep(1000);
	    				check();
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
                }
            };
        }.start();
	}

	public void check() {
		Collection<ThreadPool> tps = THREADS.values();
		for (ThreadPool tp : tps) {
			tp.check();
		}

	}

	public void startThread(String name, Class<? extends AbstractThread> cs, int min, int max, int start) {
		ThreadPool tp = new ThreadPool();
		THREADS.put(name, tp);
		tp.setCls(cs);
		tp.setName(name);
		tp.setMax(max);
		tp.setMin(min);
		tp.setStartLength(start);
		tp.start();

	}
	
	public static void main(String[] args) {
		try {
			ThreadManager.newInstance().start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
