/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.rest;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.onemysoft.scriptflow.flow.steps.Step;

/** 
* @ClassName: Rest 
* @Description: TODO 
* @author zongshuo 
* @date 2018年8月23日 下午2:10:03 
*  
*/
public class Rest extends Step {

	/* (non-Javadoc)
	 * @see com.onemysoft.scriptflow.flow.steps.Step#execute()
	 */
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try { 
			   
	        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

	        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
	                .loadTrustMaterial(null, acceptingTrustStrategy)
	                .build();

	        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

	        CloseableHttpClient httpClient = HttpClients.custom()
	                .setSSLSocketFactory(csf)
	                .build();

	        HttpComponentsClientHttpRequestFactory requestFactory =new HttpComponentsClientHttpRequestFactory();

	        requestFactory.setHttpClient(httpClient);
	        
	        requestFactory.setConnectionRequestTimeout(30000);
	        requestFactory.setReadTimeout(30000);
	        requestFactory.setConnectTimeout(30000);

//	        RestTemplate restTemplate = new RestTemplate(requestFactory);
//	        RestTemplateBuilder restTemplate2=new RestTemplateBuilder();
	        RestTemplate restTemplate=new RestTemplateBuilder().basicAuthorization("test", "123").build();
//	        restTemplate.setRequestFactory(requestFactory);
	        
	        
	        String url = "https://free-api.heweather.com/v5/forecast?city=CN101080101&key=5c043b56de9f4371b0c7f8bee8f5b75e";
	        String resp = restTemplate.getForObject(url, String.class);
//	        String resp = restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables);
	        System.out.println(resp);
		   
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return null;
	}
	
	public static void main(String[] args) {
		Rest rest=new Rest();
		rest.execute();
	}
}
