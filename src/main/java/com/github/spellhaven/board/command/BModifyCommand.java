package com.github.spellhaven.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.spellhaven.board.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		// 교: (그 당시엔 bid가 폼에 없었다. 유저가 수정하길 원하지 않았기 때문이다.) .modify는 인수가 4개 필요한데, bid는 어디서 가져와야 할까??
		// 나: 글쎄요.
		// 답: content_view.jsp에서(폼에서) bid를 hidden 값으로 들어오게 한다. 이러면 얘는 유저가 바꿀 순 없으나, request 객체에는 들어온다.
		
		BDao dao = new BDao();
		dao.modify(bname, btitle, bcontent, bid);

	}

}














