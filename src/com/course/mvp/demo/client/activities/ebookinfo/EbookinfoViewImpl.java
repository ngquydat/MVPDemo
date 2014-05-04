package com.course.mvp.demo.client.activities.ebookinfo;

import com.course.mvp.demo.shared.Ebook;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

public class EbookinfoViewImpl implements IsWidget {

	private LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	
	HeaderButton backBtn = new HeaderButton();
	Button EditBtn = new Button("Edit");
	
	private Label ebookname = new Label();
	private Label owner = new Label();
	private Label created = new Label();
	private Label updated = new Label();
	
	public EbookinfoViewImpl(Ebook ebook) {
		ebookname.setText("File name: " + ebook.name);
		owner.setText("owner: " + ebook.owner);
		created.setText("created: "+ ebook.created.toString());
		if (ebook.updated != null)updated.setText("updated: " + ebook.updated.toString());
		
		main.setWidth("100%");
		headerPanel.setCenter("Ebook Info");
		backBtn.setText("Back");
		headerPanel.setLeftWidget(backBtn);
		main.add(headerPanel);
		main.add(ebookname);
		main.add(owner);
		main.add(created);
		main.add(updated);
		main.add(EditBtn);
	}	
	
	public HeaderButton getbackBtn() {
		return this.backBtn;
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}
}