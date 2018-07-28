/**   
* 
*/
package com.onemysoft.scriptflow.core.exception;

/** 
* @ClassName: AppException 
* @Description: TODO 
* @author zongshuo 
* @date 2018年7月14日 上午10:02:13 
*  
*/
public class AppException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5257246965873934519L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}
}
