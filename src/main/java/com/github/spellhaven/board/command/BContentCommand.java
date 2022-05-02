package com.github.spellhaven.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.spellhaven.board.dao.BDao;
import com.github.spellhaven.board.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bid = request.getParameter("bid");		
		
		BDao dao = new BDao();
		BDto dto = dao.content_view(bid);
		
		// 커맨드는 DAO에서 받은 dto를 "content_view"라는 이름으로 세팅해 주고, 이를 컨트롤러에게 넘긴다. 그리고 퇴근한다 ㅋ
		request.setAttribute("content_view", dto);

	}

}
