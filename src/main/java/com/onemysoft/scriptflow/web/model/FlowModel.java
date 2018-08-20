/**   
* 
*/
package com.onemysoft.scriptflow.web.model;

import java.util.Map;

/** 
* @ClassName: Flow 
* @Description: TODO 
* @author zongshuo 
* @date 2018年8月20日 下午2:10:44 
*  
*/
public class FlowModel {
	private Map parameter;
	private String body;
	/**
	 * @return the parameter
	 */
	public Map getParameter() {
		return parameter;
	}
	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(Map parameter) {
		this.parameter = parameter;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
}
