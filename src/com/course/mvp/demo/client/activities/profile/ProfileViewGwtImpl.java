package com.course.mvp.demo.client.activities.profile;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class ProfileViewGwtImpl implements IsWidget {

	LayoutPanel main = new LayoutPanel();
	WidgetList widgetList = new WidgetList();
	HeaderPanel headerPanel = new HeaderPanel();
	HeaderButton backButton = new HeaderButton();
	Image avatar = new Image("http://image.xahoi.com.vn/news/2013/1/28/ngoc-trinh1.jpg");
	
	public ProfileViewGwtImpl() {
		headerPanel.setCenter("Profile");
		backButton.setText("Back");
		headerPanel.setLeftWidget(backButton);
		main.add(headerPanel);
		main.add(widgetList);
		main.add(avatar);
	}
	public HeaderButton getBackButton() {
		return this.backButton;
	}
	@Override
	public Widget asWidget() {
		return main;
	}
}