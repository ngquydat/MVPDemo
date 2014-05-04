package com.course.mvp.demo.client.activities.profile;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class ProfileActivity extends MGWTAbstractActivity {
	
	private final ClientFactoryImpl clientFactory;
	private LoginInfo loginInfo;
	
	public ProfileActivity(ClientFactoryImpl clientFactory, ProfilePlace place) {
		this.clientFactory = clientFactory;
		this.loginInfo = place.getLoginInfo();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ProfileViewGwtImpl view = clientFactory.getProfileView();
		panel.setWidget(view);
		view.getBackButton().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace(loginInfo));
			}
		});
	}
}