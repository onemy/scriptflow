/**   
* 
*/
package com.onemysoft.scriptflow.flow;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.onemysoft.scriptflow.core.enums.ScriptSourceTypeEnum;
import com.onemysoft.scriptflow.core.exception.AppException;

/** 
* @ClassName: Flow 
* @Description: TODO 
* @author zongshuo 
* @date 2018��7��13�� ����3:47:24 
*  
*/
public class Flow {
	
	private static final Logger LOG = LogManager.getLogger(Flow.class);
	
	private Map parameters=new HashMap();
	
	private Map globalParameters=new HashMap();
	
	private ScriptSourceTypeEnum scriptSourceType;
	
	private String scriptFileUrl;
	
	private String scriptSource;
	
	//singleton ScriptEngine
	private static ScriptEngine engine;
	
	public Flow(){
		this.engine=getInstance();
	}
	/**
	 * Class method to access the singleton instance of the class.
	 */
	public synchronized static ScriptEngine getInstance() {
		if (engine == null)
			engine = new ScriptEngineManager().getEngineByName("nashorn");
		return engine;
	}
	
	public String run() throws AppException{
		
		
		try{
			//ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			
			Bindings bindings=engine.createBindings();
			bindings.putAll(globalParameters);
			engine.getContext().setBindings(bindings, ScriptContext.ENGINE_SCOPE);
			
			//engine.getBindings(ScriptContext.ENGINE_SCOPE).putAll(globalParameters);
			
			if(scriptSourceType.equals(scriptSourceType.FILE)){
				if(scriptFileUrl==null) throw new AppException("file url is null !");
				engine.eval(new FileReader(scriptFileUrl));
			}else if(scriptSourceType.equals(scriptSourceType.TEXT)){
				if(scriptSource==null) throw new AppException("script content is null !");
				engine.eval(scriptSource);
			}else{
				engine.eval("");
			}
			
			
			Invocable invocable = (Invocable) engine;
			
			
			Object result = invocable.invokeFunction("run",parameters);
			
			if(LOG.isDebugEnabled()){
				LOG.debug("job run result : "+result);
			}
			if(result==null){
				return "null";
			}else{
				return result.toString();
			}
			

			
		}catch(Exception e){
			throw new AppException("engine execute error !",e);
		}
		
	}

	/**
	 * @return the parameters
	 */
	public Map getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the scriptSourceType
	 */
	public ScriptSourceTypeEnum getScriptSourceType() {
		return scriptSourceType;
	}

	/**
	 * @param scriptSourceType the scriptSourceType to set
	 */
	public void setScriptSourceType(ScriptSourceTypeEnum scriptSourceType) {
		this.scriptSourceType = scriptSourceType;
	}

	/**
	 * @return the globalParameters
	 */
	public Map getGlobalParameters() {
		return globalParameters;
	}

	/**
	 * @param globalParameters the globalParameters to set
	 */
	public void setGlobalParameters(Map globalParameters) {
		this.globalParameters = globalParameters;
	}

	/**
	 * @return the scriptFileUrl
	 */
	public String getScriptFileUrl() {
		return scriptFileUrl;
	}

	/**
	 * @param scriptFileUrl the scriptFileUrl to set
	 */
	public void setScriptFileUrl(String scriptFileUrl) {
		this.scriptFileUrl = scriptFileUrl;
	}

	/**
	 * @return the scriptSource
	 */
	public String getScriptSource() {
		return scriptSource;
	}

	/**
	 * @param scriptSource the scriptSource to set
	 */
	public void setScriptSource(String scriptSource) {
		this.scriptSource = scriptSource;
	}
	
	
}
