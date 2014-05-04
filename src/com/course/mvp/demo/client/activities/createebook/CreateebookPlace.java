package com.course.mvp.demo.client.activities.createebook;

import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.place.shared.Place;

public class CreateebookPlace extends Place {
	private LoginInfo loginInfo;
	public CreateebookPlace(LoginInfo token) {
		this.loginInfo = token;
	}
	
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	
	
}
