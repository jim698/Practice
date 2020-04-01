package com.tj.ch12.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tj.ch12.dto.Student;
import com.tj.ch12.dto.StudentValidator;

@Controller
public class HomeController {
	
	@RequestMapping(value="inputForm", method = RequestMethod.GET)
	
	public String inputForm() {		
		
		return "inputForm";
	}
	
	@RequestMapping(value = "input", method = RequestMethod.POST)
	public String input(@Valid Student student, BindingResult errors, Model model) { // validated 말고 , valid를 쓰려면 dependency 추가하면 됩니다.      

		//StudentValidator validator = new StudentValidator();
		//validator.validate(student, errors); // 검증해라!!
		if (errors.hasErrors()) {
			if(errors.getFieldError("name") != null) {
				model.addAttribute("nameError", "name은 반드시 입력요망");
			}
			
			if(errors.getFieldErrors("id")!=null) {
				model.addAttribute("idError", "id는 자연수로 입력요망");
			}				
				
			return "inputForm";
		}

		return "inputResult";
	}
	
	@InitBinder // @valid를 작성했을 경우, Student 객체와 StudentValidator class를 연결하고자 하는 어노테이션
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new StudentValidator());
	}
}
