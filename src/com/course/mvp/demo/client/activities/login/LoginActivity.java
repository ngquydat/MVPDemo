package com.course.mvp.demo.client.activities.login;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.course.mvp.demo.client.activities.register.RegisterPlace;
import com.course.mvp.demo.client.services.LoginService;
import com.course.mvp.demo.client.services.LoginServiceAsync;
import com.course.mvp.demo.shared.FieldVerifier;
import com.course.mvp.demo.shared.LoginInfo;
import com.course.mvp.demo.shared.Person;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class LoginActivity extends MGWTAbstractActivity {

	private final ClientFactoryImpl clientFactory;
	public static PhoneGap phoneGap = null;
	private Place place;
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	public LoginActivity(ClientFactoryImpl clientFactory, Place place) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		phoneGap = GWT.create(PhoneGap.class);
		final LoginViewGwtImpl view = new LoginViewGwtImpl();
		panel.setWidget(view);

		view.getLoginBtn().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				String username = view.getUserName();
				String password = view.getPassword();
				if (!FieldVerifier.isValidName(username)
						|| !FieldVerifier.isValidName(password)) {
					Window.alert("enter value please!");
				} else {
					Person user = new Person();
					user.userName = username;
					user.passWord = password;
					loginService.login(user, new AsyncCallback<Person>() {

						@Override
						public void onSuccess(Person result) {
							if (result.id != null) {
								LoginInfo loginInfo = new LoginInfo();
								loginInfo.email = result.email;
								loginInfo.nickname = result.nickName;
								loginInfo.username = result.userName;
								loginInfo.password = result.passWord;
								loginInfo.userid = result.id;
								clientFactory.getPlaceController().goTo(
										new HomePlace(loginInfo));
							} else {
								Window.alert("Failed");
							}

						}

						@Override
						public void onFailure(Throwable caught) {

						}
					});
				}

			}
		});
		view.getRegisterBtn().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new RegisterPlace());
			}
		});
	}
}