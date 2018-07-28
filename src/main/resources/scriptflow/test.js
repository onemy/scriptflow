//<scriptFlow:flow>
var run = function(parameters) {
	//<scriptFlow:flow:step:varDefine>
	var return_value="json:"; 
	//</scriptFlow:flow:step:varDefine>
	
	//<scriptFlow:flow:step:try>
//	try{
	
//	}catch(e){
//		print('Error ：' + e);
//	}
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
		
    
    //<scriptFlow:flow:step:return>
    return return_value;
	//</scriptFlow:flow:step:return>
};
//</scriptFlow:flow>
