package com.course.mvp.demo.client.activities.register;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.login.LoginPlace;
import com.course.mvp.demo.client.services.LoginService;
import com.course.mvp.demo.client.services.LoginServiceAsync;
import com.course.mvp.demo.shared.FieldVerifier;
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

public class RegisterActivity extends MGWTAbstractActivity {

	private final ClientFactoryImpl clientFactory;
	public static PhoneGap phoneGap = null;
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	public RegisterActivity(ClientFactoryImpl clientFactory, Place place) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		phoneGap = GWT.create(PhoneGap.class);
		final RegisterViewGwtImpl view = new RegisterViewGwtImpl();
		panel.setWidget(view);
		
		view.getRegisterBtn().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				String username = view.getUserName();
				String password = view.getPassword();
				String email = view.getEmail();
				Person user = new Person();
				user.userName = username;
				user.passWord = password;
				user.email = email;
				loginService.register(user, new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						if (result) {
							Window.alert("Successfully!");
							clientFactory.getPlaceController().goTo(new LoginPlace());
						} else
							Window.alert("Failed");
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
			}
		});

	}
}