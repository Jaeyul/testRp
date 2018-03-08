<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- 메시지 표시 영역 -->
    <textarea id="messageTextArea" readonly="readonly" rows="10" cols="45"></textarea><br />    
    <!-- 송신 메시지 텍스트박스 -->
    <input type="text" id="messageText" size="50" />
    <!-- 송신 버튼 -->
    
    
    <input type="button" value="Send" onclick="sendMessage()" />
    <script type="text/javascript">
    	var webSocket;
		var messageTextArea = document.getElementById("messageTextArea");
        webSocket = new WebSocket("ws://192.168.0.19/${root}/broadsocket"); 
        
        webSocket.onopen = function(message){        		
			messageTextArea.value += "Server connect...\n";
			$(document).ready(function(){
	        	$.ajax({ 
	    	        type     : "POST"
	    	    ,   url      : "${root}/user/test"
	    	    ,header:{
	    	 	    	"Accept":"application/json",
	    	 	    	"Content-Type":"application/json; charset=UTF-8"
	    	 	    },
				 beforeSend:function(xhr){
	    	 	    	xhr.setRequestHeader("Accept","application/json");
	    	 	    	xhr.setRequestHeader("Content-Type","application/json; charset=UTF-8");
	    	 	    }
	    	    ,	datatype : "JSON"
	    	    ,   success : function(res){
	    	    		
	    	    		webSocket.send(res.userId);
	    			}
	    	    ,   error : function(xhr, status, e) {
	    		    	alert("에러 : "+xhr.responseText);
	    			}
	        	})
	        })
        };
        
      
       	
        webSocket.onmessage = function processMessge(message){
        	console.log(message);
        	messageTextArea.value += message.data + "\n";
        }

       
        function sendMessage(){
            var messageText = document.getElementById("messageText");
            webSocket.send(messageText.value);
            messageText.value = "";
        }
        
        
        webSocket.onerror = function(message){
            messageTextArea.value += "error...\n";
        };
        
		
        	
        
        
        
        
        
    </script>
</body>
</html>

