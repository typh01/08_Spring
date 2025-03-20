package com.kh.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.spring.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping("boards")
	public String toBoardList() {
		return "board/board_list";
	}
	
	@GetMapping("form.bo")
	public String goToForm() {
		return "board/insert_board";
	}
	
	//@RequestMapping과 함께 이런 방식들을 나중에 사용함
	//@PostMapping
	//@PutMapping
	//@DeleteMapping
	//@GetMapping("/(id)")
	
}
