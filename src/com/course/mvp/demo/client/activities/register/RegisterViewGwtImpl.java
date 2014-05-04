package com.course.mvp.demo.client.activities.register;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class RegisterViewGwtImpl implements IsWidget {

	LayoutPanel main = new LayoutPanel();
	WidgetList widgetList = new WidgetList();
	HeaderPanel headerPanel = new HeaderPanel();
	Button register = new Button("Register");
	MTextBox userNameBox = new MTextBox();
	MPasswordTextBox passwordBox = new MPasswordTextBox();
	MTextBox mailBox = new MTextBox();
	
	public RegisterViewGwtImpl() {
		headerPanel.setCenter("Login");
		main.add(headerPanel);
		userNameBox.setPlaceHolder("Enter username");
		passwordBox.setPlaceHolder("Enter password");
		mailBox.setPlaceHolder("Enter mail");
		widgetList.add(userNameBox);
		widgetList.add(passwordBox);
		widgetList.add(mailBox);
		widgetList.add(register);
		main.add(widgetList);
	}
	public Button getRegisterBtn() {
		return this.register;
	}
	
	public String getUserName() {
		return userNameBox.getText();
	}
	public String getPassword() {
		return passwordBox.getText();
	}
	public String getEmail() {
		return mailBox.getText();
	}
	@Override
	public Widget asWidget() {
		return main;
	}
}