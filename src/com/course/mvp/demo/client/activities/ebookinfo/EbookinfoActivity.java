package com.course.mvp.demo.client.activities.ebookinfo;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.editebook.EditebookPlace;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.course.mvp.demo.shared.Ebook;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class EbookinfoActivity extends MGWTAbstractActivity {

	private final ClientFactoryImpl clientFactory;
	private Ebook ebook;
	private LoginInfo loginInfo;
	
	public EbookinfoActivity(ClientFactoryImpl clientFactory, EbookinfoPlace place) {
		this.clientFactory = clientFactory;
		this.ebook = place.getEbook();
		this.loginInfo = place.getLoginInfo();
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		EbookinfoViewImpl view = new EbookinfoViewImpl(ebook);
		panel.setWidget(view);
		view.getbackBtn().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace(loginInfo));
				
			}
		});
		view.EditBtn.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new EditebookPlace(loginInfo,ebook));
			}
		});
	}
}