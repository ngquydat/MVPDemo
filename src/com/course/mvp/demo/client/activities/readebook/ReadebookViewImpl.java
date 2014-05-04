package com.course.mvp.demo.client.activities.readebook;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

public class ReadebookViewImpl implements IsWidget {

	public LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	
	private HeaderButton profileBtn = new HeaderButton();
	HeaderButton logoutBtn = new HeaderButton();
	VerticalPanel myEbookPanel = new VerticalPanel();
	Button createEbookBtn = new Button("Create Ebook");
	
	public ReadebookViewImpl() {
		main.setWidth("100%");
		headerPanel.setCenter("My Ebook");
		headerPanel.setRightWidget(profileBtn);
		logoutBtn.setText("logout");
		headerPanel.setLeftWidget(logoutBtn);
		main.add(headerPanel);
		main.add(myEbookPanel);
		main.add(createEbookBtn);
	}

	public HeaderButton getProfileButton() {
		return this.profileBtn;
	}	
	
	public HeaderButton getLogoutBtn() {
		return this.logoutBtn;
	}
	public Button getCreateEbookBtn(){
		return this.createEbookBtn;
	}
	@Override
	public Widget asWidget() {
		return main;
	}
}