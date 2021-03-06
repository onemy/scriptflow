/**   
* 
*/
package com.onemysoft.scriptflow.flow.steps.dbquery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.onemysoft.scriptflow.core.exception.AppException;
import com.onemysoft.scriptflow.database.JDBCDataSource;
import com.onemysoft.scriptflow.flow.steps.Executeable;
import com.onemysoft.scriptflow.flow.steps.Step;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/** 
* @ClassName: DBQuery 
* @Description: TODO 
* @author zongshuo 
* @date 2018��7��13�� ����4:25:54 
*  
*/
public class DBQuery extends Step implements Executeable {
	
	private DriverManagerDataSource dataSource;
	
	private String sql;
	
	private boolean bindParameters;
	
	private Object[] arguments;
	
	//singleton ScriptEngine
	private static JdbcTemplate jdbcTemplate;
	/**
	 * 
	 */
	public DBQuery(JDBCDataSource jdbcDataSource) {
		// TODO Auto-generated constructor stub
		
		init(jdbcDataSource);
	}
	
	public DBQuery(ScriptObjectMirror object){
		//ScriptObjectMirror ds=(ScriptObjectMirror)object.get("datasource");
		System.out.println((((ScriptObjectMirror)object.get("arguments")).values()).toArray());
		
		JDBCDataSource jdbcDataSource=new JDBCDataSource();
		String DS_Driver=(String)((ScriptObjectMirror)object.get("datasource")).get("dirver");
		String DS_Url=(String)((ScriptObjectMirror)object.get("datasource")).get("url");
		String DS_Username=(String)((ScriptObjectMirror)object.get("datasource")).get("username");
		String DS_Password=(String)((ScriptObjectMirror)object.get("datasource")).get("password");
		
		if(DS_Driver!=null)
			jdbcDataSource.setDriver(DS_Driver);
		if(DS_Url!=null)
			jdbcDataSource.setUrl(DS_Url);
		if(DS_Username!=null)
			jdbcDataSource.setUsername(DS_Username);
		if(DS_Password!=null)
			jdbcDataSource.setPassword(DS_Password);
		
		if(object.get("sqlText")!=null)
			this.setSql((String)object.get("sqlText"));
		if(object.get("bindingParameter")!=null)
			this.setBindParameters((Boolean)object.get("bindingParameter"));	
		if(object.get("arguments")!=null)
			this.setArguments((((ScriptObjectMirror)object.get("arguments")).values()).toArray());
		
		init(jdbcDataSource);
	}
	
	private void init(JDBCDataSource jdbcDataSource){
		if(jdbcDataSource==null) throw new AppException("jdbcDatasource is null !");
		this.jdbcTemplate=getInstance();
		
		dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDataSource.getDriver());	//"com.mysql.jdbc.Driver"
        dataSource.setUrl(jdbcDataSource.getUrl());					//"jdbc:mysql://localhost:3306/om?useUnicode=true&amp;characterEncoding=utf-8"
        dataSource.setUsername(jdbcDataSource.getUsername());
        dataSource.setPassword(jdbcDataSource.getPassword());
	}
	
	public synchronized static JdbcTemplate getInstance() {
		if (jdbcTemplate == null)
			jdbcTemplate = new JdbcTemplate();
		return jdbcTemplate;
	}
	
	public Object execute(){
		if(sql==null) throw new AppException("sql text is null !");
		
		//final List<Map> list=new ArrayList<Map>();
		List list=new ArrayList();
		try{
			
	        
	        //JdbcTemplate jdbcTemplate=new JdbcTemplate();
	        jdbcTemplate.setDataSource(dataSource);
	        
	        if(bindParameters){
	        	list=jdbcTemplate.queryForList(this.sql,arguments);
	        }else{
	        	list=jdbcTemplate.queryForList(this.sql);
	        }
	        
//	        jdbcTemplate.query("select * from auth_user",new RowCallbackHandler() {
//	        	   public void processRow(ResultSet rs) throws SQLException {
//	        		    Map map = new HashMap();
//	        		    map.put("name", rs.getString("name"));
//	        		    map.put("password", rs.getString("password"));
//	        		    list.add(map);
//	        		   }
//	        		});

		}catch(Exception e){
			throw new AppException("waqs error",e);
			//e.printStackTrace();
		}
		System.out.println("step1 execute!");
		
		return list;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the bindParameters
	 */
	public boolean isBindParameters() {
		return bindParameters;
	}

	/**
	 * @param bindParameters the bindParameters to set
	 */
	public void setBindParameters(boolean bindParameters) {
		this.bindParameters = bindParameters;
	}

	/**
	 * @return the arguments
	 */
	public Object[] getArguments() {
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}



}
