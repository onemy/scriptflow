/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.httpclient;

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

	
}
