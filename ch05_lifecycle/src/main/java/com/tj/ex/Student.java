package com.tj.ex;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class Student implements InitializingBean, DisposableBean, EnvironmentAware{
	
	private String name;
	private int age;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", age=" + age + "]";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		System.out.println("= 2. 막 student 빈 객체 생성 함. 초기화 작업 여기서");
		
	}

	@Override
	public void destroy() throws Exception {
		
		System.out.println("= 3. 이제 student 빈 객체는 소멸됨. 마무리 작업 여기서");
	}

	@Override
	public void setEnvironment(Environment environment) {
		
		System.out.println("1. student 빈이 생성되기 바로 직전에 환경이 설정되는 그 시점에 수행");
	}

}
