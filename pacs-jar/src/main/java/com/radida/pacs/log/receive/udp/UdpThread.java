package com.radida.pacs.log.receive.udp;

import java.net.DatagramPacket;

import com.radida.pacs.log.counter.Counter;
import com.radida.pacs.log.server.LogCache;
import com.radida.pacs.log.thread.AbstractThread;

/**
 * @author baixd
 */
public class UdpThread extends AbstractThread {

	private DatagramPacket recvPacket;

	@Override
	public void init() {
		byte[] recvBuf = new byte[5000];
		recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
	}

	@Override
	public void execute() throws Exception {
		
		UdpServer.newInstance().receive(recvPacket);
//		Counter.add();
	}

	public void execute2() throws Exception {
		synchronized (recvPacket) {
			UdpServer.newInstance().receive(recvPacket);
		}
		Counter.add();
		LogCache.list.getLast().add(new Object[] { recvPacket.getAddress().toString(), new Object[] { recvPacket.getData(), recvPacket.getLength() } });

	}
}
