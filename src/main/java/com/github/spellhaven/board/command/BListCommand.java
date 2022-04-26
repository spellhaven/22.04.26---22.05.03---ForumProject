package com.github.spellhaven.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.spellhaven.board.dao.BDao;
import com.github.spellhaven.board.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list(); //dto들의 배열인 dtos(ArrayList 타입)가 반환됬으면 그걸 받어. ㅋ.
		
		// 왜 request인가? BFrontController.java의 L59를 봐라. (BListCommand가 set한) dtos가 set된 채로 request가 출발해야 하기 때문이다.
		request.setAttribute("list", dtos); // 왜 나는 여기가 response가 아니고 request라는 걸 이해를 몯하는중일까 몯하는중일까
		
		// 설명 이상한데... 그냥 "받아들여" 머리아파
	}

}
