/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps;

/** 
* @ClassName: Step 
* @Description: TODO 
* @author zongshuo 
* @date 2018��7��13�� ����3:53:09 
*  
*/
public abstract class Step {
	private String stepName;
	
	public abstract Object execute();

	/**
	 * @return the stepName
	 */
	public String getStepName() {
		return stepName;
	}

	/**
	 * @param stepName the stepName to set
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	
}
