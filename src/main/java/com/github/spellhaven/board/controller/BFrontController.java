package com.github.spellhaven.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.spellhaven.board.command.*;

/**
 * Servlet implementation class BFrontController
 */

@WebServlet("*.do") // .do를 모두 잡겠다는 의지
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); // "모든 길은 컨트롤러로 통한다". 그러니까 여기다 인코딩 한 번만 해 주면 편하지.
		
		String viewPage = null; //테스트용으로 쓴 코드... 였는데 이젠 아니다. 하단 RequestDispatcher에 쓰여.
		BCommand command = null;
		
		String uri = request.getRequestURI(); // 클라이언트가 요청한 uri 전부 가져오기. (uri랑 url의 차이가 뭐더라? 맨날 헷갈리네.)
		String conPath = request.getContextPath(); // Context Path가 뭔진 저번 시간에 말씀하셨지만, 나는 오류랑 씨름하느라 잘 못 들었다. 대충 url 앞부분이긴 했다.
		String com = uri.substring(conPath.length()); //전체 uri에서 Context Path 빼면 Command가 됨
		
		
		// ㅇㄷ로 가고 싶다는 Command를 보고, 그러려면 정확히 ㅇㄷ로 가면 된다고 정해 주는 놈.
		// (즉, 컨트롤러는 커맨드를 호출한다는 뜻이다.)
		if(com.equals("/list.do")) { // 글 목록 보기 요청		
			command = new BListCommand(); // 와! 업캐스팅!
			command.execute(request, response);			
			viewPage = "list.jsp";
			
		} else if (com.equals("/write_view.do")) { // 글쓰기 화면 보기 요청	
				viewPage = "write_view.jsp"; // 글쓰기 화면 보기 요청을 하면, write_view.jsp 페이지로 보내 줘라.
							
		} else if (com.equals("/delete.do")) { // 글 삭제 요청
			command = new BDeleteCommand();
			command.execute(request, response);			
			viewPage = "list.do"; // 글삭 끝나면 글목록으로 ㄱㄱ하자.
			
		} else if (com.equals("/modify.do")) { // 글 수정 요청			
			command = new BModifyCommand();
			command.execute(request, response);			
			viewPage = "list.do"; //글 수정을 끝내면 글목록으로 간다는 얘기
			
		} else if (com.equals("/write.do")) { // 글쓰기 요청
//			BWriteCommand command = new BWriteCommand();
			command = new BWriteCommand(); // 와! 업캐스팅!
			command.execute(request, response);			
			viewPage = "list.do"; //글쓰기를 끝내면 글목록으로 간다는 예기
			
		} else if (com.equals("/content_view.do")) { // 쓴 글 보기 요청
			command = new BContentCommand();
			command.execute(request, response);			
			viewPage = "content_view.jsp";
		}
		
		// 그 정해 준 곳으로 보내주는놈
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	
}



