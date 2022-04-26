package com.github.spellhaven.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BCommand { // 메소드의 규격만 설정해 주는 '인터페이스'.
	void execute(HttpServletRequest request, HttpServletResponse response);

}
