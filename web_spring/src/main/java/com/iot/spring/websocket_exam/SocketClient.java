package com.iot.spring.websocket_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {
	
	public static void main(String[] args) {
		Socket s;
		try {
			Scanner sc = new Scanner(System.in);
			s = new Socket("192.168.0.47",8881);
			//System.out.println(s.isConnected());
			boolean maintain = true;
			System.out.println("서버 연결 완료");
			while(maintain) {
				OutputStream os = s.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
				String msg = sc.nextLine();
				pw.println(msg);
				pw.flush();
				
				InputStream is = s.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String inputStr = null;				
				while((inputStr =br.readLine())!=null) {
					System.out.println("수신자 : " + inputStr);
				}
				
				pw.close();	
				br.close();

				if(msg.equals("close")) {
					
					maintain = false;
				}
			}
			s.close();
			System.out.println("서버 연결 종료");
		} 
		
		
		catch (UnknownHostException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
