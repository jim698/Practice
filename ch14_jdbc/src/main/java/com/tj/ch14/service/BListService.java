package com.tj.ch14.service;

import java.util.Map;

import org.springframework.ui.Model;

import com.tj.ch14.dao.BoardDao;

public class BListService implements BService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap(); // model을 Map화 했습니당~ String은 key, Object는 값(model.addAttribute("pageNum", "1"))
		String pageNum = (String) map.get("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		final int PAGESIZE = 10, BLOCKSIZE=10;
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage-1)*PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		
		BoardDao bDao = BoardDao.getInstance();
		model.addAttribute("list", bDao.list(startRow, endRow));
		
		int orderNum = startRow; // 출력될 순번
		int totCnt = bDao.getBoardTotCnt(); // 전체 글 갯수
		int inverseNum = totCnt - startRow + 1;
		
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		
		model.addAttribute("orderNum", orderNum);
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("inverseNum", inverseNum);
		model.addAttribute("pageCnt", pageCnt);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("BLOCKSIZE", BLOCKSIZE);
		model.addAttribute("pageNum", pageNum);
		
		
	}

}
