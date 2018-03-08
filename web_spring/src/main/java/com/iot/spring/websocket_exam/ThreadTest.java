package com.iot.spring.websocket_exam;

import java.util.Random;

public class ThreadTest implements Runnable{
	private int num;
	
	ThreadTest(int num){
		this.num = num;
		
	}
	
	@Override
	public void run() {
		System.out.println(num + "번 마!! 달리기 시작");
		Random r = new Random();
		try {
			Thread.sleep(r.nextInt(3000));
			System.out.println(num + "번 마!! 달리기 종료");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

	
	public static void main(String[] args) {
		for(int i=1; i<=10; i++) {
			new Thread(new ThreadTest(i)).start();
			
		}
		
		
	}

	
}
