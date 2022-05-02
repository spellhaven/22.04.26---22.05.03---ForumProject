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
		
		dao.modify(bid, bname, btitle, bcontent);
		
	}

		 /*
		 조심하자. dao.modify(bname, btitle, bcontent, bid);라고 순서를 이상하게 썼다가, 에러는 안 나는데 글 수정이 안 되는 기현상이 났다.
		
		 왜) BDao.java의 168열을 보자.
		 public void modify(String bid, String bname, String btitle, String bcontent)
		 사실 파라미터의 이름은 placeholder라 의미가 없다. 머리로는 알고 있지만 마음에 더 새겨야 한다.
		 그러니까 bid라고 우리가 여기(커맨드)랑 저기(DAO 상의 modify 명령)에 일치하게 썼어도, 그건 우리 사정이지. 컴퓨터는 '아 느그 이름 몰라 난 무조건 여기서 n번째 순서로 들어온 인수를 저기 n번째 자리에 넣는다' 이것만 앎.
		 개무섭지? 그래서 꼭 파라미터 순서를 잘 지켜 주렴~~~ 야호호~~~
		 어차피 sql문에서 필요한 순서대로 인수를 재배치하는 건 pstmt.setString이 알아서 할 테니까
		 인수들을 순서대로 넣어 줘야 해~~~ 알았지 하하하~~~~~~
		 */

}













