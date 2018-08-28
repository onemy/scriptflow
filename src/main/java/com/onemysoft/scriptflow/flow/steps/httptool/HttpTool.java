/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.httptool;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;

import com.onemysoft.scriptflow.core.exception.AppException;
import com.onemysoft.scriptflow.core.ssl.MySSLProtocolSocketFactory;
import com.onemysoft.scriptflow.flow.steps.Step;

/** 
* @ClassName: Rest 
* @Description: TODO 
* @author zongshuo 
* @date 2018年7月18日 下午5:45:50 
*  
*/
public class HttpTool extends Step {
	
	private String url;
	private String username;
	private String password;
	private String encoding;
	private String requestEntity;
	private int socketTimeout;
	private int connectionTimeout;
	private int closeIdleConnectionsTime;
	private String proxyHost;
	private int proxyPort;

	private String responseResult;
	private String responseCode;
	private String responseTime;
	
	private String httpMethodType;
	private String contentType;
	
	/**
	 * @param url
	 * @param username
	 * @param password
	 * @param encoding
	 * @param requestEntity
	 * @param socketTimeout
	 * @param connectionTimeout
	 * @param closeIdleConnectionsTime
	 * @param proxyHost
	 * @param proxyPort
	 * @param responseResult
	 * @param responseCode
	 * @param responseTime
	 * @param httpMethodType
	 * @param contentType
	 */
	public HttpTool(String url, String username, String password, String encoding, String requestEntity,
			int socketTimeout, int connectionTimeout, int closeIdleConnectionsTime, String proxyHost, int proxyPort,
			String responseResult, String responseCode, String responseTime, String httpMethodType,
			String contentType) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
		this.encoding = encoding;
		this.requestEntity = requestEntity;
		this.socketTimeout = socketTimeout;
		this.connectionTimeout = connectionTimeout;
		this.closeIdleConnectionsTime = closeIdleConnectionsTime;
		this.proxyHost = proxyHost;
		this.proxyPort = proxyPort;
		this.responseResult = responseResult;
		this.responseCode = responseCode;
		this.responseTime = responseTime;
		this.httpMethodType = httpMethodType;
		this.contentType = contentType;
	}

	/* (non-Javadoc)
	 * @see com.onemysoft.scriptflow.flow.steps.Step#execute()
	 */
	@Override
	public Object execute() {

		// TODO Auto-generated method stub
		Map returnMap=new HashMap();
		
		HttpClient httpClient = new HttpClient();
		
      	if(url.contains("https")){
     		 Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);   
             Protocol.registerProtocol("https", myhttps);
     	}
      	
      	HttpMethod httpMethod=new HttpMethodFactory().getHttpMethod(httpMethodType,url);
      	
		//PostMethod postMethod = new PostMethod(url);
		
		 // Set timeout
		if ( connectionTimeout > -1 ) {
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout( connectionTimeout );
		}
		if ( socketTimeout > -1 ) {
			httpClient.getHttpConnectionManager().getParams().setSoTimeout( socketTimeout );
		}

		if ( username!=null && !username.equals("") ) {
			httpClient.getParams().setAuthenticationPreemptive( true );
		  Credentials defaultcreds = new UsernamePasswordCredentials( username, password );
		  httpClient.getState().setCredentials( AuthScope.ANY, defaultcreds );
		}

		HostConfiguration hostConfiguration = new HostConfiguration();
		if ( proxyHost!=null && !proxyHost.equals("") ) {
		  hostConfiguration.setProxy( proxyHost, proxyPort );
		}
	      
		try {
			byte[] b = requestEntity.getBytes(encoding);
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length,ContentTypeEnum.valueOf(contentType).getVal()+"; charset=" + encoding);
			
			if(httpMethodType.equals(HttpMethodTypeEnum.POST)){
				((PostMethod)httpMethod).setRequestEntity(re);
			}else if(httpMethodType.equals(HttpMethodTypeEnum.PUT)){
				((PutMethod)httpMethod).setRequestEntity(re);
			}
			
						
	        // used for calculating the responseTime
	        long startTime = System.currentTimeMillis();

	        // Execute the POST method
	        int statusCode = httpClient.executeMethod( hostConfiguration, httpMethod );
	        
	        String responseText = httpMethod.getResponseBodyAsString();

	        // calculate the responseTime
	        long executeTime = System.currentTimeMillis() - startTime;
	        
			returnMap.put(responseResult, responseText);
			returnMap.put(responseCode, statusCode);
			returnMap.put(responseTime, executeTime);

		} catch (Exception e) {
			throw new AppException("HTTP_REQUEST_ERROR", e);
		} finally {
			// 释放连接，无论执行方法是否成功，都必须释放连接。
			if (httpMethod != null) {
				httpMethod.releaseConnection();
			}
	        if ( closeIdleConnectionsTime > -1 ) {
	        	httpClient.getHttpConnectionManager().closeIdleConnections( closeIdleConnectionsTime );
	        }
		}
		
		
		return returnMap;
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

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the requestEntity
	 */
	public String getRequestEntity() {
		return requestEntity;
	}

	/**
	 * @param requestEntity the requestEntity to set
	 */
	public void setRequestEntity(String requestEntity) {
		this.requestEntity = requestEntity;
	}

	/**
	 * @return the socketTimeout
	 */
	public int getSocketTimeout() {
		return socketTimeout;
	}

	/**
	 * @param socketTimeout the socketTimeout to set
	 */
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	/**
	 * @return the connectionTimeout
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * @param connectionTimeout the connectionTimeout to set
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @return the closeIdleConnectionsTime
	 */
	public int getCloseIdleConnectionsTime() {
		return closeIdleConnectionsTime;
	}

	/**
	 * @param closeIdleConnectionsTime the closeIdleConnectionsTime to set
	 */
	public void setCloseIdleConnectionsTime(int closeIdleConnectionsTime) {
		this.closeIdleConnectionsTime = closeIdleConnectionsTime;
	}

	/**
	 * @return the proxyHost
	 */
	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * @param proxyHost the proxyHost to set
	 */
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	/**
	 * @return the proxyPort
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * @param proxyPort the proxyPort to set
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * @return the responseResult
	 */
	public String getResponseResult() {
		return responseResult;
	}

	/**
	 * @param responseResult the responseResult to set
	 */
	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseTime
	 */
	public String getResponseTime() {
		return responseTime;
	}

	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * @return the httpMethodType
	 */
	public String getHttpMethodType() {
		return httpMethodType;
	}

	/**
	 * @param httpMethodType the httpMethodType to set
	 */
	public void setHttpMethodType(String httpMethodType) {
		this.httpMethodType = httpMethodType;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


}
