package com.somnath;

import java.beans.PropertyEditorSupport;

public class StudentNameEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String name) throws IllegalArgumentException
	{
		setValue(name.toUpperCase());
	}

}
