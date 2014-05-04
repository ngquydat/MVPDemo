package com.course.mvp.demo.client.activities.editebook;

import com.course.mvp.demo.shared.Ebook;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.place.shared.Place;

public class EditebookPlace extends Place {
	private LoginInfo loginInfo;
	private Ebook ebook;
	public EditebookPlace(LoginInfo loginInfo, Ebook ebook) {
		this.loginInfo = loginInfo;
		this.ebook = ebook;
	}
	
	public Ebook getEbook() {
		return ebook;
	}
	public LoginInfo getLoginInfo(){
		return loginInfo;
	}
	
}
