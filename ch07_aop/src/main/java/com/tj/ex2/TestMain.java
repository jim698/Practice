package com.tj.ex2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		String location = "classpath:applicationCTX2.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(location);

		Student2 student2 = ctx.getBean("student", Student2.class);
		Worker2 worker2 = ctx.getBean("worker", Worker2.class);

		student2.getStudentInfo();
		worker2.getWorkerInfo();

		ctx.close();
	}
}
