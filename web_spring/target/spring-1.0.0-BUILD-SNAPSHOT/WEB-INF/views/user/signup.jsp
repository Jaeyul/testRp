<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script>
function signup(){
	
	var uiName = $("#uiName").val();
	var uiId = $("#uiId").val();
	var uiPwd = $("#uiPwd").val();
	var uiAddress = $("#uiAddress").val();
	
	var json = {'uiName':uiName, 'uiId':uiId, 'uiPwd':uiPwd, 'uiAddress':uiAddress};
	
	json = JSON.stringify(json);
	
	
	$.ajax({ 
	        type     : "POST"
	    ,   url      : "${root}/user/signup"
	    ,header:{
	 	    	"Accept":"application/json",
	 	    	"Content-Type":"application/json; charset=UTF-8"
	 	    },
	 	    beforeSend:function(xhr){
	 	    	xhr.setRequestHeader("Accept","application/json");
	 	    	xhr.setRequestHeader("Content-Type","application/json; charset=UTF-8");
	 	    }
	    ,   data     : json
	    ,	datatype : "JSON"
	    ,   success : function(res){
	    		alert(res.msg);
	    		if(res.biz){
	    			document.location.href="${root}/path/login";
	    		}
			}
	    ,   error : function(xhr, status, e) {
		    	alert("에러 : "+xhr.responseText);
			}
	    ,
			done : function(e) {
				
			}
		
	})
}


</script>


<body>
	Name : <input type="text" id="uiName" name="uiName"><br>
	ID : <input type="text" id="uiId" name="uiId"><br>
	Pwd : <input type="text" id="uiPwd" name="uiPwd"><br>
	Address : <input type="text" id="uiAddress" name="uiAddress"><br>	
	<input type="button" value="회원가입" onclick="signup()">	
</body>
</html>