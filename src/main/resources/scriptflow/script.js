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
		
		//<scriptFlow:flow:step:for>
		for each (var el in DBQuery_list){
		//</scriptFlow:flow:step:for>
			//<scriptFlow:flow:step:log>
			print(el.name +" "+ el.password);
			//</scriptFlow:flow:step:log>
		//<scriptFlow:flow:step:forEnd>	
		}
		//</scriptFlow:flow:step:forEnd>
		
		//<scriptFlow:flow:step:if>
		if(1==1){
			//<scriptFlow:flow:step:WebService>
			var WebServiceType = Java.type('com.onemysoft.scriptflow.flow.steps.webservice.WebService');
			var WebService=new WebServiceType('{"wsdlUrl":"http://xxx.onemy.com/wsdl", '+
				'	"username":"",	'+
				'	"password":"",	'+
				'	"endpoint":"http://10.238.103.138:8180/aip/component/http/JSON/test001/component012601",	'+
				'	"encoding":"",	'+
				'	"requestEntity":"",	'+
				'	"socketTimeout":0,	'+
				'	"connectionTimeout":0,	'+
				'	"closeIdleConnectionsTime":0,	'+
				'	"proxyHost":"",	'+
				'	"proxyPort":0,	'+
				'	"responseResult":"",	'+
				'	"responseCode":"",	'+
				'	"responseTime":""	'+
			'}');
			print(WebService.execute());
			//</scriptFlow:flow:step:WebService>
		}else{
			//<scriptFlow:flow:step:WebService1>
			var WebService1Type = Java.type('com.onemysoft.scriptflow.flow.steps.webservice.WebService');
			var wsJson={"wsdlUrl":"http://xxx.onemy.com/wsdl", 
					"username":"",	
					"password":"",	
					"endpoint":"http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx",	
					"encoding":"",	
					"requestEntity":"",	
					"socketTimeout":0,	
					"connectionTimeout":0,	
					"closeIdleConnectionsTime":0,	
					"proxyHost":"",	
					"proxyPort":0,	
					"responseResult":"",	
					"responseCode":"",	
					"responseTime":""	
				};
			wsJson.requestEntity='<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://WebXml.com.cn/">'+
								   '<soapenv:Header/>'+
								   '<soapenv:Body>'+
								      '<web:qqCheckOnline>'+
								         '<!--Optional:-->'+
								         '<web:qqCode>445789008</web:qqCode>'+
								      '</web:qqCheckOnline>'+
								   '</soapenv:Body>'+
								'</soapenv:Envelope>';
			
			var WebService1=new WebService1Type(JSON.stringify(wsJson));
			print((WebService1.execute()).result);
			print((WebService1.execute()).code);
			print((WebService1.execute()).time);
			//<scriptFlow:flow:step:WebService1>
		}
		//</scriptFlow:flow:step:if>


	//<scriptFlow:flow:step:comment>
	/*
	 * 测试注释文本
	 */
	//</scriptFlow:flow:step:comment>
		
	//<scriptFlow:flow:step:log>
    print('map parameter test, ' + parameters.key2);
	//</scriptFlow:flow:step:log>
    
    //<scriptFlow:flow:step:return>
    return return_value;
	//</scriptFlow:flow:step:return>
};
//</scriptFlow:flow>
