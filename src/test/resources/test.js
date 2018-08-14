//<scriptFlow:flow>
var run = function(parameters) {
	//<scriptFlow:flow:step:varDefine>
	var return_value="json:"; 
	//</scriptFlow:flow:step:varDefine>
	
	//<scriptFlow:flow:step:try>

	//</scriptFlow:flow:step:try>	
		//<scriptFlow:flow:step:javascript>
		return_value+="aaaaaa";
		//</scriptFlow:flow:step:javascript>
		
		//<scriptFlow:flow:step:DBQuery>
		var DBQueryType = Java.type('com.onemysoft.scriptflow.flow.steps.dbquery.DBQuery');
		var DBQuery=new DBQueryType({"datasource":{"name":"db_example","dirver":"com.mysql.jdbc.Driver","url":"jdbc:mysql://localhost:3306/om?useUnicode=true&amp;characterEncoding=utf-8","username":"root","password":"root"},"sqlText":"select * from auth_user where id>? and name=?","bindingParameter":true,"arguments":["0","admin"]});
		var DBQuery_list = DBQuery.execute();
		//</scriptFlow:flow:step:DBQuery>
		
		//<scriptFlow:flow:step:ToJSON>
		var ToJSONType = Java.type('com.onemysoft.scriptflow.flow.steps.tojson.ToJSON');
		var ToJSON=new ToJSONType();
		ToJSON.setList(DBQuery_list);
		return_value=ToJSON.execute();
		//</scriptFlow:flow:step:ToJSON>
		
		
		//继承和扩展线程类
		var Run = Java.type("java.lang.Runnable");
		var MyRun = Java.extend(Run, {
		    run: function() {
		    	Thread.sleep(5000);
		        print("I am running in separate thread");
		        
		    }
		});
		//构造
		var Thread = Java.type("java.lang.Thread");
		var th = new Thread(new MyRun());
		print("Thread:"+th.getId());
		th.start();
		 
		 
		var th1 = new Thread(new MyRun());
		print("Thread:"+th1.getId());
		th1.start();


		
    //<scriptFlow:flow:step:return>
    return return_value;
	//</scriptFlow:flow:step:return>
};
//</scriptFlow:flow>
