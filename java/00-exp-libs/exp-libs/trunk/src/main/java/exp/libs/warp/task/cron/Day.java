package exp.libs.warp.task.cron;

public class Day extends _TimeUnit {

	@Override
	public String setSubExpression(String subExpression) {
		// TODO Auto-generated method stub
		return subExpression;
	}
	
	/** 任意值: 即此位置的时间值对最终的cron规则无约束 */
	public String withAny() {
		subExpression = "?";
		return subExpression;
	}

}
