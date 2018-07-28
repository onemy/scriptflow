/**   
* 
*/
package com.onemysoft.scriptflow.database;

/** 
* @ClassName: DataSource 
* @Description: TODO 
* @author zongshuo 
* @date 2018��7��13�� ����4:29:12 
*  
*/
public class JDBCDataSource {

	private String driver;
	
	private String url;
	
	private String username;
	
	private String password;
	

	
	/**
	 * 
	 */
	public JDBCDataSource() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}



	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}



	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
