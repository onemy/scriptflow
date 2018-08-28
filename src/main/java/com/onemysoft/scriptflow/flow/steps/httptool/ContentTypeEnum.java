/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.httptool;

/** 
* @ClassName: ScriptSourceTypeEnum 
* @Description: TODO 
* @author zongshuo 
* @date 2018年7月14日 上午10:24:28 
*  
*/
public enum ContentTypeEnum {
	//GET,POST,PUT,DELETE,HEAD,OPTIONS;
	TEXT("text/plain"),
	JSON("application/json"),
	XML("text/xml"),
	JAVASCRIPT("application/javascript");
	
	
	private String val;
	
	private ContentTypeEnum(String val) {
		this.val = val;
	}
	public String getVal() {
		return val;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test=ContentTypeEnum.TEXT.toString();
		System.out.println(ContentTypeEnum.valueOf("TEXT").getVal());
	}

}
