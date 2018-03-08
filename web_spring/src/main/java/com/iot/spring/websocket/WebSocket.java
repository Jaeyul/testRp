package com.iot.spring.websocket;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocket {
	
	 	private Session wsSession;
	    private HttpSession httpSession;

	    @OnOpen
	    public void handleOpen(Session session, EndpointConfig config){
	    	this.wsSession = session;
	        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

	    }
	    
	    @OnMessage
	    public String handleMessage(String message) throws IOException{
	    	wsSession.getBasicRemote().sendText(message);	    	
	        System.out.println("receive from client : "+message);
	        String replymessage = "echo "+message;
	        System.out.println("send to client : "+replymessage);
	        return replymessage;
	    }
	    
	    @OnClose
	    public void handleClose(){
	        System.out.println("client is now disconnected...");
	    }
	    /**
	     * 웹 소켓이 에러가 나면 호출되는 이벤트
	     * @param t
	     */
	    @OnError
	    public void handleError(Throwable t){
	        t.printStackTrace();
	    }
	}



