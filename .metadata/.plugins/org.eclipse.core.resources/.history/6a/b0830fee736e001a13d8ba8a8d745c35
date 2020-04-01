package com.tj.ex3xml_java;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tj.ex1xml.Student;

@Configuration
public class ApplicationConfig {
	@Bean
	public Student student1() {
		ArrayList<String> hobbies = new ArrayList<String>();
		hobbies.add("낚시"); hobbies.add("마라톤");
		Student student = new Student("신길동", 50, hobbies);
		student.setHeight(165);
		student.setWeight(80);
		return student;
	}
	
	
	
}
