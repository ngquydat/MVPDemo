package com.course.mvp.demo.client.activities.home;

import java.util.List;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.createebook.CreateebookPlace;
import com.course.mvp.demo.client.activities.ebookinfo.EbookinfoPlace;
import com.course.mvp.demo.client.activities.login.LoginPlace;
import com.course.mvp.demo.client.activities.profile.ProfilePlace;
import com.course.mvp.demo.client.activities.readebook.ReadebookPlace;
import com.course.mvp.demo.client.services.EbookService;
import com.course.mvp.demo.client.services.EbookServiceAsync;
import com.course.mvp.demo.shared.Ebook;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;

public class HomeActivity extends MGWTAbstractActivity {

	private final ClientFactoryImpl clientFactory;
	private LoginInfo loginInfo;
	private final EbookServiceAsync ebookService = GWT.create(EbookService.class);
	
	public HomeActivity(ClientFactoryImpl clientFactory, HomePlace place) {
		this.clientFactory = clientFactory;
		this.loginInfo = place.getLoginInfo();
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		final HomeViewImpl view = new HomeViewImpl();
		panel.setWidget(view);
		view.getProfileButton().setText(loginInfo.username);
		view.getProfileButton().setTitle(loginInfo.username);
		view.getProfileButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new ProfilePlace(loginInfo));
			}
		});
		view.getLogoutBtn().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new LoginPlace());
			}
		});
		view.getCreateEbookBtn().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new CreateebookPlace(loginInfo));
				
			}
		});
		ebookService.getAll(loginInfo.email, new AsyncCallback<List<Ebook>>() {

			@Override
			public void onSuccess(final List<Ebook> result) {
				for (int i = 0; i < result.size(); i++) {
					final Ebook ebook = result.get(i);
					//add ebook to main
					HeaderButton ebookinfo = new HeaderButton();
					ebookinfo.setText("i");
					ebookinfo.addTapHandler(new TapHandler() {
						
						@Override
						public void onTap(TapEvent event) {
							clientFactory.getPlaceController().goTo(new EbookinfoPlace(loginInfo,ebook));
							
						}
					});
					
					HeaderButton ebookdel = new HeaderButton();
					ebookdel.setText("X");
					ebookdel.addTapHandler(new TapHandler() {
						
						@Override
						public void onTap(TapEvent event) {
							ebookService.delete(ebook.id,
									new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											clientFactory.getPlaceController().goTo(new HomePlace(loginInfo));
										}

										@Override
										public void onFailure(Throwable caught) {
										}
									});
							
						}
					});
					
					Anchor ebookdownload = new Anchor("download");
					final String url = GWT.getHostPageBaseURL()
							+ "download?blobkey=" + result.get(i).blobkey
							+ "&&filename=" + result.get(i).name + ".epub";
					ebookdownload.setHref(url);
					HeaderButton readBtn = new HeaderButton();
					readBtn.setText(ebook.name);
					readBtn.addTapHandler(new TapHandler() {
						
						@Override
						public void onTap(TapEvent event) {
							clientFactory.getPlaceController().goTo(new ReadebookPlace(loginInfo,url));
						}
					});
					HorizontalPanel h = new HorizontalPanel();
					h.add(ebookinfo);
					h.add(ebookdel);
					h.add(ebookdownload);
					h.add(readBtn);
					view.myEbookPanel.add(h);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}