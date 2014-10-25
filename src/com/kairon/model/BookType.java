package com.kairon.model;

public class BookType {			//图书类别信息类
	private String id;			//图书类别编号
	private String typeName;	//图书类别名称
	private String days;		//可借天数
	private String fk;			//迟还一天的罚款数目
	
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
