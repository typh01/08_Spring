package com.kh.spring.board.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDTO;

public interface BoardService {

	// 게시글 작성(파일 첨부)
	void insertBoard(BoardDTO board, MultipartFile file);
	/*
	 * insertBoard();
	 * 하는 것 + 대상
	 * save();
	 */
	
	// 게시글 목록 조회
	List<BoardDTO> selectBoardList(int currentPage);
	/*
	 * selectBoardList();
	 * selectAll();
	 * findAll();
	 */
	
	// 게시글 상세보기(댓글도 같이 조회) --> 엄청난 신기술 임박!
	BoardDTO selectBoard(int boardNo); // 요즘엔 Long 씀 
	/*
	 * selectBoard();
	 * findByXXXX();
	 */
	
	// 게시글 수정
	BoardDTO updateBoard(BoardDTO board, MultipartFile file);
	
	
	// 게시글 삭제(딜리트가 아닌 업데이트 STATUS컬럼값 N으로 바꿀 것)
	void deleteBoard(int boardNNo);
	
	
	// -------------
	
	// 게시글 검색 기능
	
	// 댓글 작성
	
}
