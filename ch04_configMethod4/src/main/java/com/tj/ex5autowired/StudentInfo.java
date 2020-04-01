package com.tj.ex5autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentInfo {
	
	private Student student; // @autowired를 변수위에 적으면, setter을 작성안해도 됩니다.! 지금은 선언한 변수 밑에 적으니께, setter 작성
	
	@Autowired
	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

}
