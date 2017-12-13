package exp.bilibli.plugin.bean.ldm;

import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import exp.libs.utils.os.ThreadUtils;
import exp.libs.utils.verify.RegexUtils;

public class WebSockClient extends WebSocketClient {

	private final static long TIMEOUT = 20000;
	
	/**
	 * 
	 * @param serverUri
	 * @param draft WebSocket协议版本
	 * 				WebSocket协议说明可查看 http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
	 * 				通过打开调试开关 WebSocketImpl.DEBUG = true 可以知道服务端的协议版本
	 */
	public WebSockClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}
	
	public WebSockClient(URI serverUri) {
		super(serverUri, new Draft_6455());	// Draft_6455 为最新的WebSocket协议版本
	}
	
	public void debug(boolean open) {
		WebSocketImpl.DEBUG = open;
	}
	
	@Deprecated
	@Override
	public void connect() {
		// Undo
	}

	public boolean conn() {
		boolean isOk = false;
		super.connect();
		
		long bgnTime = System.currentTimeMillis();
		do {
			if(isOpen()) {
				isOk = true;
				break;
			}
			ThreadUtils.tSleep(1000);
		} while(System.currentTimeMillis() - bgnTime <= TIMEOUT);
		return isOk;
	}
	
	@Override
	public void onOpen(ServerHandshake serverhandshake) {
		System.out.println("connect success");
	}

	@Override
	public void onMessage(String msg) {
		System.out.println("received String: ".concat(msg));
	}

	/**
	 * 
小电视抽奖：
{
  "cmd": "SYS_MSG",
  "msg": "\u3010\u745f\u60c5\u7684\u74f6\u5b50\u83cc\u3011:?\u5728\u76f4\u64ad\u95f4:?\u30103779462\u3011:?\u8d60\u9001 \u5c0f\u7535\u89c6\u4e00\u4e2a\uff0c\u8bf7\u524d\u5f80\u62bd\u5956",
  "msg_text": "\u3010\u745f\u60c5\u7684\u74f6\u5b50\u83cc\u3011:?\u5728\u76f4\u64ad\u95f4:?\u30103779462\u3011:?\u8d60\u9001 \u5c0f\u7535\u89c6\u4e00\u4e2a\uff0c\u8bf7\u524d\u5f80\u62bd\u5956",
  "rep": 1,
  "styleType": 2,
  "url": "http:\/\/live.bilibili.com\/3779462",
  "roomid": 3779462,
  "real_roomid": 3779462,
  "rnd": 1822599641,
  "tv_id": "31572"
}

火力抽奖：
{
  "cmd": "SYS_GIFT",
  "msg": "00\u515c\u515c00\u5728\u76f4\u64ad\u95f45279\u706b\u529b\u5168\u5f00\uff0c\u55e8\u7ffb\u5168\u573a\uff0c\u901f\u53bb\u56f4\u89c2\uff0c\u8fd8\u80fd\u514d\u8d39\u9886\u53d6\u706b\u529b\u7968\uff01",
  "msg_text": "00\u515c\u515c00\u5728\u76f4\u64ad\u95f45279\u706b\u529b\u5168\u5f00\uff0c\u55e8\u7ffb\u5168\u573a\uff0c\u901f\u53bb\u56f4\u89c2\uff0c\u8fd8\u80fd\u514d\u8d39\u9886\u53d6\u706b\u529b\u7968\uff01",
  "tips": "00\u515c\u515c00\u5728\u76f4\u64ad\u95f45279\u706b\u529b\u5168\u5f00\uff0c\u55e8\u7ffb\u5168\u573a\uff0c\u901f\u53bb\u56f4\u89c2\uff0c\u8fd8\u80fd\u514d\u8d39\u9886\u53d6\u706b\u529b\u7968\uff01",
  "url": "http:\/\/live.bilibili.com\/5279",
  "roomid": 5279,
  "real_roomid": 5279,
  "giftId": 106,
  "msgTips": 0
}

公告
{
  "cmd": "SYS_GIFT",
  "msg": "茕茕茕茕孑立丶:?  在裕刺Fy的:?直播间447:?内赠送:?105:?共367个",
  "rnd": "0",
  "uid": 8277884,
  "msg_text": "茕茕茕茕孑立丶在裕刺Fy的直播间447内赠送火力票共367个"
}
	 */
	@Override
	public void onMessage(ByteBuffer byteBuffer) {
		String msg = new String(byteBuffer.array());
		if(Frame.SERVER_HB.equals(msg)) {
			System.out.println("received ByteBuffer: heartbeat");
		} else {
			System.out.println("received ByteBuffer: " + msg);
			String json = RegexUtils.findFirst(msg, "[^{]*(.*)");
			System.out.println("received ByteBuffer: " + json);
		}
    }

	@Override
    public void onFragment(Framedata framedata) {
		System.out.println("received Framedata: ".concat(framedata.toString()));
    }
	
	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("Connection closed by " + ( remote ? "remote peer" : "us" ) + 
				" Code: " + code + " Reason: " + reason );
	}

	@Override
	public void onError(Exception e) {
		e.printStackTrace();
	}

}
