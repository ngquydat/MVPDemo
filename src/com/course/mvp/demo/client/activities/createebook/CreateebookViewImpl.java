package com.course.mvp.demo.client.activities.createebook;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class CreateebookViewImpl implements IsWidget {

	private LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	
	HeaderButton backBtn = new HeaderButton();
	MTextBox title = new MTextBox();
	VerticalPanel h = new VerticalPanel();
	Button addChapterBtn = new Button("Add Chapter");
	Button createebookBtn = new Button("Create Ebook");
	WidgetList widgetList = new WidgetList();
	
	public CreateebookViewImpl() {
		main.setWidth("100%");
		headerPanel.setCenter("Create Ebook");
		backBtn.setText("Back");
		headerPanel.setLeftWidget(backBtn);
		main.add(headerPanel);
		title.setPlaceHolder("Title");
		widgetList.add(title);
		widgetList.add(h);
		widgetList.add(addChapterBtn);
		widgetList.add(createebookBtn);
		main.add(widgetList);
	}	
	
	public HeaderButton getbackBtn() {
		return this.backBtn;
	}
	public String getTitle(){
		return this.title.getText();
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}
}