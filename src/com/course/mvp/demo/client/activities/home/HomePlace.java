package com.course.mvp.demo.client.activities.home;

import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.place.shared.Place;

public class HomePlace extends Place {
	private LoginInfo loginInfo;
	public HomePlace(LoginInfo token) {
		this.loginInfo = token;
	}
	
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	
	
}
