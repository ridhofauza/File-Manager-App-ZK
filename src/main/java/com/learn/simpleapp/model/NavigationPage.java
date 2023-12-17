package com.learn.simpleapp.model;

public abstract class NavigationPage {
	private String title;
	private String includeUri;
	private String subTitle;
	private Object data;
	
	public NavigationPage(String title, String subTitle, String includeUri, Object data) {
		super();
		this.title = title;
		this.includeUri = includeUri;
		this.subTitle = subTitle;
		this.data = data;
	}
	
	public abstract boolean isSelected();

	public String getTitle() {
		return title;
	}

	public String getIncludeUri() {
		return includeUri;
	}

	public String getSubTitle() {
		return subTitle;
	}


	public Object getData() {
		return data;
	}

}
