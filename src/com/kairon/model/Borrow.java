package com.kairon.model;

public class Borrow {			//ͼ�������Ϣ��
	private int id;				//���ĺ�
	private String bookSIBN;	//�鼮���ĺ�
	private String readerISBN;	//���߱��
	private String num;			//��������
	private String borrowDate;	//��������
	private String backDate;	//Ӧ������
	private String bookName;	//�鼮����
	
	
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
