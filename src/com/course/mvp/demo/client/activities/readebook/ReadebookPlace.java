package com.course.mvp.demo.client.activities.readebook;

import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.place.shared.Place;

public class ReadebookPlace extends Place {
	private LoginInfo loginInfo;
	private String url;
	public ReadebookPlace(LoginInfo token, String url) {
		this.loginInfo = token;
		this.url = url;
	}
	
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public String getUrl(){
		return url;
	}
	
}
