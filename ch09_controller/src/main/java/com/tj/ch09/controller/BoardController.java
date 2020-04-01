package com.tj.ch09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tj.ch09.dto.Member;

@Controller
@RequestMapping("board")

public class BoardController {
	
	@RequestMapping(value="view") // spring에서는 /를 해도 되고 안해도 되공~ /board/view 
	public String view() {
		
		return "/board/view"; 
		
		// 뷰단 : /WEB_INF/views/board/view.jsp를 의미한다. //이 겹치면 알아서, 하나 제거해준당
	}
	
	@RequestMapping(value="content")
	public String content(Model model) {
		
		model.addAttribute("id", "aaa");
		model.addAttribute("pw", "111");
		
		Member member = new Member("bbb", "222");
		model.addAttribute("member", member);
		
		return "board/content";
	}
	
	@RequestMapping(value="write") //value는 생략가능 "write" 밑에는 1%방법, 위의 로직 99%방법
	public ModelAndView write(ModelAndView mav) {
		Member member = new Member("ccc", "333");
		mav.addObject("member", member); // model.addAttribute("member", member)랑 동일
		mav.setViewName("board/write");
		
		return mav;
		
	}
	
	@RequestMapping("reply")
	public String reply() {
		
		return "board/reply";
	}
	
	
}









