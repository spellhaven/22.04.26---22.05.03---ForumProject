package com.github.spellhaven.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.spellhaven.board.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
//		System.out.println(bid); // 이렇게 중간중간에 잘 되나 확인하는 습관이 좋은 디버깅 실력을 기른다.
		
		BDao dao = new BDao();
		dao.write(bid, bname, btitle, bcontent);
		
	}

}
