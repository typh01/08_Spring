package com.kh.spring.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.BoardDTO;
@Service
public class BoardServiceImpl implements BoardService {


	@Override
	public void insertBoard(BoardDTO board, MultipartFile file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardDTO> selectBoardList(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO selectBoard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO updateBoard(BoardDTO board, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBoard(int boardNNo) {
		// TODO Auto-generated method stub
		
	}

}
