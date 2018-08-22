/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.httpclient;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.OptionsMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

import com.onemysoft.scriptflow.core.exception.AppException;

/** 
* @ClassName: HttpMethodFactory 
* @Description: TODO 
* @author zongshuo 
* @date 2018年8月22日 上午11:16:22 
*  
*/
public class HttpMethodFactory {
	
	public HttpMethod getHttpMethod(String httpMethod,String url){
		
		if(httpMethod.equals(HttpMethodTypeEnum.GET)){
			return new GetMethod(url);
		}else if(httpMethod.equals(HttpMethodTypeEnum.POST)){
			return new PostMethod(url);
		}else if(httpMethod.equals(HttpMethodTypeEnum.PUT)){
			return new PutMethod(url);
		}else if(httpMethod.equals(HttpMethodTypeEnum.HEAD)){
			return new HeadMethod(url);
		}else if(httpMethod.equals(HttpMethodTypeEnum.DELETE)){
			return new DeleteMethod(url);
		}else if(httpMethod.equals(HttpMethodTypeEnum.OPTIONS)){
			return new OptionsMethod(url);
		}else{
			throw new AppException("Http method is not supported !");
		}
	}
}
