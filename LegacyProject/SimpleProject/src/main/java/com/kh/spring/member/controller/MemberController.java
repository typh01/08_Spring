package com.kh.spring.member.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.SetCharacterEncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequiredArgsConstructor // 의존성 주입 생성자를 생성해주는 어노테이션
public class MemberController {
	
	//DI 3가지
	@Autowired // 1번
	private MemberService memberService;
	
	
	/* 
	@Autowired // 2번
 	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	*/
	
	/* 
	@Autowired // 3번 (권장)
	public MemberController(MemberService memberSerivce) {
		this.memberService = memberSerivce;
	}
	*/
	
	
	
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
//
//	@PostMapping("login")
//	public String login(MemberDTO member, 
//						HttpSession session,
//						Model model) {
//		
//		// log.info("페이지의 키값과 담을 필드의 이름이 같으면 오! {}", member);
//		
//		/* 
//		 * 1. 데이터 가공 => 스프링이 대신해줌
//		 * 2. 요청 처리 => 서비스로!
//		 * 3. 응답 화면 지정
//		 */
//		
//		MemberDTO loginMember = memberService.login(member);
//		/*
//		if(loginMember != null) {
//			log.info("로그인 성공!");
//		} else {
//			log.info("로그인 실패...");
//		}
//		*/
//		
//		if(loginMember != null) { // 성공했을 때
//			// sessionScope에 로그인정보를 담아줌
//			session.setAttribute("loginMember", loginMember);
//			// main_page
//			// /WEB-INF/views/
//			// .jsp
//			// => 포워딩
//			// sendRedirect
//			
//			// localhost/spring  /
//			
//			return "redirect:/";
//			
//		} else { // 실패 했을 때
//					
//			// error_page
//			// requestScope에 에러문구를 담아서 포워딩 <에러 페이지에서만 쓸 값이니까>
//			// Spring에서는 Model객체를 이용해서 RequestScope에 값을 담음
//			model.addAttribute("message", "로그인 실패!");
//			
//			// forwarding
//			// /WEB-INF/views/
//			// include/error_page
//			// .jsp 
//			// 논리적인 경로로 물리적인 경로를 찾아감
//			return "include/error_page";	
//		}
//		
//		//return "main_page";
//	}
	
	// 두 번째 방법 반환타입 ModelAndView로 돌아가기
	@PostMapping("login")
	public ModelAndView login(MemberDTO member,
							  HttpSession session,
							  ModelAndView mv) {
		MemberDTO loginMember = memberService.login(member);
		
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			mv.setViewName("redirect:/");
		}else {
			mv.addObject("message", "로그인실패!")
			  .setViewName("include/error_page");
		}
		
		return mv;
	}
	
	
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session,
						  	   ModelAndView mv) {
	
		
		session.removeAttribute("loginMember");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@GetMapping("signup-form")
	public String signupForm() {
		// /WEB-INF/views/member/signup-form.jsp
		return "member/signup-form";
	}
	
	
	/**
	 * @param memberId, memberPw...
	 * 
	 * @return 성공 시 main_page 실패하면 err담아서 error_page
	 */
	@PostMapping("signup")
	public String signup(MemberDTO member) {
		/* 이미 뽑았음
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/
//		log.info("멤버 필드 찍어보기 : {}", member);
		memberService.signUp(member);
		return "main_page";
	}
	
	@GetMapping("my-page")
	public String myPage() {
		return "member/my_page";
	}
	
	@PostMapping("update-member")
	public String update(MemberDTO member, HttpSession session) {
		// 1. Controller에서는 RequestMapping 애노테이션 및 요청 시 전달값이 잘 전달되는지 확인
		/*
		 * 1_1) 404 발생 : mapping값 잘못 적음
		 * org.springframework.web.servlet.PageNotFound
		 * - No mapping for Post /spring/update-member
		 * 
		 * 1_2) 405 발생 : 앞단에선 POST / GET으로 요청을 보내놓고 메소드와 맞지않은 애노테이션을 사용했을 때
		 * Request method 'POST' not supported 
		 * 
		 * 1_3) 필드에 값이 들어오지 않는 경우 : jsp name 
		 * 
		 * 
		 */
		log.info("사용자가 입력한 값 : {}", member);
		
		// 2. 이번에 실행할 SQL문 생각
		// UPDATE문 ==> KH_MEMBER(MEMBER_ID)
		// ID, PW, NAME, EMAIL, DATE
		// 2_1) 매개변수 MemberDTO 타입의 memberId 필드값
		//	- PK를 이용해서 요소 값들을 업데이트 할 것이므로 PK를 포함해서 받아야함!
		
		// 2_2) SessionScope에 loginMember 키값에 memberId 필드값 
		//  - 사용자가 권한이 있는가? <Service에서 하는 것이 좋으므로 Session을 같이 넘겨주자!>
		// + 
		// 값들이 유효한 값인지 체크하기
		// MemberID가 존재하는 아이디인지 체크하기
		
		// UPDATE KH_MEMBER SET MEMBER_NAME = 사용자가 입력한 이름,
		//							  EMAIL = 사용자가 입력한 이메일
		//					WHERE MEMBER_ID = 사용자가 입력한 아이디
		
		// UPDATE 수행의 결과 => PK를 조건으로 수행함 => 0 / 1
		
		// 수행에 성공했을 경우 =>
		// my-page.jsp 로 이동 + 갱싱된 회원의 정보 출력
		
		// Id를 가지고 다시 조회 => login메서드 재활용
		
		// 수행에 실패했을 경우 =>
		// message를 담아서 error_page로 포워딩
		// 예외 발생 => 예외처리기로 위임 - Service에서 했음
		
		memberService.update(member, session);
		
		return "redirect:my:page";
	}
	
	// 탈퇴구현 숙제로하기
	// 비밀번호 입력 받음
	// 비밀번호가 맞는지 검증 => 예외 발생시키기
	// DELETE 성공했는지

	

}
