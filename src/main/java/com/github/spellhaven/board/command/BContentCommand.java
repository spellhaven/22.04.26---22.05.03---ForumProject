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
		
		request.setAttribute("content_view", dto);

	}

}
