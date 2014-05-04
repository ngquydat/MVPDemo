package com.course.mvp.demo.client.activities;

import com.course.mvp.demo.client.activities.createebook.CreateebookActivity;
import com.course.mvp.demo.client.activities.createebook.CreateebookPlace;
import com.course.mvp.demo.client.activities.ebookinfo.EbookinfoActivity;
import com.course.mvp.demo.client.activities.ebookinfo.EbookinfoPlace;
import com.course.mvp.demo.client.activities.editebook.EditebookActivity;
import com.course.mvp.demo.client.activities.editebook.EditebookPlace;
import com.course.mvp.demo.client.activities.home.HomeActivity;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.course.mvp.demo.client.activities.login.LoginActivity;
import com.course.mvp.demo.client.activities.login.LoginPlace;
import com.course.mvp.demo.client.activities.profile.ProfileActivity;
import com.course.mvp.demo.client.activities.profile.ProfilePlace;
import com.course.mvp.demo.client.activities.readebook.ReadebookActivity;
import com.course.mvp.demo.client.activities.readebook.ReadebookPlace;
import com.course.mvp.demo.client.activities.register.RegisterActivity;
import com.course.mvp.demo.client.activities.register.RegisterPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PhoneActivityMapper implements ActivityMapper {

	private ClientFactoryImpl clientFactory;

	public PhoneActivityMapper(ClientFactoryImpl clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace)
			return new HomeActivity(clientFactory, (HomePlace)place);
		else if (place instanceof LoginPlace)
			return new LoginActivity(clientFactory, (LoginPlace)place);
		else if (place instanceof ProfilePlace)
			return new ProfileActivity(clientFactory, (ProfilePlace)place);
		else if (place instanceof RegisterPlace)
			return new RegisterActivity(clientFactory, (RegisterPlace)place);
		else if (place instanceof CreateebookPlace)
			return new CreateebookActivity(clientFactory, (CreateebookPlace)place);
		else if (place instanceof EbookinfoPlace)
			return new EbookinfoActivity(clientFactory, (EbookinfoPlace)place);
		else if (place instanceof EditebookPlace)
			return new EditebookActivity(clientFactory, (EditebookPlace)place);
		else if (place instanceof ReadebookPlace)
			return new ReadebookActivity(clientFactory, (ReadebookPlace)place);
		return null;
	}
}
