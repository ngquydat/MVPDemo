package com.course.mvp.demo.client.activities;

import com.course.mvp.demo.client.activities.profile.ProfileViewGwtImpl;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl {

	private SimpleEventBus eventBus;
	private PlaceController placeController;
	private ProfileViewGwtImpl profileView = null;

	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}

	public PlaceController getPlaceController() {
		return placeController;
	}
	
	public ProfileViewGwtImpl getProfileView() {
		if (profileView == null)
			profileView = new ProfileViewGwtImpl();
		return profileView;
	}

	public EventBus getEventBus() {
		return eventBus;
	}
}