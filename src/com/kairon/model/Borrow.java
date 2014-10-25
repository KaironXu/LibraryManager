package com.kairon.model;

public class Borrow {			//图书借阅信息类
	private int id;				//借阅号
	private String bookSIBN;	//书籍借阅号
	private String readerISBN;	//读者编号
	private String num;			//借书数量
	private String borrowDate;	//借书日期
	private String backDate;	//应还日期
	private String bookName;	//书籍名称
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookSIBN() {
		return bookSIBN;
	}
	public void setBookSIBN(String bookSIBN) {
		this.bookSIBN = bookSIBN;
	}
	public String getReaderISBN() {
		return readerISBN;
	}
	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	
}
