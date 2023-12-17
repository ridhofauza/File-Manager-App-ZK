package com.learn.simpleapp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import com.learn.simpleapp.model.NavigationPage;

public class NavigationViewModel {
	NavigationPage currentPage;
	private Map<String, Map<String, NavigationPage>> pageMap;
	
	@Init
	public void init() {
		initPageMap();
		currentPage = pageMap.get("Dashboard").get("File Manager");
	}
	
	@Command
	public void navigatePage(@BindingParam("target") NavigationPage targetPage) {
		BindUtils.postNotifyChange(null, null, currentPage, "selected");
		currentPage = targetPage;
		BindUtils.postNotifyChange(null, null, this, "currentPage");
	}
	
	public NavigationPage getCurrentPage() {
		return currentPage;
	}
	
	public Map<String, Map<String, NavigationPage>> getPageMap() {
		return pageMap;
	}
	
	private void initPageMap() {
		pageMap = new LinkedHashMap<String, Map<String, NavigationPage>>();
		addPage("Dashboard", "File Manager", "/dashboard.zul");
	}
	
	private void addPage(String title, String subTitle, String includeUri) {
		addPage(title, subTitle, includeUri, null);
	}
	
	private void addPage(String title, String subTitle, String includeUri, String data) {
		String folder = "~./../view";
		Map<String, NavigationPage> subPageMap = pageMap.get(title);
		if(subPageMap == null) {
			subPageMap = new LinkedHashMap<String, NavigationPage>();
			pageMap.put(title, subPageMap);
		}
		NavigationPage navigationPage = new NavigationPage(title, subTitle, folder + includeUri + "?random=" + Math.random(), data) {
			@Override
			public boolean isSelected() {
				return currentPage == this;
			}
		};
		subPageMap.put(subTitle, navigationPage);
	}

}
