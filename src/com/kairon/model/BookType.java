package com.kairon.model;

public class BookType {			//ͼ�������Ϣ��
	private String id;			//ͼ�������
	private String typeName;	//ͼ���������
	private String days;		//�ɽ�����
	private String fk;			//�ٻ�һ��ķ�����Ŀ
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	
	
}
