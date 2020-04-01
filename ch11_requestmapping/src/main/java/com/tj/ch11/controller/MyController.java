package com.tj.ch11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
		
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
	
	/*
	 * @RequestMapping(value = "student") 
	 * public String student(HttpServletRequest request, Model model) { 
	 * String method = request.getMethod(); // method가 * get인지, post인지 값을 가지고 가기 위해서~ 
	 * String id = request.getParameter("id");
	 * model.addAttribute("method", method); 
	 * model.addAttribute("id", id); 
	 * return "studentId";	  
	 * }
	 */
	
	@RequestMapping(value="student", method = RequestMethod.GET)
	public String student(String id, Model model) {
		
		System.out.println("get방식 student메소드");
		model.addAttribute("id", id);
		model.addAttribute("method", "GET");
		return "studentId";
	}
		
	@RequestMapping(value="student", method = RequestMethod.POST)
	public ModelAndView student(String id, ModelAndView modelAndView) {
		
		System.out.println("post방식 student메소드");
		modelAndView.addObject("id", id);
		modelAndView.addObject("method", "POST");
		modelAndView.setViewName("studentId");
		
		return modelAndView;
	}
	
	@RequestMapping(value="studentConfirm", method = RequestMethod.GET)
	public String studentconfirm(String id, Model model) {
		model.addAttribute("id", id);
		if(id.equals("aaa")) {
			return "forward:studentOk.do"; 
		}
		
		return "redirect:studentNg.do"; // 새로운 요청(새로운 request 요청)
	}	
	
	@RequestMapping(value="studentOk", method = RequestMethod.GET)
	public String studentOk() {
		return "studentOk";
	}
	
	@RequestMapping(value="studentNg", method = RequestMethod.GET)
	public String studentNg() {
		return "studentNg";
	}
		
	@RequestMapping(value="fullPath", method = RequestMethod.GET)
	public String fullPath() {
		return "redirect:http://localhost:9090/ch11/student/fullPath.jsp"; 
		/* return "redirect:http://www.naver.com"; */
	}
}
