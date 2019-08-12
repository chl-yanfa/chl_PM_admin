package com.car.auction.common;

public class PaginationEntity {
	//综合文本或查询
	private String textOrSearch=null;
	//当前页号
	private Integer page;
	//当前显示记录数
	private Integer rows;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getTextOrSearch() {
		return textOrSearch;
	}
	public void setTextOrSearch(String textOrSearch) {
		this.textOrSearch = textOrSearch;
	}
	
}
