package com.kh.spring.util.template;

import com.kh.spring.util.model.dto.PageInfo;

public class Pagination {
	
	public static PageInfo getPageInfo(int count,
									   int currentPage,
									   int boardLimit,
									   int pageLimit) {
		int maxPage = (int)Math.ceil((double)count / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) { endPage = maxPage; }
		
		/*
		setCount(){
			this.count = count;
			
			return this;
		} //메서드 체이닝 가능! 해당 객체에 
		  //@Builder를 이용해서 아래 반환 부분 처럼 순서에 구애받지 않고 원하는 것만 넣어서 만들어 줄 수 있음!
		
		new PageInfo(count, currentPage, boardLimit, pageLimit, maxPage, startPage, endPage);
		PageInfo page = new PageInfo();
		page.setBoardLimit(boardLimit);
		page.setCount(count);
		*/
		
		return PageInfo.builder()
					   .boardLimit(boardLimit)
					   .count(count)
					   .currentPage(currentPage)
					   .startPage(startPage)
					   .endPage(endPage)
					   .maxPage(maxPage)
					   .pageLimit(pageLimit)
					   .build();
	}
	
}
