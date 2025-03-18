package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	/* 1번 방법
	@RequestMapping(value="login")
	public String login(HttpServletRequest request) {
		//System.out.println("나는 로그인 요청임!!");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		//System.out.println("id : " + id + ", pw : " + pw);
		//System.out.printf("id : %s, pw : %s", id, pw);
		log.info("id : {}, pw : {}", id, pw);
		return "main_page";
	}
	*/
	
	
	/* 보통은 이걸 사용함 <권장>
	// 2번 핸들러 어댑터 이용하기!
	@RequestMapping("login")
	public String login(@RequestParam(value="id", defaultValue="아무 것도") String id,
						@RequestParam(value="pw", defaultValue="없을 때 기본 값") String pw) {
		log.info("오 개쩜 이걸로도 오나? id : {} / pw : {}", id, pw);
		return "main_page";
	}
	*/
	
	
	/* 3번 작성만 쉬운 것
	@PostMapping("login")
	public String login(String id, String pw) {
		
		log.info("찐찐찐막으로 이게 온다고? id : {} / pw : {}", id, pw);
		
		MemberDTO member = new MemberDTO();
		member.setMemberId(id);
		member.setMemberPw(pw);
		
		return "main_page";
	}
	*/
	
	/**
	 * 커맨드 객체 방식
	 * 
	 * 1. 매개변수 자료형의 반드시 기본생성자가 존재할 것 ( Bean객체 생성을 위해선 기본 생성자가 있어야 함 )
	 * 2. 전달되는 키값과 객체의 필드명이 동일할 것
	 * 3. setter메서드가 반드시 존재할 것
	 * 
	 * 스프링에서 해당 객체를 기본 생성자를 통해서 생성한 후 내부적으로 setter메서드를 찾아서 요청 시 전달 값을 해당 필드에 대입해줌
	 * (Setter Injection)
	 */
	@PostMapping("login")
	public String login(MemberDTO member) {
		
		log.info("페이지의 키값과 담을 필드의 이름이 같으면 오! {}", member);
		
		return "main_page";
	}
}
