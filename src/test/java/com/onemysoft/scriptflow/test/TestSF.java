/**   
* 
*/
package com.onemysoft.scriptflow.test;

import java.util.HashMap;
import java.util.Map;

import com.onemysoft.scriptflow.core.enums.ScriptSourceTypeEnum;
import com.onemysoft.scriptflow.flow.Flow;

/** 
* @ClassName: TestSF 
* @Description: TODO 
* @author zongshuo 
* @date 2018年7月14日 上午10:00:12 
*  
*/
public class TestSF {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			Flow job=new Flow();
			
			job.setScriptSourceType(ScriptSourceTypeEnum.FILE);
			job.setScriptFileUrl("C:\\work\\workspace\\scriptflow\\src\\test\\resources\\sqlExecute.js");
			
			Map paras=new HashMap();
			paras.put("key2", 123);
			paras.put("name", 5);
			job.setParameters(paras);
			
			for(int i=0;i<1;i++){
				System.out.println("ID:"+i+" - "+job.run());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
