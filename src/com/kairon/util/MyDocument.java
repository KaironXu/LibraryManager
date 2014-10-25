package com.kairon.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MyDocument extends PlainDocument {
	private int maxLength = 10;
	
	public MyDocument(int newMaxLength) {
		super();
		maxLength = newMaxLength;
	}
	
	public MyDocument() {
		this(10);
	}

	@Override
	public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		if(getLength()+str.length()>maxLength) {
			return;
		}
		
		super.insertString(offset, str, a);
	}
	
	
}
