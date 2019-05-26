package com.hzmsc.scada.entity;

public class QueryInfo {

	
	 private int currentPage = 1;   //用户当前看的页数
	 private int pageSize = 5;        //每页多少条显示数据
	 private int startIndex;            //记住用户想看的页的数据在数据库的起始位置
	 private int[] pageBar; // 页码条
	 
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	 
	public int getStartIndex() {
		 this.startIndex = (this.currentPage-1)*this.pageSize;
		 return startIndex;
	}
	public int[] getPageBar() {
		return pageBar;
	}
	public void setPageBar(int[] pageBar) {
		this.pageBar = pageBar;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	
}
