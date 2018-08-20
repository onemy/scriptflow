package com.onemysoft.scriptflow.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onemysoft.scriptflow.core.enums.ScriptSourceTypeEnum;
import com.onemysoft.scriptflow.flow.Flow;
import com.onemysoft.scriptflow.web.model.FlowModel;

@RestController
public class RunController {
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public void runAsText(@RequestBody FlowModel flow) {
        System.out.println(flow.getBody());
    }
	@RequestMapping(value = "/run/{name}", method = RequestMethod.GET)
    public String runAsFile(@PathVariable("name") String name) {
		
		
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
