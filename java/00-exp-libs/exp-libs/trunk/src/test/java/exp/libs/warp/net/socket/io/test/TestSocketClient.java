package exp.libs.warp.net.socket.io.test;

import exp.libs.warp.net.socket.bean.SocketBean;
import exp.libs.warp.net.socket.io.client.SocketClient;

public class TestSocketClient {

	public static void main(String[] args) {
		SocketBean sb = new SocketBean();
		sb.setIp("172.168.10.26");
		sb.setPort(9998);
		
		SocketClient client = new SocketClient(sb);
		client.conn();
		System.out.println(client.read());
		client.close();
	}
	
}
