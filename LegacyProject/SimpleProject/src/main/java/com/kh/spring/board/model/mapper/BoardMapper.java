package com.kh.spring.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	void insertBoard(BoardDTO board);
	
	int selectTotalCount();
	
	List<BoardDTO> selectBoardList(RowBounds rowBounds);
	
	BoardDTO selectBoard(int boardNo);
	
	int updateBoard(BoardDTO board);
	
	int deleteBoard(int boardNo);
	
}
