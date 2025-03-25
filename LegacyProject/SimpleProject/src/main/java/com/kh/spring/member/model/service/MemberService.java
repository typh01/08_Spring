package com.kh.spring.member.model.service;

import javax.servlet.http.HttpSession;

import com.kh.spring.member.model.dto.MemberDTO;

public interface MemberService {
	
	// 로그인
	MemberDTO login(MemberDTO member); 
	
	// 회원가입
	// 좋은 방법 : 가입된 회원의 정보를 반환해준다(Hibernate)
	// 일반적인 방법 : 정수값을 반환하거나 
	//			  값을 반환하지 않는다. (MyBatis)
	void signUp(MemberDTO member);
	
	// 회원정보수정
//	int update(MemberDTO member, HttpSession session); // 1번
	void update(MemberDTO member, HttpSession session); // 2번
	
	// 회원탈퇴
	void delete(MemberDTO member, HttpSession session);
	
	//----------------- P1
	
	// 아이디 중복 체크
	String idCheck(String memberId);
	
	//----------------- P2
	
	// 이메일 인증
}
