package com.kairon.model;

public class Back { // ͼ�������Ϣ��
	private String bookISBN; // ͼ����
	private String bookName; // ͼ������
	private String operatorId; // ����Ա���
	private String borrowDate; // ͼ�����ʱ��
	private String backDate; // ͼ��Ӧ��ʱ��
	private String readerName; // ��������
	private String readerISBN; // ���߱��

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
