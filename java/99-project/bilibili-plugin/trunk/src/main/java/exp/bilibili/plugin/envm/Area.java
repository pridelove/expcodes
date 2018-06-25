package exp.bilibili.plugin.envm;

/**
 * <PRE>
 * B站分区
 * </PRE>
 * <B>PROJECT：</B> bilibili-plugin
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2018-06-22
 * @author    EXP: <a href="http://www.exp-blog.com">www.exp-blog.com</a>
 * @since     jdk版本：jdk1.6
 */
public class Area {

	public final static Area AMUSE = new Area("1", "娱乐区");
	
	public final static Area PC_GAME = new Area("2", "游戏区");
	
	public final static Area APP_GAME = new Area("3", "手游区");
	
	public final static Area DRAW = new Area("4", "绘画区");
	
	private String id;
	
	private String desc;
	
	private Area(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public String ID() {
		return id;
	}
	
	public String DESC() {
		return desc;
	}
	
	@Override
	public String toString() {
		return DESC();
	}
	
}