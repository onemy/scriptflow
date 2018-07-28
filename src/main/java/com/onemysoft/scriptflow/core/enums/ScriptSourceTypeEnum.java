/**   
* 
*/
package com.onemysoft.scriptflow.core.enums;

/** 
* @ClassName: ScriptSourceTypeEnum 
* @Description: TODO 
* @author zongshuo 
* @date 2018年7月14日 上午10:24:28 
*  
*/
public enum ScriptSourceTypeEnum {
	FILE,TEXT;
//	FILE("1"),
//	STRING("2");
//	
//	private String val;
//	
//	private ScriptSourceTypeEnum(String val) {
//		this.val = val;
//	}
//	public String getVal() {
//		return val;
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test=ScriptSourceTypeEnum.FILE.toString();
		System.out.println(test.equals("FILE"));
	}

}
