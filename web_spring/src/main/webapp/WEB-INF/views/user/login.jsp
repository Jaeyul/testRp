<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script>
function login(){
	
	var uiId = $("#uiId").val();
	var uiPwd = $("#uiPwd").val();
	
	var json = {uiId : uiId, uiPwd : uiPwd};
	
	console.log(json);
	json = JSON.stringify(json);
	
	$.ajax({ 
	        type     : "POST"
	    ,   url      : "${root}/user/login"
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
	    			document.location.href="${root}/server/broadcast";
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

ID  : <input type="text" id="uiId">
PWD : <input type="text" id="uiPwd">
<input type="button" value="로그인" onclick="login()">

<br>
<a href="${root}/path/signup">회원가입</a>

</body>
</html>