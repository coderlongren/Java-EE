package cn.itcast.action;

import cn.itcast.service.LinkManService;

import com.opensymphony.xwork2.ActionSupport;

public class LinkManAction extends ActionSupport{
	private  LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
		
	}
	public String toAddPage(){
		return "toAddPage";
	}
	
}
