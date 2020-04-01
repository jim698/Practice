package com.tj.ch12.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Student.class.isAssignableFrom(clazz); // 검증할 객체를 명시
	}

	@Override // validator.validate(student, errors); // 검증해라!! - HomeController.java에 적어둠! // 여기에 구체적인 메소드 작성할 것!
	public void validate(Object target, Errors errors) { // 검증하는 메소드
		String name = ((Student) target).getName();
		int id = ((Student) target).getId();
//		if (name == null || name.trim().isEmpty()) {
//			System.out.println("name이 비어서 검증 못 함");
//			errors.rejectValue("name", "no"); // name 때문에 오류가 난거야~ 지정해주는 것!
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "no"); // 위의 주석을 한 줄로하는 ~ 

		if (id <= 0) {
			System.out.println("id가 자연수가 아니라 reject");
			errors.rejectValue("id", "no"); // id 때문에 오류가 난거야~ 지정해주는 것!
		}
	}

}
