package com.course.mvp.demo.client.activities.editebook;

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
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class EditebookActivity extends MGWTAbstractActivity {

	private final ClientFactoryImpl clientFactory;
	private final EbookServiceAsync ebookService = GWT.create(EbookService.class);
	private Ebook ebook;
	private LoginInfo loginInfo;
	List<MTextBox> listChapterTextBox = new ArrayList<MTextBox>();
	List<RichTextArea> listChapterContentRTA = new ArrayList<RichTextArea>();
	
	public EditebookActivity(ClientFactoryImpl clientFactory, EditebookPlace place) {
		this.clientFactory = clientFactory;
		this.ebook = place.getEbook();
		this.loginInfo = place.getLoginInfo();
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		final EditebookViewImpl view = new EditebookViewImpl();
		view.title.setText(ebook.name);
		panel.setWidget(view);
		List<MTextBox> listMTextBox = new ArrayList<MTextBox>();
		List<RichTextArea> listRichTextArea = new ArrayList<RichTextArea>();
		for (int j = 0; j < ebook.listChapters.size(); j++) {
			MTextBox editchapter = new MTextBox();
			editchapter.setText(ebook.listChapters.get(j));
			listMTextBox.add(editchapter);
			view.v.add(editchapter);
			
			RichTextArea editchapterContent = new RichTextArea();
			editchapterContent.setHTML(ebook.listChapterContents.get(j));
			listRichTextArea.add(editchapterContent);
			editchapterContent.setWidth("100%");
			view.v.add(editchapterContent);
		}
		view.getbackBtn().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace(loginInfo));
				
			}
		});
		view.addChapter.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				MTextBox chapter = new MTextBox();
				chapter.setPlaceHolder("chapter name");
				RichTextArea chaptercontent = new RichTextArea();
				chapter.setWidth("100%");
				chaptercontent.setWidth("100%");
				view.v.add(chapter);
				view.v.add(chaptercontent);
				listChapterTextBox.add(chapter);
				listChapterContentRTA.add(chaptercontent);
			}
		});
		view.saveBtn.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				for (int j = 0; j < listChapterTextBox.size(); j++) {
					ebook.listChapters.add(listChapterTextBox.get(j).getText());
					ebook.listChapterContents.add(listChapterContentRTA.get(j)
							.getHTML());
				}
				ebookService.createEbook(loginInfo, view.title.getText(),
						ebook.listChapters, ebook.listChapterContents,
						new AsyncCallback<Ebook>() {
							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(final Ebook result) {
								ebookService.delete(ebook.id,
										new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												Window.alert("Succeed!!! The Ebook you have saved is in your \"My ebooks\"");
												
											}

											@Override
											public void onFailure(
													Throwable caught) {
											}
										});

							}
						});
			}
		});
	}
}