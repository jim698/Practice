package com.tj.test1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	@ModelAttribute("msg")
	public String msg() {
		return "현재 총 4명";
	}
	
	@RequestMapping(value="inputForm", method = RequestMethod.GET)
	public String inputForm() {
		return "inputForm";
	}
	
	@RequestMapping(value="input", method = RequestMethod.GET)
	public String inputResult(String name, int kor, int eng, int mat, Model model) {
		int sum = kor + eng + mat;
		double avg = sum / 3.0;
		avg = Math.round(avg*100)/100.0;
		
		
		model.addAttribute("name", name);
		model.addAttribute("kor", kor);
		model.addAttribute("eng", eng);
		model.addAttribute("mat", mat);
		model.addAttribute("sum", sum);
		model.addAttribute("avg", avg);		
		
		return "inputResult";
		
		
	}
	
}
