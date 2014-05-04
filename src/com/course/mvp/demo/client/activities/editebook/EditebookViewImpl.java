package com.course.mvp.demo.client.activities.editebook;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class EditebookViewImpl implements IsWidget {

	public LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	
	HeaderButton backBtn = new HeaderButton();
	public VerticalPanel v = new VerticalPanel();
	public HorizontalPanel h = new HorizontalPanel();
	public Button addChapter = new Button("Add Chapter");
	public Button saveBtn = new Button("Save");
	public MTextBox title = new MTextBox();
	public EditebookViewImpl() {
		
		main.setWidth("100%");
		title.setWidth("100%");
		headerPanel.setCenter("Ebook Edit");
		backBtn.setText("Back");
		headerPanel.setLeftWidget(backBtn);
		main.add(headerPanel);
		main.add(title);
		main.add(v);
		h.add(addChapter);
		h.add(saveBtn);
		main.add(h);
	}	
	
	public HeaderButton getbackBtn() {
		return this.backBtn;
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}
}