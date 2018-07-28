/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.webservice;

import java.util.List;

/** 
* @ClassName: PortType 
* @Description: TODO 
* @author zongshuo 
* @date 2018年7月19日 下午12:21:44 
*  
*/
public class PortType {
	
	private String name;
	
	private String endpoint;

	private List operations;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the operations
	 */
	public List getOperations() {
		return operations;
	}

	/**
	 * @param operations the operations to set
	 */
	public void setOperations(List operations) {
		this.operations = operations;
	}
	
	
}
