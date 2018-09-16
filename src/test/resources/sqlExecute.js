/**
 * scriptflow run method
 * @param parameters to map
 */
var run = function(parameters) {
	var result="";
	
	var SqlExecuteType = Java.type('com.onemysoft.scriptflow.flow.steps.sqlexecute.SqlExecute');
	var SqlExecute=new SqlExecuteType({"datasource":{"name":"db_example","dirver":"com.mysql.jdbc.Driver","url":"jdbc:mysql://localhost:3306/om?useUnicode=true&amp;characterEncoding=utf-8","username":"root","password":"root"},"sqlText":"insert into mall_sn (last_value,type) values ('1','1')","bindingParameter":false,"arguments":["0","admin"]});
	var i = SqlExecute.execute();
	print(i);
	
	return i;
}