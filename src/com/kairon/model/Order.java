package com.kairon.model;

import java.util.Date;

public class Order { // ͼ�鶩����Ϣ��
	private String ISBN; // ͼ����
	private Date date; // �µ�ʱ��
	private int number; // ͼ������
	private String operator; // ����Ա
	private String checkAndAccept;// �Ƿ�����
	private String zk; // �鼮�ۿ�

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
