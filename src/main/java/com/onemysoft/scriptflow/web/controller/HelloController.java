package com.onemysoft.scriptflow.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onemysoft.scriptflow.core.enums.ScriptSourceTypeEnum;
import com.onemysoft.scriptflow.flow.Flow;

@RestController
public class HelloController {

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name) {
		
		
		String returnString="";
		try{
			File file = ResourceUtils.getFile("classpath:scriptflow");
			Flow flow=new Flow();
			
			flow.setScriptSourceType(ScriptSourceTypeEnum.FILE);
			flow.setScriptFileUrl(file.getPath()+"\\"+name+".js");
			
			Map paras=new HashMap();
			paras.put("key2", 123);
			paras.put("id", 5);
			flow.setParameters(paras);
			
			for(int i=0;i<1;i++){
				//System.out.println("ID:"+i+" - "+job.run());
			}
			returnString=flow.run();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
        return returnString;
    }
}
