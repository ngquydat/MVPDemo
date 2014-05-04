package com.course.mvp.demo.client.activities.ebookinfo;

import com.course.mvp.demo.shared.Ebook;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.place.shared.Place;

public class EbookinfoPlace extends Place {
	private LoginInfo loginInfo;
	private Ebook ebook;
	public EbookinfoPlace(LoginInfo loginInfo, Ebook ebook) {
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
