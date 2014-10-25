package com.kairon.model;

public class Back { // 图书借阅信息类
	private String bookISBN; // 图书编号
	private String bookName; // 图书名称
	private String operatorId; // 操作员编号
	private String borrowDate; // 图书借阅时间
	private String backDate; // 图书应还时间
	private String readerName; // 读者姓名
	private String readerISBN; // 读者编号

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getReaderISBN() {
		return readerISBN;
	}

	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}

}
