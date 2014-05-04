package com.course.mvp.demo.client.activities.readebook;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.services.EbookService;
import com.course.mvp.demo.client.services.EbookServiceAsync;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class ReadebookActivity extends MGWTAbstractActivity {

	private final ClientFactoryImpl clientFactory;
	private LoginInfo loginInfo;
	private String url;
	private final EbookServiceAsync ebookService = GWT
			.create(EbookService.class);

	public ReadebookActivity(ClientFactoryImpl clientFactory,
			ReadebookPlace place) {
		this.clientFactory = clientFactory;
		this.loginInfo = place.getLoginInfo();
		this.url = place.getUrl();
	}

	public static native void jsreader(String url) /*-{
		$wnd.EPUBJS.VERSION = "0.1.7";
		$wnd.EPUBJS.filePath = "js/libs/";
		$wnd.EPUBJS.cssPath = "css/";
		$wnd.ePubReader(url);
	}-*/;

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		RootPanel.get("reader").setVisible(true);
		jsreader(url);
	}
}