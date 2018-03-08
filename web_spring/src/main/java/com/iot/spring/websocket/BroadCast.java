package com.iot.spring.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/broadsocket")
public class BroadCast {
	

	private static final Logger log = LoggerFactory.getLogger(BroadCast.class);
	private ObjectMapper om;
	
    static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<Session>());
    private HttpSession httpSession;
    
    @OnOpen
    public void handleOpen(Session userSession, EndpointConfig config){
        sessionUsers.add(userSession);
        log.info("userSession=>{}",userSession);
        /*this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        if(httpSession.getAttribute("user")!=null) {
        	  System.out.println(httpSession.getAttribute("user"));
        	  
        }else {
        	System.out.println("앤 뭐하는 거임?");
        	
        }*/
    }
  
    @OnMessage
    public void handleMessage(String message,Session userSession) throws IOException{
    	//log.info("userName =>{} " , userName);
        if(userSession.getUserProperties().get("username") == null){        	        	
            userSession.getUserProperties().put("username", message);
            userSession.getBasicRemote().sendText("System : " + "you are now connected as " + message);            
            return;
        }else {
        	String username = (String)userSession.getUserProperties().get("username");
        	Iterator<Session> iterator = sessionUsers.iterator();
            while(iterator.hasNext()){
                iterator.next().getBasicRemote().sendText(username+ ":" + message);
            }
        }        
    }
    /**
     * 웹소켓을 닫으면 해당 유저를 유저리스트에서 뺀다.
     * @param userSession
     */
    @OnClose
    public void handleClose(Session userSession){
        sessionUsers.remove(userSession);
    }
    /**
     * json타입의 메시지 만들기
     * @param username
     * @param message
     * @return
     */
    
    public String buildJsonData(String username,String message){
        Map<String, String> jMap = new HashMap<String, String>();
        jMap.put("message", username + ":" + message);
        
        String json;
		try {
			json = om.writeValueAsString(jMap);
			return json;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
