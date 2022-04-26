package com.github.spellhaven.board.dto;

public class BDto {
	String bid;
	String bname;
	String btitle;
	String bcontent;
	int bhit;

	
	// 혹시 빈 Bdto를 만들어야 할 수도 있으니까 이 생성자도 ㅋ
	public BDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 우클릭. 생성자 생성. "너무 편해"
	public BDto(String bid, String bname, String btitle, String bcontent, int bhit) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bhit = bhit;
	}

	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public int getBhit() {
		return bhit;
	}
	public void setBhit(int bhit) {
		this.bhit = bhit;
	}
	
	

}
