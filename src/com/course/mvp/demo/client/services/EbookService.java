package com.course.mvp.demo.client.services;

import java.util.List;

import com.course.mvp.demo.shared.Ebook;
import com.course.mvp.demo.shared.LoginInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("ebook")
public interface EbookService extends RemoteService {
	Ebook createEbook (LoginInfo loginInfo, String Title, String WebURL);
	Ebook createEbook (LoginInfo loginInfo, String Title, List<String> listChapters, List<String> listChapterContents);
	List<Ebook> getAll(String username);
	public void delete(Long ebookid);
}
