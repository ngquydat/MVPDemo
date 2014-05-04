package com.course.mvp.demo.client.activities.profile;

import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.place.shared.Place;

public class ProfilePlace extends Place {
	private LoginInfo loginInfo;
	public ProfilePlace(LoginInfo token) {
		this.loginInfo = token;
	}

	public LoginInfo getLoginInfo() {
		return this.loginInfo;
	}
}
