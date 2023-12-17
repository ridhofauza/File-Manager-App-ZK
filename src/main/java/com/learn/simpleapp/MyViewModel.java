package com.learn.simpleapp;

import java.io.File;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import com.learn.simpleapp.services.MyService;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {

	@WireVariable
	private MyService myService;
	private String answer;

	@Init
	public void init() {
		answer = "?";
	}

	@Command
	@NotifyChange("answer")
	public void cmd() {
		answer = myService.ask("What day is today?");
	}

	public String getAnswer() {
		return answer;
	}
	
	@Command
	public void gotoDashboard(@ContextParam(ContextType.VIEW) Window window) {
		Executions.createComponents("~./../view/navigation.zul", window.getParent(), null);
		window.detach();
	}
}
