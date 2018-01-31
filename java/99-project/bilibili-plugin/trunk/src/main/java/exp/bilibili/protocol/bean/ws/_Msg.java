package exp.bilibili.protocol.bean.ws;

import net.sf.json.JSONObject;
import exp.bilibili.protocol.envm.BiliCmd;

abstract class _Msg {

	protected BiliCmd cmd;
	
	protected JSONObject json;
	
	protected _Msg(JSONObject json) {
		this.cmd = BiliCmd.UNKNOW;
		this.json = (json == null ? new JSONObject() : json);
		analyse(this.json);
	}

	protected abstract void analyse(JSONObject json);
	
	public BiliCmd getCmd() {
		return cmd;
	}

	public JSONObject getJson() {
		return json;
	}
	
}