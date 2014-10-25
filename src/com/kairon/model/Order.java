package com.kairon.model;

import java.util.Date;

public class Order { // 图书订单信息类
	private String ISBN; // 图书编号
	private Date date; // 下单时间
	private int number; // 图书数量
	private String operator; // 操作员
	private String checkAndAccept;// 是否验收
	private String zk; // 书籍折扣

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCheckAndAccept() {
		return checkAndAccept;
	}

	public void setCheckAndAccept(String checkAndAccept) {
		this.checkAndAccept = checkAndAccept;
	}

	public String getZk() {
		return zk;
	}

	public void setZk(String zk) {
		this.zk = zk;
	}

}
