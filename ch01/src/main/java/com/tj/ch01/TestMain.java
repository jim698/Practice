package com.tj.ch01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		/*
		 * Calculation calculation = new Calculation(); 
		 * calculation.setNum1(10);
		 * calculation.setNum2(5);
		 */
		
		// 스프링 컨테이너를 생성하여, xml설정 파일을 파싱
		
		String resourceLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocation);
		Calculation calculation = ctx.getBean("calculation", Calculation.class);
		
		calculation.add();
		calculation.sub();
		calculation.mul();
		calculation.div();
		
		ctx.close(); // 스프링 컨테이너 안의 빈객체 삭제, 스프링 컨테이너도 제거
	}
}
