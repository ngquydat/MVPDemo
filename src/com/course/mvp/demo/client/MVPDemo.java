package com.course.mvp.demo.client;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.PhoneActivityMapper;
import com.course.mvp.demo.client.activities.PhoneAnimationMapper;
import com.course.mvp.demo.client.activities.login.LoginPlace;
import com.course.mvp.demo.client.services.GreetingService;
import com.course.mvp.demo.client.services.GreetingServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.contacts.Contact;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MVPDemo implements EntryPoint {
	public static PhoneGap phoneGap = null;
	public static ClientFactoryImpl clientFactory = GWT.create(ClientFactoryImpl.class);
	public static PhoneActivityMapper appActivityMapper;

	public void onModuleLoad() {
		//Window.alert("onModuleLoaded: " + getOSName());
		phoneGap = GWT.create(PhoneGap.class);
		phoneGap.addHandler(new PhoneGapAvailableHandler() {
			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				startApp();
			}
		});
		phoneGap.addHandler(new PhoneGapTimeoutHandler() {
			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				Window.alert("Time-out");
			}
		});
		phoneGap.initializePhoneGap();
	}
	
	private void startApp() {
		PlaceHistoryMapper historyMapper = GWT.create(PlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		createPhoneDisplay(clientFactory);
		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new LoginPlace());				
		historyHandler.handleCurrentHistory();
	}
	
	private void createPhoneDisplay(ClientFactoryImpl clientFactory) {
		AnimatableDisplay display = GWT.create(AnimatableDisplay.class);
		appActivityMapper = new PhoneActivityMapper(clientFactory);
		PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();
		AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, 
				appAnimationMapper, clientFactory.getEventBus());
		activityManager.setDisplay(display);
		RootPanel.get().add(display);
	}
}
