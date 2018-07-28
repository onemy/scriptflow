/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.tojson;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.onemysoft.scriptflow.core.exception.AppException;
import com.onemysoft.scriptflow.flow.steps.Executeable;
import com.onemysoft.scriptflow.flow.steps.Step;

/** 
* @ClassName: ToJSON 
* @Description: TODO 
* @author zongshuo 
* @date 2018��7��13�� ����4:27:37 
*  
*/
public class ToJSON extends Step implements Executeable {

	private List list;
	/**
	 * 
	 */
	public ToJSON() {
		// TODO Auto-generated constructor stub
	}

	public Object execute(){
		String jsonText="";
		try{
			//List list=(List)obj;
			System.out.println(list.size());
			jsonText = JSON.toJSONString(list);
					
		}catch(Exception e){
			throw new AppException("list convert json error !",e);
			//e.printStackTrace();
		}
		System.out.println("step4 execute!");
		
		return jsonText;
	}

	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	
	
}
