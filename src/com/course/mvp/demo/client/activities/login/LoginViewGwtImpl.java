package com.course.mvp.demo.client.activities.login;

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

public class LoginViewGwtImpl implements IsWidget {

	LayoutPanel main = new LayoutPanel();
	WidgetList widgetList = new WidgetList();
	HeaderPanel headerPanel = new HeaderPanel();
	Button login = new Button("Login");
	Button registerBtn = new Button("Register");
	MTextBox userNameBox = new MTextBox();
	MPasswordTextBox passwordBox = new MPasswordTextBox();
	
	
	public LoginViewGwtImpl() {
		headerPanel.setCenter("Login");
		main.add(headerPanel);
		userNameBox.setPlaceHolder("Enter username");
		passwordBox.setPlaceHolder("Enter password");
		widgetList.add(userNameBox);
		widgetList.add(passwordBox);
		widgetList.add(login);
		widgetList.add(registerBtn);
		main.add(widgetList);
	}
	public Button getLoginBtn() {
		return this.login;
	}
	public Button getRegisterBtn() {
		return this.registerBtn;
	}
	
	public String getUserName() {
		return userNameBox.getText();
	}
	public String getPassword() {
		return passwordBox.getText();
	}
	@Override
	public Widget asWidget() {
		return main;
	}
}