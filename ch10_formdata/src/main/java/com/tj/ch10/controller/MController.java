package com.tj.ch10.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tj.ch10.dto.MemberDto;

@Controller
@RequestMapping("member")

public class MController {
	@ModelAttribute("cnt")
	public int cnt() {
		return 5;
	}
	
	@ModelAttribute("list")
	public ArrayList<String> list(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("test1"); list.add("test2"); list.add("test3");  
		
		return list;
	}
	
	@RequestMapping("join1") // jsp식 방식~ 이거보다 편한 방식이 뒤에 있으나, 파일첨부를 하게되면, 이거 써야된다잉~!
	public String join1(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		model.addAttribute("name", name);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("age", age);
		model.addAttribute("email", email);
		model.addAttribute("address", address);

		return "member/result1";
	}

	@RequestMapping("join2")
	public String join2(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam("age") int age, @RequestParam("email") String email, @RequestParam("address") String address,
			Model model) {

		model.addAttribute("name", name);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("age", age);
		model.addAttribute("email", email);
		model.addAttribute("address", address);

		return "member/result1";
	}

	@RequestMapping("join3")
	public String join3(String name, String id, String pw, int age, String email, String address, Model model) {

		model.addAttribute("name", name);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("age", age);
		model.addAttribute("email", email);
		model.addAttribute("address", address);

		return "member/result1";
	}
	
	@RequestMapping("join4") 
	// memberDto 객체를 만들어서, memberDto.setName(request.getParameter("name")) 알아서 각각 다 붙는다~ 
	// int 값은 integer까지 알아서 다 해주네~, 마지막으로 model.addAttribute("member", memberDto); member객체까지 다 넣어준당!
	
	public String join4(@ModelAttribute("member") MemberDto memeberDto) {		
		
		return "member/result4";
	}
	
	@RequestMapping("join5")
	public String join5(MemberDto memberdto, Model model) {
		
		model.addAttribute("member", memberdto);
		return "member/result4";
	}
	
	@RequestMapping("join6")
	public String join6(MemberDto memberdto) {
		// model.addAttribute("memberDto", memberdto)로 자동 입력이 되어진다. MemberDto의 앞자리를 소문자 m으로 바꾸고 알아서 객체 생성, Member로 만들었으면 개이득?
		return "member/result6";
	}
}







