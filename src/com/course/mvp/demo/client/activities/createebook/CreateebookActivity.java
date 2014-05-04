package com.course.mvp.demo.client.activities.createebook;

import java.util.ArrayList;
import java.util.List;

import com.course.mvp.demo.client.activities.ClientFactoryImpl;
import com.course.mvp.demo.client.activities.home.HomePlace;
import com.course.mvp.demo.client.services.EbookService;
import com.course.mvp.demo.client.services.EbookServiceAsync;
import com.course.mvp.demo.shared.Ebook;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class CreateebookActivity extends MGWTAbstractActivity {

	private final ClientFactoryImpl clientFactory;
	private LoginInfo loginInfo;
	private final EbookServiceAsync ebookService = GWT.create(EbookService.class);
	List<MTextBox> listChapterTextBox = new ArrayList<MTextBox>();
	List<RichTextArea> listChapterContentRTA = new ArrayList<RichTextArea>();
	public CreateebookActivity(ClientFactoryImpl clientFactory, CreateebookPlace place) {
		this.clientFactory = clientFactory;
		this.loginInfo = place.getLoginInfo();
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		final CreateebookViewImpl view = new CreateebookViewImpl();
		panel.setWidget(view);
		view.addChapterBtn.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				MTextBox chapter = new MTextBox();
				chapter.setPlaceHolder("chapter name");
				RichTextArea chaptercontent = new RichTextArea();
				view.h.add(chapter);
				view.h.add(chaptercontent);
				listChapterTextBox.add(chapter);
				listChapterContentRTA.add(chaptercontent);
			}
		});
		view.createebookBtn.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				view.createebookBtn.setText("creating...");
				List<String> listChapters = new ArrayList<String>();
				List<String> listChapterContents = new ArrayList<String>();
				for(int i=0;i<listChapterTextBox.size();i++){
					listChapters.add(listChapterTextBox.get(i).getText());
					listChapterContents.add(listChapterContentRTA.get(i).getHTML());
				}
				ebookService.createEbook(loginInfo, view.getTitle(), listChapters,listChapterContents, new AsyncCallback<Ebook>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failed "+caught);
						view.createebookBtn.setText("Create Ebook");
					}

					@Override
					public void onSuccess(final Ebook result) {
						Window.alert("Succeed!!!");
						view.createebookBtn.setText("Create Ebook");

					}
				});
			}
		});
		view.getbackBtn().addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace(loginInfo));
			}
		});
	}
}