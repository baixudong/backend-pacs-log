package com.radida.pacs.log.receive.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;

import com.radida.pacs.log.server.LogCache;


/**
 * @author baixd
 */
public class UdpServer {

	private int port = 515; // Udp端口

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private DatagramSocket server = null;

	private UdpServer() {

	}

	private static UdpServer udpserver = new UdpServer();

	public static UdpServer newInstance() {
		return udpserver;
	}

	public void init() {
		try {
			server = new DatagramSocket(port);
			server.setReceiveBufferSize(1024000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run(String ip, LinkedList<String> list) {
		
	}
//	public void run() {

//		try {
//			server = new DatagramSocket(port);
//			server.setReceiveBufferSize(1024000);
//			byte[] recvBuf = new byte[5000];
//			DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
//			while (true) {
//				try {
//					server.receive(recvPacket);
//					LogCache.exe(recvPacket.getAddress().toString(), recvPacket.getData(), recvPacket.getLength());
//				} catch (Exception e) {
//
//				}
//			}
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
//    }

	public void receive(DatagramPacket recvPacket) throws Exception {
		server.receive(recvPacket);
		LogCache.put(recvPacket.getAddress().getHostAddress(), new String(recvPacket.getData(), 0, recvPacket.getLength()));
	}
	
}
