/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.webservice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.onemysoft.scriptflow.core.exception.AppException;
import com.onemysoft.scriptflow.core.ssl.MySSLProtocolSocketFactory;
import com.onemysoft.scriptflow.flow.steps.Step;

/** 
* @ClassName: WebService 
* @Description: TODO 
* @author zongshuo 
* @date 2018年7月18日 下午3:27:53 
*  
*/
public class WebService extends Step {
	
	private String wsdlUrl;
	private String username;
	private String password;
	private String endpoint;
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
	
	
	
	/**
	 * 
	 */
	public WebService(String jsonString) {
		JSONObject jo=JSON.parseObject(jsonString);
		System.out.println(jo.getString("endpoint"));
		
		if(jo.getString("wsdlUrl")==null||jo.getString("wsdlUrl").equals("")||jo.getString("endpoint")==null||jo.getString("endpoint").equals("")){
			throw new AppException("wsdlUrl or endpoint is empty");
		}
		this.wsdlUrl=jo.getString("wsdlUrl") != null ? jo.getString("wsdlUrl") : "";
		this.username=jo.getString("username") != null ? jo.getString("username"):"";
		this.password=jo.getString("password") !=null ? jo.getString("password"):"";
		this.endpoint=jo.getString("endpoint") !=null ? jo.getString("endpoint"):"";
		this.encoding=jo.getString("encoding") !=null && !jo.getString("encoding").equals("") ? jo.getString("encoding"):"utf-8";
		this.requestEntity=jo.getString("requestEntity") != null ? jo.getString("requestEntity"):"";
		this.socketTimeout=jo.getIntValue("socketTimeout");
		this.connectionTimeout=jo.getIntValue("connectionTimeout");
		this.closeIdleConnectionsTime=jo.getIntValue("closeIdleConnectionsTime");
		this.proxyHost=jo.getString("proxyHost") != null ? jo.getString("proxyHost"):"";
		this.proxyPort=jo.getIntValue("proxyPort");
		
		this.responseResult=jo.getString("responseResult") !=null && !jo.getString("responseResult").equals("") ? jo.getString("responseResult"):"result";
		this.responseCode=jo.getString("responseCode") !=null && !jo.getString("responseCode").equals("") ? jo.getString("responseCode"):"code";
		this.responseTime=jo.getString("responseTime") !=null && !jo.getString("responseTime").equals("") ? jo.getString("responseTime"):"time";
	}
	
	/* (non-Javadoc)
	 * @see com.onemysoft.scriptflow.job.steps.Step#execute()
	 */
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		Map returnMap=new HashMap();
		
		HttpClient httpClient = new HttpClient();
		
      	if(this.endpoint.contains("https")){
     		 Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);   
              Protocol.registerProtocol("https", myhttps);
     	}
		
		PostMethod postMethod = new PostMethod(this.endpoint);
		 // Set timeout
		if ( this.connectionTimeout > -1 ) {
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout( this.connectionTimeout );
		}
		if ( this.socketTimeout > -1 ) {
			httpClient.getHttpConnectionManager().getParams().setSoTimeout( this.socketTimeout );
		}

		if ( this.username!=null && !this.username.equals("") ) {
			httpClient.getParams().setAuthenticationPreemptive( true );
		  Credentials defaultcreds = new UsernamePasswordCredentials( this.username, this.password );
		  httpClient.getState().setCredentials( AuthScope.ANY, defaultcreds );
		}

		HostConfiguration hostConfiguration = new HostConfiguration();
		if ( this.proxyHost!=null && !this.proxyHost.equals("") ) {
		  hostConfiguration.setProxy( this.proxyHost, this.proxyPort );
		}
	      
		try {
			byte[] b = this.requestEntity.getBytes(encoding);
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			RequestEntity re = new InputStreamRequestEntity(is, b.length,"text/xml; charset=" + encoding);
			postMethod.setRequestEntity(re);
//			HttpClient httpClient = new HttpClient();
//			
//			if (this.username!=null&&this.username.equals("")) {
//				httpClient.getParams().setAuthenticationPreemptive(true);
//				Credentials defaultcreds = new UsernamePasswordCredentials(username, password);
//				httpClient.getState().setCredentials(AuthScope.ANY, defaultcreds);
//			
//			}
			
			// 执行
//			this.responseCode=httpClient.executeMethod(postMethod);
//
			
			
	        // used for calculating the responseTime
	        long startTime = System.currentTimeMillis();

	        // Execute the POST method
	        int statusCode = httpClient.executeMethod( hostConfiguration, postMethod );
	        
	        String responseText = postMethod.getResponseBodyAsString();

	        // calculate the responseTime
	        long executeTime = System.currentTimeMillis() - startTime;
	        
			returnMap.put(this.responseResult, responseText);
			returnMap.put(this.responseCode, statusCode);
			returnMap.put(this.responseTime, executeTime);

		} catch (Exception e) {
			throw new AppException("SOAP_REQUEST_ERROR", e);
		} finally {
			// 释放连接，无论执行方法是否成功，都必须释放连接。
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
	        if ( this.closeIdleConnectionsTime > -1 ) {
	        	httpClient.getHttpConnectionManager().closeIdleConnections( this.closeIdleConnectionsTime );
	        }
		}
		
		
		return returnMap;
	}

	/**
	 * @return the wsdlUrl
	 */
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	/**
	 * @param wsdlUrl the wsdlUrl to set
	 */
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
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



}
